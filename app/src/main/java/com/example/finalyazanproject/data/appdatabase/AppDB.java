package com.example.finalyazanproject.data.appdatabase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.finalyazanproject.data.MyUserTable.MyUser;
import com.example.finalyazanproject.data.MyUserTable.MyUserQuery;
import com.example.finalyazanproject.data.horsesubject.Horse;
import com.example.finalyazanproject.data.horsesubject.MyHorseQuery;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {MyUser.class, Horse.class}, version = 5)
public abstract class AppDB extends RoomDatabase {

    private static AppDB instance;

    // ⭐ Executor لتشغيل عمليات الداتابيس بالخلفية
    private static final int NUMBER_OF_THREADS = 4;
    private static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static synchronized AppDB getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDB.class, "app_database")
                    .fallbackToDestructiveMigration() //   <-- أضف هذا السطر
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }


    public abstract MyUserQuery myUserQuery();
    public abstract MyHorseQuery myHorseQuery();


}