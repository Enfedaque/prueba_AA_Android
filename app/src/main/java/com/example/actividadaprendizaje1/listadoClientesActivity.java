package com.example.actividadaprendizaje1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.DialogInterface;
import android.content.Intent;
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

import com.example.actividadaprendizaje1.bbdd.clientesBBDD;
import com.example.actividadaprendizaje1.domain.clientes;

public class listadoClientesActivity extends AppCompatActivity {

    private ArrayAdapter<clientes> listadoClientesAdapter;
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

        indexActivity.listadoClientes.clear();
        clientesBBDD database= Room.databaseBuilder(getApplicationContext(), clientesBBDD.class,
                "Taller").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        indexActivity.listadoClientes.addAll(database.clientesDAO().getAll());

        listadoClientesAdapter.notifyDataSetChanged();
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
        for (clientes miCliente : indexActivity.listadoClientes){
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
        for (clientes miCliente : indexActivity.listadoClientes){
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
        if(item.getItemId() == R.id.home){
            Intent miIntent=new Intent(this, indexActivity.class);
            startActivity(miIntent);
            return true;
        //Si toca lo mando a crear una factura
        }else if (item.getItemId()==R.id.facturaNueva){
            Intent miIntent=new Intent(this, facturaActivity.class);
            startActivity(miIntent);
        }

        return false;
    }

    //Metodo para eliminar clientes de mi lista
    private void eliminar(AdapterView.AdapterContextMenuInfo info){
        clientes miCliente=indexActivity.listadoClientes.get(info.position);
        clientesBBDD database= Room.databaseBuilder(getApplicationContext(), clientesBBDD.class,
                "Taller").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        database.clientesDAO().eliminar(miCliente);
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
            clientes miCliente=indexActivity.listadoClientes.get(info.position);
            AlertDialog.Builder dialogo = new AlertDialog.Builder(this);
            dialogo.setTitle("Información");
            dialogo.setMessage(miCliente.toString2());
            dialogo.show();
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
                    /*
                    TODO , me lo borra pero no me lo muestra al instante borrado, tengo que salir
                     de la activity y volver a entrar para ver que se ha borrado
                     */
                    eliminar(info);
                    listadoClientesAdapter.notifyDataSetChanged();
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

        if (item.getItemId()==R.id.editar){
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            LayoutInflater in=getLayoutInflater();
            View v=in.inflate(R.layout.editar_clientes, null);
            builder.setView(v);
            Button save=v.findViewById(R.id.guardarCambiosCliente);
            EditText etNombre=v.findViewById(R.id.editarNombreCliente);
            EditText etApellido=v.findViewById(R.id.editarApellidoCliente);
            EditText etDNI=v.findViewById(R.id.editarDNICliente);
            EditText etTelefono=v.findViewById(R.id.editarTelefonoCliente);
            EditText etEmail=v.findViewById(R.id.editarEmailCliente);
            save.setOnClickListener(v1 -> {

                clientes miCliente=indexActivity.listadoClientes.get(info.position);
                miCliente.setNombre(etNombre.getText().toString());
                miCliente.setApellido(etApellido.getText().toString());
                miCliente.setDni(etDNI.getText().toString());
                miCliente.setTelefono(etTelefono.getText().toString());
                miCliente.setEmail(etEmail.getText().toString());

                clientesBBDD database= Room.databaseBuilder(getApplicationContext(), clientesBBDD.class,
                        "Taller").allowMainThreadQueries().fallbackToDestructiveMigration().build();
                database.clientesDAO().editar(miCliente);
            });


            AlertDialog alertDialog= builder.create();
            alertDialog.show();

        }
        return false;
    }



}