package com.example.actividadaprendizaje1.vehiculos;

import static com.example.actividadaprendizaje1.usuarios.listadoClientesActivity.mostrarClientes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.room.Room;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
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
import com.example.actividadaprendizaje1.facturas.facturaActivity;
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

    //Para las notificaciones
    private final String CHANNEL_ID="Notificacion";
    private final int NOTIFICATION_ID=01;
    baseDeDatos baseDeDatos;

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
        for (vehiculos miVehiculo : mostrarVehiculos){
            /*Controlo las excepciones que puedan saltar como intrudicr un tipo de dato incorrecto
            en la busqueda
             */
            try {
                if (miVehiculo.getMatricula()
                        .equalsIgnoreCase(texto.getText().toString())){
                    String mostrarResultado=miVehiculo.toString2();
                    //Muestro en un dialogo la informacion completa del usuario
                    AlertDialog.Builder dialogo = new AlertDialog.Builder(this);
                    dialogo.setTitle("Informarción");
                    dialogo.setMessage(mostrarResultado);
                    dialogo.show();
                }else{
                    AlertDialog.Builder dialogo = new AlertDialog.Builder(this);
                    dialogo.setTitle("Informarción");
                    dialogo.setMessage("Vehiculo no encontrado");
                    dialogo.show();
                }
            }catch ( Exception ex){
                AlertDialog.Builder dialogo = new AlertDialog.Builder(this);
                dialogo.setTitle("Informarción");
                dialogo.setMessage("Vehiculo no encontrado");
                dialogo.show();
            }

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
            Intent miIntent=new Intent(this, facturaActivity.class);
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
            //TODO
            AlertDialog.Builder dialogo = new AlertDialog.Builder(this);
            dialogo.setTitle("Atención");
            dialogo.setMessage("¿Estas seguro de eliminar este vehiculo?");
            dialogo.setPositiveButton("Si",new DialogInterface.OnClickListener(){
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
            dialogo.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    dialog.cancel();
                }
            });
            dialogo.show();
            return true;
        }

        //TODO, AQUI FALLA
        if(item.getItemId()==R.id.edicionVehiculos){

            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            LayoutInflater in=getLayoutInflater();
            View v=in.inflate(R.layout.activity_nueva_entrada, null);
            builder.setView(v);
            //recojo los campos segun el id
            ImageView fotoVehiculo=findViewById(R.id.fotoVehiculo);
            EditText etMarca=findViewById(R.id.marca);
            EditText etModelo=findViewById(R.id.modelo);
            EditText etMatricula=findViewById(R.id.matricula);
            Spinner spTrabajadorAlCargo=findViewById(R.id.spTrabajadores);
            EditText etAveria=findViewById(R.id.averia);
            Spinner spCliente=findViewById(R.id.spClientes);
            Button editar=findViewById(R.id.button13);

            editar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    vehiculos miVehiculo=mostrarVehiculos.get(info.position);
                    miVehiculo.setMarca(etMarca.getText().toString());
                    miVehiculo.setModelo(etModelo.getText().toString());
                    miVehiculo.setMatricula(etMatricula.getText().toString());
                    clientes cliente=(clientes) spCliente.getSelectedItem();
                    miVehiculo.setClienteID(cliente.getClienteID());
                    trabajadores trabajadores=(trabajadores) spTrabajadorAlCargo.getSelectedItem();
                    miVehiculo.setTrabajadorID(trabajadores.getTrabajadorID());
                    miVehiculo.setAveria(etAveria.getText().toString());

                    baseDeDatos database= Room.databaseBuilder(getApplicationContext(), baseDeDatos.class,
                            "Taller").allowMainThreadQueries().fallbackToDestructiveMigration().build();
                    database.vehiculosDAO().editar(miVehiculo);
                    cargarBBDDenLista();
                }
            });

        }

        return false;
    }

    //Metodo para eliminar clientes de mi lista
    private void eliminar(AdapterView.AdapterContextMenuInfo info){
        vehiculos miVehiculo=mostrarVehiculos.get(info.position);
        baseDeDatos database= Room.databaseBuilder(getApplicationContext(), baseDeDatos.class,
                "Taller").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        database.vehiculosDAO().eliminar(miVehiculo);
    }


    //Metodo para mandar notificaciones
    private void mandarNotificacion(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence nombre= "Notificacion";
            String descripcion= "Notificacion simple";
            int importancia= NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel notificationChannel=new NotificationChannel(CHANNEL_ID, nombre,importancia);
            notificationChannel.setDescription(descripcion);

            NotificationManager notificationManager=getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }
}