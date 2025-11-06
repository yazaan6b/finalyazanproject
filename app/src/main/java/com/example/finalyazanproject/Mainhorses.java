package com.example.finalyazanproject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Mainhorses extends AppCompatActivity
{
private TextView tvavilable,tvname1,tveng;
private LinearLayout layout1;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_mainhorses);
         ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        tvavilable = findViewById(R.id.tvavilable);
        tvname1 = findViewById(R.id.tvname1);
        tveng = findViewById(R.id.tveng);
        layout1 = findViewById(R.id.layout1);
        layout1.setOnClickListener(v ->
        {
            Intent intent = new Intent(Mainhorses.this, Maindetail.class);
            startActivity(intent);
            finish();
        });
    }
}