package com.example.actividadaprendizaje1.domain;

public class facturas {

    private long ID_Factura;
    private final String empresa="Garage Admin";
    private String direccion;
    private String fechaEmision;
    private clientes miCliente;
    private vehiculos miVehiculo;

    public facturas(String direccion, String fechaEmision, clientes miCliente, vehiculos miVehiculo) {
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

    public clientes getMiCliente() {
        return miCliente;
    }

    public void setMiCliente(clientes miCliente) {
        this.miCliente = miCliente;
    }

    public vehiculos getMiVehiculo() {
        return miVehiculo;
    }

    public void setMiVehiculo(vehiculos miVehiculo) {
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
