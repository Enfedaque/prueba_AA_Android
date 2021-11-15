package com.example.actividadaprendizaje1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.actividadaprendizaje1.domain.Clientes;
import com.example.actividadaprendizaje1.domain.Trabajadores;
import com.example.actividadaprendizaje1.domain.Vehiculos;

import java.util.ArrayList;
import java.util.List;

public class indexActivity extends AppCompatActivity {

    //Los ARRAyList los voy a usar para mostrar la info que me da la BBDD a traves del ListView,
    // ya que para guardar la informacion uso la BBDD
    public static ArrayList<Clientes> listadoClientes;
    public static ArrayList<Trabajadores> listadoTrabajadores;
    public static List<Vehiculos> listadoVehiculos;
    public static ArrayList<String> departamentos;
    public static ArrayList<String> puestos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        listadoClientes=new ArrayList<>();
        listadoTrabajadores=new ArrayList<>();
        listadoVehiculos=new ArrayList<>();
        departamentos=new ArrayList<>();
        departamentos();
        puestos=new ArrayList<>();
        puestos();

    }

    protected void onResume(){
        super.onResume();

    }

    //TODO , hacer los spinner en en nuevoTrabajadores para mostrar los departamentos y los puestos
    //Para rellena de forma mas bonita el departamento en vez de tirar el codigo en el create
    private void  departamentos(){
        departamentos.add("Ventas");
        departamentos.add("Administración");
        departamentos.add("Taller");
        departamentos.add("Dirección");
        departamentos.add("Atención al cliente");
    }

    //Rellena los puestos
    private void  puestos(){
        puestos.add("Peón");
        puestos.add("Oficial");
        puestos.add("Gerente");
        puestos.add("Encargado");
        puestos.add("Limpiador");

    }

    //Menu actionBar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar_index, menu);
        return true;
    }
    //Manu actionBar y menu lateral opciones
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {


        //todo por hacer las opciones

        return false;
    }

    //Boton para acceder a la zona de usuarios, antes introduceindo la contraseña "usuarios"
    public void zonaUsuarios(View view){
        Intent miIntent=new Intent(this, zonaUsuariosActivity.class);
        startActivity(miIntent);
    }

    //Boton para acceder a la zona de vehiculos, antes indtroduciendo la contraseña "vehiculos"
    public void zonaVehiculos(View view){
        Intent miIntent=new Intent(this, zonaVehiculosActivity.class);
        startActivity(miIntent);
    }

    //Boton para registrar un nuevo vehiculo averiado directamente
    public void nuevaEntrada(View view){
        Intent miIntent=new Intent(this, nuevaEntradaActivity.class);
        startActivity(miIntent);
    }

    //TODO hacer que se muestre el layout qye cree a modo de dialogo con contraseña
    //Metodo para mostrar el dialogo
    public void mostrarDialogo(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View v = inflater.inflate(R.layout.acceso_usuarios_dialog, null);
        builder.setView(v);
        AlertDialog dialogo= builder.create();
        dialogo.show();

        Button btAcceder=findViewById(R.id.okPasswordUsuarios);
        Button btCancelar=findViewById(R.id.cancelPasswordUsuarios);
        EditText etContraseña=findViewById(R.id.contraseña);

        btAcceder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent miIntent=new Intent(getBaseContext(), zonaUsuariosActivity.class);
                startActivity(miIntent);
                dialogo.dismiss();
            }
        });
        btCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Contraseña incorrecta", Toast.LENGTH_LONG).show();
                dialogo.dismiss();
            }
        });
    }


}