package com.example.actividadaprendizaje1.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.actividadaprendizaje1.domain.clientes;
import com.example.actividadaprendizaje1.domain.vehiculos;

import java.util.List;

@Dao
public interface vehiculosDAO {

    //Aqui van los metodos con los que voy a sacar los datos de la BBDD

    //Metodo que me lista todos los vehiculos
    @Query("SELECT * FROM vehiculos")
    List<vehiculos> getAll();

    //Metodo para consultar la info de un  vehiculo a traves de su id
    @Query("SELECT * FROM vehiculos WHERE idVehiculo= :id")
    List<vehiculos> findById(long id);

    //Metodo para regitsrar un nuevo vehiculo
    @Insert
    void insert(vehiculos miVehiculo);

    //Metodo para borrar un vehiculo
    @Delete
    void eliminar(vehiculos miVehiculo);

    @Update
    void editar(vehiculos miVehiculo);
}
