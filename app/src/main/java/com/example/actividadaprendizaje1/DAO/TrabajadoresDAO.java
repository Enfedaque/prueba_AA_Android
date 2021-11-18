package com.example.actividadaprendizaje1.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.actividadaprendizaje1.domain.Trabajadores;
import com.example.actividadaprendizaje1.domain.Vehiculos;

import java.util.List;

@Dao
public interface TrabajadoresDAO {

    //Aqui van los metodos con los que voy a sacar los datos de la BBDD

    //Metodo que me lista todos los vehiculos
    @Query("SELECT * FROM Trabajadores")
    List<Trabajadores> getAll();

    //Metodo para consultar la info de un  vehiculo a traves de su id
    @Query("SELECT * FROM Trabajadores WHERE trabajadorID= :id")
    List<Trabajadores> findById(long id);

    //Metodo para regitsrar un nuevo vehiculo
    @Insert
    void insert(Trabajadores miTrabajador);

    //Metodo para borrar un vehiculo
    @Delete
    void eliminar(Trabajadores miTrabajador);

    @Update
    void editar(Trabajadores miTrabajador);
}
