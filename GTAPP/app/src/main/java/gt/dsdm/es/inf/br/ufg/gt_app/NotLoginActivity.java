package gt.dsdm.es.inf.br.ufg.gt_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import gt.dsdm.es.inf.br.ufg.gt_app.presenter.activity.LoginActivity;

public class NotLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_not_login);

        ImageView buttonClose = findViewById(R.id.button_close_not_login);

        buttonClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentPassword = new Intent(getApplicationContext(),
                        MainActivity.class);
                startActivity(intentPassword);
            }
        });
    }

    public void login_not_login(View view) {
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
