package com.example.myapplication.recycling;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextInputEditText name = findViewById(R.id.name_input);
        TextInputEditText mes = findViewById(R.id.mes_input);

        SharedPreferences sharedPref = getSharedPreferences("MainActivity", Context.MODE_PRIVATE);
        if (sharedPref.contains("NAME")){
            Intent intent = new Intent(this, Home.class);
            startActivity(intent);
        };

        //Button nextButton = findViewById(R.id.next_button);
    }

    public void homeActivity(View view){
        Intent intent = new Intent(this, Home.class);
        TextInputEditText god = findViewById(R.id.god_input);
        TextInputEditText nameText = findViewById(R.id.name_input);
        String name = nameText.getText().toString();
        String godString = god.getText().toString();

        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("NAME", name);
        editor.putString("GOD", godString);
        editor.apply();

        //intent.putExtra("NAME", name);
        //intent.putExtra("GOD", godString);
        startActivity(intent);

    }
}