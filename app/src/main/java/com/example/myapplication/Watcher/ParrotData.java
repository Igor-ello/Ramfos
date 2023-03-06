package com.example.myapplication.Watcher;

import com.example.myapplication.Data;
import com.example.myapplication.Parrots.Parrot;

import java.util.ArrayList;

public class ParrotData implements Subject {
    private ArrayList<Observer> observers;
    private Parrot parrot = Data.parrot;

    private int thirst = parrot.getThirst();
    private int hunger = parrot.getHunger();
    private int health = parrot.getHealth();
    private int happiness = parrot.getHappiness();
    private int vitamins = parrot.getVitamins();

    public ParrotData() {
        observers = new ArrayList<>();
    }

    public void registerObserver(Observer o) {
        observers.add(o);
    }

    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    public void notifyObservers() {
        for (Observer observer: observers) {
            observer.update();
        }
    }

    public void setMeasurements() {
        notifyObservers(); //оповещение наблюдателя про изменение значений
    }
}