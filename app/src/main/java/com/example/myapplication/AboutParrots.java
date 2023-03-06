package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Parrots.Parrot;
import com.example.myapplication.Threads.DayThread;

import java.util.ArrayList;
import java.util.List;

public class AboutParrots extends AppCompatActivity {
    List<Integer> PictureParrot = new ArrayList<>();
    String[] headerList;
    String[] textList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parrots);

        headerList = getResources().getStringArray(R.array.array_ParrotHeader);
        textList = getResources().getStringArray(R.array.array_ParrotText);

        Intent intent = getIntent();
        Integer id = intent.getIntExtra("ID", 0);

        TextView header_parrot = findViewById(R.id.header_parrot);
        header_parrot.setText(headerList[id]);
        TextView text_parrot = findViewById(R.id.text_parrot);
        text_parrot.setText(textList[id]);

        createPictureList();
        ImageView image_parrot = findViewById(R.id.image_parrot);
        image_parrot.setImageResource(PictureParrot.get(id));

        Button setParrotButton = findViewById(R.id.setButton);
        setParrotButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Main.parrot.disable();
                Data.parrot_id = id;
                Data.setPerrot();
                Intent intent = new Intent(getApplication(), Main.class);
                startActivity(intent);
            }
        });

    }


    private void createPictureList(){
        PictureParrot.add(R.drawable.budgerigar_img);
        PictureParrot.add(R.drawable.corella_img);
        PictureParrot.add(R.drawable.jacko_img);
        PictureParrot.add(R.drawable.aratinga_img);
        PictureParrot.add(R.drawable.cockatoo_img);
        PictureParrot.add(R.drawable.ara_img);
        PictureParrot.add(R.drawable.amazon_img);
        PictureParrot.add(R.drawable.sinegal_img);
        PictureParrot.add(R.drawable.kalita_img);
        PictureParrot.add(R.drawable.guzo_img);
    }
}
