package es.inf.ufg.br;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String VERIFICADOR = "MainActivity";

    private SectionsPageAdapter adapterPage;

    private ViewPager mViewPager;



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





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapterPage = new SectionsPageAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(mViewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.layout);
        tabLayout.setupWithViewPager(mViewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        //adapter.addFragment(new MuralListFragment(), "Mural");
        viewPager.setAdapter(adapter);
    }

}
