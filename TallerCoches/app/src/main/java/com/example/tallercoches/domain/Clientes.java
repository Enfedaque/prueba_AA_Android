package com.example.tallercoches.domain;

public class Clientes {

    private String nombre;
    private String apellidos;
    private String telefono;
    private String email;
    private String marcaVehiculo;
    private String matriculaVehiculo;

    public Clientes(String nombre, String apellidos, String telefono, String email, String marcaVehiculo, String matriculaVehiculo) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.email = email;
        this.marcaVehiculo = marcaVehiculo;
        this.matriculaVehiculo = matriculaVehiculo;
    }

    @Override
    public String toString() {
        return  apellidos + "," + nombre + ": " + matriculaVehiculo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMarcaVehiculo() {
        return marcaVehiculo;
    }

    public void setMarcaVehiculo(String marcaVehiculo) {
        this.marcaVehiculo = marcaVehiculo;
    }

    public String getMatriculaVehiculo() {
        return matriculaVehiculo;
    }

    public void setMatriculaVehiculo(String matriculaVehiculo) {
        this.matriculaVehiculo = matriculaVehiculo;
    }
}
