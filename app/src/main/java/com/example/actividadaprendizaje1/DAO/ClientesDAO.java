package com.example.actividadaprendizaje1.DAO;

import android.widget.AdapterView;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.actividadaprendizaje1.domain.Clientes;
import com.example.actividadaprendizaje1.domain.Vehiculos;

import java.util.List;

@Dao
public interface ClientesDAO {

    //Aqui van los metodos con los que voy a sacar los datos de la BBDD

    //Metodo que me lista todos los clientes
    @Query("SELECT * FROM Clientes")
    List<Clientes> getAll();

    //Metodo para consultar la info de un  cliente a traves de su id
    @Query("SELECT * FROM Clientes WHERE clienteID= :id")
    List<Clientes> findById(long id);

    //Metodo para regitsrar un nuevo cliente
    @Insert
    void insert(Clientes miCliente);

    //Metodo para borrar un cliente
    @Delete
    void eliminar(Clientes miCliente);

    @Update
    void editar(Clientes miCiente);
}
