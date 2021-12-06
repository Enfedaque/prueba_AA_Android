package com.example.actividadaprendizaje1.usuarios;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.actividadaprendizaje1.R;
import com.example.actividadaprendizaje1.bbdd.baseDeDatos;
import com.example.actividadaprendizaje1.domain.trabajadores;
import com.example.actividadaprendizaje1.inicio.indexActivity;
import com.example.actividadaprendizaje1.mapas.talleresActivity;

/*Aqui se podra registar un nuevo trabajador que haya sido contratado*/

public class nuevoTrabajadorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_trabajador);
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
        }else if (item.getItemId()==R.id.buscarTalleres2){
            Intent miIntent=new Intent(this, talleresActivity.class);
            startActivity(miIntent);
            return true;
        }

        return false;
    }

    //Boton para registar a un nuevo trabajador
    public void añadirTrabajador(View view){
        EditText etNombre=findViewById(R.id.nombreTrabajador);
        EditText etApellido=findViewById(R.id.apellidoTrabajador);
        EditText etDni=findViewById(R.id.dniTrabajador);
        EditText etTelefono=findViewById(R.id.telefonoTrabajador);
        EditText etEmail=findViewById(R.id.emailTrabajador);
        EditText etDepartamento=findViewById(R.id.departamentoTrabajador);
        EditText etPuesto=findViewById(R.id.puestoTrabajador);

        if(etNombre.getText().toString().equals("") || etApellido.getText().toString().equals("") ||
                etDni.getText().toString().equals("") || etTelefono.getText().toString().equals("") ||
                etEmail.getText().toString().equals("")
                || etDepartamento.getText().toString().equals("")
                || etPuesto.getText().toString().equals("")){
            Toast.makeText(this, R.string.obligatorioRellenar , Toast.LENGTH_LONG).show();
        }

        String nombre=etNombre.getText().toString();
        String apellido=etApellido.getText().toString();
        String dni=etDni.getText().toString();
        String telefono=etTelefono.getText().toString();
        String email=etEmail.getText().toString();
        String departamento=etDepartamento.getText().toString();
        String puesto=etPuesto.getText().toString();

        trabajadores miTrabajador=new trabajadores(nombre, apellido, dni, telefono, email, puesto,
                departamento);

        baseDeDatos database= Room.databaseBuilder(getApplicationContext(), baseDeDatos.class,
                "Taller").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        database.trabajadoresDAO().insert(miTrabajador);
        
        Toast.makeText(this, R.string.trabajadorRegistradoCorrectamente, Toast.LENGTH_LONG).show();

        etNombre.setText("");
        etApellido.setText("");
        etDni.setText("");
        etTelefono.setText("");
        etEmail.setText("");
        etDepartamento.setText("");
        etPuesto.setText("");
    }

    public void cancelarTrabajador(View view){
        EditText etNombre=findViewById(R.id.nombreTrabajador);
        EditText etApellido=findViewById(R.id.apellidoTrabajador);
        EditText etDni=findViewById(R.id.dniTrabajador);
        EditText etTelefono=findViewById(R.id.telefonoTrabajador);
        EditText etEmail=findViewById(R.id.emailTrabajador);
        EditText etDepartamento=findViewById(R.id.departamentoTrabajador);
        EditText etPuesto=findViewById(R.id.puestoTrabajador);

        etNombre.setText("");
        etApellido.setText("");
        etDni.setText("");
        etTelefono.setText("");
        etEmail.setText("");
        etDepartamento.setText("");
        etPuesto.setText("");
    }
}