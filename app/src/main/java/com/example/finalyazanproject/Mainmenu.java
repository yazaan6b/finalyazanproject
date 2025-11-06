package com.example.finalyazanproject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Mainmenu extends AppCompatActivity
{
private TextView tvarab,tvmanu1,tvallhorses,tvsearch,tvdetail,tvsettings;
private ImageButton btnSettings;
private LinearLayout layout1,layout2,layout3,layout4;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_mainmenu);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        tvarab = findViewById(R.id.tvarab);
        tvmanu1 = findViewById(R.id.tvmanu1);
        tvallhorses = findViewById(R.id.tvallhorses);
        tvsearch = findViewById(R.id.tvsearch);
        tvdetail = findViewById(R.id.tvdetail);
        tvsettings = findViewById(R.id.tvsettings);
        btnSettings = findViewById(R.id.btnSettings);
        layout1 = findViewById(R.id.layout1);
        layout2 = findViewById(R.id.layout2);
        layout3 = findViewById(R.id.layout3);
        layout4 = findViewById(R.id.layout4);
        layout1.setOnClickListener(v ->
        {
            Intent intent = new Intent(Mainmenu.this, Mainhorses.class);
            startActivity(intent);
            finish();
        });
    }
}