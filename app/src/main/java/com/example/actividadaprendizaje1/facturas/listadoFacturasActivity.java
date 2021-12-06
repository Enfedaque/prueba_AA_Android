package com.example.actividadaprendizaje1.facturas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.ContextMenu;

import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.actividadaprendizaje1.R;
import com.example.actividadaprendizaje1.bbdd.baseDeDatos;
import com.example.actividadaprendizaje1.domain.clientes;
import com.example.actividadaprendizaje1.domain.facturas;

import java.util.ArrayList;
import java.util.List;

public class listadoFacturasActivity extends AppCompatActivity {

    private List<facturas>  mostrarFacturas;
    private ArrayAdapter<facturas> mostrarFacturasAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_facturas);

        mostrarFacturas=new ArrayList<>();
        ListView lvListadoFacturas=findViewById(R.id.lvFacturas);

        mostrarFacturasAdapter =new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                mostrarFacturas);
        lvListadoFacturas.setAdapter(mostrarFacturasAdapter);

        registerForContextMenu(lvListadoFacturas);
    }

    protected void onResume(){
        super.onResume();

        cargarBBDDenLista();
    }

    @Override
    protected void onStart() {
        super.onStart();
        cargarBBDDenLista();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        cargarBBDDenLista();
    }

    //Metodo para recargar y refrescar la lista
    private void cargarBBDDenLista(){
        mostrarFacturas.clear();
        baseDeDatos database= Room.databaseBuilder(getApplicationContext(), baseDeDatos.class,
                "Taller").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        mostrarFacturas.addAll(database.facturasDAO().getAll());

        mostrarFacturasAdapter.notifyDataSetChanged();
    }

    //MENU CONTEXTUAL
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.contextual_facturas, menu);
    }
    //MENU CONTEXTUAL
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item){
        //Contextual
        AdapterView.AdapterContextMenuInfo info= (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        //Opcion de mostrar la informacion
        if (item.getItemId()==R.id.infoFacturas){
            facturas miFactura=mostrarFacturas.get(info.position);
            AlertDialog.Builder dialogo = new AlertDialog.Builder(this);
            dialogo.setTitle(R.string.info);
            dialogo.setMessage(miFactura.toString());
            dialogo.show();
            return true;
        }
        //Opcion de eliminar de la lista
        if(item.getItemId()==R.id.eliminarFacturas){
            AlertDialog.Builder dialogoBorrar = new AlertDialog.Builder(this);
            dialogoBorrar.setTitle(R.string.importante);
            dialogoBorrar.setMessage("Esta seguro de eliminar esta factura?");
            //Que hace si aprieta el boton de confirmar
            dialogoBorrar.setPositiveButton(R.string.confirmar, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogo1, int id) {
                    eliminar(info);
                    cargarBBDDenLista();
                }
            });
            //Que hace si aprieta el boton de cancelar
            dialogoBorrar.setNegativeButton(R.string.cancelar2, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogo1, int id) {

                    dialogo1.cancel();
                }
            });
            dialogoBorrar.show();
            return true;
        }

        return false;
    }

    //Metodo para eliminar clientes de mi lista
    private void eliminar(AdapterView.AdapterContextMenuInfo info){
        facturas miFactura=mostrarFacturas.get(info.position);
        baseDeDatos database= Room.databaseBuilder(getApplicationContext(), baseDeDatos.class,
                "Taller").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        database.facturasDAO().eliminar(miFactura);
    }
}