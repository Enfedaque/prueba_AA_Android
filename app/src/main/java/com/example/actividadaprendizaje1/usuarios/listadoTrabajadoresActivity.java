package com.example.actividadaprendizaje1.usuarios;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;

import com.example.actividadaprendizaje1.R;
import com.example.actividadaprendizaje1.bbdd.baseDeDatos;
import com.example.actividadaprendizaje1.domain.trabajadores;
import com.example.actividadaprendizaje1.inicio.indexActivity;
import com.example.actividadaprendizaje1.mapas.talleresActivity;

import java.util.ArrayList;
import java.util.List;

public class listadoTrabajadoresActivity extends AppCompatActivity {

    public static List<trabajadores> mostrarTrabajadores;
    //Adapter para la lista y la BBDD
    private ArrayAdapter<trabajadores> mostrarTrabajadoresAdapter;
    Switch miSwitch;
    EditText apellidoBuscador;
    Button btBuscar;
    EditText idBuscador;
    Button btBuscar2;
    CheckBox cbApellido;
    CheckBox cbId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_trabajadores);

        mostrarTrabajadores=new ArrayList<>();

        miSwitch=findViewById(R.id.swBuscarTrabajador);
        apellidoBuscador=findViewById(R.id.apellidoBuscadorTrabajador);
        btBuscar=findViewById(R.id.btBuscadorTrabajador);
        idBuscador =findViewById(R.id.idBuscadorTrabajador);
        btBuscar2=findViewById(R.id.bt2BuscadorTrabajador);
        cbApellido=findViewById(R.id.cbApellidoTrabajador);
        cbId=findViewById(R.id.cbIdTrabajador);

        mostrarTrabajadoresAdapter =new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                mostrarTrabajadores);

        ListView lvListadoTrabajadores=findViewById(R.id.listadoTrabajadores);
        lvListadoTrabajadores.setAdapter(mostrarTrabajadoresAdapter);

        registerForContextMenu(lvListadoTrabajadores);
    }

    protected void onResume(){
        super.onResume();
        cargarBaseDeDatos();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        cargarBaseDeDatos();
    }

    @Override
    protected void onStart() {
        super.onStart();
        cargarBaseDeDatos();
    }

    private void cargarBaseDeDatos(){
        mostrarTrabajadores.clear();
        baseDeDatos database= Room.databaseBuilder(getApplicationContext(), baseDeDatos.class,
                "Taller").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        mostrarTrabajadores.addAll(database.trabajadoresDAO().getAll());
    }

    //Metodo para el switch que muestra el buscador
    public void buscadorTrabajadoresVisible(View view){
        if (view.getId()==R.id.swBuscarTrabajador){
            if (miSwitch.isChecked()){
                apellidoBuscador.setVisibility(View.VISIBLE);
                btBuscar.setVisibility(View.VISIBLE);
                cbApellido.setVisibility(View.VISIBLE);
                cbId.setVisibility(View.VISIBLE);
                cbApellido.setChecked(true);
            }else{
                cbApellido.setVisibility(View.GONE);
                cbId.setVisibility(View.GONE);
                apellidoBuscador.setVisibility(View.GONE);
                btBuscar.setVisibility(View.GONE);
                idBuscador.setVisibility(View.GONE);
                btBuscar2.setVisibility(View.GONE);
            }
        }

    }

    //Metodo para saber que mostrador de busqueda mostrar segun los checkbox
    public void checkeado(View view){
        if (view.getId()==R.id.cbApellidoTrabajador){
            apellidoBuscador.setVisibility(View.VISIBLE);
            btBuscar.setVisibility(View.VISIBLE);
            idBuscador.setVisibility(View.GONE);
            btBuscar2.setVisibility(View.GONE);
            cbId.setChecked(false);
        }else {
            apellidoBuscador.setVisibility(View.GONE);
            btBuscar.setVisibility(View.GONE);
            idBuscador.setVisibility(View.VISIBLE);
            btBuscar2.setVisibility(View.VISIBLE);
            cbApellido.setChecked(false);
        }
    }

    //Metodo que busca al trabajador en la lista por el apellido y lo muestra
    public void resultadoBusquedaTrabajadorPorApellido(View view){
        for (trabajadores miTrabajador : mostrarTrabajadores){
            /*Controlo las excepciones que puedan saltar como intrudicr un tipo de dato incorrecto
            en la busqueda
             */
            try {
                if (miTrabajador.getApellido()
                        .equalsIgnoreCase(apellidoBuscador.getText().toString())){
                    String mostrarResultado=miTrabajador.toString2();
                    //Muestro en un dialogo la informacion completa del usuario
                    AlertDialog.Builder dialogo = new AlertDialog.Builder(this);
                    dialogo.setTitle(R.string.info);
                    dialogo.setMessage(mostrarResultado);
                    dialogo.show();
                }else{
                    AlertDialog.Builder dialogoBorrar = new AlertDialog.Builder(this);
                    dialogoBorrar.setTitle(R.string.info);
                    dialogoBorrar.setMessage(R.string.usurioNoEncontrado);
                    dialogoBorrar.show();
                }

            }catch (Exception ex){
                AlertDialog.Builder dialogoBorrar = new AlertDialog.Builder(this);
                dialogoBorrar.setTitle(R.string.info);
                dialogoBorrar.setMessage(R.string.usurioNoEncontrado);
                dialogoBorrar.show();
            }
        }
    }

    //Metodo que busca al trabajador en la lista por el Id y lo muestra
    public void resultadoBusquedaTrabajadorPorIdTrabajador(View view){
        for (trabajadores miTrabajador : mostrarTrabajadores){
            /*Controlo las excepciones que puedan saltar como intrudicr un tipo de dato incorrecto
            en la busqueda
             */
            try {
                if (miTrabajador.getTrabajadorID() == Long.parseLong(idBuscador.getText().toString())){
                    String mostrarResultado=miTrabajador.toString2();
                    //Muestro en un dialogo la informacion completa del usuario
                    AlertDialog.Builder dialogo = new AlertDialog.Builder(this);
                    dialogo.setTitle(R.string.info);
                    dialogo.setMessage(mostrarResultado);
                    dialogo.show();
                }else{
                    AlertDialog.Builder dialogoBorrar = new AlertDialog.Builder(this);
                    dialogoBorrar.setTitle(R.string.info);
                    dialogoBorrar.setMessage(R.string.usurioNoEncontrado);
                    dialogoBorrar.show();
                }

            }catch (Exception ex){
                AlertDialog.Builder dialogoBorrar = new AlertDialog.Builder(this);
                dialogoBorrar.setTitle(R.string.info);
                dialogoBorrar.setMessage(R.string.usurioNoEncontrado);
                dialogoBorrar.show();
            }
        }
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
        }else if (item.getItemId()==R.id.buscarTalleres2){
            Intent miIntent=new Intent(this, talleresActivity.class);
            startActivity(miIntent);
            return true;
        }

        return false;
    }

    //Metodo para eliminar trabajadores de mi lista
    private void eliminar(AdapterView.AdapterContextMenuInfo info){
        trabajadores miTrabajador=mostrarTrabajadores.get(info.position);
        baseDeDatos database= Room.databaseBuilder(getApplicationContext(), baseDeDatos.class,
                "Taller").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        database.trabajadoresDAO().eliminar(miTrabajador);
    }

    //MENU CONTEXTUAL
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.contextual_usuarios, menu);
    }
    //MENU CONTEXTUAL
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item){
        //Contextual
        AdapterView.AdapterContextMenuInfo info= (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        //Opcion de mostrar la informacion
        if (item.getItemId()==R.id.informacion){
            trabajadores miTrabajador=mostrarTrabajadores.get(info.position);
            AlertDialog.Builder dialogo = new AlertDialog.Builder(this);
            dialogo.setTitle(R.string.info);
            dialogo.setMessage(miTrabajador.toString2());
            dialogo.show();
            return true;
        }
        //Opcion de eliminar de la lista
        if(item.getItemId()==R.id.borrar){
            //DIALOGO PARA PREGUNTAR EL QUERER ELIMINAR
            AlertDialog.Builder dialogoBorrar = new AlertDialog.Builder(this);
            dialogoBorrar.setTitle(R.string.importante);
            dialogoBorrar.setMessage(R.string.preguntaEliminar);
            //Que hace si aprieta el boton de confirmar
            dialogoBorrar.setPositiveButton(R.string.confirmar, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogo1, int id) {

                    eliminar(info);
                    cargarBaseDeDatos();
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
        if (item.getItemId()==R.id.editar){

            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            LayoutInflater in=getLayoutInflater();
            View v=in.inflate(R.layout.editar_trabajadores, null);
            builder.setView(v);
            Button save=v.findViewById(R.id.guardarCambiosTrabajador);
            EditText etNombre=v.findViewById(R.id.editarNombretrabajador);
            EditText etApellido=v.findViewById(R.id.editarApellidoTrabajador);
            EditText etDNI=v.findViewById(R.id.editarDNITrabajador);
            EditText etTelefono=v.findViewById(R.id.editarTelefonoTrabajador);
            EditText etEmail=v.findViewById(R.id.editarEmailTrabajador);
            EditText etDepartamento=findViewById(R.id.editarDepartamentoTrabajador);
            EditText etPuesto=findViewById(R.id.editarPuestoTrabajador);

            save.setOnClickListener(v1 -> {

                trabajadores miTrabajador=mostrarTrabajadores.get(info.position);
                miTrabajador.setNombre(etNombre.getText().toString());
                miTrabajador.setApellido(etApellido.getText().toString());
                miTrabajador.setDni(etDNI.getText().toString());
                miTrabajador.setTelefono(etTelefono.getText().toString());
                miTrabajador.setEmail(etEmail.getText().toString());
                miTrabajador.setDepartamento(etDepartamento.getText().toString());
                miTrabajador.setPuesto(etPuesto.getText().toString());

                baseDeDatos database= Room.databaseBuilder(getApplicationContext(), baseDeDatos.class,
                        "Taller").allowMainThreadQueries().fallbackToDestructiveMigration().build();
                database.trabajadoresDAO().editar(miTrabajador);
                cargarBaseDeDatos();
            });

            AlertDialog alertDialog= builder.create();
            alertDialog.show();
        }

        //Opcion de llamar al trabajador
        if (item.getItemId()==R.id.llamar){
            trabajadores miTrabajador=mostrarTrabajadores.get(info.position);
            String numeroTelefono=miTrabajador.getTelefono();

            if (!numeroTelefono.equals("")){
                Intent miIntent=new Intent(Intent.ACTION_DIAL);
                miIntent.setData(Uri.parse("tel: " + numeroTelefono));
                startActivity(miIntent);
            }else{
                AlertDialog.Builder dialogo = new AlertDialog.Builder(this);
                dialogo.setTitle(R.string.info);
                dialogo.setMessage(R.string.algoHaSalidoMal);
                dialogo.show();
            }
            return true;
        }

        return false;
    }
}