/*
 * Copyright (c) 2018 Marcelo Quinta
 * Copyright (c) 2018 Antonio Silva
 * Copyright (c) 2018 Keslley Lima
 * MIT License.
 */
package gt.dsdm.es.inf.br.ufg.gt_app.presenter.list;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afollestad.materialdialogs.MaterialDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


import gt.dsdm.es.inf.br.ufg.gt_app.R;
import gt.dsdm.es.inf.br.ufg.gt_app.model.Mural;
import gt.dsdm.es.inf.br.ufg.gt_app.persistencia.MuralDAO;
import gt.dsdm.es.inf.br.ufg.gt_app.presenter.BaseFragment;
import gt.dsdm.es.inf.br.ufg.gt_app.web.WebMural;


/**
 * A simple {@link Fragment} subclass.
 */
public class MuralFragment extends BaseFragment {

    private List<Mural> muralList;
    private AdapterMural adapter;

    public MuralFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mural, container, false);
        muralList = new LinkedList<>();

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
        initRecycler();
        //getMurals();
        tryMural();
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    public void initRecycler(){
        RecyclerView recyclerView = (RecyclerView) getView().findViewById(R.id.mural_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new AdapterMural(muralList,getActivity());
        recyclerView.setAdapter(adapter);
    }


    public void getMurals(){
        List<Mural> muralList = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            Mural mural = new Mural();
            mural.setTitulo("titulo" + i);
            mural.setDescricao("descricao" + i);
            mural.setConteudo("conteudo" + i);
            mural.setImagem("link_imagem" + i);
            muralList.add(mural);
        }

        showDialogWithMessage(getString(R.string.load_tasks));
        MuralDAO dao = new MuralDAO(getActivity());
        adapter.setMural(dao.getAll());
        adapter.notifyDataSetChanged();
        dismissDialog();
    }

    private void tryMural() {
        WebMural webMural = new WebMural();

        webMural.call();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(Exception exception) {
        dismissDialog();
        showAlert(exception.getMessage());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(List<Mural> murals) {
        dismissDialog();
        adapter.setMural(murals);
        adapter.notifyDataSetChanged();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(Mural murals) {
        MuralDAO dao = new MuralDAO(getActivity());
        adapter.setMural(dao.getAll());
        adapter.notifyDataSetChanged();
    }

//    public void add(){
//        Remedy remedy = new Remedy();
//        remedy.setName("NEW");
//        RemedyDAO dao = new RemedyDAO(getActivity());
//        dao.create(remedy);
//
//        adapter.setRemedies(dao.getAll());
//        adapter.notifyDataSetChanged();
//    }

}
