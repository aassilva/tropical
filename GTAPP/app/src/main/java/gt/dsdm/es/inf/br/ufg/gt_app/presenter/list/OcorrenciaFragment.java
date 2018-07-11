/*
 * Copyright (c) 2018 Marcelo Quinta
 * Copyright (c) 2018 Antonio Silva
 * Copyright (c) 2018 Keslley Lima
 * MIT License.
 */
package gt.dsdm.es.inf.br.ufg.gt_app.presenter.list;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import gt.dsdm.es.inf.br.ufg.gt_app.R;
import gt.dsdm.es.inf.br.ufg.gt_app.model.Ocorrencia;
import gt.dsdm.es.inf.br.ufg.gt_app.persistencia.EasySharedPreferences;
import gt.dsdm.es.inf.br.ufg.gt_app.persistencia.OcorrenciaDAO;
import gt.dsdm.es.inf.br.ufg.gt_app.presenter.BaseFragment;
import gt.dsdm.es.inf.br.ufg.gt_app.presenter.activity.Activity_criar_ocorrencia;
import gt.dsdm.es.inf.br.ufg.gt_app.presenter.activity.NotLoginActivity;
import gt.dsdm.es.inf.br.ufg.gt_app.presenter.activity.OcorrenciaActivity;
import gt.dsdm.es.inf.br.ufg.gt_app.utils.UserUtils;
import gt.dsdm.es.inf.br.ufg.gt_app.web.WebConnection;
import gt.dsdm.es.inf.br.ufg.gt_app.web.WebOcorrencia;

public class OcorrenciaFragment extends BaseFragment implements View.OnClickListener, WebConnection.onRegisterResponse {

    private List<Ocorrencia> ocorrenciaList;
    private AdapterOcorrencia adapter;
    private FloatingActionButton fabAddOc;
    private EasySharedPreferences easySharedPreferences = new EasySharedPreferences();
    private Context context;

    public OcorrenciaFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ocorrencia, container, false);
        ocorrenciaList = new LinkedList<>();

        fabAddOc = view.findViewById(R.id.fab);
        fabAddOc.setOnClickListener(this);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        this.context = context;
        super.onAttach(context);
    }

    @Override
    public void onStart() {

//        if (easySharedPreferences.getStringFromKey(context, "token").equals(null) ||
//                easySharedPreferences.getStringFromKey(context, "token").equals("")) {
//        if(UserUtils.isLoged(this.getContext())){
//            //Não está logado
//            Intent intentPassword = new Intent(this.getContext(), NotLoginActivity.class);
//            startActivity(intentPassword);
//            super.onStart();
//        } else {


            EventBus.getDefault().register(this);
            initRecycler();
            //getMurals();
            tryOcorrencia();
            super.onStart();
//        }


    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onDestroy() {

        super.onDestroy();
    }

    public void initRecycler(){
        RecyclerView recyclerView = (RecyclerView) getView().findViewById(R.id.ocorrencia_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new AdapterOcorrencia(ocorrenciaList,getActivity());
        recyclerView.setAdapter(adapter);
    }

    public void getOcorrencias(){
        List<Ocorrencia> ocorrenciaList = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            Ocorrencia ocorrencia = new Ocorrencia("titulo" + i,
                    "descricao" + i,
                    "status" + i,
                    "localização" + i,
                    "categoria" + i
                    );
            ocorrenciaList.add(ocorrencia);
        }

        showDialogWithMessage(getString(R.string.load_tasks_ocorrencia));
        OcorrenciaDAO dao = new OcorrenciaDAO(getActivity());
        adapter.setOcorrencia(dao.getAll());
        adapter.notifyDataSetChanged();
        dismissDialog();
    }

    //Colocar aqui para pegar o token do usuário---------------------------------------------
    private void tryOcorrencia() {
        WebOcorrencia webOcorrencia = new WebOcorrencia("us123ur8", this);

        webOcorrencia.call();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(Exception exception) {
        dismissDialog();
        showAlert(exception.getMessage());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(List<Ocorrencia> ocorrencias) {
        dismissDialog();
        adapter.setOcorrencia(ocorrencias);
        adapter.notifyDataSetChanged();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(Ocorrencia ocorrencia) {
//        OcorrenciaDAO dao = new OcorrenciaDAO(getActivity());
//        adapter.setOcorrencia(dao.getAll());
//        adapter.notifyDataSetChanged();

        Intent intent = new Intent(getContext(), OcorrenciaActivity.class);
        EventBus.getDefault().unregister(this);
        EventBus.getDefault().postSticky(ocorrencia);

        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == fabAddOc.getId()){
            getActivity().startActivity(new Intent(getActivity(), Activity_criar_ocorrencia.class));
        }
    }

    @Override
    public void handleResponse() {

    }
}
