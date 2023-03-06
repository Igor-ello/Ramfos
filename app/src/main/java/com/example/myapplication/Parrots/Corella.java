package com.example.myapplication.Parrots;

import com.example.myapplication.R;

public class Corella extends Parrot {
    public final static int DAY_FOOD = 35;
    public final static int DAY_WATER = 30;
    private final static String TYPE = "Corella";
    private final static int picture = R.drawable.corella;
    private String name;

    public Corella(String name){
        super(name, TYPE, picture);
    }

    @Override
    public int getPicture() {
        return picture;
    }

    public int getDayFood(){
        return DAY_FOOD;
    }
    public int getDayWater() {
        return DAY_WATER;
    }
}