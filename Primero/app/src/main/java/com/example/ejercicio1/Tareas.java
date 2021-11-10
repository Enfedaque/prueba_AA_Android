package com.example.ejercicio1;

public class Tareas {

    public String nombreTarea;

    public Tareas(String nombreTarea) {
        this.nombreTarea = nombreTarea;
    }

    @Override
    public String toString() {
        return nombreTarea;
    }

    public String getNombreTarea() {
        return nombreTarea;
    }

    public void setNombreTarea(String nombreTarea) {
        this.nombreTarea = nombreTarea;
    }
}
