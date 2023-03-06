package com.example.myapplication.Parrots;

import com.example.myapplication.R;

public class Jacko extends Parrot {
    public final static int DAY_FOOD = 30;
    public final static int DAY_WATER = 30;
    private final static String TYPE = "Jacko";
    private final static int picture = R.drawable.amazon;
    private String name;

    public Jacko(String name){
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
