package com.example.actividadaprendizaje1.facturas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.actividadaprendizaje1.R;
import com.example.actividadaprendizaje1.bbdd.baseDeDatos;
import com.example.actividadaprendizaje1.domain.clientes;
import com.example.actividadaprendizaje1.domain.facturas;
import com.example.actividadaprendizaje1.domain.vehiculos;
import com.example.actividadaprendizaje1.inicio.indexActivity;

import java.util.ArrayList;
import java.util.List;

public class facturaActivity extends AppCompatActivity {

    public static List<facturas> mostrarFacturas;
    private List<clientes> mostrarSpinnerClientes;
    private List<vehiculos> mostrarSpinnerVehiculos;
    //Guardo el nombre ya que aunque tenga que seleccionar el cliente, puede que
    // la factura vaya a nombre de su empresa por ejemplo*/
    TextView numeroFactura;
    EditText nombreFactura;
    EditText direccionFactura;
    EditText fechaFactura;
    Spinner spClienteFactura;
    Spinner spVehiculoFactura;

    //Adapter para los spinner
    ArrayAdapter<clientes> miSpinnerAdapterClientes;
    ArrayAdapter<vehiculos> miSpinnerAdapterVehiculos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_factura);

        mostrarSpinnerClientes=new ArrayList<>();
        mostrarSpinnerVehiculos=new ArrayList<>();

        cargarListasSpinner();

        spClienteFactura=findViewById(R.id.spClientesFactura);
        spVehiculoFactura=findViewById(R.id.spVehiculosFactura);

        miSpinnerAdapterClientes=new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,
                mostrarSpinnerClientes);
        spClienteFactura.setAdapter(miSpinnerAdapterClientes);
        miSpinnerAdapterVehiculos=new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,
                mostrarSpinnerVehiculos);
        spVehiculoFactura.setAdapter(miSpinnerAdapterVehiculos);

    }

    private void cargarListasSpinner(){
        baseDeDatos database= Room.databaseBuilder(getApplicationContext(), baseDeDatos.class,
                "Taller").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        mostrarSpinnerClientes.addAll(database.clientesDAO().getAll());
        mostrarSpinnerVehiculos.addAll(database.vehiculosDAO().getAll());
    }

    //Metodo que crea la nueva factura
    public void nuevaFactura(View view){

        nombreFactura=findViewById(R.id.nombreFactura);
        direccionFactura=findViewById(R.id.direccionFactura);
        fechaFactura=findViewById(R.id.fechaFactura);


        if (direccionFactura.getText().toString().equals("")
                || fechaFactura.getText().toString().equals("")
                || spClienteFactura==null
                || spVehiculoFactura==null){
            Toast.makeText(this, R.string.factura1 , Toast.LENGTH_SHORT).show();
            return;
        }

        String direccion=direccionFactura.getText().toString();
        String fecha= fechaFactura.getText().toString();
        clientes miCliente=(clientes) spClienteFactura.getSelectedItem();
        vehiculos miVehiculo=(vehiculos) spVehiculoFactura.getSelectedItem();

        try {
            //Creo y registro la nueva factura
            facturas miFactura=new facturas(direccion, fecha, miCliente.getClienteID(), miVehiculo.getIdVehiculo());
            mostrarFacturas.add(miFactura);
            Toast.makeText(this, R.string.factura2, Toast.LENGTH_LONG).show();

        }catch (Exception exc){
            Toast.makeText(this, R.string.factura3,
                    Toast.LENGTH_LONG).show();
        }

        nombreFactura.setText("");
        numeroFactura.setText("");
        direccionFactura.setText("");
        fechaFactura.setText("");

    }
}