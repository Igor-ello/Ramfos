package com.example.myapplication;

public class Random extends java.util.Random {
    static Random random = new Random();

    public static int getRandomInRange(int min, int max){
        int diff = max - min;
        int i = random.nextInt(diff + 1);
        System.out.println(i);
        return i;
    }
};

