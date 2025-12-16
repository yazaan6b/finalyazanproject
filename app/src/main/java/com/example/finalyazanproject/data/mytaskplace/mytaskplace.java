package com.example.finalyazanproject.data.mytaskplace;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface mytaskplace {

    // ➕ إضافة مهمة جديدة
    @Insert
    void insertTask(Mytask task);

    // 🔄 تحديث مهمة موجودة
    @Update
    void updateTask(Mytask task);

    // ❌ حذف مهمة
    @Delete
    void deleteTask(Mytask task);

    // 📋 جلب كل المهام
    @Query("SELECT * FROM tasksTable")
    List<Mytask> getAllTasks();

    // 🔎 جلب مهمة حسب ID
    @Query("SELECT * FROM tasksTable WHERE taskId = :taskId")
    Mytask getTaskById(int taskId);

    // 👤 جلب المهام الخاصة بمستخدم معين
    @Query("SELECT * FROM tasksTable WHERE userId = :userId")
    List<Mytask> getTasksByUserId(int userId);

    // ✅ جلب المهام المكتملة فقط
    @Query("SELECT * FROM tasksTable WHERE status = 1")
    List<Mytask> getCompletedTasks();

    // 🕓 جلب المهام غير المكتملة فقط
    @Query("SELECT * FROM tasksTable WHERE status = 0")
    List<Mytask> getPendingTasks();

    // 🔍 البحث عن مهمة حسب العنوان
    @Query("SELECT * FROM tasksTable WHERE title LIKE '%' || :keyword || '%'")
    List<Mytask> searchTasksByTitle(String keyword);
}
