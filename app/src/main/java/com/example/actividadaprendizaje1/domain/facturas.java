package com.example.actividadaprendizaje1.domain;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class facturas {

    @PrimaryKey(autoGenerate = true)
    private long ID_Factura;
    @ColumnInfo
    private String direccion;
    @ColumnInfo
    private String fechaEmision;
    @ColumnInfo
    private long miCliente;
    @ColumnInfo
    private long miVehiculo;

    public facturas(String direccion, String fechaEmision, long miCliente, long miVehiculo) {
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

    public String getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(String fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public long getMiCliente() {
        return miCliente;
    }

    public void setMiCliente(long miCliente) {
        this.miCliente = miCliente;
    }

    public long getMiVehiculo() {
        return miVehiculo;
    }

    public void setMiVehiculo(long miVehiculo) {
        this.miVehiculo = miVehiculo;
    }

    @Override
    public String toString() {
        return   direccion + " / " + miCliente   +" / " + miVehiculo;
    }
}
