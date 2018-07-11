package gt.dsdm.es.inf.br.ufg.gt_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import gt.dsdm.es.inf.br.ufg.gt_app.presenter.activity.LoginActivity;
import gt.dsdm.es.inf.br.ufg.gt_app.presenter.list.MuralFragment;
import gt.dsdm.es.inf.br.ufg.gt_app.presenter.list.NotLogedFragment;
import gt.dsdm.es.inf.br.ufg.gt_app.presenter.list.OcorrenciaFragment;
import gt.dsdm.es.inf.br.ufg.gt_app.presenter.list.PerfilFragment;
import gt.dsdm.es.inf.br.ufg.gt_app.utils.UserUtils;

public class MainActivity extends AppCompatActivity
        implements BottomNavigationView.OnNavigationItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);

        loadFragment(new MuralFragment());
    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame_layouts, fragment)
                    .commit();

            return true;
        }

        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        Fragment fragment = null;

        switch (menuItem.getItemId()) {
            case R.id.navigation_home:
                fragment = new MuralFragment();
                break;

            case R.id.navigation_dashboard:
                if(UserUtils.isLoged(this))
                    fragment = new OcorrenciaFragment();
                else
                    fragment = new NotLogedFragment();
                break;

            case R.id.navigation_notifications:
                if(UserUtils.isLoged(this))
                    fragment = new PerfilFragment();
                else
                fragment = new NotLogedFragment();
                break;
        }
        return loadFragment(fragment);
    }

    public void login(View view){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}