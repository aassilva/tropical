package br.ufg.inf.es.goistropical.presenter;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.afollestad.materialdialogs.MaterialDialog;

public class BaseActivity extends AppCompatActivity {

    MaterialDialog dialogo;

    public String getStringFromEdit(int id){
        EditText input = (EditText) findViewById(id);
        return input.getText().toString();
    }

    public void showAlert(String message){
        Snackbar.make(findViewById(android.R.id.content),message,Snackbar.LENGTH_LONG).show();
    }

    public void showFixedBottom(String message){
        Snackbar.make(findViewById(android.R.id.content),message,Snackbar.LENGTH_INDEFINITE).show();
    }

    public void hideKeyboard(){
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public void setStringFromEdit(int id, String text){
        EditText input = (EditText) findViewById(id);
        input.setText(text);
    }

    public void showDialogWithMessage(String message){
        dialogo = new MaterialDialog.Builder(this)
                .content(message)
                .progress(true,0)
                .show();
    }

    public void dismissDialog(){
        if(dialogo != null && dialogo.isShowing()) {
            dialogo.dismiss();
        }
    }
}
