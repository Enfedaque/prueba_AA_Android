package com.example.actividadaprendizaje1.domain;

import java.util.Date;

public class Facturas {

    private long ID_Factura;
    private final String empresa="Garage Admin";
    private String direccion;
    private String fechaEmision;
    private Clientes miCliente;
    private Vehiculos miVehiculo;

    public Facturas(String direccion, String fechaEmision, Clientes miCliente, Vehiculos miVehiculo) {
        this.direccion=direccion;
        this.fechaEmision = fechaEmision;
        this.miCliente = miCliente;
        this.miVehiculo = miVehiculo;
    }


    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public long getID_Factura() {
        return ID_Factura;
    }

    public void setID_Factura(long ID_Factura) {
        this.ID_Factura = ID_Factura;
    }

    public String getEmpresa() {
        return empresa;
    }

    public String getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(String fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public Clientes getMiCliente() {
        return miCliente;
    }

    public void setMiCliente(Clientes miCliente) {
        this.miCliente = miCliente;
    }

    public Vehiculos getMiVehiculo() {
        return miVehiculo;
    }

    public void setMiVehiculo(Vehiculos miVehiculo) {
        this.miVehiculo = miVehiculo;
    }

    @Override
    public String toString() {
        return "Facturas{" +
                "ID_Factura=" + ID_Factura +
                ", empresa='" + empresa + '\'' +
                ", fechaEmision=" + fechaEmision +
                ", miCliente=" + miCliente +
                ", miVehiculo=" + miVehiculo +
                '}';
    }
}
