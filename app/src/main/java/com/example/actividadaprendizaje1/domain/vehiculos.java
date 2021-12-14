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
    private long clienteID;
    @ColumnInfo
    private String marca;
    @ColumnInfo
    private String modelo;
    @ColumnInfo
    private String matricula;
    @ColumnInfo
    private long trabajadorID;
    @ColumnInfo
    private String averia;
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    private byte[] fotoVehiculo;


    public vehiculos(long clienteID, String marca, String modelo, String matricula,
                     long trabajadorID, String averia, byte[] fotoVehiculo) {
        this.clienteID = clienteID;
        this.marca = marca;
        this.modelo = modelo;
        this.matricula = matricula;
        this.trabajadorID = trabajadorID;
        this.averia=averia;
        this.fotoVehiculo=fotoVehiculo;
    }
    public byte[] getFotoVehiculo() {
        return fotoVehiculo;
    }

    public void setFotoVehiculo(byte[] fotoVehiculo) {
        this.fotoVehiculo = fotoVehiculo;
    }

    public long getClienteID() {
        return clienteID;
    }

    public void setClienteID(long clienteID) {
        this.clienteID = clienteID;
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

    public long getTrabajadorID() {
        return trabajadorID;
    }

    public void setTrabajadorID(long trabajadorID) {
        this.trabajadorID = trabajadorID;
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
        return getModelo().toUpperCase() + "/ " + getMarca() + " / " + getClienteID();
    }

    public String toString2() {
        return getMarca().toUpperCase() + ", " + getModelo().toUpperCase() + "/ " + getMatricula()
                + " / " + getAveria() + "/ " + getClienteID() ;
    }
}