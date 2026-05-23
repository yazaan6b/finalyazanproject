package com.example.finalyazanproject;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Mainseting extends AppCompatActivity {

    // Views
    private ImageButton backButton;
    private TextView headerTextView;

    private LinearLayout languageLinearLayout;
    private TextView languageTextView, languageSubTextView;

    private LinearLayout notificationsLinearLayout;
    private TextView notificationsTextView, notificationsSubTextView;
    private Switch notificationSwitch;

    private LinearLayout accountInfoLinearLayout;
    private TextView accountInfoTextView, accountInfoSubTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Edge-to-Edge
        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_mainseting);

        // Apply window insets safely
        View rootLayout = findViewById(R.id.rootLayout);
        if (rootLayout != null) {
            ViewCompat.setOnApplyWindowInsetsListener(rootLayout, (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });
        }

        // Initialize header
        backButton = findViewById(R.id.backButtonImageButton);
        headerTextView = findViewById(R.id.headerTextView);

        backButton.setOnClickListener(v -> finish()); // زر الرجوع

        // Initialize General Section
        languageLinearLayout = findViewById(R.id.languageLinearLayout);
        languageTextView = findViewById(R.id.languageTextView);
        languageSubTextView = findViewById(R.id.languageSubTextView);

        notificationsLinearLayout = findViewById(R.id.notificationsLinearLayout);
        notificationsTextView = findViewById(R.id.notificationsTextView);
        notificationsSubTextView = findViewById(R.id.notificationsSubTextView);
        notificationSwitch = findViewById(R.id.notificationSwitch);


        languageLinearLayout.setOnClickListener(v -> {
            // هنا ممكن تفتح شاشة اختيار اللغة
        });

        notificationsLinearLayout.setOnClickListener(v -> {
            boolean isChecked = !notificationSwitch.isChecked();
            notificationSwitch.setChecked(isChecked);
            notificationsSubTextView.setText(isChecked ? "On / تشغيل" : "Off / إيقاف");
        });

        // Initialize Account Section
        accountInfoLinearLayout = findViewById(R.id.accountInfoLinearLayout);
        accountInfoTextView = findViewById(R.id.accountInfoTextView);
        accountInfoSubTextView = findViewById(R.id.accountInfoSubTextView);

        accountInfoLinearLayout.setOnClickListener(v -> {
            // هنا ممكن تفتح شاشة الحساب
        });
    }
}