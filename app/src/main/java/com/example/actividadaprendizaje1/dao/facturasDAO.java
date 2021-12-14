package com.example.actividadaprendizaje1.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.actividadaprendizaje1.domain.facturas;

import java.util.List;

@Dao
public interface facturasDAO {

    @Query("SELECT * FROM facturas")
    List<facturas> getAll();

    @Delete
    void eliminar(facturas miFactura);

    @Insert
    void insertar(facturas miFactura);
}
