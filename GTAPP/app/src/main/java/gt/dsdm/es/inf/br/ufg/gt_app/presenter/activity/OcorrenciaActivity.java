package gt.dsdm.es.inf.br.ufg.gt_app.presenter.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.view.MenuItem;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import gt.dsdm.es.inf.br.ufg.gt_app.R;
import gt.dsdm.es.inf.br.ufg.gt_app.model.Ocorrencia;

public class OcorrenciaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ocorrencia);

        Ocorrencia ocorrencia = EventBus.getDefault().removeStickyEvent(Ocorrencia.class);
        setupView(ocorrencia);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

    public void setupView(Ocorrencia ocorrencia) {

        TextView idView;
        TextView tituloView;
        TextView descricaoView;
        TextView statusView;

        idView = (TextView) findViewById(R.id.label_task_id_ocorrencia_oc);
        idView.setText("ID: " + ocorrencia.getId());

        tituloView = (TextView) findViewById(R.id.label_task_title_ocorrencia_oc);
        tituloView.setText("TITULO: " + ocorrencia.getTitulo());

        descricaoView = (TextView) findViewById(R.id.label_task_desc_ocorrencia_oc);
        descricaoView.setText("DESCRIÇÃO: " + ocorrencia.getDescricao());

        statusView = (TextView) findViewById(R.id.label_task_status_ocorrencia_oc);
        statusView.setText("STATUS: " + ocorrencia.getStatus());

    }
}
