package com.example.actividadaprendizaje1.domain;

public class Vehiculos {

    private String miCliente;
    private String marca;
    private String modelo;
    private String matricula;
    private String miTrabajador;
    private String averia;

    public Vehiculos(String miCliente, String marca, String modelo, String matricula,
                     String miTrabajador, String averia) {
        this.miCliente = miCliente;
        this.marca = marca;
        this.modelo = modelo;
        this.matricula = matricula;
        this.miTrabajador = miTrabajador;
        this.averia=averia;
    }

    public String getMiCliente() {
        return miCliente;
    }

    public void setMiCliente(String miCliente) {
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
