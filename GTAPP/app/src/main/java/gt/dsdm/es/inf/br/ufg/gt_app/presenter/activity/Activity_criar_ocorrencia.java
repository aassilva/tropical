package gt.dsdm.es.inf.br.ufg.gt_app.presenter.activity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

import gt.dsdm.es.inf.br.ufg.gt_app.R;

public class Activity_criar_ocorrencia extends AppCompatActivity implements OnMapReadyCallback {

    Spinner simpleSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_ocorrencia);

        simpleSpinner = findViewById(R.id.simple_spinner);

        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        final List<String> listaCategoria = new ArrayList<String>();
        listaCategoria.add("Solicitação de visita");
        listaCategoria.add("Solicitação de intervenção");
        listaCategoria.add("Denuncia em espaço público");
        listaCategoria.add("Denuncia em espaço privado");

        ArrayAdapter<String> adapterCategoria = new ArrayAdapter<>(Activity_criar_ocorrencia.this, R.layout.support_simple_spinner_dropdown_item, listaCategoria);
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

    @Override
    public void onMapReady(GoogleMap googleMap) {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }


        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();

        Location location = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, false));
        if (location != null)
        {

            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 15));

            Log.i("aaa", "bbb");
        }

        googleMap.setMyLocationEnabled(true);

    }
}
