package gt.dsdm.es.inf.br.ufg.gt_app.presenter.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import gt.dsdm.es.inf.br.ufg.gt_app.R;

public class Activity_criar_ocorrencia extends AppCompatActivity {

    Spinner simpleSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_ocorrencia);

        simpleSpinner = (Spinner)findViewById(R.id.simple_spinner);

        final List<String> listaCategoria = new ArrayList<String>();
        listaCategoria.add("Solicitação de visita");
        listaCategoria.add("Solicitação de intervenção");
        listaCategoria.add("Denuncia em espaço público");
        listaCategoria.add("Denuncia em espaço privado");

        ArrayAdapter<String> adapterCategoria = new ArrayAdapter<>(Activity_criar_ocorrencia.this, R.layout.support_simple_spinner_dropdown_item,listaCategoria);
        simpleSpinner.setAdapter(adapterCategoria);

        simpleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(Activity_criar_ocorrencia.this, listaCategoria.get(position), Toast.LENGTH_SHORT);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
