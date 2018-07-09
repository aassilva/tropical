package gt.dsdm.es.inf.br.ufg.gt_app;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import gt.dsdm.es.inf.br.ufg.gt_app.presenter.list.MuralFragment;
import gt.dsdm.es.inf.br.ufg.gt_app.presenter.list.OcorrenciaFragment;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    FragmentManager fragmentManager = getSupportFragmentManager();
    FragmentTransaction transaction = fragmentManager.beginTransaction();

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    //mTextMessage.setText(R.string.title_home);
                    transaction
                            .replace(R.id.frame_layouts,  new MuralFragment())
                            .commit();
                    return true;
                case R.id.navigation_dashboard:
                    //mTextMessage.setText(R.string.title_dashboard);
                    transaction
                            .replace(R.id.frame_layouts,  new OcorrenciaFragment())
                            .commit();
                    return true;
                case R.id.navigation_notifications:
                    //mTextMessage.setText(R.string.title_notifications);
                    transaction
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

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        transaction
                //.add(R.id.frame_layouts,  new PerfilFragment())
                .add(R.id.frame_layouts,  new  MuralFragment())
                .commit();

    }

}

//
//    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.navigation_home:
//                //mTextMessage.setText(R.string.title_home);
//                getSupportFragmentManager()
//                        .beginTransaction()
//                        .replace(R.id.frame_layouts,  new MuralFragment())
//                        .commit();
//                return true;
//            case R.id.navigation_dashboard:
//                //mTextMessage.setText(R.string.title_dashboard);
//                getSupportFragmentManager()
//                        .beginTransaction()
//                        .replace(R.id.frame_layouts,  new OcorrenciaFragment())
//                        .commit();
//                return true;
//            case R.id.navigation_notifications:
//                //mTextMessage.setText(R.string.title_notifications);
//                getSupportFragmentManager()
//                        .beginTransaction()
//                        .replace(R.id.frame_layouts,  new PerfilFragment())
//                        .commit();
//                return true;
//        }
//        return false;
//    }