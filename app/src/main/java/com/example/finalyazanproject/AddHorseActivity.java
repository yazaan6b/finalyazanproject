/* <<<<<<<<<<<<<<  ✨ Windsurf Command 🌟 >>>>>>>>>>>>>>>> */
/* <<<<<<<<<<<<<<  ✨ Windsurf Command 🌟 >>>>>>>>>>>>>>>> */
/* <<<<<<<<<<<<<<  ✨ Windsurf Command 🌟 >>>>>>>>>>>>>>>> */
/* <<<<<<<<<<<<<<  ✨ Windsurf Command 🌟 >>>>>>>>>>>>>>>> */
package com.example.finalyazanproject;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
// استيراد ActivityResultContracts
// وهي كلاس جاهز في AndroidX يُستخدم مع ActivityResultLauncher
// بدل الطريقة القديمة onActivityResult لطلب النتائج (مثل الصور أو الأذونات)
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.helper.widget.MotionEffect;
import androidx.core.content.ContextCompat;

import com.example.finalyazanproject.data.appdatabase.AppDB;
import com.example.finalyazanproject.data.horsesubject.Horse;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * فعالية إضافة مركبة جديدة
 */
public class AddHorseActivity extends AppCompatActivity {

    /**
     * الحقل لإدخال إسم المركبة
     */
    private EditText etHorseName;
    /**
     * الحقل لإدخال عمر المركبة
     */
    private EditText etHorseAge;
    /**
     * الحقل لإدخال سعر المركبة
     */
    private EditText etHorsePrice;
    /**
     * الحقل لإدخال وصف المركبة
     */
    private EditText etHorseDescription;
    /**
     * صورة المركبة
     */
    private ImageView ivHorseImage;
    /**
     * زر حفظ المركبة
     */
    private Button btnSaveHorse;

    private DatabaseReference databaseReference;
    private ActivityResultLauncher<String> requestReadMediaImagesPermission;
    private ActivityResultLauncher<String> requestReadMediaVideoPermission;
    private ActivityResultLauncher<String> requestReadExternalStoragePermission;
    private ActivityResultLauncher<String> pickImage;
    private final Uri[] selectedImageUri = new Uri[1];

    /**
     * تهيئة الشاشة والتسمية للأجزاء
     *
     * @param savedInstanceState حفظ الحالة
     */
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_horse);

        etHorseName = findViewById(R.id.etHorseName);
        etHorseAge = findViewById(R.id.etHorseAge);
        etHorsePrice = findViewById(R.id.etHorsePrice);
        etHorseDescription = findViewById(R.id.etHorseLocation);
        ivHorseImage = findViewById(R.id.ivHorseImage);
        btnSaveHorse = findViewById(R.id.btnSaveHorse);
// تهيئة المحدد الخاص بمحدد الصور
// Initialize image picker
        initializeImagePicker();

        // تهيئة مستمع النقر على صورة المركبة
        // Set click listener for image selection
        ivHorseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickImage.launch("image/*"); // تشغيل المحدد الخاص بالصور
                pickImage.launch("image/*"); // Launch the image picker
            }
        });

        /**
         * طلب إذن الوصول إلى الصور (READ_MEDIA_IMAGES)
         * باستخدام ActivityResultContracts.RequestPermission
         * وهو الأسلوب الحديث بدلاً من onRequestPermissionsResult
         */
        requestReadMediaImagesPermission =
                registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {

                    // في حال وافق المستخدم على منح الإذن
                    if (isGranted) {

                        Log.d(TAG, "READ_MEDIA_IMAGES permission granted");

                        Toast.makeText(this,
                                "تم منح إذن قراءة الصور",
                                Toast.LENGTH_SHORT).show();

                        // الآن يمكن تنفيذ العمليات التي تحتاج الوصول للصور (مثل اختيار صورة)

                    } else {

                        // في حال رفض المستخدم الإذن
                        Log.d(TAG, "READ_MEDIA_IMAGES permission denied");

                        Toast.makeText(this,
                                "تم رفض إذن قراءة الصور",
                                Toast.LENGTH_SHORT).show();

                        // التعامل مع رفض الإذن (تعطيل الميزة أو إظهار تنبيه)
                    }
                });


