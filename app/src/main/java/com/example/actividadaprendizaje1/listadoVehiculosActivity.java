package com.example.actividadaprendizaje1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.actividadaprendizaje1.BBDD.VehiculosBBDD;
import com.example.actividadaprendizaje1.domain.Vehiculos;

public class listadoVehiculosActivity extends AppCompatActivity {

    private ArrayAdapter<Vehiculos> listadoVehiculosAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_vehiculos);

        //Llamo al metodo
        cargarVehiculos();

        listadoVehiculosAdapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                indexActivity.listadoVehiculos);

        ListView lvListadoClientes=findViewById(R.id.listadoVehiculos);
        lvListadoClientes.setAdapter(listadoVehiculosAdapter);
    }

    @Override
    protected void onResume(){
        super.onResume();

        cargarVehiculos();
    }

    public void cargarVehiculos(){
        indexActivity.listadoVehiculos.clear();
        //Instancia la clase BBDD creada antes
        VehiculosBBDD vDB= Room.databaseBuilder(getApplicationContext(), VehiculosBBDD.class,
                "taller").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        //Añado mi vehiculo a la BBDD a traves del DAO
        indexActivity.listadoVehiculos.addAll(vDB.vehiculosDAO().getAll());
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