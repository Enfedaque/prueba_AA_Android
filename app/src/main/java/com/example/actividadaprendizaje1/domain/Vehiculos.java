package com.example.actividadaprendizaje1.domain;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

//Indico que quiero que se cree una tabla
@Entity
public class Vehiculos {

    //Con la anotacion @ColumnInfo hago que sea un campo de la tabla
    @PrimaryKey(autoGenerate = true)
    private long idVehiculo; //No lo meto en el constructor porque sera automatico en la BBDD
    @ColumnInfo
    private Clientes miCliente;
    @ColumnInfo
    private String marca;
    @ColumnInfo
    private String modelo;
    @ColumnInfo
    private String matricula;
    @ColumnInfo
    private Trabajadores miTrabajador;
    @ColumnInfo
    private String averia;

    public Vehiculos(Clientes miCliente, String marca, String modelo, String matricula,
                     Trabajadores miTrabajador, String averia) {
        this.idVehiculo=idVehiculo;
        this.miCliente = miCliente;
        this.marca = marca;
        this.modelo = modelo;
        this.matricula = matricula;
        this.miTrabajador = miTrabajador;
        this.averia=averia;
    }

    public Clientes getMiCliente() {
        return miCliente;
    }

    public void setMiCliente(Clientes miCliente) {
        this.miCliente = miCliente;
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

    public Trabajadores getMiTrabajador() {
        return miTrabajador;
    }

    public void setMiTrabajador(Trabajadores miTrabajador) {
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
        return "Vehiculos{" +
                "miCliente=" + miCliente +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", matricula='" + matricula + '\'' +
                ", miTrabajador=" + miTrabajador +
                '}';
    }
}
