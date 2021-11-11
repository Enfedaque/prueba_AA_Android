package com.example.actividadaprendizaje1.listas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.actividadaprendizaje1.R;
import com.example.actividadaprendizaje1.domain.Vehiculos;
import com.example.actividadaprendizaje1.indexActivity;

public class listadoVehiculosActivity extends AppCompatActivity {

    private ArrayAdapter<Vehiculos> listadoVehiculosAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_vehiculos);

        listadoVehiculosAdapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                indexActivity.listadoVehiculos);

        ListView lvListadoClientes=findViewById(R.id.listadoVehiculos);
        lvListadoClientes.setAdapter(listadoVehiculosAdapter);
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
        }else if (item.getItemId()==R.id.acercaDe){
            //todo aqui quiero mostrar un activity o un alert o algo con informacion de la
            // aplicacion
        } else if (item.getItemId()==R.id.webCoches) {
            //todo aun no se que opcion poner
        }else if (item.getItemId()==R.id.navegador){
            //todo aun no se que opcion poner
        }

        return false;
    }
}