package com.example.actividadaprendizaje1.domain;

public class Vehiculos {

    private Clientes miCliente;
    private String marca;
    private String modelo;
    private String matricula;
    private String miTrabajador;
    private Averias averia;

    public Vehiculos(Clientes miCliente, String marca, String modelo, String matricula,
                     String miTrabajador, Averias averia) {
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

    public Averias getAveria() {
        return averia;
    }

    public void setAveria(Averias averia) {
        this.averia = averia;
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

    public String getMiTrabajador() {
        return miTrabajador;
    }

    public void setMiTrabajador(String miTrabajador) {
        this.miTrabajador = miTrabajador;
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
