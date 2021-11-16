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

import com.example.actividadaprendizaje1.domain.Clientes;

public class listadoClientesActivity extends AppCompatActivity {

    private ArrayAdapter<Clientes> listadoClientesAdapter;
    Switch miSwitch;
    EditText apellidoBuscador;
    EditText idBuscar;
    Button btBuscar;
    Button btBuscar2;
    CheckBox cbApellido;
    CheckBox cbId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_clientes);

        miSwitch=findViewById(R.id.swBuscarCliente);
        apellidoBuscador=findViewById(R.id.apellidosBuscadorCliente);
        idBuscar=findViewById(R.id.idBuscadorCliente);
        btBuscar=findViewById(R.id.btBuscadorCliente);
        btBuscar2=findViewById(R.id.bt2BuscadorCliente);
        cbApellido=findViewById(R.id.cbApellidoCliente);
        cbId=findViewById(R.id.cbIdCliente);

        listadoClientesAdapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                indexActivity.listadoClientes);

        ListView lvListadoClientes=findViewById(R.id.listadoClientes);
        lvListadoClientes.setAdapter(listadoClientesAdapter);

        registerForContextMenu(lvListadoClientes);

    }

    protected void onResume(){
        super.onResume();

    }

    //Metodo para el switch que muestra el buscador
    public void buscadorClientesVisible(View view){
        if (view.getId()==R.id.swBuscarCliente){
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
                idBuscar.setVisibility(View.GONE);
                btBuscar2.setVisibility(View.GONE);
            }
        }

    }

    //Metodo para saber que mostrador de busqueda mostrar segun los checkbox
    public void checkeado(View view){
        if (view.getId()==R.id.cbApellidoCliente){
            apellidoBuscador.setVisibility(View.VISIBLE);
            btBuscar.setVisibility(View.VISIBLE);
            idBuscar.setVisibility(View.GONE);
            btBuscar2.setVisibility(View.GONE);
            cbId.setChecked(false);
        }else {
            apellidoBuscador.setVisibility(View.GONE);
            btBuscar.setVisibility(View.GONE);
            idBuscar.setVisibility(View.VISIBLE);
            btBuscar2.setVisibility(View.VISIBLE);
            cbApellido.setChecked(false);
        }
    }

    //Metodo del boton que busca al cliente por apellido en la lista y lo muestra
    public void resultadoBusquedaClientePorApellido(View view){
        for (Clientes miCliente : indexActivity.listadoClientes){
            /*Controlo las excepciones que puedan saltar como intrudicr un tipo de dato incorrecto
            en la busqueda
             */
            try {
                if (miCliente.getApellido()
                        .equalsIgnoreCase(apellidoBuscador.getText().toString())){
                    String mostrarResultado=miCliente.toString2();
                    //Muestro en un dialogo la informacion completa del usuario
                    AlertDialog.Builder dialogoBorrar = new AlertDialog.Builder(this);
                    dialogoBorrar.setTitle("Informarción");
                    dialogoBorrar.setMessage(mostrarResultado);
                    dialogoBorrar.show();
                }else{
                    AlertDialog.Builder dialogoBorrar = new AlertDialog.Builder(this);
                    dialogoBorrar.setTitle("Informarción");
                    dialogoBorrar.setMessage("Usuario no encontrado");
                    dialogoBorrar.show();
                }
            }catch ( Exception ex){
                AlertDialog.Builder dialogoBorrar = new AlertDialog.Builder(this);
                dialogoBorrar.setTitle("Informarción");
                dialogoBorrar.setMessage("Usuario no encontrado");
                dialogoBorrar.show();
            }

        }
    }

    //Metodo del boton que busca al cliente por Id_Cliente en la lista y lo muestra
    public void resultadoBusquedaClientePorId(View view){
        for (Clientes miCliente : indexActivity.listadoClientes){
            try {
                if (miCliente.getClienteID() == Long.parseLong(idBuscar.getText().toString())){
                    String mostrarResultado=miCliente.toString2();
                    //Muestro en un dialogo la informacion completa del usuario
                    AlertDialog.Builder dialogoBorrar = new AlertDialog.Builder(this);
                    dialogoBorrar.setTitle("Informarción");
                    dialogoBorrar.setMessage(mostrarResultado);
                    dialogoBorrar.show();
                }else{
                    AlertDialog.Builder dialogoBorrar = new AlertDialog.Builder(this);
                    dialogoBorrar.setTitle("Informarción");
                    dialogoBorrar.setMessage("Usuario no encontrado");
                    dialogoBorrar.show();
                }
            }catch ( Exception ex){
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
        /*if(item.getItemId() == R.id.home){
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
        }*/

        return false;
    }

    //Metodo para eliminar clientes de mi lista
    private void eliminar(AdapterView.AdapterContextMenuInfo info){
        indexActivity.listadoClientes.remove(info.position);
        listadoClientesAdapter.notifyDataSetChanged();
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
            //todo crear pantalla que muestre la informacion
            return true;
        }
        //Opcion de eliminar de la lista
        if(item.getItemId()==R.id.borrar){
            //DIALOGO PARA PREGUNTAR EL QUERER ELIMINAR
            AlertDialog.Builder dialogoBorrar = new AlertDialog.Builder(this);
            dialogoBorrar.setTitle("Importante");
            dialogoBorrar.setMessage("¿ Estas seguro de eliminar este cliente ?");
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