package com.example.actividadaprendizaje1.domain;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
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

    //Para mostrar simplemente en los arrayList
    @Override
    public String toString() {

        return getApellido().toUpperCase() + ", " + getNombre().toUpperCase();
    }

    //Este lo uso para mostrar la informacion completa por un dialog
    public String toString2(){
        return getNombre().toUpperCase() + ", " + getApellido().toUpperCase() + "/ " + getDni()
                + " / " + getTelefono() + "/ " + getEmail() ;
    }
}
