package com.example.myapplication.Threads;


import static com.example.myapplication.Time.getScoreSkip;
import static com.example.myapplication.Time.timeRun;

import com.example.myapplication.Data;
import com.example.myapplication.Main;
import com.example.myapplication.Parrots.Parrot;
import com.example.myapplication.Time;

public class DayThread extends Thread{
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
        score = getScoreSkip();

        while (parrot.getIsActive()) {
            System.out.println("\nНачало работы потока ДЕНЬ " + getName());
            try {
                Thread.sleep(timeUpdate);
                timeRun();
                parrot.eat(parrot);
                parrot.drink(parrot);
            } catch (InterruptedException e) {
                System.out.println("exception caught");
            }
            System.out.println("Поток " + getName() + " ДЕНЬ завершил работу.");
            Main.updatePrsBar();
        }
    }

}