// تهيئة مستمع النقر على فيديو المركبة
// تسجيل مُشغّل لطلب إذن READ_MEDIA_VIDEO
/**
 * طلب إذن الوصول إلى الفيديوهات (READ_MEDIA_VIDEO)
 * باستخدام ActivityResultContracts.RequestPermission
 * بديل حديث عن onRequestPermissionsResult
 */
        requestReadMediaVideoPermission =
                registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {

                    // إذا وافق المستخدم على منح الإذن
                    if (isGranted) {

                        Log.d(TAG, "READ_MEDIA_VIDEO permission granted");

                        Toast.makeText(this,
                                "تم منح إذن قراءة الفيديو",
                                Toast.LENGTH_SHORT).show();

                        // هنا يتم تنفيذ العمليات التي تحتاج الوصول للفيديوهات

                    } else {

                        // إذا رفض المستخدم الإذن
                        Log.d(TAG, "READ_MEDIA_VIDEO permission denied");

                        Toast.makeText(this,
                                "تم رفض إذن قراءة الفيديو",
                                Toast.LENGTH_SHORT).show();

                        // هنا يتم التعامل مع رفض الإذن (تعطيل الميزة أو تنبيه المستخدم)
                    }
                });


// تهيئة مستمع النقر على تخزين المركبة
// تسجيل مُشغّل لطلب إذن READ_EXTERNAL_STORAGE
/**
 * طلب إذن الوصول إلى التخزين الخارجي (READ_EXTERNAL_STORAGE)
 * باستخدام ActivityResultContracts بدلاً من الطريقة القديمة (onRequestPermissionsResult)
 */
        requestReadExternalStoragePermission =
                registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {

                    // في حال وافق المستخدم على منح الإذن
                    if (isGranted) {

                        Log.d(TAG, "READ_EXTERNAL_STORAGE permission granted");

                        Toast.makeText(this,
                                "تم منح إذن قراءة التخزين الخارجي",
                                Toast.LENGTH_SHORT).show();

                        // هنا يتم تنفيذ العمليات التي تحتاج إذن (مثل اختيار صورة أو قراءة ملفات)

                    } else {

                        // في حال رفض المستخدم الإذن
                        Log.d(TAG, "READ_EXTERNAL_STORAGE permission denied");

                        Toast.makeText(this,
                                "تم رفض إذن قراءة التخزين الخارجي",
                                Toast.LENGTH_SHORT).show();

                        // هنا يتم التعامل مع الرفض (تعطيل الميزة أو إظهار رسالة)
                    }
                });

        // مرجع Firebase للمركبات
        // Firebase reference
        databaseReference = FirebaseDatabase.getInstance().getReference("Horses");

        btnSaveHorse.setOnClickListener(v -> saveHorse());
    }

    /**
     * التحقق من وجود الإذن وطلبه إذا كان غير منسحب
     */
    private void checkAndRequestPermissions() {
        // فحص وطلب إذن READ_MEDIA_IMAGES (للإصدارات الحديثة)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) { // أندرويد 13+
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_MEDIA_IMAGES)
                    != PackageManager.PERMISSION_GRANTED) {
                requestReadMediaImagesPermission.launch(android.Manifest.permission.READ_MEDIA_IMAGES);
            } else {
                Log.d(TAG, "READ_MEDIA_IMAGES permission already granted");
                Toast.makeText(this, "إذن قراءة الصور ممنوح بالفعل", Toast.LENGTH_SHORT).show();
            }
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) { // أندرويد 10 و 11 و 12// على هذه الإصدارات، READ_EXTERNAL_STORAGE له سلوك مختلف
            // إذا كنت تستخدم Scoped Storage بشكل صحيح، قد لا تحتاج إلى هذا الإذن
            // ولكن إذا كنت تحتاج إلى الوصول إلى جميع الصور، فقد تحتاج إلى READ_EXTERNAL_STORAGE
            // في هذا المثال، سنفحص READ_EXTERNAL_STORAGE للإصدارات الأقدم من 13
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                requestReadExternalStoragePermission.launch(android.Manifest.permission.READ_EXTERNAL_STORAGE);
            } else {
                Log.d(TAG, "READ_EXTERNAL_STORAGE permission already granted (for older versions)");
                Toast.makeText(this, "إذن قراءة التخزين ممنوح بالفعل (للإصدارات الأقدم)", Toast.LENGTH_SHORT).show();
            }
        } else { // أندرويد 9 والإصدارات الأقدم
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                requestReadExternalStoragePermission.launch(android.Manifest.permission.READ_EXTERNAL_STORAGE);
            } else {
                Log.d(TAG, "READ_EXTERNAL_STORAGE permission already granted (for older versions)");
                Toast.makeText(this, "إذن قراءة التخزين ممنوح بالفعل (للإصدارات الأقدم)", Toast.LENGTH_SHORT).show();
            }
        }


        // فحص وطلب إذن READ_MEDIA_VIDEO (للإصدارات الحديثة)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) { // أندرويد 13+
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_MEDIA_VIDEO)
                    != PackageManager.PERMISSION_GRANTED) {
                requestReadMediaVideoPermission.launch(android.Manifest.permission.READ_MEDIA_VIDEO);
            } else {
                Log.d(TAG, "READ_MEDIA_VIDEO permission already granted");
                Toast.makeText(this, "إذن قراءة الفيديو ممنوح بالفعل", Toast.LENGTH_SHORT).show();
            }
        }// ملاحظة: إذن INTERNET لا يحتاج إلى فحص أو

}

    /**
     * تهيئة أداة اختيار الصور (Image Picker) باستخدام ActivityResultContracts
     * الهدف منها: فتح معرض الصور واختيار صورة وإرجاعها للتطبيق بدون onActivityResult القديم
     */
    private void initializeImagePicker() {

        // إنشاء ActivityResultLauncher لاختيار نوع المحتوى (هنا: الصور)
        pickImage = registerForActivityResult(
                new ActivityResultContracts.GetContent(),
                result -> {

                    // التأكد أن المستخدم اختار صورة ولم يرجع null
                    if (result != null) {

                        // تخزين URI الخاص بالصورة المختارة
                        selectedImageUri[0] = result;

                        // عرض الصورة داخل ImageView
                        ivHorseImage.setImageURI(result);

                        // إظهار ImageView إذا كان مخفي
                        ivHorseImage.setVisibility(View.VISIBLE);
                    }
                }
        );
    }

    
    private boolean validateInputs() {
        boolean isValid = true;

        if (etHorseName.getText().toString().trim().isEmpty()) {
            etHorseName.setError("Please enter horse name");
            isValid = false;
        }
        if (etHorseAge.getText().toString().trim().isEmpty()) {
            etHorseAge.setError("Please enter horse age");
            isValid = false;
        }
        if (etHorsePrice.getText().toString().trim().isEmpty()) {
            etHorsePrice.setError("Please enter horse price");
            isValid = false;
        }
        if (etHorseDescription.getText().toString().trim().isEmpty()) {
            etHorseDescription.setError("Please enter horse description");
            isValid = false;
        }

        return isValid;
    }
    /**
     * Converts an image Uri to a Base64 string.
     *
     * @param uri The Uri of the image to convert.
     * @return The Base64 string representation of the image.
     */
    /**
     * دالة لتحويل الصورة من Uri إلى String بصيغة Base64
     * الهدف منها: تخزين الصورة داخل قاعدة البيانات كنص بدل تخزين ملف الصورة
     */
    public String convertImageToString(Uri uri) {

        InputStream inputStream = null;
        String imageString = null;

        // فتح الصورة من التخزين عبر الـ ContentResolver
        try {
            inputStream = getContentResolver().openInputStream(uri);

            // تحويل الـ InputStream إلى Bitmap (صورة قابلة للمعالجة)
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

            // التحقق إذا فشل تحميل الصورة
            if (bitmap == null) {
                Toast.makeText(this, "Failed to process image", Toast.LENGTH_SHORT).show();
                return null;
            }

            // ضغط الصورة لتقليل الحجم قبل تحويلها إلى نص
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            // JPEG + جودة 40% لتقليل حجم البيانات
            bitmap.compress(Bitmap.CompressFormat.JPEG, 40, outputStream);

            // تحويل الصورة المضغوطة إلى مصفوفة بايتات
            byte[] imageBytes = outputStream.toByteArray();

            // تحويل البايتات إلى نص Base64 لتخزينه في قاعدة البيانات
            imageString = Base64.encodeToString(imageBytes, Base64.DEFAULT);

            return imageString;

        } catch (FileNotFoundException e) {

            // في حال لم يتم العثور على الملف
            Toast.makeText(this, "Failed file not found", Toast.LENGTH_SHORT).show();

            // إعادة رمي الخطأ (قد يوقف التطبيق إذا لم يتم معالجته لاحقاً)
            throw new RuntimeException(e);
        }
    }
    /**
     * Decodes the image string and returns the corresponding Bitmap object.
     *
     * @param imageString the image string to decode
     * @return the decoded Bitmap object
     */
    // دالة لتحويل نص (String) مشفّر بصيغة Base64 إلى صورة (Bitmap)
    private Bitmap stringToBitmap(String imageString) {

        // إذا كان النص فارغ أو null يتم إرجاع null مباشرة
        if (imageString == null || imageString.isEmpty()) return null;

        try {
            // تحويل النص من Base64 إلى مصفوفة بايتات (Bytes)
            byte[] decodedString = Base64.decode(imageString, Base64.DEFAULT);

            // تحويل البايتات إلى صورة Bitmap يمكن عرضها في ImageView
            return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

        } catch (Exception e) {
            // في حال حدوث خطأ أثناء التحويل يتم إرجاع null
            return null;
        }
    }


    private void saveHorse() {

        if (!validateInputs()) {
            return;
        }

        String horseName = etHorseName.getText().toString().trim();
        int horseAge = Integer.parseInt(etHorseAge.getText().toString().trim());
        int horsePrice = Integer.parseInt(etHorsePrice.getText().toString().trim());
        String horseDescription = etHorseDescription.getText().toString().trim();

        Horse horse = new Horse();
        horse.setName(horseName);
        horse.setAge(horseAge);
        horse.setPrice(horsePrice);
        horse.setDescription(horseDescription);
        //todo  key feiald to hore class
        String key = databaseReference.push().getKey();
        //horse.setKey(key);
        // 🔥 حفظ على Firebase
        databaseReference.child(key).setValue(horse);

        // 💾 حفظ على Room
        AppDB.getInstance(this).myHorseQuery().insert(horse);

        Toast.makeText(this, "Horse added successfully", Toast.LENGTH_SHORT).show();

        startActivity(new Intent(AddHorseActivity.this, Mainmenu.class));
        finish();
    }
}
/* <<<<<<<<<<  8b0ddd88-d473-48e7-ad62-430addbb13a7  >>>>>>>>>>> */
/* <<<<<<<<<<  c710ba6b-969b-4b3a-9c8d-bb3633f40972  >>>>>>>>>>> */
/* <<<<<<<<<<  660cf912-2adf-441e-a934-cb1110b884fd  >>>>>>>>>>> */
/* <<<<<<<<<<  81eb8499-31c8-411f-8cd2-c0fa75153045  >>>>>>>>>>> */