package com.example.actividadaprendizaje1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.actividadaprendizaje1.domain.Clientes;
import com.example.actividadaprendizaje1.domain.Trabajadores;

/*Activity de la zona de usuarios*/

public class zonaUsuariosActivity extends AppCompatActivity {

    EditText etBuscarCliente;
    EditText etBuscarTrabajador;
    Button btConsultaCliente;
    Button btConsultaTrabajador;
    TextView mostrarInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zona_usuarios);

        etBuscarCliente=findViewById(R.id.busquedaClienteIntro);
        etBuscarTrabajador=findViewById(R.id.busquedaTrabajadorIntro);
        String buscarTrabajador=etBuscarTrabajador.getText().toString();
        btConsultaCliente=findViewById(R.id.botonConsultaCliente);
        btConsultaTrabajador=findViewById(R.id.botonConsultaTrabajador);
        mostrarInfo=findViewById(R.id.respuestaConsulta);

    }

    protected void onResume(){
        super.onResume();

    }

    //Metodo para mostrar los resultados de una busqueda
    public void mostrarInfoClientes(View view){
        //Vacio el textView del resultado cada vez
        mostrarInfo.setText("");
        String buscarCliente=etBuscarCliente.getText().toString();
        //Miro que el campo de busqueda no este vacio
        if (etBuscarCliente.getText().toString().equalsIgnoreCase("")){
            Toast.makeText(this, "Introduzca caracteres a buscar", Toast.LENGTH_LONG).show();
        }else{
            //Recorro el Array buscando si hay algun objeto que tenga ese apellido
            for (Clientes cliente : indexActivity.listadoClientes) {
                if (cliente.getApellido().equals(buscarCliente)) {
                    //Muestro el cliente el el textView
                    mostrarInfo.setText(cliente.toString());
                }
            }
            //Si no ha encontrado resultados es que el textView esta vacio y muestro lo siguiente
            if (mostrarInfo.equals("")){
                Toast.makeText(this, "Usuario no encontrado", Toast.LENGTH_LONG).show();
            }
        }
    }
    public void mostrarInfoTrabajadores(View view){
        //Vacio el textView del resultado cada vez
        mostrarInfo.setText("");
        String buscarTrabajadores=etBuscarTrabajador.getText().toString();
        //Miro que el campo de busqueda no este vacio
        if (etBuscarTrabajador.getText().toString().equalsIgnoreCase("")){
            Toast.makeText(this, "Introduzca caracteres a buscar", Toast.LENGTH_LONG).show();
        }else{
            //Recorro el Array buscando si hay algun objeto que tenga ese apellido
            for (Trabajadores trabajador : indexActivity.listadoTrabajadores) {
                if (trabajador.getApellido().equals(buscarTrabajadores)) {
                    //Muestro el trabajador el el textView
                    mostrarInfo.setText(trabajador.toString());
                }
            }
            //Si no ha encontrado resultados es que el textView esta vacio y muestro lo siguiente.
            if (mostrarInfo.equals("")){
                Toast.makeText(this, "Usuario no encontrado", Toast.LENGTH_LONG).show();
            }
        }
    }

    //CheckBox para permitir o no las busquedas
    public void checkBoxClicked(View view){
        // Compruebo si esta clickado
        boolean checked = ((CheckBox) view).isChecked();
        switch(view.getId()) {
            case R.id.busquedaCliente:
                if (checked){
                    etBuscarCliente.setVisibility(View.VISIBLE);
                    btConsultaCliente.setVisibility(View.VISIBLE);
                }else{
                    etBuscarCliente.setVisibility(View.INVISIBLE);
                    btConsultaCliente.setVisibility(View.INVISIBLE);
                }
                break;
            case R.id.busquedaTrabajador:
                if (checked){
                    etBuscarTrabajador.setVisibility(View.VISIBLE);
                    btConsultaTrabajador.setVisibility(View.VISIBLE);
                }else{
                    etBuscarTrabajador.setVisibility(View.INVISIBLE);
                    btConsultaTrabajador.setVisibility(View.INVISIBLE);
                }
                break;
            // TODO: Veggie sandwich
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
        } else if (item.getItemId()==R.id.opcion1) {
            //todo aun no se que opcion poner
            return true;
        }else if (item.getItemId()==R.id.opcion2){
            //todo aun no se que opcion poner
            return true;
        }


        return false;
    }

    public void nuevoCliente(View view){
        Intent miIntent=new Intent(this, nuevoClienteActivity.class);
        startActivity(miIntent);
    }

    public void listadoClientes(View view){
        Intent miIntent=new Intent(this, listadoClientesActivity.class);
        startActivity(miIntent);
    }

    public void nuevoTrabajador(View view){
        Intent miIntent=new Intent(this, nuevoTrabajadorActivity.class);
        startActivity(miIntent);
    }

    public void listadoTrabajadores(View view){
        Intent miIntent=new Intent(this, listadoTrabajadoresActivity.class);
        startActivity(miIntent);
    }
}