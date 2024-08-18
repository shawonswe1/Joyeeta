package com.queueit.joyeeta;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class StartActivity extends AppCompatActivity {

    PrefManager prefManager;
    TextView jamdaniGallery,jayeetaTower,startText;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        jamdaniGallery = findViewById(R.id.jamdani_Gallery);
        jayeetaTower = findViewById(R.id.jayeeta_Tower);
        startText = findViewById(R.id.startText);
        prefManager = new PrefManager(this);
        if (prefManager.getLanguage().equals("Bangla")){
            jamdaniGallery.setText("জামদানি গ্যালারি");
            jayeetaTower.setText("জয়িতা টাওয়ার");
            startText.setText("শুরু করুন");
        }else {
            jamdaniGallery.setText("Jamdani Gallery");
            jayeetaTower.setText("Jayeeta Tower");
            startText.setText("Start");
        }
        startText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StartActivity.this,HomeActivity.class));
                finish();
            }
        });
    }
}