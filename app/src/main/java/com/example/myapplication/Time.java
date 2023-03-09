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

    public static final String APP_PREFERENCES = "settings";
    public static final String APP_PREFERENCES_DAY = "day";
    public static final String APP_PREFERENCES_DAY_LAST = "dayLast";
    public static final String APP_PREFERENCES_HOUR = "hour";
    public static final String APP_PREFERENCES_HOUR_LAST = "hourLast";
    public static final String APP_PREFERENCES_MINUTES = "minutes";
    public static final String APP_PREFERENCES_MINUTES_LAST = "minutesLast";
    public static SharedPreferences mSettings;


    @SuppressLint("SimpleDateFormat")
    static SimpleDateFormat date = new SimpleDateFormat();

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

    public static void initTimeLast(){
        SharedPreferences sharedPref = getInstance().getSharedPreferences("Time", Context.MODE_PRIVATE);
        String defaultValue = "0";
        dayLast = Integer.parseInt(sharedPref.getString("dayLast", defaultValue));
        hourLast = Integer.parseInt(sharedPref.getString("hourLast", defaultValue));
        minutesLast = Integer.parseInt(sharedPref.getString("minutesLast", defaultValue));
        timeLast = Integer.parseInt(sharedPref.getString("timeLast", defaultValue));
    }

    public static void saveTime(){
        dayLast = day;
        hourLast = hour;
        minutesLast = minutes;
        timeLast = valuesToTime(day, hour, minutes);

        SharedPreferences sharedPreferences = getInstance().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("dayLast", String.valueOf(dayLast));
        editor.putString("hourLast", String.valueOf(hourLast));
        editor.putString("minutesLast", String.valueOf(minutesLast));
        editor.putString("timeLast", String.valueOf(timeLast));
        editor.apply();

        System.out.println("Значения сохранены!");
    }

    public static void initTimeSkip(){
        int timeDifference = time - timeLast;
        System.out.println("Время в минутах: " + time + " Время: день "+ day + ", час " + hour + ", минуты " + minutes);
        System.out.println("Время в минутах: " + timeLast + " Время: день "+ dayLast+ ", час " + hourLast + ", минуты " + minutesLast);
        if (day - dayLast >= 3){
            System.out.println("ПОПУГАЙ НЕ ВЫЖИЛ ");
        }
        else {
            //TODO
        }
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


    public int getHour() {
        return hour;
    }
    public void setHour(int hour) {
        Time.hour = hour;
    }

    public int getHourLast() {
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
