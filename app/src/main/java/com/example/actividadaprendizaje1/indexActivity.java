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
import com.example.actividadaprendizaje1.domain.Facturas;
import com.example.actividadaprendizaje1.domain.Trabajadores;
import com.example.actividadaprendizaje1.domain.Vehiculos;

import java.util.ArrayList;
import java.util.List;

public class indexActivity extends AppCompatActivity{

    //Los ARRAyList los voy a usar para mostrar la info que me da la BBDD a traves del ListView,
    // ya que para guardar la informacion uso la BBDD
    public static ArrayList<Clientes> listadoClientes;
    public static ArrayList<Trabajadores> listadoTrabajadores;
    public static List<Vehiculos> listadoVehiculos;
    public static ArrayList<String> departamentos;
    public static ArrayList<String> puestos;
    //Lista de facturas
    public static ArrayList<Facturas> listadoFacturas;


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
        listadoFacturas=new ArrayList<>();



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

    //Metodo que me permite entrar a la zona usuarios
    public void accesoUsuarios(View view){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        LayoutInflater in=getLayoutInflater();
        View v=in.inflate(R.layout.acceso_usuarios_dialog, null);
        builder.setView(v);
        Button acceder=v.findViewById(R.id.okPasswordUsuarios);
        Button cancelar=v.findViewById(R.id.cancelPasswordUsuarios);
        EditText contraseña=v.findViewById(R.id.contraseña);
        acceder.setOnClickListener(v1 -> {
            //La contraseña es "usuarios"
            if (contraseña.getText().toString().equals("usuarios")){
                Intent miIntent=new Intent(this, zonaUsuariosActivity.class);
                startActivity(miIntent);
            }else{
                Toast.makeText(this, "Contraseña incorrecta " , Toast.LENGTH_SHORT).show();
            }
        });

        cancelar.setOnClickListener(v12 -> {
            contraseña.setText("");
        });

        AlertDialog alertDialog= builder.create();
        alertDialog.show();
    }



    //Boton para acceder a la zona de vehiculos, antes indtroduciendo la contraseña "vehiculos"
    public void accesoVehiculos(View view){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        LayoutInflater in=getLayoutInflater();
        View v=in.inflate(R.layout.acceso_usuarios_dialog, null);
        builder.setView(v);
        Button acceder=v.findViewById(R.id.okPasswordUsuarios);
        Button cancelar=v.findViewById(R.id.cancelPasswordUsuarios);
        EditText contraseña=v.findViewById(R.id.contraseña);
        acceder.setOnClickListener(v1 -> {
            //La contraseña es "vehiculos"
            if (contraseña.getText().toString().equals("vehiculos")){
                Intent miIntent=new Intent(this, zonaVehiculosActivity.class);
                startActivity(miIntent);
            }else{
                Toast.makeText(this, "Contraseña incorrecta " , Toast.LENGTH_SHORT).show();
            }
        });

        cancelar.setOnClickListener(v12 -> {
            contraseña.setText("");
        });

        AlertDialog alertDialog= builder.create();
        alertDialog.show();
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
}