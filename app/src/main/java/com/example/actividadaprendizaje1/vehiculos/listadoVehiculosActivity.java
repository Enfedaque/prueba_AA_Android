package com.example.actividadaprendizaje1.vehiculos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;

import com.example.actividadaprendizaje1.R;
import com.example.actividadaprendizaje1.bbdd.baseDeDatos;
import com.example.actividadaprendizaje1.domain.clientes;
import com.example.actividadaprendizaje1.domain.vehiculos;
import com.example.actividadaprendizaje1.inicio.indexActivity;
import com.example.actividadaprendizaje1.mapas.talleresActivity;

import java.util.List;

public class listadoVehiculosActivity extends AppCompatActivity {

    private ArrayAdapter<vehiculos> listadoVehiculosAdapter;
    Button btBuscar;
    EditText texto;
    Switch miSwitch;

    //Para las notificaciones
    private final String CHANNEL_ID="Notificacion";
    private final int NOTIFICATION_ID=01;
    baseDeDatos miClientesBBDD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_vehiculos);

        btBuscar=findViewById(R.id.btBuscarVehiculo);
        texto=findViewById(R.id.matriculaBuscadorVehiculo);
        miSwitch=findViewById(R.id.swBuscarVehiculo);

        listadoVehiculosAdapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                indexActivity.listadoVehiculos);

        ListView lvListadoVehiculos=findViewById(R.id.listadoVehiculos);
        lvListadoVehiculos.setAdapter(listadoVehiculosAdapter);

        registerForContextMenu(lvListadoVehiculos);

    }

    @Override
    protected void onResume(){
        super.onResume();

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
        for (vehiculos miVehiculo : indexActivity.listadoVehiculos){
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
                    vehiculos miVehiculo=indexActivity.listadoVehiculos.get(info.position);
                    try {
                        //todo falla
                        miClientesBBDD.vehiculosDAO().eliminar(miVehiculo);

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

        //Mando notificacion al cliente
        if (item.getItemId()==R.id.notificarAlCliente){
            vehiculos miVehiculo=indexActivity.listadoVehiculos.get(info.position);
            String matricula=miVehiculo.getMatricula();
            long identificador=miVehiculo.getClienteID();
            clientes miCliente=miClientesBBDD.clientesDAO().findById(identificador);
            String email=miCliente.getEmail();

            AlertDialog.Builder dialogo = new AlertDialog.Builder(this);
            dialogo.setTitle("Petición notificar información");
            dialogo.setMessage("Seleccione el medio por el que quiera notificarlo:");
            dialogo.setPositiveButton("Notificacion push", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    mandarNotificacion();

                    NotificationCompat.Builder builder=new NotificationCompat.Builder(getApplicationContext(),
                            CHANNEL_ID)
                            .setContentTitle("Garage Admin")
                            .setContentText("Ya esta disponible su vehiculo con matricula " + matricula +
                                    " para retirarlo")
                            .setPriority(NotificationCompat.PRIORITY_DEFAULT);
                    NotificationManagerCompat notificationManagerCompat=NotificationManagerCompat
                            .from(getApplicationContext());
                    notificationManagerCompat.notify(NOTIFICATION_ID, builder.build());
                }
            });
            dialogo.setNegativeButton("Email", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    try {
                        Intent intent=new Intent(Intent.ACTION_SENDTO);
                        intent.setData(Uri.parse("mailto: "));
                        String[] to = {email};
                        intent.putExtra(Intent.EXTRA_EMAIL, to);
                        startActivity(Intent.createChooser(intent, "Enviar email"));
                    }catch (Exception e){
                        AlertDialog.Builder dialogo = new AlertDialog.Builder(getApplicationContext());
                        dialogo.setTitle("Error");
                        dialogo.setMessage("Algo ha salido mal...");
                    }
                }
            });
            dialogo.show();

            return  true;
        }

        //Llamo al dueño del vehiculo
        if (item.getItemId()==R.id.contactoCliente){
            vehiculos miVehiculo=indexActivity.listadoVehiculos.get(info.position);
            long numeroTelefono=miVehiculo.getClienteID();
            try {
                Intent miIntent=new Intent(Intent.ACTION_DIAL);
                miIntent.setData(Uri.parse("tel: " + numeroTelefono));
                startActivity(miIntent);
            }catch (Exception e){
                AlertDialog.Builder dialogo = new AlertDialog.Builder(this);
                dialogo.setTitle("Información");
                dialogo.setMessage("Algo ha fallado, intentelo de nuevo");
                dialogo.show();
            }
            return true;
        }

        return false;
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