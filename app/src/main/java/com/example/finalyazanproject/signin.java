package com.example.finalyazanproject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class signin extends AppCompatActivity
{
    private TextView tvsignuplink;
    private TextView tvsignuptext;
    private TextView tvsubtitle;
    private TextView tvtitle;
    private EditText etpassword;
    private EditText etemail;
    private Button btnlogin;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signin);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        tvsignuplink = findViewById(R.id.tvsignuplink);
        tvsignuptext = findViewById(R.id.tvsignuptext);
        tvsubtitle = findViewById(R.id.tvsubtitle);
        tvtitle = findViewById(R.id.tvtitle);
        etpassword = findViewById(R.id.etpassword);
        etemail = findViewById(R.id.etemail);
        btnlogin = findViewById(R.id.btnlogin);

        btnlogin.setOnClickListener(v ->
        {
            Intent intent = new Intent(signin.this, Mainmenu.class);
            startActivity(intent);
        });
        tvsignuplink.setOnClickListener(v ->
        {
            Intent intent = new Intent(signin.this, Signup.class);
            startActivity(intent);
        });


    }

}