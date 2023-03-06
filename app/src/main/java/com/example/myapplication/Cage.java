package com.example.myapplication;


import com.example.myapplication.Parrots.Parrot;

public class Cage {
    public static Cage uniqueCage;
    private int cage_size_X;
    private int cage_size_Y;
    private int cage_size_Z;
    private boolean dirty_cage;
    private int food;
    private int foodTotal;
    private int water;
    private int waterTotal;
    private int vitamins;
    private Parrot parrot;


    private Cage() {
        this.dirty_cage = false;
        this.food = 0;
        this.foodTotal = 0;
        this.water = 0;
        this.waterTotal = 0;
        this.vitamins = 0;
    }


    public static Cage getInstance() {
        if (uniqueCage == null) {
            uniqueCage = new Cage();
        }
        return uniqueCage;
    }


    public void setDimension(int x, int y, int z){
        this.cage_size_X = x;
        this.cage_size_Y = y;
        this.cage_size_Z = z;
    }



    //FOOD
    public void addFood(int food) {
        this.food += food;
    }
    public int getFood() {
        return this.food;
    }
    public void setFood(int value) {
        this.food = checkInputValueMin(value);
    }

    public void addFoodTotal(int food) {
        this.foodTotal += food;
    }
    public int getFoodTotal() {
        return this.foodTotal;
    }
    public void setFoodTotal(int food) {
        this.foodTotal = food;
    }



    //WATER
    void addWater(int water){
        this.water += water;
    }
    public int getWater() {return this.water;}
    public void setWater(int value) {
        this.water = checkInputValueMin(value);
    }

    public void addWaterTotal(int water) {
        this.waterTotal += water;
    }
    public int getWaterTotal() {
        return this.waterTotal;
    }
    public void setWaterTotal(int water) {
        this.waterTotal = water;
    }



    //VITAMINS
    public int getVitamins() {return this.vitamins;}
    public void addVitamins(int vitamins) {this.vitamins += vitamins;}
    public void setVitamins(int vitamins) {this.vitamins = vitamins;}
    public void useVitamins(int vitamins) {this.vitamins -= vitamins;}


    //OTHER
    public void putParrotInCage(Parrot parrot){
        this.parrot = parrot;
    }
    public Parrot getParrotInCage(){
        return this.parrot;
    }
    public String getParrotName(){
        return this.parrot.getName();
    }


    public int checkInputValueMin(int value){
        if (value < 0){
            return 0;
        }
        else{
            return value;
        }
    }

    public static void Info(Cage cage){
        System.out.println("\nВ клкетке " + cage.getFood() + " гр. еды, "
                + cage.getWater() + " мл. воды, "
                + cage.getVitamins() + " шт.\n"
        );
    }
}
