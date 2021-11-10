package com.example.actividadaprendizaje1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.actividadaprendizaje1.domain.Clientes;
import com.example.actividadaprendizaje1.domain.Trabajadores;
import com.example.actividadaprendizaje1.domain.Vehiculos;

import java.util.ArrayList;

public class indexActivity extends AppCompatActivity {

    //Creo el array para mostrar los clientes, trabajadores, vehiculos...
    public static ArrayList<Clientes> listadoClientes;
    public static ArrayList<Trabajadores> listadoTrabajadores;
    public static ArrayList<Vehiculos> listadoVehiculos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        listadoClientes=new ArrayList<>();
        listadoTrabajadores=new ArrayList<>();
        listadoVehiculos=new ArrayList<>();


    }
    //Menu actionBar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar_index, menu);
        return true;
    }
    //Manu actionBar opciones
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.buscadorListas){
            return true;
        }else if (item.getItemId()==R.id.talleres){
            //todo
        }
        //todo por hacer las opciones

        return false;
    }

    //Boton para acceder a la zona de usuarios
    public void zonaUsuarios(View view){
        Intent miIntent=new Intent(this, zonaUsuariosActivity.class);
        startActivity(miIntent);
    }

    //Boton para acceder a la zona de vehiculos
    public void zonaVehiculos(View view){
        Intent miIntent=new Intent(this, zonaVehiculosActivity.class);
        startActivity(miIntent);
    }

    //Boton para registrar un nuevo vehiculo averiado directamente
    public void nuevaEntrada(View view){
        Intent miIntent=new Intent(this, nuevaEntradaActivity.class);
        startActivity(miIntent);
    }
}