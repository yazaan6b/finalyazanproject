package com.example.finalyazanproject.data.MyUserTable;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface MyUserQuery {

    // استخراج جميع المستعملين
    @Query("SELECT * FROM usersTable")
    List<MyUser> getAll();

    // استخراج مستعمل حسب أرقام IDs
    @Query("SELECT * FROM usersTable WHERE userId IN (:userIds)")
    List<MyUser> loadAllByIds(int[] userIds);

    // فحص هل المستعمل موجود حسب الإيميل والباسورد
    @Query("SELECT * FROM usersTable WHERE email = :myEmail AND password = :myPassw LIMIT 1")
    MyUser checkEmailPassw(String myEmail, String myPassw);

    // فحص إذا الإيميل موجود من قبل
    @Query("SELECT * FROM usersTable WHERE email = :myEmail LIMIT 1")
    MyUser checkEmail(String myEmail);

    // إضافة مجموعة مستخدمين
    @Insert
    void insertAll(MyUser... users);

    // حذف مستخدم واحد
    @Delete
    void delete(MyUser user);

    // حذف حسب ID
    @Query("DELETE FROM usersTable WHERE userId = :id")
    void delete(int id);

    // إضافة مستخدم واحد
    @Insert
    void insert(MyUser myUser);

    // تعديل مستخدم أو مجموعة مستخدمين
    @Update
    void update(MyUser... values);
}
