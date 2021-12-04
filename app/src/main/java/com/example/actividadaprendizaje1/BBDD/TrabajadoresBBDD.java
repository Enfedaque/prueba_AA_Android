package com.example.actividadaprendizaje1.bbdd;


import androidx.room.RoomDatabase;

import com.example.actividadaprendizaje1.dao.trabajadoresDAO;

//Le indico que es una bbdd y con que tabla voy a trabajar
/*Cada vez que haga algun cambio en las anotaciones @ de la bbdd o algo que haga que afecte
 * a la bbdd tengo que cambiar el numero de version para que la bbdd sepa que tiene que
 * hacer cambios*/
public abstract class trabajadoresBBDD extends RoomDatabase {

    //Aqui hace de intermediario entre la clase y el DAO

    //Me creo un metodo abstracto que me devuelva el DAO
    public abstract trabajadoresDAO trabajadoresDAO();
}
