package com.example.actividadaprendizaje1.domain;

public class Trabajadores extends Usuarios{

    private String trabajadorID;
    private String departamento;
    private String puesto;

    public Trabajadores(String nombre, String apellido, String dni, String telefono, String email,
                        String trabajadorID, String departamento, String puesto) {
        super(nombre, apellido, dni, telefono, email);
        this.trabajadorID = trabajadorID;
        this.departamento = departamento;
        this.puesto = puesto;
    }

    public String getTrabajadorID() {
        return trabajadorID;
    }

    public void setTrabajadorID(String trabajadorID) {
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
