package com.example.actividadaprendizaje1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.actividadaprendizaje1.domain.Clientes;
import com.example.actividadaprendizaje1.domain.Facturas;
import com.example.actividadaprendizaje1.domain.Vehiculos;

public class FacturaActivity extends AppCompatActivity {

    //Guardo el nombre ya que aunque tenga que seleccionar el cliente, puede que
    // la factura vaya a nombre de su empresa por ejemplo*/
    TextView numeroFactura;
    EditText nombreFactura;
    EditText direccionFactura;
    EditText fechaFactura;
    Spinner spClienteFactura;
    Spinner spVehiculoFactura;

    //Adapter para los spinner
    ArrayAdapter<Clientes> miSpinnerAdapterClientes;
    ArrayAdapter<Vehiculos> miSpinnerAdapterVehiculos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_factura);

        spClienteFactura=findViewById(R.id.spClientesFactura);
        spVehiculoFactura=findViewById(R.id.spVehiculosFactura);

        miSpinnerAdapterClientes=new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,
                indexActivity.listadoClientes);
        spClienteFactura.setAdapter(miSpinnerAdapterClientes);
        miSpinnerAdapterVehiculos=new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,
                indexActivity.listadoVehiculos);
        spVehiculoFactura.setAdapter(miSpinnerAdapterVehiculos);

    }

    //Metodo que crea la nueva factura
    public void nuevaFactura(View view){
        numeroFactura=findViewById(R.id.numFactura);
        nombreFactura=findViewById(R.id.nombreFactura);
        direccionFactura=findViewById(R.id.direccionFactura);
        fechaFactura=findViewById(R.id.fechaFactura);


        if (direccionFactura.getText().toString().equals("")
                || fechaFactura.getText().toString().equals("")
                || (!spClienteFactura.isClickable()
                && !spVehiculoFactura.isClickable())){
            Toast.makeText(this, "Es obligaorio rellenar todos los campos" , Toast.LENGTH_SHORT).show();
            return;
        }

        String direccion=direccionFactura.getText().toString();
        String fecha= fechaFactura.getText().toString();
        Clientes miCliente=(Clientes) spClienteFactura.getSelectedItem();
        Vehiculos miVehiculo=(Vehiculos) spVehiculoFactura.getSelectedItem();

        try {
            //Creo y registro la nueva factura
            Facturas miFactura=new Facturas(direccion, fecha, miCliente, miVehiculo);
            indexActivity.listadoFacturas.add(miFactura);
            Toast.makeText(this, "Factura creada", Toast.LENGTH_LONG).show();
        }catch (Exception exc){
            Toast.makeText(this, "No se ha podido crear la factura, intentelo de nuevo...",
                    Toast.LENGTH_LONG).show();
        }

        numeroFactura.setText("");
        direccionFactura.setText("");


    }
}