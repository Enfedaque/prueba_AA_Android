package com.example.actividadaprendizaje1.vehiculos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Switch;
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

import java.util.ArrayList;
import java.util.List;

public class listadoVehiculosActivity extends AppCompatActivity {

    public static List<vehiculos> mostrarVehiculos;
    //Adapter para la lista y la BBDD
    private ArrayAdapter<vehiculos> mostrarVehiculosAdapter;
    Button btBuscar;
    EditText texto;
    Switch miSwitch;

    baseDeDatos baseDeDatos;

    static final int REQUEST_IMAGE_CAPTURE = 1;
    ImageView etFoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_vehiculos);

        mostrarVehiculos=new ArrayList<>();
        baseDeDatos database= Room.databaseBuilder(getApplicationContext(), baseDeDatos.class,
                "Taller").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        mostrarVehiculos.addAll(database.vehiculosDAO().getAll());

        btBuscar=findViewById(R.id.btBuscarVehiculo);
        texto=findViewById(R.id.matriculaBuscadorVehiculo);
        miSwitch=findViewById(R.id.swBuscarVehiculo);

        mostrarVehiculosAdapter =new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                mostrarVehiculos);

        ListView lvListadoVehiculos=findViewById(R.id.listadoVehiculos);
        lvListadoVehiculos.setAdapter(mostrarVehiculosAdapter);

        registerForContextMenu(lvListadoVehiculos);

    }

    @Override
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
        mostrarVehiculos.clear();
        baseDeDatos database= Room.databaseBuilder(getApplicationContext(), baseDeDatos.class,
                "Taller").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        mostrarVehiculos.addAll(database.vehiculosDAO().getAll());

        mostrarVehiculosAdapter.notifyDataSetChanged();
    }

    //Metodo para el switch que muestra el buscador
    public void buscadorVehiculosVisible(View view){
        if (view.getId()==R.id.swBuscarVehiculo){
            if (miSwitch.isChecked()){
                texto.setVisibility(View.VISIBLE);
                btBuscar.setVisibility(View.VISIBLE);
            }else{
                texto.setVisibility(View.GONE);
                btBuscar.setVisibility(View.GONE);
            }
        }

    }

    //Metodo del boton que busca al cliente por apellido en la lista y lo muestra
    public void resultadoBusquedaVehiculo(View view){
        String mostrarResultado="";
        for (vehiculos miVehiculo : mostrarVehiculos){
            /*Controlo las excepciones que puedan saltar como intrudicr un tipo de dato incorrecto
            en la busqueda
             */
                if (miVehiculo.getMatricula()
                        .equalsIgnoreCase(texto.getText().toString())){
                    mostrarResultado=miVehiculo.toString2();
                    //Muestro en un dialogo la informacion completa del usuario
                    AlertDialog.Builder dialogo = new AlertDialog.Builder(this);
                    dialogo.setTitle(R.string.info);
                    dialogo.setMessage(mostrarResultado);
                    dialogo.show();
                }
        }
        if (mostrarResultado.equalsIgnoreCase("")){
            AlertDialog.Builder dialogo = new AlertDialog.Builder(this);
            dialogo.setTitle(R.string.info);
            dialogo.setMessage(R.string.vehiculoNoEncontrado);
            dialogo.show();
        }
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

    //MENU CONTEXTUAL
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.contextual_vehiculos, menu);
    }
    //MENU CONTEXTUAL
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item){
        //Contextual
        AdapterView.AdapterContextMenuInfo info= (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        //Opcion de mostrar la informacion
        if (item.getItemId()==R.id.terminar){
            AlertDialog.Builder dialogo = new AlertDialog.Builder(this);
            dialogo.setTitle(R.string.atencion);
            dialogo.setMessage(R.string.preguntaEliminarVehiculo);
            dialogo.setPositiveButton(R.string.si,new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    vehiculos miVehiculo=mostrarVehiculos.get(info.position);
                    try {
                        eliminar(info);
                        cargarBBDDenLista();

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });
            dialogo.setNegativeButton(R.string.cancelar2, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    dialog.cancel();
                }
            });
            dialogo.show();
            return true;
        }

        //TODO, REVISAR PORQUE SIGUE FALLANDO
        if(item.getItemId()==R.id.edicionVehiculos){

            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            LayoutInflater in=getLayoutInflater();
            View v=in.inflate(R.layout.editar_vehiculos, null);
            builder.setView(v);

            Button foto=v.findViewById(R.id.editHacerFoto);
            Button editok=v.findViewById(R.id.editOk);
            etFoto=v.findViewById(R.id.editFoto);
            EditText etMarca=v.findViewById(R.id.editMarca);
            EditText etModelo=v.findViewById(R.id.editModelo);
            EditText etMatricula=v.findViewById(R.id.editMatricula);
            EditText etAveria=v.findViewById(R.id.editAveria);

            foto.setOnClickListener(v12 -> {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            });

            editok.setOnClickListener(v1 -> {
                byte[] imageBytes= imagenes.fromImageViewToByteArray(etFoto);

                vehiculos miVehiculo=mostrarVehiculos.get(info.position);
                miVehiculo.setFotoVehiculo(imageBytes);
                miVehiculo.setMarca(etMarca.getText().toString());
                miVehiculo.setModelo(etModelo.getText().toString());
                miVehiculo.setMatricula(etMatricula.getText().toString());
                miVehiculo.setAveria(etAveria.getText().toString());

                baseDeDatos database= Room.databaseBuilder(getApplicationContext(), baseDeDatos.class,
                        "Taller").allowMainThreadQueries().fallbackToDestructiveMigration().build();
                database.vehiculosDAO().editar(miVehiculo);

                cargarBBDDenLista();
            });

            AlertDialog alertDialog= builder.create();
            alertDialog.show();

        }

        if (item.getItemId()==R.id.infoVehiculoContextual){
            vehiculos miVehiculo=mostrarVehiculos.get(info.position);
            AlertDialog.Builder dialogo = new AlertDialog.Builder(this);
            dialogo.setTitle(R.string.info);
            dialogo.setMessage(miVehiculo.toString2());
            dialogo.show();
            return true;
        }

        return false;
    }


    //Metodo para eliminar clientes de mi lista
    private void eliminar(AdapterView.AdapterContextMenuInfo info) throws Exception{
        try {
            vehiculos miVehiculo=mostrarVehiculos.get(info.position);
            baseDeDatos database= Room.databaseBuilder(getApplicationContext(), baseDeDatos.class,
                    "Taller").allowMainThreadQueries().fallbackToDestructiveMigration().build();
            database.vehiculosDAO().eliminar(miVehiculo);
        }catch (Exception exc){
            AlertDialog.Builder dialogo = new AlertDialog.Builder(this);
            dialogo.setTitle(R.string.info);
            dialogo.setMessage("Hubo un problema con la base de datos");
            dialogo.show();
        }
        
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            etFoto.setImageBitmap(imageBitmap);
        }
    }


}