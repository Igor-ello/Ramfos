package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.AboutParrots;
import com.example.myapplication.R;
import com.example.myapplication.recycling.Home;

public class Info extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        TextView  textView_budgies = findViewById(R.id.budgies);
        textView_budgies.setOnClickListener(v->{
            send(0);
        });

        TextView textView_corella = findViewById(R.id.corella);
        textView_corella.setOnClickListener(v->{
            send(1);
        });

        TextView textView_jacko = findViewById(R.id.jacko);
        textView_jacko.setOnClickListener(v->{
            send(2);
        });

        TextView textView_aratinga = findViewById(R.id.aratinga);
        textView_aratinga.setOnClickListener(v->{
            send(3);
        });

        TextView textView_cockatoo = findViewById(R.id.cockatoo);
        textView_cockatoo.setOnClickListener(v->{
            send(4);
        });

        TextView textView_ara = findViewById(R.id.ara);
        textView_ara.setOnClickListener(v->{
            send(5);
        });

        TextView textView_amazon = findViewById(R.id.amazon);
        textView_amazon.setOnClickListener(v->{
            send(6);
        });

        TextView textView_sinegal = findViewById(R.id.sinegal);
        textView_sinegal.setOnClickListener(v->{
            send(7);
        });

        TextView textView_kalita = findViewById(R.id.kalita);
        textView_kalita.setOnClickListener(v->{
            send(8);
        });

        TextView textView_guzo = findViewById(R.id.guzo);
        textView_guzo.setOnClickListener(v->{
            send(9);
        });



    }
    public void send(int id){
        Intent intent = new Intent(this, AboutParrots.class);

        intent.putExtra("ID", id);
        startActivity(intent);
    }

    public void homeActivity(){
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }

}