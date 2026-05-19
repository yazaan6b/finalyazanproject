package com.example.finalyazanproject;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;



public class Mainsplash extends AppCompatActivity
{
private TextView tvtextarabic;
private TextView tvtextenglish;

    private Handler handler = new Handler();

    // تعريف Runnable لتنفيذ أوامر معينة داخل Thread أو بعد مدة زمنية
    private Runnable runnable = new Runnable() {

        @Override
        public void run() {

            // إنشاء Intent للانتقال من صفحة Splash إلى صفحة تسجيل الدخول
            Intent intent = new Intent(Mainsplash.this, signin.class);

            // تشغيل صفحة تسجيل الدخول
            startActivity(intent);

            // إغلاق صفحة الـ Splash الحالية حتى لا يعود المستخدم إليها عند الضغط على زر الرجوع
            finish();
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        handler.postDelayed(runnable, 3000);
    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
    }
}