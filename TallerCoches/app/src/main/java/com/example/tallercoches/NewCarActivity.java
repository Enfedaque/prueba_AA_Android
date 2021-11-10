package com.example.tallercoches;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tallercoches.domain.Clientes;

public class NewCarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_car);
    }

    public void registrar(View view){
        EditText etNombre=findViewById(R.id.nombre);
        EditText etApellidos=findViewById(R.id.apellidos);
        EditText etTelefono=findViewById(R.id.telefono);
        EditText etEmail=findViewById(R.id.email);
        EditText etMarcaVehiculo=findViewById(R.id.marcaVehiculo);
        EditText etMatricula=findViewById(R.id.matricula);

        if(etNombre.getText().toString().equals("") || etApellidos.getText().toString().equals("") ||
                etTelefono.getText().toString().equals("") || etEmail.getText().toString().equals("")
                || etMarcaVehiculo.getText().toString().equals("") ||
                etMatricula.getText().toString().equals("")){
            Toast.makeText(this, "Debes rellenar todos los campos" , Toast.LENGTH_LONG).show();
        }

        String nombre=etNombre.getText().toString();
        String apellidos=etApellidos.getText().toString();
        String telefono=etTelefono.getText().toString();
        String email=etEmail.getText().toString();
        String marcaVehiculo=etMarcaVehiculo.getText().toString();
        String matricula=etMatricula.getText().toString();

        Clientes miCliente=new Clientes(nombre,apellidos, telefono, email, marcaVehiculo, matricula);

        MainActivity.listaClientes.add(miCliente);
        Toast.makeText(this, "Entrada de vehiculo registrada", Toast.LENGTH_LONG).show();

        etNombre.setText("");
        etApellidos.setText("");
        etTelefono.setText("");
        etEmail.setText("");
        etMarcaVehiculo.setText("");
        etMarcaVehiculo.setText("");
    }
}