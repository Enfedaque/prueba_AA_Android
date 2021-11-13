package com.example.actividadaprendizaje1.BBDD;


import androidx.room.Database;

import com.example.actividadaprendizaje1.DAO.TrabajadoresDAO;
import com.example.actividadaprendizaje1.domain.Trabajadores;
import com.example.actividadaprendizaje1.domain.Vehiculos;

//Le indico que es una bbdd y con que tabla voy a trabajar
/*Cada vez que haga algun cambio en las anotaciones @ de la bbdd o algo que haga que afecte
 * a la bbdd tengo que cambiar el numero de version para que la bbdd sepa que tiene que
 * hacer cambios*/
@Database(entities = {Trabajadores.class}, version = 1)
public abstract class TrabajadoresBBDD {

    //Aqui hace de intermediario entre la clase y el DAO

    //Me creo un metodo abstracto que me devuelva el DAO
    public abstract TrabajadoresDAO trabajadoresDAO();
}
