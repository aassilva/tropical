package br.ufg.inf.es.dsdm.goiastropical.presenter.murallist;

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

import br.ufg.inf.es.dsdm.goiastropical.R;
import br.ufg.inf.es.dsdm.goiastropical.model.Mural;
import br.ufg.inf.es.dsdm.goiastropical.persistencia.MuralDAO;
import br.ufg.inf.es.dsdm.goiastropical.presenter.BaseFragment;

public class MuralListFragment extends BaseFragment {

    private List<Mural> muralList;
    private AdapterMural adapter;


    public MuralListFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_mural, container, false);

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
        RecyclerView recyclerView = (RecyclerView) getView().findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new AdapterMural(muralList,getActivity());
        recyclerView.setAdapter(adapter);
    }


    public void getMurals(){
        showDialogWithMessage(getString(R.string.load_mural));
        MuralDAO dao = new MuralDAO(getActivity());
        adapter.setMural(dao.getAll());
        adapter.notifyDataSetChanged();
        dismissDialog();
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

}
