package com.example.finalyazanproject;

/**
 * هذا الكود يتم تشغيله عند فتح الشاشة الرئيسية للتطبيق.
 * يقوم بإضافة المحتوى والتمرير وعرض البيانات المطلوبة للحصانات.
 * @author يازن النوري
 */

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.finalyazanproject.data.appdatabase.AppDB;
import com.example.finalyazanproject.data.horsesubject.Horse;
import com.example.finalyazanproject.data.horsesubject.MyHorseAdabter;
import com.example.finalyazanproject.data.notification.AlarmScheduler;
import com.example.finalyazanproject.data.notification.NotificationHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Mainmenu extends AppCompatActivity {
    private MyHorseAdabter myHorseAdabter;
    private ListView listView;
    private FloatingActionButton btnAdd;
    private View btnss;

    /**
     * هذه الدالة تتم تشغيلها عند فتح الشاشة.
     * تقوم بتعيين المحتوى والتمرير وعرض البيانات المطلوبة للحصانات.
     *
     * @param savedInstanceState
     */
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

        // طلب إذن الإشعارات (لنظام Android 13+)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.POST_NOTIFICATIONS}, 101);
            }
        }

        // إعداد الإشعارات
        NotificationHelper.createNotificationChannel(this);

        // جدولة إشعار تجريبي بعد 10 ثوانٍ (اختياري)
        AlarmScheduler.scheduleReminder(
                this,
                System.currentTimeMillis() + 10000,
                "Horse  Reminder",
                "أهلاً بك في تطبيق الخيول"
        );

    }
    

    /**
     * هذه الدالة تتم تشغيلها عند إعادة تنشيط الشاشة.
     * تقوم بتحديث البيانات وإعادة عرضها للحصانات.
     */
// يتم استدعاء هذه الدالة عندما تعود الـ Activity إلى الواجهة (بعد الخروج منها أو الرجوع لها)
    @Override
    protected void onResume() {
        super.onResume();

        // تفريغ جميع البيانات الموجودة داخل الـ Adapter أولاً
        myHorseAdabter.clear();

        // جلب كل بيانات الخيول من قاعدة البيانات (Room Database)
        // ثم إضافتها داخل الـ Adapter
        myHorseAdabter.addAll(AppDB.getInstance(this).myHorseQuery().getall());

        // إعلام الـ Adapter أن البيانات تغيّرت حتى يتم تحديث الـ RecyclerView أو ListView
        myHorseAdabter.notifyDataSetChanged();
    }
}