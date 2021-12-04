package com.example.actividadaprendizaje1.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.actividadaprendizaje1.domain.trabajadores;

import java.util.List;

@Dao
public interface trabajadoresDAO {

    //Aqui van los metodos con los que voy a sacar los datos de la BBDD

    //Metodo que me lista todos los vehiculos
    @Query("SELECT * FROM trabajadores")
    List<trabajadores> getAll();

    //Metodo para consultar la info de un  vehiculo a traves de su id
    @Query("SELECT * FROM trabajadores WHERE trabajadorID= :id")
    List<trabajadores> findById(long id);

    //Metodo para regitsrar un nuevo vehiculo
    @Insert
    void insert(trabajadores miTrabajador);

    //Metodo para borrar un vehiculo
    @Delete
    void eliminar(trabajadores miTrabajador);

    @Update
    void editar(trabajadores miTrabajador);
}
