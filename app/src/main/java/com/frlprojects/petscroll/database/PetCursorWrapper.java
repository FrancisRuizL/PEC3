package com.frlprojects.petscroll.database;

import android.database.Cursor;
import android.database.CursorWrapper;


import java.util.Date;
import java.util.UUID;

import com.frlprojects.petscroll.Pet;
import com.frlprojects.petscroll.database.PetDbSchema.PetTable;

//Clase creada para empaquetar los métodos de tratamiento del cursor
//CursorWrapper nos permite envolver la clase Cursor y añadirle nuevos metodos

public class PetCursorWrapper extends CursorWrapper {
    public PetCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Pet getPet(){
        //Obtenemos el contenido de las columnas
        String uuidString = getString(getColumnIndex(PetTable.Cols.UUID));
        String name = getString(getColumnIndex(PetTable.Cols.NAME));
        int score = getInt(getColumnIndex(PetTable.Cols.SCORE));
        int image = getInt(getColumnIndex(PetTable.Cols.IMAGE));

        Pet pet = new Pet();
        pet.setId(UUID.fromString(uuidString));
        pet.setName(name);
        pet.setScore(score);
        pet.setImage(image);
        return pet;

    }
}