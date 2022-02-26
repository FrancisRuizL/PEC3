package com.frlprojects.petscroll;

import android.content.Context;

import java.util.UUID;

public class Pet {

    private UUID mId;
    private String mName;
    private int mImage;
    private int mScore;

    public Pet(){
        this (UUID.randomUUID());
    }
    public Pet(UUID id){
        mId = id;
    }
    public Pet(String name, int image){
        this (UUID.randomUUID());
        this.mName = name;
        this.mScore = 0;
        this.mImage = image;
    }

    public UUID getId() {return mId; }
    public String getName() {return mName; }
    public int getScore() {return mScore; }
    public int getImage() {return mImage; }

    public void setId(UUID id) {mId = id; }
    public void setName(String name) {mName = name; }
    public void setScore(int score) {mScore = score; }
    public void setImage(int image) {mImage = image; }



}
