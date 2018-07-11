package gt.dsdm.es.inf.br.ufg.gt_app.presenter.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.view.MenuItem;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import gt.dsdm.es.inf.br.ufg.gt_app.R;
import gt.dsdm.es.inf.br.ufg.gt_app.model.Mural;

public class NoticiasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticias);

        Mural mural = EventBus.getDefault().removeStickyEvent(Mural.class);
        setupView(mural);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

    public void setupView(Mural mural) {

        TextView tituloView;
        TextView noticiaView;
        AppCompatImageView imageView;

        imageView = (AppCompatImageView) findViewById(R.id.label_task_img_noticias);
        Picasso.get().load(mural.getImagem()).into(imageView);

        tituloView = (TextView) findViewById(R.id.label_task_title_noticias);
        tituloView.setText(mural.getTitulo());

        noticiaView = (TextView) findViewById(R.id.label_task_noticias);
        noticiaView.setText(mural.getConteudo());

    }
}
