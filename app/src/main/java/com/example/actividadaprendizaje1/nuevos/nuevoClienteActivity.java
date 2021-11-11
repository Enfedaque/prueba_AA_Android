package com.example.actividadaprendizaje1.nuevos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.actividadaprendizaje1.R;
import com.example.actividadaprendizaje1.domain.Clientes;
import com.example.actividadaprendizaje1.indexActivity;

public class nuevoClienteActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_cliente);

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
        }else if (item.getItemId()==R.id.buscadorUsuarios){
            //todo
        }else if (item.getItemId()==R.id.acercaDe){
            //todo aqui quiero mostrar un activity o un alert o algo con informacion de la
            // aplicacion
            return true;
        } else if (item.getItemId()==R.id.navegador) {
            //todo aun no se que opcion poner
            return true;
        }else if (item.getItemId()==R.id.opcion2){
            //todo aun no se que opcion poner
            return true;
        }

        return false;
    }

    //Boton para registar a un nuevo cliente
    public void añadirCliente(View view){
        EditText etNombre=findViewById(R.id.nombreCliente);
        EditText etApellido=findViewById(R.id.apellidoCliente);
        EditText etDNI=findViewById(R.id.dniCliente);
        EditText etTelefono=findViewById(R.id.telefonoCliente);
        EditText etEmail=findViewById(R.id.emailCliente);
        EditText etIdCliente=findViewById(R.id.idCliente);

        if(etNombre.getText().toString().equals("") || etApellido.getText().toString().equals("") ||
                etDNI.getText().toString().equals("") || etTelefono.getText().toString().equals("") ||
                etEmail.getText().toString().equals("") || etIdCliente.getText().toString().equals("")){
            Toast.makeText(this, "Es obligaorio rellenar todos los campos" , Toast.LENGTH_LONG).show();
        }

        String nombre=etNombre.getText().toString();
        String apellido=etApellido.getText().toString();
        String dni=etDNI.getText().toString();
        String telefono=etTelefono.getText().toString();
        String email=etEmail.getText().toString();
        String idCliente=etIdCliente.getText().toString();

        Clientes miCliente=new Clientes(nombre,apellido, dni, telefono, email, idCliente);

        indexActivity.listadoClientes.add(miCliente);
        Toast.makeText(this, "Cliente registrado correctamente", Toast.LENGTH_LONG).show();

        //Vacio los campos para poder seguir introduciendo
        etNombre.setText("");
        etApellido.setText("");
        etDNI.setText("");
        etTelefono.setText("");
        etEmail.setText("");
        etIdCliente.setText("");
    }

    public void cancelarCliente(View view){
        EditText etNombre=findViewById(R.id.nombreCliente);
        EditText etApellido=findViewById(R.id.apellidoCliente);
        EditText etDNI=findViewById(R.id.dniCliente);
        EditText etTelefono=findViewById(R.id.telefonoCliente);
        EditText etEmail=findViewById(R.id.emailCliente);
        EditText etIdCliente=findViewById(R.id.idCliente);

        etNombre.setText("");
        etApellido.setText("");
        etDNI.setText("");
        etTelefono.setText("");
        etEmail.setText("");
        etIdCliente.setText("");

    }

}