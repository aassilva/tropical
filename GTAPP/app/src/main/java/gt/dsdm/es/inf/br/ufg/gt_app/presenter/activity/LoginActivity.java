package gt.dsdm.es.inf.br.ufg.gt_app.presenter.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.afollestad.materialdialogs.MaterialDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import gt.dsdm.es.inf.br.ufg.gt_app.MainActivity;
import gt.dsdm.es.inf.br.ufg.gt_app.R;
import gt.dsdm.es.inf.br.ufg.gt_app.RecuperarSenhaActivity;
import gt.dsdm.es.inf.br.ufg.gt_app.model.Usuario;
import gt.dsdm.es.inf.br.ufg.gt_app.persistencia.EasySharedPreferences;
import gt.dsdm.es.inf.br.ufg.gt_app.utils.UserUtils;
import gt.dsdm.es.inf.br.ufg.gt_app.web.login.WebError;
import gt.dsdm.es.inf.br.ufg.gt_app.web.login.WebTaskLogin;

public class LoginActivity extends AppCompatActivity {

    MaterialDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ImageView buttonClose = findViewById(R.id.button_close);

        buttonClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        setupButtonRememberPassword();
        setupButtonRegister();
        setupLogin();
    }

    @Override
    protected void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    private void setupButtonRememberPassword() {
        Button buttonRememberPassword =
                findViewById(R.id.button_forgot_password);
        buttonRememberPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentPassword = new Intent(getApplicationContext(),
                        RecuperarSenhaActivity.class);
                startActivity(intentPassword);
            }
        });
    }

    private void setupButtonRegister() {
        Button buttonRegister =
                findViewById(R.id.button_register);
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),
                        RegistroActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setupLogin() {
        Button buttonLogin =
                findViewById(R.id.button_login);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tryLogin();
            }
        });
    }

    private void tryLogin() {
        EditText editTextEmail = findViewById(R.id.input_email);
        EditText editTextPassword = findViewById(R.id.input_password);

        if(!"".equals(editTextEmail.getText().toString())){
            showLoading();
            sendCredentials(editTextEmail.getText().toString(),
                    editTextPassword.getText().toString());
        }else{
            editTextEmail.setError("Preencha o campo email");
        }

    }

    private void sendCredentials(String email, String pass) {
        WebTaskLogin taskLogin = new WebTaskLogin(this, email, pass);
        taskLogin.execute();
    }

    private void showLoading(){
        dialog = new MaterialDialog.Builder(this)
                .content(R.string.label_wait)
                .progress(true,0)
                .cancelable(false)
                .show();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(Usuario response) {
        UserUtils.saveUser(response, this);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Subscribe
    public void onEvent(WebError error){
        hideLoading();
        Snackbar.make(findViewById(R.id.container),
                error.getMessage(),
                Snackbar.LENGTH_LONG).show();
    }

    private void hideLoading(){
        if(dialog != null && dialog.isShowing()){
            dialog.hide();
            dialog = null;
        }
    }

}
