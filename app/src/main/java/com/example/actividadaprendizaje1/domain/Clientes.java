package com.example.actividadaprendizaje1.domain;

import android.graphics.Bitmap;
import android.widget.ImageView;

public class Clientes extends Usuarios{

    private String clienteID;

    public Clientes(String nombre, String apellido, String dni, String telefono, String email,
                    String clienteID) {
        super(nombre, apellido, dni, telefono, email);
        this.clienteID = clienteID;
    }

    public String getClienteID() {
        return clienteID;
    }

    public void setClienteID(String clienteID) {
        this.clienteID = clienteID;
    }

    @Override
    public String toString() {
        return getApellido() + ", " + getNombre() + " / " + getDni();
    }
}
