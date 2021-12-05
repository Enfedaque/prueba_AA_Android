package com.example.actividadaprendizaje1.vehiculos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.actividadaprendizaje1.R;
import com.example.actividadaprendizaje1.inicio.indexActivity;
import com.example.actividadaprendizaje1.mapas.talleresActivity;

public class zonaVehiculosActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zona_vehiculos);
    }

    protected void onResume(){
        super.onResume();

    }

    //Menu actionBar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar_vehiculos, menu);
        return true;
    }
    //Manu actionBar opciones
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //Si toca la casa lo envio al inicio
        if(item.getItemId() == R.id.home){
            Intent miIntent=new Intent(this, indexActivity.class);
            startActivity(miIntent);
            return true;
        }else if (item.getItemId()==R.id.buscarTalleres){
            Intent miIntent=new Intent(this, talleresActivity.class);
            startActivity(miIntent);
            return true;
        }return true;
    }

    public void nuevoVehiculo(View view){
        Intent miIntent=new Intent(this, nuevaEntradaActivity.class);
        startActivity(miIntent);
    }

    public void listadoVehiculos(View view){
        Intent miIntent=new Intent(this, listadoVehiculosActivity.class);
        startActivity(miIntent);
    }
}