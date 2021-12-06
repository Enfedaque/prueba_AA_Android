package com.example.actividadaprendizaje1.vehiculos;

import static com.example.actividadaprendizaje1.vehiculos.listadoVehiculosActivity.mostrarVehiculos;

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
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.actividadaprendizaje1.R;
import com.example.actividadaprendizaje1.bbdd.baseDeDatos;
import com.example.actividadaprendizaje1.domain.clientes;
import com.example.actividadaprendizaje1.domain.trabajadores;
import com.example.actividadaprendizaje1.domain.vehiculos;
import com.example.actividadaprendizaje1.facturas.listadoFacturasActivity;
import com.example.actividadaprendizaje1.facturas.nuevaFacturaActivity;
import com.example.actividadaprendizaje1.inicio.indexActivity;
import com.example.actividadaprendizaje1.mapas.talleresActivity;
import com.example.actividadaprendizaje1.util.imagenes;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

/*Aqui se va a mostrar el registrar un nuevo vehiculo para arreglar en el taller*/

public class nuevaEntradaActivity extends AppCompatActivity {

    //Spinner de los clientes y trabajadores
    Spinner miSpinnerClientes;
    Spinner miSpinnerTrabajadores;
    //Adapter para el spinner
    ArrayAdapter<clientes> miAdapter;
    ArrayAdapter<trabajadores> miAdapter2;

    private List<clientes> mostrarSpinnerClientes;
    private List<trabajadores> mostrarSpinnerTrabajadores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_entrada);

        mostrarSpinnerClientes=new ArrayList<>();
        mostrarSpinnerTrabajadores=new ArrayList<>();

        cargarListasSpinner();

        //Instancio el spinner y el adapter
        miSpinnerClientes =findViewById(R.id.spClientes);
        miSpinnerTrabajadores=findViewById(R.id.spTrabajadores);
        miAdapter=new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,
                mostrarSpinnerClientes);
        miSpinnerClientes.setAdapter(miAdapter);
        miAdapter2=new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,
                mostrarSpinnerTrabajadores);
        miSpinnerTrabajadores.setAdapter(miAdapter2);

    }

    private void cargarListasSpinner(){
        baseDeDatos database= Room.databaseBuilder(getApplicationContext(), baseDeDatos.class,
                "Taller").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        mostrarSpinnerClientes.addAll(database.clientesDAO().getAll());
        mostrarSpinnerTrabajadores.addAll(database.trabajadoresDAO().getAll());
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
        }else if (item.getItemId()==R.id.activityFacturas){
            Intent miIntent=new Intent(this, nuevaFacturaActivity.class);
            startActivity(miIntent);
            return true;
        }else if (item.getItemId()==R.id.activityListarFacturas){
            Intent miIntent=new Intent(this, listadoFacturasActivity.class);
            startActivity(miIntent);
            return true;
        }

        return false;
    }

    public void añadirEntrada(View view){

        //recojo los campos segun el id
        ImageView fotoVehiculo=findViewById(R.id.fotoVehiculo);
        EditText etMarca=findViewById(R.id.marca);
        EditText etModelo=findViewById(R.id.modelo);
        EditText etMatricula=findViewById(R.id.matricula);
        Spinner spTrabajadorAlCargo=findViewById(R.id.spTrabajadores);
        EditText etAveria=findViewById(R.id.averia);
        Spinner spCliente=findViewById(R.id.spClientes);


        if(etMarca.getText().toString().equals("")
                || fotoVehiculo==null
                || etModelo.getText().toString().equals("")
                || etMatricula.getText().toString().equals("")
                || spTrabajadorAlCargo==null
                || etAveria.getText().toString().equals("") || spCliente==null){
            Toast.makeText(this, R.string.obligatorioRellenar , Toast.LENGTH_SHORT).show();
            return;
        }

        byte[] imageBytes= imagenes.fromImageViewToByteArray(fotoVehiculo);

        String marca=etMarca.getText().toString();
        String modelo=etModelo.getText().toString();
        String matricula=etMatricula.getText().toString();
        //Guardo como objeto trabajador la opcion del spinner selecionado
        trabajadores trabajador= (trabajadores) spTrabajadorAlCargo.getSelectedItem();
        String averia=etAveria.getText().toString();
        //Guardo como objeto cliente la opcion del spinner selecionado
        clientes miCliente= (clientes) spCliente.getSelectedItem();

        vehiculos miVehiculo=new vehiculos(miCliente.getClienteID(), marca, modelo,
                matricula, trabajador.getTrabajadorID(), averia, imageBytes);

        baseDeDatos database= Room.databaseBuilder(getApplicationContext(), baseDeDatos.class,
                "Taller").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        database.vehiculosDAO().insert(miVehiculo);
        Toast.makeText(this, R.string.vehiculoRegistradoCorrectamente, Toast.LENGTH_LONG).show();

    }

    public void cancelarEntrada(View view){

        EditText etMarca=findViewById(R.id.marca);
        EditText etModelo=findViewById(R.id.modelo);
        EditText etMatricula=findViewById(R.id.matricula);
        EditText etAveria=findViewById(R.id.averia);

        etMarca.setText("");
        etModelo.setText("");
        etMatricula.setText("");
        etAveria.setText("");
    }
}