package com.example.ejercicio1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.LauncherActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //Definimos un primer array que nos mostrara las tareas creadas y por lo tanto pendientes
    private ArrayList<Tareas> tareasPendientes;
    //Definimos un adapter(Sirve para enlazar el arrayList con la ListView)
    private ArrayAdapter<Tareas> tareasPendientesAdapter;

    //Creo un segundo arrayList donde mostrare las tareas marcadas como terminadas
    //private ArrayList<Tareas> tareasTerminadas;
    //private ArrayAdapter<Tareas> tareasTerminadasAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Inicializo los arrayList cuando se cargue la pantalla
        tareasPendientes =new ArrayList<>();
        //tareasTerminadas=new ArrayList<>();

        //Consigo y pinto en la lista el arrayList
        ListView lvListaTareasPendientes=findViewById(R.id.listaTareas);
        //Instancio el adapter
        tareasPendientesAdapter =new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tareasPendientes);
        lvListaTareasPendientes.setAdapter(tareasPendientesAdapter);

        //ListView lvListaTareasTerminadas=findViewById(R.id.listaTareas);
        //tareasTerminadasAdapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tareasTerminadas);
        //lvListaTareasTerminadas.setAdapter(tareasTerminadasAdapter);
    }


    public void añadirTarea(View view){
        EditText etTarea=findViewById(R.id.tarea);
        Tareas miTarea=new Tareas(etTarea.getText().toString());
        //Al darle al boton voy añadiente objetos tarea a mi arrayList
        tareasPendientes.add(miTarea);
        //Notificamos al adapater que hay cambiios en la lista
        tareasPendientesAdapter.notifyDataSetChanged();

        //Limpio la caja de texto puna vez añadida la tarea para que quede lista
        etTarea.setText("");
    }

    //Aqui quiero que al hacer click en un elemento de la listView se borre de una Lista y se añada
    // en la otra
    public void marcarTarea(View view){
        /*tareasPendientes.remove(???);
        tareasTerminadas.add(???);
        tareasPendientesAdapter.notifyDataSetChanged();*/

    }

    //Aqui quiero mostrar la lista de los que quedan pendientes
    public void mostrarPendientes(View view){
        //tareasPendientesAdapter.notifyDataSetChanged();
    }

    //Aqui quiero mostrar la lista de los que estan hechas
    public void mostrarTerminadas(View view){
        //tareasTerminadasAdapter.notifyDataSetChanged();
    }

}