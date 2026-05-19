package com.example.finalyazanproject;

import static io.reactivex.internal.schedulers.SchedulerPoolFactory.start;

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

/**
 * هذه الفئة تعرّف شاشة التسجيل.
 * يتم تعريف معاملات التسجيل والتحقق من الأخطاء والتحقق من المستخدم والتسجيل.
 */
public class Signup extends AppCompatActivity {

    /**
     * هذا الحقل يحتوي على عناصر الإدخال المستخدم لإدخال البريد الإلكتروني الخاص به.
     */
    private EditText etemail;
    /**
     * هذا الحقل يحتوي على عناصر الإدخال المستخدم لإدخال اسم المستخدم.
     */
    private EditText etname;
    /**
     * هذا الحقل يحتوي على عناصر الإدخال المستخدم لإدخال كلمة المرور.
     */
    private EditText etpassword;
    /**
     * هذا الحقل يحتوي على عناصر الإدخال المستخدم لتأكيد كلمة المرور.
     */
    private EditText etconfirmpassword;
    /**
     * هذا الزر يستخدم لإنشاء حساب مستخدم جديد.
     */
    private Button btnCreateAccount;
    /**
     * هذا النص يستخدم لربط المستخدم بصفحة التسجيل.
     */
    private TextView tvloginlink;

    /**
     * هذا الكائن يعرّف المستخدم في Firebase.
     */
    private FirebaseAuth firebaseAuth;

    /**
     * هذه الدالة تتم تشغيلها عند فتح الشاشة.
     * تتحقق من المعاملات التي تم تمريرها بشكل صحيح وتقوم بتسجيل المستخدم في Firebase.
     * تقوم بتحديث قاعدة البيانات والقاعدة البيانات المحلية بالمعلومات المطلوبة.
     * @param savedInstanceState
     */
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

    /**
     * هذه الدالة تتم تشغيلها عند تحقق من الأخطاء وتتابع مع المستخدم في عملية التسجيل.
     */
    private void validateAndRegister() {

        String email = etemail.getText().toString().trim();
        String name = etname.getText().toString().trim();
        String password = etpassword.getText().toString().trim();
        String confirmPassword = etconfirmpassword.getText().toString().trim();

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etemail.setError("البريد الإلكتروني غير صحيح");
            return;
        }

        if (name.isEmpty()) {
            etname.setError("الرجاء إدخال اسم المستخدم");
            return;
        }

        if (password.length() < 6) {
            etpassword.setError("يجب أن تكون كلمة المرور لا تقل عن 6 أحرف");
            return;
        }

        if (!password.equals(confirmPassword)) {
            etconfirmpassword.setError("كلمات المرور غير متطابقتان");
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
                        // إنشاء Thread جديد لتنفيذ العملية في الخلفية بدون التأثير على واجهة المستخدم
                        new Thread(() -> {

                            // إدخال المستخدم إلى قاعدة البيانات باستخدام Room Database
                            AppDB.getInstance(this).myUserQuery().insert(user);

                        });

                       start();

                        // 🔹 Firebase Realtime Database
                        FirebaseDatabase.getInstance()
                                .getReference("users")
                                .child(firebaseAuth.getUid())
                                .setValue(user);

                        Toast.makeText(this, "تم إنشاء الحساب بنجاح", Toast.LENGTH_SHORT).show();
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