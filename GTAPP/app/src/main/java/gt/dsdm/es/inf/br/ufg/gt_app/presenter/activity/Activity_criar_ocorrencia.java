package gt.dsdm.es.inf.br.ufg.gt_app.presenter.activity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import gt.dsdm.es.inf.br.ufg.gt_app.R;
import gt.dsdm.es.inf.br.ufg.gt_app.model.Ocorrencia;
import gt.dsdm.es.inf.br.ufg.gt_app.request.ApiarySaveOcurrenceRequest;

public class Activity_criar_ocorrencia extends AppCompatActivity implements OnMapReadyCallback {

    Spinner spinnerCategorias;
    private MarkerOptions marker;
    private EditText edtTitulo, edtDescricao;
    double latitude = 0, longitude = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_ocorrencia);

        edtTitulo = findViewById(R.id.edt_titulo);
        edtDescricao = findViewById(R.id.edt_descricao);

        spinnerCategorias = findViewById(R.id.simple_spinner);

        edtDescricao = findViewById(R.id.edt_descricao);

        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        final List<String> listaCategoria = new ArrayList<String>();
        listaCategoria.add("Selecione");
        listaCategoria.add("Solicitação de visita");
        listaCategoria.add("Solicitação de intervenção");
        listaCategoria.add("Denuncia em espaço público");
        listaCategoria.add("Denuncia em espaço privado");

        ArrayAdapter<String> adapterCategoria = new ArrayAdapter<>(Activity_criar_ocorrencia.this, R.layout.support_simple_spinner_dropdown_item, listaCategoria);
        spinnerCategorias.setAdapter(adapterCategoria);

        spinnerCategorias.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(Activity_criar_ocorrencia.this, listaCategoria.get(position), Toast.LENGTH_SHORT);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }


        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();

        Location location = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, false));
        if (location != null) {

            CameraUpdate center = CameraUpdateFactory.newLatLng(new LatLng(location.getLatitude(), location.getLongitude()));
            googleMap.moveCamera(center);

        }

        googleMap.setMyLocationEnabled(true);



        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {

                googleMap.clear();

                latitude = latLng.latitude;
                longitude = latLng.longitude;

                marker = new MarkerOptions()
                        .position(new LatLng(latLng.latitude, latLng.longitude))
                        .anchor(0.5f, 0.1f)
                        .title("")
                        .snippet("")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));

                googleMap.addMarker(marker);
            }
        });
    }

    public void saveOccurrence(View view) {

        if(validaCampos()){
            //Digitou certo, vou salvar a ocorrência

            ApiarySaveOcurrenceRequest request = new ApiarySaveOcurrenceRequest(
                    new Ocorrencia(edtTitulo.getText().toString(),
                            edtDescricao.getText().toString(),
                            "Aberto",
                            latitude+"|"+longitude,
                            spinnerCategorias.getSelectedItem().toString()), this);
            request.execute();
        }
    }

    private boolean validaCampos(){

        if (spinnerCategorias.getSelectedItem() == "Selecione") {
            Toast.makeText(this, "Selecione uma uma categoria!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (edtTitulo.getText().toString().isEmpty()) {
            Toast.makeText(this, "Digite um Título!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (edtDescricao.getText().toString().isEmpty()) {
            Toast.makeText(this, "Digite uma descrição!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(latitude == 0 && longitude == 0){
            Toast.makeText(this, "Selecione uma localização!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
