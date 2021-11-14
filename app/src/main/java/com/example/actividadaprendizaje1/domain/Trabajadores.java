package com.example.actividadaprendizaje1.domain;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(inheritSuperIndices = true)
public class Trabajadores extends Usuarios{

    @PrimaryKey(autoGenerate = true)
    private long trabajadorID;
    @ColumnInfo
    private String departamento;
    @ColumnInfo
    private String puesto;

    public Trabajadores(String nombre, String apellido, String dni, String telefono, String email
            , String departamento, String puesto) {
        super(nombre, apellido, dni, telefono, email);
        this.departamento = departamento;
        this.puesto = puesto;
    }

    public long getTrabajadorID() {
        return trabajadorID;
    }

    public void setTrabajadorID(long trabajadorID) {
        this.trabajadorID = trabajadorID;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    @Override
    public String toString() {
        return getApellido() + ", " + getNombre() + " / " + getDni();
    }
}
