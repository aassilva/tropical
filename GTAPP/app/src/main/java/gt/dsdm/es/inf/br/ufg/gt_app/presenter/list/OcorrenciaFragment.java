/*
 * Copyright (c) 2018 Marcelo Quinta
 * Copyright (c) 2018 Antonio Silva
 * Copyright (c) 2018 Keslley Lima
 * MIT License.
 */
package gt.dsdm.es.inf.br.ufg.gt_app.presenter.list;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


import gt.dsdm.es.inf.br.ufg.gt_app.R;
import gt.dsdm.es.inf.br.ufg.gt_app.model.Ocorrencia;
import gt.dsdm.es.inf.br.ufg.gt_app.persistencia.OcorrenciaDAO;
import gt.dsdm.es.inf.br.ufg.gt_app.presenter.BaseFragment;
import gt.dsdm.es.inf.br.ufg.gt_app.presenter.activity.Activity_criar_ocorrencia;
import gt.dsdm.es.inf.br.ufg.gt_app.web.WebOcorrencia;


/**
 * A simple {@link Fragment} subclass.
 */
public class OcorrenciaFragment extends BaseFragment implements View.OnClickListener{

    private List<Ocorrencia> ocorrenciaList;
    private AdapterOcorrencia adapter;
    private FloatingActionButton fabAddOc;

    public OcorrenciaFragment() {
        // Required empty public constructor
    }

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
    public void onStart() {

        //Verificar se já está logado aqui.

        super.onStart();
        EventBus.getDefault().register(this);
        initRecycler();
        //getMurals();
        tryOcorrencia();
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
            Ocorrencia ocorrencia = new Ocorrencia();
            ocorrencia.setId(i);
            ocorrencia.setTitulo("titulo" + i);
            ocorrencia.setDescricao("descricao" + i);
            ocorrencia.setStatus("status" + i);
            ocorrencia.setLocalizacao("localização" + i);
            ocorrencia.setCategoria("categoria" + i);
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
        WebOcorrencia webOcorrencia = new WebOcorrencia("us123ur8");

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
        OcorrenciaDAO dao = new OcorrenciaDAO(getActivity());
        adapter.setOcorrencia(dao.getAll());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == fabAddOc.getId()){
            getActivity().startActivity(new Intent(getActivity(), Activity_criar_ocorrencia.class));
        }
    }
}
