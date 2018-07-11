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
import gt.dsdm.es.inf.br.ufg.gt_app.model.Usuario;
import gt.dsdm.es.inf.br.ufg.gt_app.utils.UserUtils;
import gt.dsdm.es.inf.br.ufg.gt_app.web.WebConnection;
import gt.dsdm.es.inf.br.ufg.gt_app.web.WebRegistro;
import gt.dsdm.es.inf.br.ufg.gt_app.web.login.WebError;

public class RegistroActivity extends AppCompatActivity implements WebConnection.onRegisterResponse {

    MaterialDialog dialog;
    EditText editTextName;
    EditText editTextCPF;
    EditText editTextTelefone;
    EditText editTextEmail;
    EditText editTextUsuario;
    EditText editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        ImageView buttonClose = findViewById(R.id.button_close_cadastro);

        buttonClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        setupRegister();

        editTextName = findViewById(R.id.input_name);
        editTextCPF = findViewById(R.id.input_cpf);
        editTextTelefone = findViewById(R.id.input_telefone);
        editTextEmail = findViewById(R.id.input_email);
        editTextUsuario = findViewById(R.id.input_usuario);
        editTextPassword = findViewById(R.id.input_password);

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

    private void setupRegister() {
        Button buttonLogin =
                findViewById(R.id.button_register_cadastro);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tryRegister();
            }
        });
    }

    private void tryRegister() {

        if(!"".equals(editTextEmail.getText().toString())){
            showLoading();
            sendCredentials(editTextName.getText().toString(),
                    editTextCPF.getText().toString(),
                    editTextTelefone.getText().toString(),
                    editTextEmail.getText().toString(),
                    editTextUsuario.getText().toString(),
                    editTextPassword.getText().toString());
        }else{
            editTextEmail.setError("Preencha o campo email");
        }

    }

    private void sendCredentials(String nome, String cpf, String telefone,
                                 String email, String usuario, String pass) {
        WebRegistro taskRegistro = new WebRegistro(nome, cpf, telefone, email, usuario, pass, this);
        taskRegistro.call();
    }

    private void showLoading(){
        dialog = new MaterialDialog.Builder(this)
                .content(R.string.label_wait)
                .progress(true,0)
                .cancelable(false)
                .show();
    }

    @Subscribe
    public void onEvent(WebError error){
        hideLoading();
        Snackbar.make(findViewById(R.id.container),
                error.getMessage(),
                Snackbar.LENGTH_LONG).show();
    }

    void hideLoading(){

        if(dialog != null && dialog.isShowing()){
            runOnUiThread(new Runnable() {

                @Override
                public void run() {

                    dialog.hide();
                    dialog = null;

                    Usuario usuario = new Usuario();
                    usuario.setNome(editTextName.getText().toString());
                    usuario.setCpf(editTextCPF.getText().toString());
                    usuario.setUsuario(editTextUsuario.getText().toString());
                    usuario.setTelefone(editTextTelefone.getText().toString());
                    usuario.setEmail(editTextEmail.getText().toString());
                    usuario.setToken("aaaaa");

                    UserUtils.saveUser(usuario, RegistroActivity.this);

                    Intent intent = new Intent(RegistroActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            });

        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(String response) {
        if (response.equals(null) || response.equals("")) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void handleResponse() {
        hideLoading();
    }
}
