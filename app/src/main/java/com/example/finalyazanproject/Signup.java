package com.example.finalyazanproject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Signup extends AppCompatActivity {
private TextView tvloginlink;
private TextView tvsign;
//private EditText tvpassword;
private TextView tvp;
private TextView tvem;
private TextView tvn;
private EditText etemail1;
private EditText etname;
private EditText etconfirmpassword;
private EditText etpassword1;
private TextView tvtitle1;
private TextView tvsignup;
private TextView tvsignuptext;
private Button btca;


    @SuppressLint({"MissingInflatedId", "WrongViewCast", "CutPasteId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        tvloginlink = findViewById(R.id.tvloginlink);
        tvsign = findViewById(R.id.tvsign);
        //tvpassword = findViewById(R.id.tvpassword);
        tvp = findViewById(R.id.tvp);
        tvem = findViewById(R.id.tvem);
        tvn = findViewById(R.id.tvn);
        etemail1 = findViewById(R.id.etemail1);
        etname = findViewById(R.id.etname);
        etconfirmpassword = findViewById(R.id.etconfirmpassword);
        etpassword1 = findViewById(R.id.etpassword1);
        tvtitle1 = findViewById(R.id.tvtitle1);
        tvsignuptext = findViewById(R.id.tvsignuptext);
         btca = findViewById(R.id.btca);

       btca.setOnClickListener(v ->
        {
            Intent intent = new Intent(Signup.this, Mainmenu.class);
            startActivity(intent);
        });

    }
}