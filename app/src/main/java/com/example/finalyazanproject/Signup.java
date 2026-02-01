package com.example.finalyazanproject;

import android.annotation.SuppressLint;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
    private Button btCreateAcount;


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
        btCreateAcount = findViewById(R.id.btca);

        btCreateAcount.setOnClickListener(v ->
        {
            validAndReadData();
        });
    }

    public boolean validAndReadData() {
        // Get input values
        String email = etemail1.getText().toString().trim();
        String name = etname.getText().toString().trim();
        String password = etpassword1.getText().toString().trim();
        String confirmPassword = etconfirmpassword.getText().toString().trim();
        boolean isValid = true;
        // Validate inputs
        if (email.isEmpty()) {
            etemail1.setError("Please enter your email");
            isValid = false;
        }
         if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etemail1.setError("Please enter a valid email address");
            isValid = false;
        }
        if (name.isEmpty()) {
            etname.setError("Please enter your name");
            isValid = false;
        }
        if (password.isEmpty()) {
            etpassword1.setError("Please enter your password");
            isValid = false;
        }
        if (confirmPassword.isEmpty()) {
            etconfirmpassword.setError("Please confirm your password");
            isValid = false;
        }
        if (!password.equals(confirmPassword)) {
            // Show error if passwords don't match
            etpassword1.setError("Passwords do not match");
            etconfirmpassword.setError("Passwords do not match");
            isValid = false;
        }
        if (isValid &&  checkEmail(email)==false) {
            MyUser myUser = new MyUser();
            myUser.setEmail(email);
            myUser.setUsername(name);
            myUser.setPassword(password);
            MyUserQuery myUserQuery = AppDB.getInstance(this).myUserQuery();
            myUserQuery.insert(myUser);
            Toast.makeText(this, "User registered successfully", Toast.LENGTH_SHORT).show();
        // Save data to Firebase
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference usersRef = firebaseDatabase.getReference("users");
        DatabaseReference newUserRef = usersRef.push();
        newUserRef.setValue(myUser);
            finish();
        }
        else {
            etemail1.setError("User already exists");
            Toast.makeText(this, "User already exists", Toast.LENGTH_SHORT).show();
        }
        // If all validations pass
        return true;
    }

    public boolean checkEmail(String email) {
        // استخدم MyUserQuery للتحقق من وجود الايميل
        MyUserQuery myUserQuery = AppDB.getInstance(this).myUserQuery();
        MyUser user = myUserQuery.checkEmail(email);
        return user != null;
    }
}



