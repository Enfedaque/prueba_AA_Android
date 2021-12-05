package com.example.actividadaprendizaje1.vehiculos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.actividadaprendizaje1.R;
import com.example.actividadaprendizaje1.bbdd.baseDeDatos;
import com.example.actividadaprendizaje1.domain.clientes;
import com.example.actividadaprendizaje1.domain.trabajadores;
import com.example.actividadaprendizaje1.domain.vehiculos;
import com.example.actividadaprendizaje1.inicio.indexActivity;
import com.example.actividadaprendizaje1.mapas.talleresActivity;

/*Aqui se va a mostrar el registrar un nuevo vehiculo para arreglar en el taller*/

public class nuevaEntradaActivity extends AppCompatActivity {

    //Spinner de los clientes y trabajadores
    Spinner miSpinnerClientes;
    Spinner miSpinnerTrabajadores;
    //Adapter para el spinner
    ArrayAdapter<clientes> miAdapter;
    ArrayAdapter<trabajadores> miAdapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_entrada);

        //Instancio el spinner y el adapter
        miSpinnerClientes =findViewById(R.id.spClientes);
        miSpinnerTrabajadores=findViewById(R.id.spTrabajadores);
        miAdapter=new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,
                indexActivity.listadoClientes);
        miSpinnerClientes.setAdapter(miAdapter);
        miAdapter2=new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,
                indexActivity.listadoTrabajadores);
        miSpinnerTrabajadores.setAdapter(miAdapter2);

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
        }

        return false;
    }

    public void añadirEntrada(View view){

        //todo metodo para recoger la imagen y guardarla en la base da datos
        //recojo los campos segun el id
        EditText etMarca=findViewById(R.id.marca);
        EditText etModelo=findViewById(R.id.modelo);
        EditText etMatricula=findViewById(R.id.matricula);
        Spinner spTrabajadorAlCargo=findViewById(R.id.spTrabajadores);
        EditText etAveria=findViewById(R.id.averia);
        Spinner spCliente=findViewById(R.id.spClientes);

        CheckBox cbUrgente=findViewById(R.id.urgente);

        if(etMarca.getText().toString().equals("")
                || etModelo.getText().toString().equals("")
                || etMatricula.getText().toString().equals("")
                || spTrabajadorAlCargo==null
                || etAveria.getText().toString().equals("") || spCliente==null){
            Toast.makeText(this, "Es obligaorio rellenar todos los campos" , Toast.LENGTH_SHORT).show();
            return;
        }

        String marca=etMarca.getText().toString();
        String modelo=etModelo.getText().toString();
        String matricula=etMatricula.getText().toString();
        //Guardo como objeto trabajador la opcion del spinner selecionado
        trabajadores trabajador= (trabajadores) spTrabajadorAlCargo.getSelectedItem();
        String averia=etAveria.getText().toString();
        //Guardo como objeto cliente la opcion del spinner selecionado
        clientes miCliente= (clientes) spCliente.getSelectedItem();

        vehiculos miVehiculo=new vehiculos(miCliente.getClienteID(), marca, modelo,
                matricula, trabajador.getTrabajadorID(), averia);

        baseDeDatos database= Room.databaseBuilder(getApplicationContext(), baseDeDatos.class,
                "Taller").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        database.vehiculosDAO().insert(miVehiculo);

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

        EditText etMarca=findViewById(R.id.marca);
        EditText etModelo=findViewById(R.id.modelo);
        EditText etMatricula=findViewById(R.id.matricula);
        EditText etAveria=findViewById(R.id.averia);
        CheckBox cbUrgente=findViewById(R.id.urgente);

        etMarca.setText("");
        etModelo.setText("");
        etMatricula.setText("");
        etAveria.setText("");
        cbUrgente.setChecked(false);
    }
}