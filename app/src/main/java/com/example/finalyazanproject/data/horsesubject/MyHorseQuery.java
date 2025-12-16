package com.example.finalyazanproject.data.horsesubject;

import androidx.room.Dao;
import androidx.room.Delete;import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface MyHorseQuery {

    // جلب كل الخيول
    @Query("SELECT * FROM Horse")
    List<Horse> getall();
    // إضافة خيول
    @Insert
    void insert(Horse... horses);

    // حذف حصان
    @Delete
    void delete(Horse horse);

    // تحديث بيانات حصان
    @Update
    void update(Horse... horses);

    // جلب حسب المعرف ID
//    @Query("SELECT * FROM horses WHERE horseId = :id")
//    Horse getById(int id);
//
//    // جلب حسب الاسم
//    @Query("SELECT * FROM horses WHERE horseName = :name")
//    Horse getByName(String name);
//
//    // جلب حسب السلالة
//    @Query("SELECT * FROM horses WHERE horseBreed = :breed")
//    List<Horse> getByBreed(String breed);
//
//    // جلب حسب اللون
//    @Query("SELECT * FROM horses WHERE horseColor = :color")
//    List<Horse> getByColor(String color);
//
//    // جلب حسب العمر
//    @Query("SELECT * FROM horses WHERE horseAge = :age")
//    List<Horse> getByAge(int age);
//
//    // جلب حسب السعر (تم تصحيح اسم الجدول هنا من Horse إلى horses)
//    @Query("SELECT * FROM horses WHERE horsePrice = :price")
//    List<Horse> getByPrice(double price);
//
//    // جلب حسب الوصف
//    @Query("SELECT * FROM horses WHERE horseDescription = :description")
//    List<Horse> getByDescription(String description);
//
//    // بحث عام: يبحث عن الكلمة في الاسم أو السلالة أو الوصف
//    // ملاحظة: تم إضافة علامات % ليقوم بالبحث الجزئي (مثال: بحث "عربي" يجد "حصان عربي أصيل")
//    @Query("SELECT * FROM horses WHERE horseName LIKE '%' || :keyword || '%' OR horseBreed LIKE '%' || :keyword || '%' OR horseDescription LIKE '%' || :keyword || '%'")
//    List<Horse> searchHorses(String keyword);

}
