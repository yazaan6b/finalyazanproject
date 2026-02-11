package com.example.finalyazanproject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.finalyazanproject.data.appdatabase.AppDB;
import com.example.finalyazanproject.data.horsesubject.Horse;
import com.example.finalyazanproject.data.horsesubject.MyHorseAdabter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Mainmenu extends AppCompatActivity
{
    private MyHorseAdabter myHorseAdabter;
    private ListView listView;
    private FloatingActionButton btnAdd;
    private View btnss;


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
        listView = findViewById(R.id.listView);
        myHorseAdabter = new MyHorseAdabter(this, R.layout.horseitem);
        listView.setAdapter(myHorseAdabter);
        btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Mainmenu.this, AddHorseActivity.class);
                startActivity(intent);
            }
        });
        btnss = findViewById(R.id.btnss);
        btnss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Mainmenu.this, Mainseting.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        myHorseAdabter.clear();
        myHorseAdabter.addAll(AppDB.getInstance(this).myHorseQuery().getall());
        myHorseAdabter.notifyDataSetChanged();
    }
}