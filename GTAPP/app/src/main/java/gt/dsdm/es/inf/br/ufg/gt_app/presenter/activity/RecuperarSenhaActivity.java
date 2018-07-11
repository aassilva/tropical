package gt.dsdm.es.inf.br.ufg.gt_app.presenter.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import gt.dsdm.es.inf.br.ufg.gt_app.MainActivity;
import gt.dsdm.es.inf.br.ufg.gt_app.R;

public class RecuperarSenhaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_senha);

        setupButtonRememberPassword();
    }


    private void setupButtonRememberPassword() {
        Button buttonRememberPassword =
                findViewById(R.id.button_recuperar);
        buttonRememberPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentPassword = new Intent(getApplicationContext(),
                        MainActivity.class);
                startActivity(intentPassword);
            }
        });
    }
}
