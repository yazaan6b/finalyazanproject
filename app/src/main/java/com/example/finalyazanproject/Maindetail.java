package com.example.finalyazanproject;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.finalyazanproject.data.horsesubject.Horse;

public class Maindetail extends AppCompatActivity {
    private Horse horse;
    private TextView tvName, tvAge, tvBreed, tvPrice, tvOwner, tvLocation, tvTrained, tvTemperament;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_maindetail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvName = findViewById(R.id.tvName);
        tvAge = findViewById(R.id.tvAge);
        tvBreed = findViewById(R.id.tvBreed);
        tvPrice = findViewById(R.id.tvPrice);
        tvOwner = findViewById(R.id.tvOwner);
        tvLocation = findViewById(R.id.tvLocation);
        tvTrained = findViewById(R.id.tvTrained);
        tvTemperament = findViewById(R.id.tvTemperament);

        horse = (Horse) getIntent().getSerializableExtra("horse_data");

        if (horse != null) {
            tvName.setText(horse.getName());
            tvAge.setText(String.valueOf(horse.getAge()));
            tvBreed.setText(horse.getBreed());
            tvPrice.setText(String.valueOf(horse.getPrice()));
            tvOwner.setText(horse.getOwner());
            tvLocation.setText(horse.getLocation());
            tvTemperament.setText(horse.getTemperament());
            tvTrained.setText(horse.isTrained() ? "مدرّب" : "غير مدرّب");
        }
    }
}