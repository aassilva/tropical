package br.ufg.inf.es.goistropical.presenter.ocorrencialist;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.LinkedList;
import java.util.List;

import br.ufg.inf.es.goistropical.R;
import br.ufg.inf.es.goistropical.model.Ocorrencia;
import br.ufg.inf.es.goistropical.persistencia.OcorrenciaDAO;
import br.ufg.inf.es.goistropical.presenter.BaseFragment;

public class OcorrenciaListFragment extends BaseFragment {

    private List<Ocorrencia> ocorrenciaList;
    private AdapterOcorrencia adapter;


    public OcorrenciaListFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_ocorrencia_row_task, container, false);

        ocorrenciaList = new LinkedList<>();

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
        RecyclerView recyclerView = (RecyclerView) getView().findViewById(R.id.recycler_view_ocorrencia);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new AdapterOcorrencia(ocorrenciaList,getActivity());
        recyclerView.setAdapter(adapter);
    }


    public void getMurals(){
        showDialogWithMessage(getString(R.string.load_tasks));
        OcorrenciaDAO dao = new OcorrenciaDAO(getActivity());
        adapter.setOcorrencias(dao.getAll());
        adapter.notifyDataSetChanged();
        dismissDialog();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(Exception exception) {
        dismissDialog();
        showAlert(exception.getMessage());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(List<Ocorrencia> ocorrencias) {
        dismissDialog();
        adapter.setOcorrencias(ocorrencias);
        adapter.notifyDataSetChanged();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(Ocorrencia ocorrencia) {
        OcorrenciaDAO dao = new OcorrenciaDAO(getActivity());
        adapter.setOcorrencias(dao.getAll());
        adapter.notifyDataSetChanged();
    }

}
