package com.frlprojects.petscroll.database;

//La clase de contratos es un contenedor de constantes que
// definen nombres de URI, tablas y columnas
public class PetDbSchema {
    //Clase interna que define la estructura de nuestra tabla
    //Caso de necesitar mas tablas creariamos una clase interna para cada tabla
    public static final class PetTable{
        //Especificamos el nombre de la table dentro de la BD
        public static final String NAME = "pets";
        //Describimos las columnas que forman parte de la tabla
        public static final class Cols{
            public static final String UUID = "uuid";
            public static final String NAME = "name";
            public static final String SCORE = "score";
            public static final String IMAGE = "image";
        }
    }

}
