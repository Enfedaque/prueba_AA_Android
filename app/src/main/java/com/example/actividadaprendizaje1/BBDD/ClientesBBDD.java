package com.example.actividadaprendizaje1.BBDD;

//Le indico que es una bbdd y con que tabla voy a trabajar
/*Cada vez que haga algun cambio en las anotaciones @ de la bbdd o algo que haga que afecte
 * a la bbdd tengo que cambiar el numero de version para que la bbdd sepa que tiene que
 * hacer cambios*/

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.actividadaprendizaje1.DAO.ClientesDAO;
import com.example.actividadaprendizaje1.DAO.TrabajadoresDAO;
import com.example.actividadaprendizaje1.DAO.VehiculosDAO;
import com.example.actividadaprendizaje1.domain.Clientes;
import com.example.actividadaprendizaje1.domain.Trabajadores;
import com.example.actividadaprendizaje1.domain.Vehiculos;

@Database(entities = {Clientes.class, Trabajadores.class, Vehiculos.class}, version = 3)
public abstract class ClientesBBDD  extends RoomDatabase {
    //Aqui hace de intermediario entre la clase y el DAO

    //Me creo un metodo abstracto que me devuelva el DAO
    public abstract ClientesDAO clientesDAO();
    public abstract TrabajadoresDAO trabajadoresDAO();
    public abstract VehiculosDAO vehiculosDAO();
}
