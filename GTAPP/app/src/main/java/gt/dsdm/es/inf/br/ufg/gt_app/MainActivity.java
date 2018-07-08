package gt.dsdm.es.inf.br.ufg.gt_app;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import gt.dsdm.es.inf.br.ufg.gt_app.presenter.list.MuralFragment;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    //mTextMessage.setText(R.string.title_home);
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.frame_layouts,  new MuralFragment())
                            .commit();
                    return true;
                case R.id.navigation_dashboard:
                    //mTextMessage.setText(R.string.title_dashboard);
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.frame_layouts,  new OcorrenciaFragment())
                            .commit();
                    return true;
                case R.id.navigation_notifications:
                    //mTextMessage.setText(R.string.title_notifications);
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.frame_layouts,  new PerfilFragment())
                            .commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState==  null)  {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.frame_layouts,  new OcorrenciaFragment())
                    //.add(R.id.frame_layouts,  new  MuralFragment())
                    .commit();
        }


        //mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
