package com.example.actividadaprendizaje1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.actividadaprendizaje1.domain.Vehiculos;

/*Aqui se va a mostrar el registrar un nuevo vehiculo para arreglar en el taller*/

public class nuevaEntradaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_entrada);
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
        }else if (item.getItemId()==R.id.opcion1){
            //todo aun no se que opcion poner
        }

        return false;
    }

    public void añadirEntrada(View view){

        EditText etIdCliente=findViewById(R.id.idCliente);
        EditText etMarca=findViewById(R.id.marca);
        EditText etModelo=findViewById(R.id.modelo);
        EditText etMatricula=findViewById(R.id.matricula);
        EditText etTrabajadorAlCargo=findViewById(R.id.trabajadorAlCargo);
        EditText etAveria=findViewById(R.id.averia);

        CheckBox cbUrgente=findViewById(R.id.urgente);

        if(etIdCliente.getText().toString().equals("") || etMarca.getText().toString().equals("")
                || etModelo.getText().toString().equals("")
                || etMatricula.getText().toString().equals("")
                || etTrabajadorAlCargo.getText().toString().equals("")
                || etAveria.getText().toString().equals("")){
            Toast.makeText(this, "Es obligaorio rellenar todos los campos" , Toast.LENGTH_LONG).show();
        }

        String idCliente=etIdCliente.getText().toString();
        String marca=etMarca.getText().toString();
        String modelo=etModelo.getText().toString();
        String matricula=etMatricula.getText().toString();
        String trabajador=etTrabajadorAlCargo.getText().toString();
        String averia=etAveria.getText().toString();

        Vehiculos miVehiculo=new Vehiculos(idCliente, marca, modelo, matricula, trabajador, averia);
        indexActivity.listadoVehiculos.add(miVehiculo);

        //Aqui hago que si esta marcado como urgente salga el primero en la lista
        if (cbUrgente.isChecked()){
            indexActivity.listadoVehiculos.add(0, miVehiculo);
            Toast.makeText(this, "Cliente registrado correctamente", Toast.LENGTH_LONG).show();
        }else{
            indexActivity.listadoVehiculos.add(miVehiculo);
            Toast.makeText(this, "Cliente registrado correctamente", Toast.LENGTH_LONG).show();
        }
    }

    public void cancelarEntrada(View view){
        EditText etIdCliente=findViewById(R.id.idCliente);
        EditText etMarca=findViewById(R.id.marca);
        EditText etModelo=findViewById(R.id.modelo);
        EditText etMatricula=findViewById(R.id.matricula);
        EditText etTrabajadorAlCargo=findViewById(R.id.trabajadorAlCargo);
        EditText etAveria=findViewById(R.id.averia);
        CheckBox cbUrgente=findViewById(R.id.urgente);

        etIdCliente.setText("");
        etMarca.setText("");
        etModelo.setText("");
        etMatricula.setText("");
        etTrabajadorAlCargo.setText("");
        etAveria.setText("");
        cbUrgente.setChecked(false);
    }
}