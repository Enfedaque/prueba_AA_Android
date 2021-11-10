package com.example.tallercoches;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.tallercoches.domain.Clientes;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<Clientes> listaClientes;
    private ArrayAdapter<Clientes> listaClientesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaClientes=new ArrayList<>();
        listaClientesAdapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaClientes);

        ListView lvListaClientes=findViewById(R.id.listaClientes);
        lvListaClientes.setAdapter(listaClientesAdapter);

        registerForContextMenu(lvListaClientes);
    }

    @Override
    protected void onResume() {
        super.onResume();

        listaClientesAdapter.notifyDataSetChanged();
    }

    private void eliminar(AdapterView.AdapterContextMenuInfo info){
        listaClientes.remove(info.position);
        listaClientesAdapter.notifyDataSetChanged();
    }

    //Menu actionBar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.acctionbar_main, menu);
        return true;
    }

    //Manu contextual
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.contextual_main, menu);
    }

    //Manu actionBar y menu contextual
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //ActionBar
        if(item.getItemId() == R.id.nuevoVehiculo){
            Intent miIntent=new Intent(this, NewCarActivity.class);
            startActivity(miIntent);
            return true;
        }

        return false;
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item){
        //Contextual
        AdapterView.AdapterContextMenuInfo info= (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        if(item.getItemId() == R.id.eliminarVehiculo){
            eliminar(info);
            return true;
        }
        return false;
    }



}