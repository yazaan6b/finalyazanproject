package com.example.finalyazanproject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.finalyazanproject.data.appdatabase.AppDB;
import com.example.finalyazanproject.data.horsesubject.Horse;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddHorseActivity extends AppCompatActivity {

    private EditText etHorseName;
    private EditText etHorseAge;
    private EditText etHorsePrice;
    private EditText etHorseDescription;
    private ImageView ivHorseImage;
    private Button btnSaveHorse;

    private DatabaseReference databaseReference;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_horse);

        etHorseName = findViewById(R.id.etHorseName);
        etHorseAge = findViewById(R.id.etHorseAge);
        etHorsePrice = findViewById(R.id.etHorsePrice);
        etHorseDescription = findViewById(R.id.etHorseLocation);
        ivHorseImage = findViewById(R.id.ivHorseImage);
        btnSaveHorse = findViewById(R.id.btnSaveHorse);

        // Firebase reference
        databaseReference = FirebaseDatabase.getInstance().getReference("Horses");

        btnSaveHorse.setOnClickListener(v -> saveHorse());
    }

    private boolean validateInputs() {
        boolean isValid = true;

        if (etHorseName.getText().toString().trim().isEmpty()) {
            etHorseName.setError("Please enter horse name");
            isValid = false;
        }
        if (etHorseAge.getText().toString().trim().isEmpty()) {
            etHorseAge.setError("Please enter horse age");
            isValid = false;
        }
        if (etHorsePrice.getText().toString().trim().isEmpty()) {
            etHorsePrice.setError("Please enter horse price");
            isValid = false;
        }
        if (etHorseDescription.getText().toString().trim().isEmpty()) {
            etHorseDescription.setError("Please enter horse description");
            isValid = false;
        }

        return isValid;
    }

    private void saveHorse() {

        if (!validateInputs()) {
            return;
        }

        String horseName = etHorseName.getText().toString().trim();
        int horseAge = Integer.parseInt(etHorseAge.getText().toString().trim());
        int horsePrice = Integer.parseInt(etHorsePrice.getText().toString().trim());
        String horseDescription = etHorseDescription.getText().toString().trim();

        Horse horse = new Horse();
        horse.setName(horseName);
        horse.setAge(horseAge);
        horse.setPrice(horsePrice);
        horse.setDescription(horseDescription);

        // 🔥 حفظ على Firebase
        databaseReference.child(horseName).setValue(horse);

        // 💾 حفظ على Room
        AppDB.getInstance(this).myHorseQuery().insert(horse);

        Toast.makeText(this, "Horse added successfully", Toast.LENGTH_SHORT).show();

        startActivity(new Intent(AddHorseActivity.this, Mainmenu.class));
        finish();
    }
}