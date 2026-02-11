package com.example.finalyazanproject;

import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.finalyazanproject.data.MyUserTable.MyUser;
import com.example.finalyazanproject.data.appdatabase.AppDB;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Signup extends AppCompatActivity {

    private EditText etemail, etname, etpassword, etconfirmpassword;
    private Button btnCreateAccount;
    private TextView tvloginlink;

    private FirebaseAuth firebaseAuth;

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

        firebaseAuth = FirebaseAuth.getInstance();

        etemail = findViewById(R.id.etemail1);
        etname = findViewById(R.id.etname);
        etpassword = findViewById(R.id.etpassword1);
        etconfirmpassword = findViewById(R.id.etconfirmpassword);
        btnCreateAccount = findViewById(R.id.btca);
        tvloginlink = findViewById(R.id.tvloginlink);

        btnCreateAccount.setOnClickListener(v -> validateAndRegister());

        tvloginlink.setOnClickListener(v -> finish());
    }

    private void validateAndRegister() {

        String email = etemail.getText().toString().trim();
        String name = etname.getText().toString().trim();
        String password = etpassword.getText().toString().trim();
        String confirmPassword = etconfirmpassword.getText().toString().trim();

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etemail.setError("Invalid email");
            return;
        }

        if (name.isEmpty()) {
            etname.setError("Enter your name");
            return;
        }

        if (password.length() < 6) {
            etpassword.setError("Password must be at least 6 characters");
            return;
        }

        if (!password.equals(confirmPassword)) {
            etconfirmpassword.setError("Passwords do not match");
            return;
        }

        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {

                        // 🔹 User object (بدون password)
                        MyUser user = new MyUser();
                        user.setEmail(email);
                        user.setUsername(name);

                        // 🔹 Room (Thread)
                        new Thread(() -> {
                            AppDB.getInstance(this).myUserQuery().insert(user);

                        }).start();

                        // 🔹 Firebase Realtime Database
                        FirebaseDatabase.getInstance()
                                .getReference("users")
                                .child(firebaseAuth.getUid())
                                .setValue(user);

                        Toast.makeText(this, "Account created successfully", Toast.LENGTH_SHORT).show();
                        finish();

                    } else {
                        Toast.makeText(
                                this,
                                task.getException().getMessage(),
                                Toast.LENGTH_LONG
                        ).show();
                    }
                });
    }
}
