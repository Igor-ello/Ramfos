package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class Settings extends AppCompatActivity {
    Toast toast;
    int season;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        SharedPreferences sharedPref = getSharedPreferences("Main", Context.MODE_PRIVATE);
        String name = sharedPref.getString("NAME", "");
        season = sharedPref.getInt("season", 0);

        TextInputEditText getName = findViewById(R.id.changeName);
        getName.setText(name);


        RadioGroup radioGroup = findViewById(R.id.radioGroup);

        switch (season){
            case 0:
                radioGroup.check(R.id.radioButtonAutumn);
                toast = Toast.makeText(getApplicationContext(), "Сезон ОСЕНЬ уже активен", Toast.LENGTH_SHORT);
                break;
            case 1:
                radioGroup.check(R.id.radioButtonWinter);
                toast = Toast.makeText(getApplicationContext(), "Сезон ЗИМА уже активен", Toast.LENGTH_SHORT);
                break;
            case 2:
                radioGroup.check(R.id.radioButtonSpring);
                toast = Toast.makeText(getApplicationContext(), "Сезон ВЕСНА уже активен", Toast.LENGTH_SHORT);
                break;
            case 3:
                radioGroup.check(R.id.radioButtonSummer);
                toast = Toast.makeText(getApplicationContext(), "Сезон ЛЕТО уже активен", Toast.LENGTH_SHORT);
                break;
            default:
                break;
        }

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radioButtonAutumn:
                        toast = Toast.makeText(getApplicationContext(), "Сезон ОСЕНЬ активирован", Toast.LENGTH_SHORT);
                        season = 0;
                        break;
                    case R.id.radioButtonWinter:
                        toast = Toast.makeText(getApplicationContext(), "Сезон ЗИМА активирован", Toast.LENGTH_SHORT);
                        season = 1;
                        break;
                    case R.id.radioButtonSpring:
                        toast = Toast.makeText(getApplicationContext(), "Сезон ВЕСНА активирован", Toast.LENGTH_SHORT);
                        season = 2;
                        break;
                    case R.id.radioButtonSummer:
                        toast = Toast.makeText(getApplicationContext(), "Сезон ЛЕТО активирован", Toast.LENGTH_SHORT);
                        season = 3;
                        break;
                    default:
                        break;
                }
            }
        });


        Button buttonSave = findViewById(R.id.button_save);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toast.show();

                SharedPreferences.Editor editor = sharedPref.edit();
                if(getName.getText().toString() != ""){
                    editor.putString("NAME", getName.getText().toString());}
                else {
                    editor.putString("NAME", name);
                }
                editor.putInt("season", season);
                editor.apply();

                Main.parrot.disable();
                Intent intent = new Intent(getApplication(), Main.class);
                startActivity(intent);
            }
        });

    }
}