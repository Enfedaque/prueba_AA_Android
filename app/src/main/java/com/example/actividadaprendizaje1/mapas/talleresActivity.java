package com.example.actividadaprendizaje1.mapas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

import com.example.actividadaprendizaje1.R;
import com.example.actividadaprendizaje1.bbdd.baseDeDatos;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Objects;


public class talleresActivity extends AppCompatActivity implements OnMapReadyCallback,
        GoogleMap.OnMarkerClickListener, GoogleMap.OnMapClickListener, LocationListener {

    private GoogleMap miMapa;
    private LocationManager locationManager;
    private LocationProvider locationProvider;

    LatLng taller1;
    LatLng taller2;
    LatLng taller3;
    LatLng taller4;
    LatLng taller5;
    LatLng taller6;
    CheckBox particulares;
    CheckBox empresas;
    CheckBox global;
    CheckBox limpiar;

    baseDeDatos miClientesBBDD;
    private int distanciaDesplazado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talleres);

        //Asi cargo el mapa
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapa);

        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

        empresas = findViewById(R.id.empresas);
        particulares = findViewById(R.id.particulares);
        global = findViewById(R.id.global);
        limpiar=findViewById(R.id.limpiarMapa);

    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {

        miMapa=googleMap;
        miMapa.setOnMarkerClickListener(this);
        miMapa.setOnMapClickListener(this);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat
                .checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION} , 1);
            return;
        }
        miMapa.setMyLocationEnabled(true);

        locationManager=(LocationManager) getSystemService(LOCATION_SERVICE);
        locationProvider=locationManager.getProvider(locationManager.GPS_PROVIDER);
        locationManager.requestLocationUpdates(locationProvider.getName(), 4000, 200, this);

    }

    //Metodo segun lo elegido en el checkbox
    public void mostrarMarkers(View view){

        crearMarkers();

        //Limpio el mapa de cualquier marca
        limpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                miMapa.clear();
                empresas.setChecked(false);
                particulares.setChecked(false);
                global.setChecked(false);
            }
        });

        if (view.getId()==R.id.particulares){
            empresas.setChecked(false);
            global.setChecked(false);
            limpiar.setChecked(false);
            miMapa.clear();

            Objects.requireNonNull(miMapa.addMarker(new MarkerOptions()
                    .position(taller1)
                    .title(getString(R.string.particular1))))
                    .setSnippet(getString(R.string.particular11));

            Objects.requireNonNull(miMapa.addMarker(new MarkerOptions()
                    .position(taller2)
                    .title(getString(R.string.particular2))))
                    .setSnippet(getString(R.string.particular22));
        }
        if (view.getId()==R.id.empresas){
            particulares.setChecked(false);
            global.setChecked(false);
            limpiar.setChecked(false);
            miMapa.clear();

            Objects.requireNonNull(miMapa.addMarker(new MarkerOptions()
                    .position(taller3)
                    .title(getString(R.string.empresas1))))
                    .setSnippet(getString(R.string.empresas11));

            Objects.requireNonNull(miMapa.addMarker(new MarkerOptions()
                    .position(taller4)
                    .title(getString(R.string.empresas2))))
                    .setSnippet(getString(R.string.empresas22));
        }
        if (view.getId()==R.id.global){
            empresas.setChecked(false);
            particulares.setChecked(false);
            limpiar.setChecked(false);
            miMapa.clear();

            Objects.requireNonNull(miMapa.addMarker(new MarkerOptions()
                    .position(taller1)
                    .title(getString(R.string.particular111))))
                    .setSnippet(getString(R.string.particular1111));

            Objects.requireNonNull(miMapa.addMarker(new MarkerOptions()
                    .position(taller2)
                    .title(getString(R.string.particular222))))
                    .setSnippet(getString(R.string.particular2222));

            Objects.requireNonNull(miMapa.addMarker(new MarkerOptions()
                    .position(taller3)
                    .title(getString(R.string.empresas111))))
                    .setSnippet(getString(R.string.empresas1111));

            Objects.requireNonNull(miMapa.addMarker(new MarkerOptions()
                    .position(taller4)
                    .title(getString(R.string.empresas222))))
                    .setSnippet(getString(R.string.empresas2222));

            Objects.requireNonNull(miMapa.addMarker(new MarkerOptions()
                    .position(taller5)
                    .title(getString(R.string.global1))))
                    .setSnippet(getString(R.string.global11));

            Objects.requireNonNull(miMapa.addMarker(new MarkerOptions()
                    .position(taller6)
                    .title(getString(R.string.global2))))
                    .setSnippet(getString(R.string.global22));
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


    //Cada vez que yo me mueva me devuelve la nueva ubicacion
    @Override
    public void onLocationChanged(@NonNull Location location) {
        AlertDialog.Builder dialogo = new AlertDialog.Builder(this);
        dialogo.setTitle("GPS");
        dialogo.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        dialogo.setMessage(R.string.desplazado);
        dialogo.show();
        distanciaDesplazado+=200;

    }

}