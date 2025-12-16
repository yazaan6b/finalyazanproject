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
import com.example.finalyazanproject.data.MyUserTable.MyUserQuery;
import com.example.finalyazanproject.data.appdatabase.AppDB;

public class signin extends AppCompatActivity {

    private EditText etemail;
    private EditText etpassword;
    private Button btnlogin;
    private TextView tvsignuplink;

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
        btnlogin.setOnClickListener(v ->
        {
            Intent intent2 = new Intent(signin.this, Mainmenu.class);
            startActivity(intent2);
        });

    }

    private void validateAndLogin() {

        String email = etemail.getText().toString().trim();
        String password = etpassword.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // ⭐ تشغيل استعلام Room بالخلفية بدون new Thread
        AppDB.databaseWriteExecutor.execute(() -> {

            MyUserQuery myUserQuery = AppDB.getInstance(this).myUserQuery();
            MyUser user = myUserQuery.checkEmail(email);

            runOnUiThread(() -> {

                if (user == null) {
                    Toast.makeText(this, "Email does not exist", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!user.getPassword().equals(password)) {
                    Toast.makeText(this, "Wrong password", Toast.LENGTH_SHORT).show();
                    return;
                }

                Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(signin.this, Mainmenu.class);
                startActivity(intent);

            });


        });
    }
}
