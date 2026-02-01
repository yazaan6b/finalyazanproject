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

public class AddHorseActivity extends AppCompatActivity {

    private EditText etHorseName;
    private EditText etHorseAge;
    private EditText etHorsePrice;
    private EditText etHorseDescription;
    private ImageView ivHorseImage;
    private Button btnSaveHorse;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_horse);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
        etHorseName = findViewById(R.id.etHorseName);
        etHorseAge = findViewById(R.id.etHorseAge);
        etHorsePrice = findViewById(R.id.etHorsePrice);
        etHorseDescription = findViewById(R.id.etHorseLocation);
        ivHorseImage = findViewById(R.id.ivHorseImage);
        btnSaveHorse = findViewById(R.id.btnSaveHorse);
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
        String horseAge = etHorseAge.getText().toString().trim();
        String horsePrice = etHorsePrice.getText().toString().trim();
        String horseDescription = etHorseDescription.getText().toString().trim();
        Horse horse = new Horse();
        horse.setName(horseName);
        horse.setAge(Integer.parseInt(horseAge));
        AppDB.getInstance(this).myHorseQuery().insert(horse);
        Toast.makeText(this, "Horse added successfully", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(AddHorseActivity.this, Mainmenu.class);
        startActivity(intent);
        

    }
}