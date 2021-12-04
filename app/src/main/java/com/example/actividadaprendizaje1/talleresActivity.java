package com.example.actividadaprendizaje1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Objects;


public class talleresActivity extends AppCompatActivity implements OnMapReadyCallback,
        GoogleMap.OnMarkerClickListener, GoogleMap.OnMapClickListener {

    private GoogleMap miMapa;
    LatLng taller1;
    LatLng taller2;
    LatLng taller3;
    LatLng taller4;
    LatLng taller5;
    LatLng taller6;
    CheckBox particulares;
    CheckBox empresas;
    CheckBox global;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talleres);

        //Asi cargo el mapa
        SupportMapFragment mapFragment=(SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapa);

        if (mapFragment != null){
            mapFragment.getMapAsync(this);
        }

        empresas=findViewById(R.id.empresas);
        particulares=findViewById(R.id.particulares);
        global=findViewById(R.id.global);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {

        miMapa=googleMap;
        miMapa.setOnMarkerClickListener(this);
        miMapa.setOnMapClickListener(this);

    }

    //Metodo segun lo elegido en el checkbox
    public void mostrarMarkers(View view){

        crearMarkers();

        if (view.getId()==R.id.particulares){
            empresas.setChecked(false);
            global.setChecked(false);
            miMapa.clear();

            Objects.requireNonNull(miMapa.addMarker(new MarkerOptions()
                    .position(taller1)
                    .title("Taller particular 1")))
                    .setSnippet("Recambios para particulares");

            Objects.requireNonNull(miMapa.addMarker(new MarkerOptions()
                    .position(taller2)
                    .title("Taller particular 2")))
                    .setSnippet("Recambios para particulares");
        }
        if (view.getId()==R.id.empresas){
            particulares.setChecked(false);
            global.setChecked(false);
            miMapa.clear();

            Objects.requireNonNull(miMapa.addMarker(new MarkerOptions()
                    .position(taller3)
                    .title("Taller empresas 1")))
                    .setSnippet("Recambios para uso profesional");

            Objects.requireNonNull(miMapa.addMarker(new MarkerOptions()
                    .position(taller4)
                    .title("Taller empresas 2")))
                    .setSnippet("Recambios para uso profesional");
        }
        if (view.getId()==R.id.global){
            empresas.setChecked(false);
            particulares.setChecked(false);
            miMapa.clear();

            Objects.requireNonNull(miMapa.addMarker(new MarkerOptions()
                    .position(taller1)
                    .title("Taller particular 1")))
                    .setSnippet("Recambios para particulares");

            Objects.requireNonNull(miMapa.addMarker(new MarkerOptions()
                    .position(taller2)
                    .title("Taller particular 2")))
                    .setSnippet("Recambios para particulares");

            Objects.requireNonNull(miMapa.addMarker(new MarkerOptions()
                    .position(taller3)
                    .title("Taller empresas 1")))
                    .setSnippet("Recambios para uso profesional");

            Objects.requireNonNull(miMapa.addMarker(new MarkerOptions()
                    .position(taller4)
                    .title("Taller empresas 2")))
                    .setSnippet("Recambios para uso profesional");

            Objects.requireNonNull(miMapa.addMarker(new MarkerOptions()
                    .position(taller5)
                    .title("Taller global 1")))
                    .setSnippet("Recambios para todo tipo de vehiculos");

            Objects.requireNonNull(miMapa.addMarker(new MarkerOptions()
                    .position(taller6)
                    .title("Taller global 2")))
                    .setSnippet("Recambios para todo tipo de vehiculos");
        }
    }

    private void crearMarkers() {
        taller1 = new LatLng(41.73463707764275, -1.0571548237694044);
        taller2 = new LatLng(40.38874829408632, -3.7842929925115243);
        taller3 = new LatLng(41.411562200476936, 2.1984557341740487);
        taller4 = new LatLng(37.39348801025005, -6.00996221441307);
        taller5 = new LatLng(42.76023393965538, -8.287488760748838);
        taller6 = new LatLng(39.47737854310233, -0.3492460469420232);
    }

    @Override
    public boolean onMarkerClick(@NonNull Marker marker) {
        return false;
    }

    @Override
    public void onMapClick(@NonNull LatLng latLng) {
        //Hago que la camara apunte hacia don de hago click
        miMapa.moveCamera(CameraUpdateFactory.newLatLng(latLng));
    }


}