package com.example.myapplication.Parrots;

import com.example.myapplication.R;

public class Budgerigar extends Parrot {
//    Random random = new Random();
    public final static int DAY_FOOD_CHANGE = 15;
    public final static int DAY_WATER_CHANGE = 12;
    private final static String TYPE = "Budgerigar";
    private final static int picture = R.drawable.budgerigar;
    private String name;

//    public int middleWater = 12;
//    public int middleFood = 15;

    public Budgerigar(String name){
        super(name, TYPE, picture);
    }

//    public int randomFood(){
//        return random.nextInt(5) + middleFood;
//    }

    @Override
    public int getPicture() {
        return picture;
    }

    public int getDayFood(){
        return DAY_FOOD_CHANGE;
    }
    public int getDayWater(){
        return DAY_WATER_CHANGE;
    }
}
