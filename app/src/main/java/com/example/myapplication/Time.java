package com.example.myapplication;

import static com.example.myapplication.Main.dayThread;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Time extends AppCompatActivity {
    public static Time uniqueTime;

    @SuppressLint("SimpleDateFormat")
    private static SimpleDateFormat date = new SimpleDateFormat();
    private static int hourOld = 0;

    private static int day;
    private static int hour;
    private static int minutes;

    private static int dayLast;
    private static int hourLast;
    private static int minutesLast;

    private static int time;
    private static int timeLast;



    private Time() {}

    public static Time getInstance() {
        if (uniqueTime == null) {
            uniqueTime = new Time();
        }
        return uniqueTime;
    }

    @SuppressLint("SimpleDateFormat")
    public static void initTime(){
        date = new SimpleDateFormat("dd");
        day = Integer.parseInt(date.format(new Date()));

        date = new SimpleDateFormat("HH");
        hour = Integer.parseInt(date.format(new Date()));

        date = new SimpleDateFormat("mm");
        minutes = Integer.parseInt(date.format(new Date()));

        time = valuesToTime(day, hour, minutes);
    }

    public static void initTimeLast(SharedPreferences sharedPreferences){
        String defaultValue = "0";
        dayLast = Integer.parseInt(sharedPreferences.getString("dayLast", defaultValue));
        hourLast = Integer.parseInt(sharedPreferences.getString("hourLast", defaultValue));
        minutesLast = Integer.parseInt(sharedPreferences.getString("minutesLast", defaultValue));
        timeLast = Integer.parseInt(sharedPreferences.getString("timeLast", defaultValue));
    }

    public static void saveTime(SharedPreferences sharedPreferences){
        dayLast = day;
        hourLast = hour;
        minutesLast = minutes;
        timeLast = valuesToTime(day, hour, minutes);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("dayLast", String.valueOf(dayLast));
        editor.putString("hourLast", String.valueOf(hourLast));
        editor.putString("minutesLast", String.valueOf(minutesLast));
        editor.putString("timeLast", String.valueOf(timeLast));
        editor.apply();

        System.out.println("Значения сохранены!");
    }

    public static int timeRun(){
        initTime();
        hourOld = 0;
        int score = getScore(hour, hourOld);
        hourOld = hour;
        return score;

    }

    public static void timeSkip(){
        System.out.println("Сейчас время в минутах: " + time + " Время: день "+ day + ", час " + hour + ", минуты " + minutes);
        System.out.println("Старое время в минутах: " + timeLast + " Время: день "+ dayLast+ ", час " + hourLast + ", минуты " + minutesLast);
        if (day - dayLast >= 3){
            System.out.println("ПОПУГАЙ НЕ ВЫЖИЛ ");
            //TODO что делать в это случае?
        }
        else {
            System.out.println("ПОПУГАЙ НЕ ДОЕЛ " + getScoreSkip() + " ОЧКОВ");
            System.out.println("ДНЕВНОЙ МАКСИМУМ " + getDayMaxScore() + " ОЧКОВ");
        }
    }

    public static int getScore(int lastHour, int initialHour){
        int score = 0;
        for(int h = lastHour; h <= initialHour; h++) {
            if (6 < h && h <= 8) {score += 1;}
            if (8 < h && h <= 11) {score += 3;}
            if (11 < h && h <= 18) {score += 1;}
            if (18 < h && h <= 21) {score += 3;}
        }
        return score;
    }
    public static int getScoreSkip(){
        return getScore(hourLast, hour) + (day - dayLast) * getDayMaxScore();
    }

    public static int getDayMaxScore(){
        return getScore(0, 24);
    }

    public static int valuesToTime(int day, int hour, int min){
        return day * 24 * 60 + hour * 60 + min;
    }

















    public int getDay() {
        return day;
    }
    public void setDay(int day) {
        Time.day = day;
    }

    public int getDayLast() {
        return dayLast;
    }
    public void setDayLast(int dayLast) {
        Time.dayLast = dayLast;
    }


    public static int getHour() {
        return hour;
    }
    public void setHour(int hour) {
        Time.hour = hour;
    }

    public static int getHourLast() {
        return hourLast;
    }
    public void setHourLast(int hourLast) {
        Time.hourLast = hourLast;
    }


    public int getMinutes() {
        return minutes;
    }
    public void setMinutes(int minutes) {
        Time.minutes = minutes;
    }

    public int getMinutesLast() {
        return minutesLast;
    }
    public void setMinutesLast(int minutesLast) {
        Time.minutesLast = minutesLast;
    }

    public int getTime() {
        return time;
    }
    public void setTime(int time) {
        Time.time = time;
    }
}
