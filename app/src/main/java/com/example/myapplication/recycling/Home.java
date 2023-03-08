package com.example.myapplication.recycling;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.myapplication.Main;
import com.example.myapplication.Info;
import com.example.myapplication.R;
import com.example.myapplication.Settings;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Date;
import java.util.Random;

public class Home extends AppCompatActivity {
    private static final String CHANNEL_ID = "";
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        SharedPreferences sharedPref = getSharedPreferences("MainActivity", Context.MODE_PRIVATE);


        String currentDateTimeString = java.text.DateFormat.getDateTimeInstance().format(new Date());


        TextView korm = findViewById(R.id.kormIcon);
        korm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), Korm.class);
                startActivity(intent);
            }
        });

        TextView training = findViewById(R.id.trainingIcon);
        training.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), Training.class);
                startActivity(intent);
            }
        });

        TextView cage = findViewById(R.id.cageIcon);
        cage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), Cages.class);
                startActivity(intent);
            }
        });

        TextView speak = findViewById(R.id.speakIcon);
        speak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), Speak.class);
                startActivity(intent);
            }
        });

        TextView game = findViewById(R.id.gameIcon);
        game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), Game.class);
                startActivity(intent);
            }
        });

        TextView imageHealth = findViewById(R.id.healthIcon);
        imageHealth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), Health.class);
                startActivity(intent);
            }
        });

        TextView imageNews = findViewById(R.id.newsIcon);
        imageNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), News.class);
                startActivity(intent);
            }
        });

        TextView imageRecommendations = findViewById(R.id.recommendationsIcon);
        imageRecommendations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), Recommendations.class);
                startActivity(intent);
            }
        });

        TextView imageInteresting = findViewById(R.id.careInteresting);
        imageInteresting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), Interesting.class);
                startActivity(intent);
            }
        });

    }



    final Random random = new Random();

    public void onClick(View v) {
        System.out.println(random.nextInt(6));
    }

    private String randomText(){
        int i = (int)random.nextInt(6);
        i++;
        String text = "Bender";

        switch (i){
            case 1:
                text =   (String) getResources().getText(R.string.timer_h1);
                break;
            case 2:
                text = (String) getResources().getText(R.string.timer_h2);
                break;
            case 3:
                text = (String) getResources().getText(R.string.timer_h3);
                break;
            case 4:
                text = (String) getResources().getText(R.string.timer_h4);
                break;
            case 5:
                text = (String) getResources().getText(R.string.timer_h5);
                break;
            case 6:
                text = (String) getResources().getText(R.string.timer_m30);
                break;
        }
        System.out.println(text);
        return text;
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Корм";
            String description = "Пора кормить";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}