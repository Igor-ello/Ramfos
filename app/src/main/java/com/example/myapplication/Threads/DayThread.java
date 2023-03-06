package com.example.myapplication.Threads;


import com.example.myapplication.Data;
import com.example.myapplication.Main;
import com.example.myapplication.Parrots.Parrot;

public class DayThread extends Thread{
    public static int day;
    public static int hour;
    public static int min;

    Parrot parrot = Data.parrot;
    int timeDiff;
    final int timeWater = 30000;
    final int timeFood = 20000;
    final int timeUpdate = 10000;
    int totalTime;
    public static int score;

    public DayThread(int timeDiff){
        this.timeDiff = timeDiff;
    }

    public void run() {
        int count = 0;
        totalTime = 0;
        score = 0;

        while (parrot.getIsActive()) {
            System.out.println("\nНачало работы потока ДЕНЬ " + getName());
            count += 1;
            try {
                if (count == 1){
                    Thread.sleep(timeDiff);
                    System.out.println("Операция: я спал " + timeDiff /1000 + " секунд");
                }
                else{
                    Thread.sleep(timeUpdate);
                    score += 1;
                    totalTime += timeUpdate;
                    if (totalTime % timeFood == 0){
                        parrot.eat(parrot);
                        System.out.println("Операция: попугай поел");
                    }
                    if (totalTime % timeWater == 0){
                        parrot.drink(parrot);
                        System.out.println("Операция: попугай попил");
                    }
                }
            } catch (InterruptedException e) {
                System.out.println("exception caught");
            }
//            parrot.Info_demo(parrot);
            System.out.println("Поток " + getName() + " ДЕНЬ завершил работу.");
            Main.updatePrsBar();
        }
    }
}