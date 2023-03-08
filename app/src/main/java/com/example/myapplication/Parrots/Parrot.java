package com.example.myapplication.Parrots;

import static com.example.myapplication.Data.cage;
import static com.example.myapplication.Data.parrot;

import static java.lang.Math.abs;

import com.example.myapplication.Data;
import com.example.myapplication.Threads.DayThread;

public abstract class Parrot {
    private final static int maxHunger = 100;
    private final static int maxThirst = 100;
    private final static int maxHealth = 100;
    private final static int subtractHealth = 25;      // стандартоное знач. уменьшения жизни
    private final static int augmentHealth = 25;      // стандартоное знач. уменьшения жизни
    private final static int percentBorder = 30;  // если парам. жажды или врды подают ниже 30%, то отбавляются жизни

    private String name;
    private String type;
    private int health;
    private int happiness;
    private int thirst;
    private int thirstTotal;
    private int hunger;
    private int hungerTotal;
    private int vitamins;
    private Boolean isActive;

    Parrot(String name, String type, int picture){
        this.name = name;
        this.type = type;
        this.isActive = Boolean.TRUE;

        this.health = 100;
        this.thirst = 100;
        this.thirstTotal = 100;
        this.happiness = 100;
        this.hunger = 100;
        this.hungerTotal = 100;
        this.vitamins = 100;
    }

    public Boolean getIsActive() {return isActive;}
    public void disable() {isActive = Boolean.FALSE;}
    public void enable() {isActive = Boolean.TRUE;}

    public String getName() {return name;}
    public String getType() {return type;}
    public void setName(String name){
        this.name = name;
    }

    //ЖАЖДА
    public int getThirst() {return this.thirst;}
    public void setThirst(int value) {this.thirst = checkInputValueMin(value);}
    public void addThirst(int value) {this.thirst = checkInputValueMax(this.thirst, value);}

    public int getThirstTotal() {return this.thirstTotal;}
    public void setThirstTotal(int value) {this.thirstTotal = Math.abs(value);}

    //ГОЛОД
    public int getHunger() {return this.hunger;}
    public void setHunger(int value) {this.hunger = checkInputValueMin(value);}
    public void addHunger(int value) {this.hunger = checkInputValueMax(this.hunger, value);}

    public int getHungerTotal() {return this.hungerTotal;}
    public void setHungerTotal(int value) {this.hungerTotal = Math.abs(value);}

    //ВИТАМИНЫ
    public int getVitamins() {return this.vitamins;}
    public void setVitamins(int value) {this.vitamins += value;}

    //ЗДОРОВЬЕ
    public int getHealth() {return this.health;}
    public void setHealth(int value) {this.health = checkInputValueMin(value);}
    public void addHealth(int value) {this.health = checkInputValueMax(this.health, value);}

    //СЧАСТЬЕ
    public int getHappiness() {return this.happiness;}
    public void setHappiness(int value) {this.happiness += value;}

    public abstract int getPicture();

    abstract int getDayFood();
    abstract int getDayWater();

    public int scoreToGrams(String param){
        int grams;
        int score = DayThread.score;
        switch (param){
            case "eat":
                grams = (int) Math.round(1.0 * parrot.getDayFood() * score / Data.maxScore);
                break;
            case "drink":
                grams = (int) Math.round(1.0 * parrot.getDayWater() * score / Data.maxScore);
                break;
            default:
                System.out.println("Внимание! не прввльня комманда " + param);
                grams = 0;
                break;
        }
        return grams;
    }

    public int scoreToPercent(int score){
        return (int) Math.round(1.0 * 100 * score / Data.maxScore);
    }

    public int gramsToPercent(String param, int grams){
        switch (param){
            case "eat":
                return (int) Math.round(1.0 * grams * 100 / parrot.getDayFood());
            case "drink":
                return (int) Math.round(1.0 * grams * 100 / parrot.getDayWater());
            default:
                System.out.println("Внимание! не прввльня комманда " + param);
                return 0;
        }
    }

    public int percentToGrams(int percent){
        return (int) Math.round(1.0 * percent * parrot.getDayFood() / 100);
    }

    public void vitamins(Parrot parrot){
        if (cage.getVitamins() > 0 && parrot.getHealth() < maxHealth){
            cage.setVitamins(cage.getVitamins() - 1);
            parrot.addHealth(augmentHealth);
        }
    }

    public void eat(Parrot parrot) {
        int score = DayThread.score;
        parrot.setHungerTotal(scoreToPercent(score));                                   //сколько попугай съел за день
        parrot.setHunger(maxHunger + gramsToPercent("eat", cage.getFoodTotal())
                - parrot.getHungerTotal());                                             //насколько попугай голоден (prsBar)
        cage.setFood(cage.getFoodTotal() - scoreToGrams("eat"));                  //сколько осталось корма в кормушке

        if(parrot.getHunger() < percentBorder){
            parrot.setHealth(parrot.getHealth() - subtractHealth);
        }
        vitamins(parrot);

        System.out.println("Операция: попугай поел");
    }

    public void drink(Parrot parrot){
        int score = DayThread.score;
        parrot.setThirstTotal(scoreToPercent(score));                                     //сколько попугай выпил за день
        parrot.setThirst(maxThirst + gramsToPercent("drink", cage.getWaterTotal())
                - parrot.getThirstTotal());                                               //насколько попугай хочет пить (prsBar)
        cage.setWater(cage.getWaterTotal() - scoreToGrams("drink"));                //сколько осталось воды в поилке

        if(parrot.getThirst() < percentBorder){
            parrot.setHealth(parrot.getHealth() - subtractHealth);
        }
        vitamins(parrot);

        System.out.println("Операция: попугай попил");
    }


    public int checkInputValueMax(int now, int value){
        if (value < 0){
            now -= value;
            return now;
        }
        else{
            if (now + value > 100){
                now = 100;
            }
            else{
                now += value;
            }
            return now;
        }
    }

    public int checkInputValueMin(int value){
        if (value < 0){
            return 0;
        }
        else{
            return value;
        }
    }

    public void Info(Parrot parrot) {
        System.out.println(
                '\n' +
                "Тип попугая: " + parrot.getType() + '\n'
                        + "имя попугая: " + name + '\n'
                        + "Сытость попугая: " + hunger + '\n'
                        + "Жажда попугая: " + thirst + '\n'
                        + "Витамины попугая: " + vitamins + '\n'
                        + "Здоровье попугая: " + health + '\n'
                        + "Счастье попугая: " + happiness + '\n'
                        + "Попугай в день съедает: " + parrot.getDayFood() + '\n'
                        + "Попугай в день выпивает: " + parrot.getDayWater() + '\n'
                + '\n'
        );
    }

    public void Info_demo(Parrot parrot) {
        System.out.println(
                        "Тип попугая: " + parrot.getType() + '\n'
                        + "Имя попугая: " + name + '\n'
        );
    }

}
