package com.example.myapplication.recycling;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Info;
import com.example.myapplication.R;
import com.example.myapplication.Settings;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Gallery extends AppCompatActivity {
    private String name;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        Intent intent = getIntent();
        name = intent.getStringExtra("NAME");
        TextView txt = findViewById(R.id.txt);
        txt.setText(name + getResources().getText(R.string.Privet));

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
                        chatActivity();
                        break;
                    case R.id.navigation_gallery:
                        break;
                    case R.id.navigation_settings:
                        settingsActivity();
                        break;
                }
                return true;
            };
        });

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

    public void settingsActivity() {
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
    }

}

