package com.example.actividadaprendizaje1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
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

import com.example.actividadaprendizaje1.domain.Trabajadores;

public class listadoTrabajadoresActivity extends AppCompatActivity {

    private ArrayAdapter<Trabajadores> listadoTrabajadoresAdapter;
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

        miSwitch=findViewById(R.id.swBuscarTrabajador);
        apellidoBuscador=findViewById(R.id.apellidoBuscadorTrabajador);
        btBuscar=findViewById(R.id.btBuscadorTrabajador);
        idBuscador =findViewById(R.id.idBuscadorTrabajador);
        btBuscar2=findViewById(R.id.bt2BuscadorTrabajador);
        cbApellido=findViewById(R.id.cbApellidoTrabajador);
        cbId=findViewById(R.id.cbIdTrabajador);

        listadoTrabajadoresAdapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                indexActivity.listadoTrabajadores);

        ListView lvListadoTrabajadores=findViewById(R.id.listadoTrabajadores);
        lvListadoTrabajadores.setAdapter(listadoTrabajadoresAdapter);

        registerForContextMenu(lvListadoTrabajadores);
    }

    protected void onResume(){
        super.onResume();

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
        for (Trabajadores miTrabajador : indexActivity.listadoTrabajadores){
            /*Controlo las excepciones que puedan saltar como intrudicr un tipo de dato incorrecto
            en la busqueda
             */
            try {
                if (miTrabajador.getApellido()
                        .equalsIgnoreCase(apellidoBuscador.getText().toString())){
                    String mostrarResultado=miTrabajador.toString2();
                    //Muestro en un dialogo la informacion completa del usuario
                    AlertDialog.Builder dialogo = new AlertDialog.Builder(this);
                    dialogo.setTitle("Informarción");
                    dialogo.setMessage(mostrarResultado);
                    dialogo.show();
                }else{
                    AlertDialog.Builder dialogoBorrar = new AlertDialog.Builder(this);
                    dialogoBorrar.setTitle("Informarción");
                    dialogoBorrar.setMessage("Usuario no encontrado");
                    dialogoBorrar.show();
                }

            }catch (Exception ex){
                AlertDialog.Builder dialogoBorrar = new AlertDialog.Builder(this);
                dialogoBorrar.setTitle("Informarción");
                dialogoBorrar.setMessage("Usuario no encontrado");
                dialogoBorrar.show();
            }
        }
    }

    //Metodo que busca al trabajador en la lista por el Id y lo muestra
    public void resultadoBusquedaTrabajadorPorIdTrabajador(View view){
        for (Trabajadores miTrabajador : indexActivity.listadoTrabajadores){
            /*Controlo las excepciones que puedan saltar como intrudicr un tipo de dato incorrecto
            en la busqueda
             */
            try {
                if (miTrabajador.getTrabajadorID() == Long.parseLong(idBuscador.getText().toString())){
                    String mostrarResultado=miTrabajador.toString2();
                    //Muestro en un dialogo la informacion completa del usuario
                    AlertDialog.Builder dialogo = new AlertDialog.Builder(this);
                    dialogo.setTitle("Informarción");
                    dialogo.setMessage(mostrarResultado);
                    dialogo.show();
                }else{
                    AlertDialog.Builder dialogoBorrar = new AlertDialog.Builder(this);
                    dialogoBorrar.setTitle("Informarción");
                    dialogoBorrar.setMessage("Usuario no encontrado");
                    dialogoBorrar.show();
                }

            }catch (Exception ex){
                AlertDialog.Builder dialogoBorrar = new AlertDialog.Builder(this);
                dialogoBorrar.setTitle("Informarción");
                dialogoBorrar.setMessage("Usuario no encontrado");
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
        }else if (item.getItemId()==R.id.buscadorUsuarios){
            //todo
        }else if (item.getItemId()==R.id.acercaDe){
            //todo aqui quiero mostrar un activity o un alert o algo con informacion de la
            // aplicacion
            return true;
        } else if (item.getItemId()==R.id.navegador) {
            //todo aun no se que opcion poner
            return true;
        }else if (item.getItemId()==R.id.opcion2){
            //todo aun no se que opcion poner
            return true;
        }

        return false;
    }

    //Metodo para eliminar trabajadores de mi lista
    private void eliminar(AdapterView.AdapterContextMenuInfo info){
        indexActivity.listadoTrabajadores.remove(info.position);
        listadoTrabajadoresAdapter.notifyDataSetChanged();
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
            //todo , no se como mostrar la info del objeto en el menu
            //Muestro en un dialogo la informacion completa del usuario
            AlertDialog.Builder dialogo = new AlertDialog.Builder(this);
            dialogo.setTitle("Informarción");
            dialogo.setMessage("");
            dialogo.show();
            return true;
        }
        //Opcion de eliminar de la lista
        if(item.getItemId()==R.id.borrar){
            //DIALOGO PARA PREGUNTAR EL QUERER ELIMINAR
            AlertDialog.Builder dialogoBorrar = new AlertDialog.Builder(this);
            dialogoBorrar.setTitle("Importante");
            dialogoBorrar.setMessage("¿ Estas seguro de eliminar este trabajador ?");
            //Que hace si aprieta el boton de confirmar
            dialogoBorrar.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogo1, int id) {
                    eliminar(info);
                }
            });
            //Que hace si aprieta el boton de cancelar
            dialogoBorrar.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogo1, int id) {
                    finish();
                }
            });
            dialogoBorrar.show();
            return true;
        }
        return false;
    }
}