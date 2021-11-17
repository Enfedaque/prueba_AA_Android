package com.example.actividadaprendizaje1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;

import com.example.actividadaprendizaje1.BBDD.VehiculosBBDD;
import com.example.actividadaprendizaje1.domain.Clientes;
import com.example.actividadaprendizaje1.domain.Vehiculos;

public class listadoVehiculosActivity extends AppCompatActivity {

    private ArrayAdapter<Vehiculos> listadoVehiculosAdapter;
    Button btBuscar;
    EditText texto;
    Switch miSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_vehiculos);

        btBuscar=findViewById(R.id.btBuscarVehiculo);
        texto=findViewById(R.id.matriculaBuscadorVehiculo);
        miSwitch=findViewById(R.id.swBuscarVehiculo);

        listadoVehiculosAdapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                indexActivity.listadoVehiculos);

        ListView lvListadoClientes=findViewById(R.id.listadoVehiculos);
        lvListadoClientes.setAdapter(listadoVehiculosAdapter);
    }

    @Override
    protected void onResume(){
        super.onResume();

    }

    //Metodo para el switch que muestra el buscador
    public void buscadorVehiculosVisible(View view){
        if (view.getId()==R.id.swBuscarVehiculo){
            if (miSwitch.isChecked()){
                texto.setVisibility(View.VISIBLE);
                btBuscar.setVisibility(View.VISIBLE);
            }else{
                texto.setVisibility(View.GONE);
                btBuscar.setVisibility(View.GONE);
            }
        }

    }

    //Metodo del boton que busca al cliente por apellido en la lista y lo muestra
    public void resultadoBusquedaVehiculo(View view){
        for (Vehiculos miVehiculo : indexActivity.listadoVehiculos){
            /*Controlo las excepciones que puedan saltar como intrudicr un tipo de dato incorrecto
            en la busqueda
             */
            try {
                if (miVehiculo.getMatricula()
                        .equalsIgnoreCase(texto.getText().toString())){
                    String mostrarResultado=miVehiculo.toString2();
                    //Muestro en un dialogo la informacion completa del usuario
                    AlertDialog.Builder dialogo = new AlertDialog.Builder(this);
                    dialogo.setTitle("Informarción");
                    dialogo.setMessage(mostrarResultado);
                    dialogo.show();
                }else{
                    AlertDialog.Builder dialogo = new AlertDialog.Builder(this);
                    dialogo.setTitle("Informarción");
                    dialogo.setMessage("Vehiculo no encontrado");
                    dialogo.show();
                }
            }catch ( Exception ex){
                AlertDialog.Builder dialogo = new AlertDialog.Builder(this);
                dialogo.setTitle("Informarción");
                dialogo.setMessage("Vehiculo no encontrado");
                dialogo.show();
            }

        }
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
        /*if(item.getItemId() == R.id.home){
            Intent miIntent=new Intent(this, indexActivity.class);
            startActivity(miIntent);
            return true;
        }else if (item.getItemId()==R.id.acercaDe){
            //todo aqui quiero mostrar un activity o un alert o algo con informacion de la
            // aplicacion
        } else if (item.getItemId()==R.id.webCoches) {
            //todo aun no se que opcion poner
        }else if (item.getItemId()==R.id.abEliminarVehiculo){
            //todo aun no se que opcion poner
        }*/

        return false;
    }
}