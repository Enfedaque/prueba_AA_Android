package com.example.actividadaprendizaje1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.actividadaprendizaje1.domain.vehiculos;

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
        }else if (item.getItemId()==R.id.abEliminarVehiculo){
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            LayoutInflater in=getLayoutInflater();
            View v=in.inflate(R.layout.borrar_vehiculos, null);
            builder.setView(v);
            Button eliminar=v.findViewById(R.id.btEliminarVehiculos);
            EditText texto=v.findViewById(R.id.etMatriculaEliminar);
            eliminar.setOnClickListener(v1 -> {

                for (vehiculos miVehiculo : indexActivity.listadoVehiculos){

                    try {

                        if (miVehiculo.getMatricula().equals(texto.getText().toString())){
                            //Muestro en un de si esta seguro de querer borrarlo
                            AlertDialog.Builder dialogoBorrar = new AlertDialog.Builder(this);
                            dialogoBorrar.setTitle("Atención");
                            dialogoBorrar.setMessage("¿Esta seguro de querer eliminar el vehiculo " +
                                    "con la matricula " + texto + " ?");
                            dialogoBorrar.setPositiveButton("Aceptar",((dialog, which) -> {
                                        indexActivity.listadoVehiculos.remove(miVehiculo);
                                        Toast.makeText(this, "Vehiculo eliminado" ,
                                                Toast.LENGTH_SHORT).show();
                                    }));
                            dialogoBorrar.setNegativeButton("Cancelar",((dialog, which) ->
                                    dialog.cancel()
                            ));
                            dialogoBorrar.show();
                        }else{
                            Toast.makeText(this, "Vehiculo no encontrado" ,
                                    Toast.LENGTH_SHORT).show();
                        }
                    }catch ( Exception ex){
                        Toast.makeText(this, "Vehiculo no encontrado" ,
                                Toast.LENGTH_SHORT).show();
                    }
                }
            });
            AlertDialog alertDialog= builder.create();
            alertDialog.show();
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