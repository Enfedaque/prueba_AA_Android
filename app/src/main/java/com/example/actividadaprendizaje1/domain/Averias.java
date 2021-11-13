package com.example.actividadaprendizaje1.domain;

public class Averias {

    private String titulo;
    private String descripcion;
    private double precioPresupuesto;
    private boolean reparable;
    private Vehiculos miVehiculo;

    public Averias(String titulo, String descripcion, double precioPresupuesto, boolean reparable,
                   Vehiculos miVehiculo) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.precioPresupuesto = precioPresupuesto;
        this.reparable = reparable;
        this.miVehiculo = miVehiculo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecioPresupuesto() {
        return precioPresupuesto;
    }

    public void setPrecioPresupuesto(double precioPresupuesto) {
        this.precioPresupuesto = precioPresupuesto;
    }

    public boolean isReparable() {
        return reparable;
    }

    public void setReparable(boolean reparable) {
        this.reparable = reparable;
    }

    public Vehiculos getMiVehiculo() {
        return miVehiculo;
    }

    public void setMiVehiculo(Vehiculos miVehiculo) {
        this.miVehiculo = miVehiculo;
    }

    @Override
    public String toString() {
        return "Averias{" +
                "titulo='" + titulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", precioPresupuesto=" + precioPresupuesto +
                ", reparable=" + reparable +
                ", miVehiculo=" + miVehiculo +
                '}';
    }
}
