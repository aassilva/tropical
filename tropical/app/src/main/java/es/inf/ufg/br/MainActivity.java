package es.inf.ufg.br;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;

import es.inf.ufg.br.presenter.BaseFragment;
import es.inf.ufg.br.presenter.list.mural.MuralListFragment;
import es.inf.ufg.br.presenter.list.ocorrencia.OcorrenciaFragment;
import es.inf.ufg.br.presenter.list.perfil.PerfilFragment;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView mMainNav;
    private FrameLayout mMainFrame;

    private PerfilFragment perfilFragment;
    private MuralListFragment muralFragment;
    private OcorrenciaFragment ocorrenciaFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMainFrame = (FrameLayout)findViewById(R.id.main_frame);
        mMainNav = (BottomNavigationView)findViewById(R.id.main_nav);

        perfilFragment = new PerfilFragment();
        muralFragment = new MuralListFragment();
        ocorrenciaFragment = new OcorrenciaFragment();

        mMainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.nav_perfil :
                        //mMainNav.setItemBackgroundResource(R.color.colorPrimary);
                        //setFragment(perfilFragment);
                        return true;

                    case R.id.nav_mural :
                        //mMainNav.setItemBackgroundResource(R.color.colorAccent);
                        setFragment(muralFragment);
                        return true;

                    case R.id.nav_ocorr :
                        //mMainNav.setItemBackgroundResource(R.color.colorPrimaryDark);
                        //setFragment(ocorrenciaFragment);
                        return true;

                    default:
                        return false;
                }

            }
        });
    }

    private void setFragment(MuralListFragment fragment) {

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, fragment);
        fragmentTransaction.commit();
    }


//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        frameLayout = (FrameLayout)findViewById(R.id.main_frame);
//        navMain = (BottomNavigationView)findViewById(R.id.main_nav);
//
//        navMain.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                switch (item.getItemId()) {
//                    case R.id.nav_perfil :
//                        navMain.setItemBackgroundResource(R.color.colorPrimary);
//                        setFragment(perfilFragment);
//                        return true;
//
//                    case R.id.nav_mural :
//                        navMain.setItemBackgroundResource(R.color.colorAccent);
//                        return true;
//
//                    case R.id.nav_ocorr :
//                        navMain.setItemBackgroundResource(R.color.colorPrimaryDark);
//                        return true;
//
//                    default:
//                        return false;
//                }
//            }
//        });
//    }

//
//    private static final String VERIFICADOR = "MainActivity";
//
//    private SectionsPageAdapter adapterPage;
//
//    private ViewPager mViewPager;


//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        adapterPage = new SectionsPageAdapter(getSupportFragmentManager());
//
//        // Set up the ViewPager with the sections adapter.
//        mViewPager = (ViewPager) findViewById(R.id.container);
//        setupViewPager(mViewPager);
//
//        TabLayout tabLayout = (TabLayout) findViewById(R.id.layout);
//        tabLayout.setupWithViewPager(mViewPager);
//    }
//
//    private void setupViewPager(ViewPager viewPager) {
//        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
//        //adapter.addFragment(new MuralListFragment(), "Mural");
//        viewPager.setAdapter(adapter);
//    }

}
