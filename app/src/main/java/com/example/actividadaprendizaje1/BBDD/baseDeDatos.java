package com.example.actividadaprendizaje1.bbdd;

//Le indico que es una bbdd y con que tabla voy a trabajar
/*Cada vez que haga algun cambio en las anotaciones @ de la bbdd o algo que haga que afecte
 * a la bbdd tengo que cambiar el numero de version para que la bbdd sepa que tiene que
 * hacer cambios*/

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.actividadaprendizaje1.dao.clientesDAO;
import com.example.actividadaprendizaje1.dao.trabajadoresDAO;
import com.example.actividadaprendizaje1.dao.vehiculosDAO;
import com.example.actividadaprendizaje1.domain.clientes;
import com.example.actividadaprendizaje1.domain.trabajadores;
import com.example.actividadaprendizaje1.domain.vehiculos;

@Database(entities = {clientes.class, trabajadores.class, vehiculos.class}, version = 5)
public abstract class baseDeDatos extends RoomDatabase {
    //Aqui hace de intermediario entre la clase y el DAO

    //Me creo un metodo abstracto que me devuelva el DAO
    public abstract clientesDAO clientesDAO();
    public abstract trabajadoresDAO trabajadoresDAO();
    public abstract vehiculosDAO vehiculosDAO();
}
