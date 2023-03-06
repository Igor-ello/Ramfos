package com.example.myapplication.recycling;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication.Info;
import com.example.myapplication.R;
import com.example.myapplication.Settings;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Chat extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        Button button_time_eat = findViewById(R.id.button_time_eat);
        button_time_eat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int startHour = 0;
                int startMinute = 0;
                EditText startTimeHour = findViewById(R.id.first_eat_hour);
                startHour = Integer.parseInt(String.valueOf(startTimeHour.getText()));
                EditText startTimeMinute = findViewById(R.id.first_eat_minute);
                startMinute = Integer.parseInt(String.valueOf(startTimeMinute.getText()));

                createAlarm("Покорми попугая!", startHour, startMinute);
                createAlarm("Покорми попугая!", startHour + 6, startMinute);
                createAlarm("Покорми попугая!", startHour + 12, startMinute);
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.icon_menu);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        homeActivity();
                        break;
                    case R.id.navigation_info:
                        infoActivity();
                        break;
                    case R.id.navigation_chat:
                        break;
                    case R.id.navigation_gallery:
                        galleryActivity();
                        break;
                    case R.id.navigation_settings:
                        settingsActivity();
                        break;
                }
                return true;
            };
        });

    }

    public void createAlarm(String message, int hour, int minutes) {
        Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM)
                .putExtra(AlarmClock.EXTRA_MESSAGE, message)
                .putExtra(AlarmClock.EXTRA_HOUR, hour)
                .putExtra(AlarmClock.EXTRA_MINUTES, minutes);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void homeActivity(){
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }

    public void infoActivity(){
        Intent intent = new Intent(this, Info.class);
        startActivity(intent);
    }

    public void chatActivity(){
        Intent intent = new Intent(this, Chat.class);
        startActivity(intent);
    }

    public void galleryActivity(){
        Intent intent = new Intent(this, Gallery.class);
        startActivity(intent);
    }

    public void settingsActivity(){
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
    }
}