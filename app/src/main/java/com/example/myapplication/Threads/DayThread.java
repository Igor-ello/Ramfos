package com.example.myapplication.Threads;


import com.example.myapplication.Data;
import com.example.myapplication.Main;
import com.example.myapplication.Parrots.Parrot;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DayThread extends Thread{
    private static int hour;
    private static int hourOld;

    Parrot parrot = Data.parrot;
    int timeDiff;
    final int timeWater = 30000;
    final int timeFood = 20000;
    final int timeUpdate = 10000;
    public static int score;

    public DayThread(int timeDiff){
        this.timeDiff = timeDiff;
    }

    public void run() {
        int count = 0;
        score = 1;

        while (parrot.getIsActive()) {
            System.out.println("\nНачало работы потока ДЕНЬ " + getName());
            SimpleDateFormat date = new SimpleDateFormat();
            date = new SimpleDateFormat("HH");
            hour = Integer.parseInt(date.format(new Date()));

            count += 1;
            try {
                if (count == 1){
                    Thread.sleep(timeDiff);
                    hourOld = hour;
                    //TODO
                    System.out.println("Операция: я спал " + timeDiff /1000 + " секунд");
                }
                else{
                    Thread.sleep(timeUpdate);
                    if(hour != hourOld){
                        if (0 <= hour && hour <= 8) {score += 1;}
                        if (8 < hour && hour <= 11) {score += 5;}
                        if (11 < hour && hour <= 15) {score += 1;}
                        if (18 < hour && hour <= 21) {score += 6;}
                        hourOld = hour;
                    }

                    parrot.eat(parrot);
                    parrot.drink(parrot);
                }
            } catch (InterruptedException e) {
                System.out.println("exception caught");
            }
            System.out.println("Поток " + getName() + " ДЕНЬ завершил работу.");
            Main.updatePrsBar();
        }
    }

}