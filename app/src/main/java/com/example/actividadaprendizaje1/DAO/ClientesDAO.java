package com.example.actividadaprendizaje1.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.actividadaprendizaje1.domain.clientes;

import java.util.List;

@Dao
public interface clientesDAO {

    //Aqui van los metodos con los que voy a sacar los datos de la BBDD

    //Metodo que me lista todos los clientes
    @Query("SELECT * FROM clientes")
    List<clientes> getAll();

    //Metodo para consultar la info de un  cliente a traves de su id
    @Query("SELECT * FROM clientes WHERE clienteID= :id")
    List<clientes> findById(long id);

    //Metodo para regitsrar un nuevo cliente
    @Insert
    void insert(clientes miCliente);

    //Metodo para borrar un cliente
    @Delete
    void eliminar(clientes miCliente);

    @Update
    void editar(clientes miCiente);
}
