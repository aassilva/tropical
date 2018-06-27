package br.ufg.inf.es.goistropical.presenter.murallist;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import br.ufg.inf.es.goistropical.R;

public class MuralListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_row_task);

        initToolbar();
        initView();
//        try {
//            loadList();
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    private void initToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.white));
    }

    public void initView(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        MuralListFragment fragment = new MuralListFragment();
        fragmentTransaction.add(R.id.recycler_view_mural, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.navigation, menu);

        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.navigation_perfil:
                //tela de perfil
                return true;

            //Case para a opção mural.
            case R.id.navigation_mural:
                return true;
            case R.id.navigation_ocorrencia:
                //tela de ocorrencia
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

        //Aqui colocar as telas que serão direcionadas quando preciona uma opção do menu.
//        if(item.getItemId() == android.R.id.home){
//            finish();
//        }

       // return super.onOptionsItemSelected(item);
    }
}
