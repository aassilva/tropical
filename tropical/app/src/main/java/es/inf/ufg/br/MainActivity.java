package es.inf.ufg.br;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView navMain;
    private FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        frameLayout = (FrameLayout)findViewById(R.id.main_frame);
        navMain = (BottomNavigationView)findViewById(R.id.main_nav);

        navMain.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_perfil :
                        navMain.setItemBackgroundResource(R.color.colorPrimary);
                        setFragment(perfilFragment);
                        return true;

                    case R.id.nav_mural :
                        navMain.setItemBackgroundResource(R.color.colorAccent);
                        return true;

                    case R.id.nav_ocorr :
                        navMain.setItemBackgroundResource(R.color.colorPrimaryDark);
                        return true;

                    default:
                        return false;
                }
            }
        });
    }
}
