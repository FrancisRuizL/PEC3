package com.frlprojects.petscroll.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.frlprojects.petscroll.Pet;
import com.frlprojects.petscroll.R;
import com.frlprojects.petscroll.database.PetDbSchema.PetTable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
//Clase que nos facilita la apertura y tratamiento de la base de datos.

public class PetBaseHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "petBase.db";

    public PetBaseHelper(Context context){
        super(context,DATABASE_NAME,null,VERSION);
    }
    //Codigo dedicado para crear la base de datos inicial
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + PetTable.NAME + " ( " +
                "id integer primary key autoincrement, " +
                PetTable.Cols.UUID + " TEXT, " +
                PetTable.Cols.NAME + " TEXT, " +
                PetTable.Cols.SCORE + " INTEGER, " +
                PetTable.Cols.IMAGE + " INTEGER" +
                ")"
        );
        List<Pet> pets = new ArrayList<>(
                Arrays.asList(
                        new Pet("Roco", R.drawable.roco),
                        new Pet("Dafne", R.drawable.dafne),
                        new Pet("Paco", R.drawable.paco),
                        new Pet("Richard", R.drawable.richard),
                        new Pet("Sober", R.drawable.sober),
                        new Pet("Venus", R.drawable.venus),
                        new Pet("Robin", R.drawable.robin),
                        new Pet("Roli", R.drawable.roli),
                        new Pet("Lana", R.drawable.lana),
                        new Pet("Sancho", R.drawable.sancho)
                )
        );
        ContentValues values = new ContentValues();
        for (int i = 0; i<pets.size();i++) {
            values.put(PetTable.Cols.UUID, pets.get(i).getId().toString());
            values.put(PetTable.Cols.NAME, pets.get(i).getName());
            values.put(PetTable.Cols.SCORE, pets.get(i).getScore());
            values.put(PetTable.Cols.IMAGE, pets.get(i).getImage());
            db.insert(PetTable.NAME, null, values);
        }
    }
    //Código destinado a gestionar cualquier actualización
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
