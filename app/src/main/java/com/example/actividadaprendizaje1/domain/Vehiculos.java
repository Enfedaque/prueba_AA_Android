package com.example.actividadaprendizaje1.domain;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

//Indico que quiero que se cree una tabla
@Entity
public class vehiculos {

    //Con la anotacion @ColumnInfo hago que sea un campo de la tabla
    @PrimaryKey(autoGenerate = true)
    private long idVehiculo; //No lo meto en el constructor porque sera automatico en la BBDD
    @ColumnInfo
    private long idCliente;
    @ColumnInfo
    private String marca;
    @ColumnInfo
    private String modelo;
    @ColumnInfo
    private String matricula;
    @ColumnInfo
    private long miTrabajador;
    @ColumnInfo
    private String averia;

    public vehiculos(long idCliente, String marca, String modelo, String matricula,
                     long miTrabajador, String averia) {
        this.idCliente = idCliente;
        this.marca = marca;
        this.modelo = modelo;
        this.matricula = matricula;
        this.miTrabajador = miTrabajador;
        this.averia=averia;
    }

    public long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(long idCliente) {
        this.idCliente = idCliente;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public long getMiTrabajador() {
        return miTrabajador;
    }

    public void setMiTrabajador(long miTrabajador) {
        this.miTrabajador = miTrabajador;
    }

    public long getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(long idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public String getAveria() {
        return averia;
    }

    public void setAveria(String averia) {
        this.averia = averia;
    }

    @Override
    public String toString() {
        return getModelo().toUpperCase() + "/ " + getMarca() + " / " + getIdCliente();
    }

    public String toString2() {
        return getMarca().toUpperCase() + ", " + getModelo().toUpperCase() + "/ " + getMatricula()
                + " / " + getAveria() + "/ " + getIdCliente() ;
    }
}
