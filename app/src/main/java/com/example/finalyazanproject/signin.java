package com.example.finalyazanproject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.finalyazanproject.data.MyUserTable.MyUser;
import com.example.finalyazanproject.data.appdatabase.AppDB;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class signin extends AppCompatActivity {

    private EditText etemail;
    private EditText etpassword;
    private Button btnlogin;
    private TextView tvsignuplink;
    private FirebaseAuth mAuth;
    private AppDB appDB;

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

        mAuth = FirebaseAuth.getInstance();
        appDB = AppDB.getInstance(this);

        etemail = findViewById(R.id.etemail);
        etpassword = findViewById(R.id.etpassword);
        btnlogin = findViewById(R.id.btnlogin);
        tvsignuplink = findViewById(R.id.tvsignuplink);

        btnlogin.setOnClickListener(v -> {
            validateAndLogin();
        });

        tvsignuplink.setOnClickListener(v -> {

            Intent intent1 = new Intent(signin.this, Signup.class);
            startActivity(intent1);
        });
    }

    private void validateAndLogin() {

        String email = etemail.getText().toString().trim();
        String password = etpassword.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(signin.this, "Login successful", Toast.LENGTH_SHORT).show();
//
                        Intent intent = new Intent(signin.this, Mainmenu.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(signin.this, "Login failed", Toast.LENGTH_SHORT).show();
                    }
                });

    }
}