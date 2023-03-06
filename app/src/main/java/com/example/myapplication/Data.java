package com.example.myapplication;

import com.example.myapplication.Parrots.Jacko;
import com.example.myapplication.Parrots.Budgerigar;
import com.example.myapplication.Parrots.Corella;
import com.example.myapplication.Parrots.Parrot;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Data {
    Data(){}
    public static int maxScore = 14;

    public static HashMap<Integer, Parrot> parrotHashMap = getParrots();
    public static HashMap<Integer, Parrot> getParrots(){
        HashMap<Integer, Parrot> parrotList = new HashMap<>();
        parrotList.put(0, new Budgerigar("Kesha"));
        parrotList.put(1, new Corella("Oliver"));
        parrotList.put(2, new Jacko("Жак"));
        return parrotList;
    }
    public static Integer parrot_id = 0;
    public static Parrot parrot = parrotHashMap.get(parrot_id);
    public static void setPerrot(){
        parrot = parrotHashMap.get(parrot_id);
    }

    public static User user = User.getInstance();
    public static Cage cage = Cage.getInstance();

    public static int seasons[] = {R.drawable.autumn, R.drawable.winter, R.drawable.spring, R.drawable.summer};


}

