package es.inf.ufg.br.presenter.list.mural;


import android.os.Bundle;
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

import es.inf.ufg.br.R;
import es.inf.ufg.br.model.Mural;
import es.inf.ufg.br.persistencia.MuralDAO;
import es.inf.ufg.br.presenter.BaseFragment;
import es.inf.ufg.br.web.MuralWeb;

public class MuralListFragment extends BaseFragment {

    private List<Mural> muralList;
    private AdapterMural adapter;


    public MuralListFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_mural_list, container, false);

        muralList = new LinkedList<>();

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
        initRecycler();
        getMurals();
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
            mural.setId(i);
            mural.setTitulo("noticia" + i);
            mural.setDescricao("descrição" + i);
            mural.setConteudo("conteúdo" + i);
            mural.setImagem("imagem" + i);
            muralList.add(mural);
        }

        showDialogWithMessage(getString(R.string.load_tasks));
        MuralDAO dao = new MuralDAO(getActivity());
        adapter.setMural(dao.getAll());
        adapter.notifyDataSetChanged();
        dismissDialog();

    }

    private void tryRemedies() {
        MuralWeb muralWeb = new MuralWeb();

        muralWeb.call();
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

    public void add(){
        Mural mural = new Mural();
        mural.setTitulo("NEW");
        MuralDAO dao = new MuralDAO(getActivity());
        dao.create(mural);

        adapter.setMural(dao.getAll());
        adapter.notifyDataSetChanged();
    }

}
