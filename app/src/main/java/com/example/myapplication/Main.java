package com.example.myapplication;

import static com.example.myapplication.Time.initTime;
import static com.example.myapplication.Time.initTimeLast;
import static com.example.myapplication.Time.initTimeSkip;
import static com.example.myapplication.Time.saveTime;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.example.myapplication.Parrots.Parrot;
import com.example.myapplication.Threads.DayThread;

public class Main extends AppCompatActivity {
    static Parrot parrot;
    static Cage cage = Data.cage;
    private static SharedPreferences sharedPreferences;
    public static Thread dayThread;
    int season;

    private static TextView textWater;
    private static TextView textFood;
    private static TextView textVitamins;

    private static TextView nameParrot;
    private static TextView typeParrot;

    private static ProgressBar prsBarWater;
    private static ProgressBar prsBarFood;
    private static ProgressBar prsBarVitamins;
    private static ProgressBar prsBarHeath;
    private static ProgressBar prsBarHappiness;

    Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_perrot);
        sharedPreferences = getSharedPreferences("Main", Context.MODE_PRIVATE);

        initParrot();

        LinearLayout cageInfoTable = findViewById(R.id.cageInfo);
        season = sharedPreferences.getInt("season", 0);
        CoordinatorLayout layout = findViewById(R.id.layout);
        layout.setBackgroundResource(Data.seasons[season]);

        initTime();
        initTimeLast(sharedPreferences);
        initTimeSkip();

        dayThread = new DayThread(1000);
        dayThread.start();

        initTextView();
        updateTextView();

        initPrsBar();
        updatePrsBar();

        ImageView updateFeeder = findViewById(R.id.updateFeeder);
        updateFeeder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateTextView();
            }
        });

        Button saveValue = findViewById(R.id.saveTime);
        saveValue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveTime(sharedPreferences);
            }
        });

        Button settingsButton = findViewById(R.id.settingsButton);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), Settings.class);
                startActivity(intent);
            }
        });


        ImageView openPageParrots = findViewById(R.id.parrots);
        openPageParrots.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), Info.class);
                startActivity(intent);
            }
        });


        TextView giveFoodButton = findViewById(R.id.putFood);
        giveFoodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cage.addFood(5);
                cage.addFoodTotal(5);
                Main.updateTextView();

                toast = Toast.makeText(getApplicationContext(),
                        "Покормили!", Toast.LENGTH_SHORT);
                toast.show();
            }
        });


        TextView giveWaterButton = findViewById(R.id.putWater);
        giveWaterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cage.addWater(5);
                cage.addWaterTotal(5);
                Main.updateTextView();

                toast = Toast.makeText(getApplicationContext(),
                        "Напоили!", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        TextView giveVitaminsButton = findViewById(R.id.putVitamins);
        giveVitaminsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cage.addVitamins(1);
                Main.updateTextView();
            }
        });


        ImageView cageButton = findViewById(R.id.feeder);
        cageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cageInfoTable.getVisibility() == View.INVISIBLE){
                    cageInfoTable.setVisibility(View.VISIBLE);
                }
                else{
                    cageInfoTable.setVisibility(View.INVISIBLE);
                }
            }
        });

        Button info = findViewById(R.id.infoButton);
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initTime();
                initTimeLast(sharedPreferences);
                initTimeSkip();
            }
        });

    }

    @SuppressLint("SetTextI18n")
    public static void updateTextView(){
        textWater.setText(Integer.toString(cage.getWater()));
        textFood.setText(Integer.toString(cage.getFood()));
        textVitamins.setText(Integer.toString(cage.getVitamins()));

        nameParrot.setText(parrot.getName());
        typeParrot.setText(parrot.getType());
    }

    public static void updatePrsBar(){
        prsBarWater.setProgress(parrot.getThirst());
        prsBarFood.setProgress(parrot.getHunger());
        prsBarVitamins.setProgress(parrot.getVitamins());
        prsBarHappiness.setProgress(parrot.getHappiness());
        prsBarHeath.setProgress(parrot.getHealth());
    }

    private void initPrsBar() {
        prsBarWater = findViewById(R.id.progress_water);
        prsBarFood = findViewById(R.id.progress_food);
        prsBarVitamins = findViewById(R.id.progress_vitamins);
        prsBarHeath = findViewById(R.id.progress_health);
        prsBarHappiness = findViewById(R.id.progress_happiness);
    }

    private void initTextView() {
        textWater = findViewById(R.id.textWater);
        textFood = findViewById(R.id.textFood);
        textVitamins = findViewById(R.id.textVitamins);

        nameParrot = findViewById(R.id.nameParrot);
        typeParrot = findViewById(R.id.typeParrot);
    }

    private void initParrot(){
        cage.putParrotInCage(parrot);
        parrot = Data.parrot;
        parrot.enable();

        ImageView view = findViewById(R.id.parrotImage);
        view.setBackgroundResource(parrot.getPicture());
    }

    public void info(){
        parrot.Info(parrot);
        Cage.Info(cage);
    }

}




// ВЫПАДАЮЩИЙ СПИСОК
//    TextView selection = findViewById(R.id.selection);
//
//    Spinner spinner = findViewById(R.id.spinner);
//    // Создаем адаптер ArrayAdapter с помощью массива строк и стандартной разметки элемета spinner
//    ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, cages);
//// Определяем разметку для использования при выборе элемента
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                // Применяем адаптер к элементу spinner
//                spinner.setAdapter(adapter);
//
//                AdapterView.OnItemSelectedListener itemSelectedListener = new AdapterView.OnItemSelectedListener() {
//@Override
//public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//
//        // Получаем выбранный объект
//        String item = (String)parent.getItemAtPosition(position);
//        selection.setText(item);
//        }
//
//@Override
//public void onNothingSelected(AdapterView<?> parent) {
//        }
//        };
//        spinner.setOnItemSelectedListener(itemSelectedListener);



//        Button stopThreadsButton = findViewById(R.id.stopThreadsButton);
//        stopThreadsButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Data.isEnabled = Boolean.FALSE;
//            }
//        });


//
//    public static void animatePrsBar(ProgressBar prsBar, int valueNow){
//        int apelsin = valueNow - prsBar.getProgress();
//        int progress = valueNow;
//
//    }
//
//    public void setProgressValue(int progress){
//        prsBar.setProgress(progress);
//        if (apelsin > 0) {
//            Thread thread = new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    setProgressValue(progress - 1);
//                }
//            });
//            thread.start();
//        }
//    }