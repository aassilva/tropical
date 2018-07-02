package br.ufg.inf.es.dsdm.goiastropical.presenter;

import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;

import com.afollestad.materialdialogs.MaterialDialog;

public class BaseFragment extends Fragment {

    MaterialDialog dialogo;

    public void showDialogWithMessage(String message){
        dialogo = new MaterialDialog.Builder(getActivity())
                .content(message)
                .progress(true,0)
                .show();
    }

    public void dismissDialog(){
        if(dialogo != null && dialogo.isShowing()) {
            dialogo.dismiss();
        }
    }

    public void showAlert(String message){
        Snackbar.make(getView().findViewById(android.R.id.content),message,Snackbar.LENGTH_LONG).show();
    }

}
