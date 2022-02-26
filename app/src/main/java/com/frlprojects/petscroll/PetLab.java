package com.frlprojects.petscroll;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.frlprojects.petscroll.database.PetBaseHelper;
import com.frlprojects.petscroll.database.PetCursorWrapper;
import com.frlprojects.petscroll.database.PetDbSchema;
import com.frlprojects.petscroll.database.PetDbSchema.PetTable;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PetLab {

    private static PetLab sPetLab;
    private Context mContext;
    private SQLiteDatabase mDatabase;



    private PetLab(Context context){
        //Código para abrir la base de datos.
        mContext = context.getApplicationContext();


        mDatabase = new PetBaseHelper(mContext).getWritableDatabase();

    }
    public List<Pet> getPets(String order, String limit){


        List<Pet> pets = new ArrayList<>();
        PetCursorWrapper cursor = queryPets(order + " DESC",limit);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                pets.add(cursor.getPet());
                cursor.moveToNext();
            }
        }finally {
            cursor.close();
        }
        return pets;
    }


    public static PetLab get(Context context){
        if(sPetLab == null){
            sPetLab = new PetLab(context);
        }
        return  sPetLab;
    }

    private static ContentValues getContentValues(Pet pet){
        ContentValues values = new ContentValues();
        values.put(PetTable.Cols.UUID, pet.getId().toString());
        values.put(PetTable.Cols.NAME,pet.getName());
        values.put(PetTable.Cols.SCORE,pet.getScore());
        values.put(PetTable.Cols.IMAGE,pet.getImage());

        return values;
    }



    public void updatePet(Pet pet){
        String uuidString = pet.getId().toString();
        ContentValues values = getContentValues(pet);
        mDatabase.update(PetTable.NAME,values,
                PetTable.Cols.UUID + " = ?",
                new String[]{uuidString});
    }


    private PetCursorWrapper queryPets(String order, String limit){
        Cursor cursor = mDatabase.query(
                PetTable.NAME,
                null,
                null,
                null,
                null,
                null,
                order,
                limit

        );
        //return cursor;
        return new PetCursorWrapper(cursor);
    }

    }



