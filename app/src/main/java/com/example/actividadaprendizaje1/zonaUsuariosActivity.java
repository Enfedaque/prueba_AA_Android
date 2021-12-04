package com.example.actividadaprendizaje1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.actividadaprendizaje1.bbdd.clientesBBDD;

/*Activity de la zona de usuarios*/

public class zonaUsuariosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zona_usuarios);


    }

    protected void onResume(){
        super.onResume();
        indexActivity.listadoClientes.clear();
        indexActivity.listadoTrabajadores.clear();
        indexActivity.listadoVehiculos.clear();
        clientesBBDD database= Room.databaseBuilder(getApplicationContext(), clientesBBDD.class,
                "Taller").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        indexActivity.listadoClientes.addAll(database.clientesDAO().getAll());
        indexActivity.listadoTrabajadores.addAll(database.trabajadoresDAO().getAll());
        indexActivity.listadoVehiculos.addAll(database.vehiculosDAO().getAll());
    }

    //Menu actionBar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar_usuarios, menu);
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
        }else if (item.getItemId()==R.id.facturaNueva){
            Intent miIntent=new Intent(this, facturaActivity.class);
            startActivity(miIntent);
            return true;
        }
        return false;
    }

    public void nuevoCliente(View view){
        Intent miIntent=new Intent(this, nuevoClienteActivity.class);
        startActivity(miIntent);
    }

    public void listadoClientes(View view){
        Intent miIntent=new Intent(this, listadoClientesActivity.class);
        startActivity(miIntent);
    }

    public void nuevoTrabajador(View view){
        Intent miIntent=new Intent(this, nuevoTrabajadorActivity.class);
        startActivity(miIntent);
    }

    public void listadoTrabajadores(View view){
        Intent miIntent=new Intent(this, listadoTrabajadoresActivity.class);
        startActivity(miIntent);
    }
}