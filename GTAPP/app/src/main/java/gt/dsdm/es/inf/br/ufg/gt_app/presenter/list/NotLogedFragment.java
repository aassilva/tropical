package gt.dsdm.es.inf.br.ufg.gt_app.presenter.list;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import gt.dsdm.es.inf.br.ufg.gt_app.R;
import gt.dsdm.es.inf.br.ufg.gt_app.presenter.activity.LoginActivity;

public class NotLogedFragment extends Fragment {


    public NotLogedFragment() {  }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_not_loged, container, false);
    }
}
