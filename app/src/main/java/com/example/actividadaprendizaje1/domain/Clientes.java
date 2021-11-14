package com.example.actividadaprendizaje1.domain;

import android.graphics.Bitmap;
import android.widget.ImageView;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(inheritSuperIndices = true)
public class Clientes extends Usuarios{

    //Lo tengo que definir como PrimaryKey autogenerado en la bbdd
    @PrimaryKey(autoGenerate = true)
    private long clienteID;

    public Clientes(String nombre, String apellido, String dni, String telefono, String email) {
        super(nombre, apellido, dni, telefono, email);
    }

    public long getClienteID() {
        return clienteID;
    }

    public void setClienteID(long clienteID) {
        this.clienteID = clienteID;
    }

    @Override
    public String toString() {
        return getApellido() + ", " + getNombre() + " / " + getDni();
    }
}
