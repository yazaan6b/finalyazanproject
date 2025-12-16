package com.example.finalyazanproject.data.mytaskplace;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ColumnInfo;

@Entity(tableName = "tasksTable")
public class Mytask {

    @PrimaryKey(autoGenerate = true)
    private int taskId;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "date")
    private String date;

    @ColumnInfo(name = "time")
    private String time;

    @ColumnInfo(name = "status")
    private boolean status; // true = تم, false = لم يتم

    @ColumnInfo(name = "userId")
    private int userId; // لو كل مهمة مرتبطة بمستخدم معين

    // 🔹 Constructor
    public Mytask(String title, String description, String date, String time, boolean status, int userId) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.time = time;
        this.status = status;
        this.userId = userId;
    }

    // 🔹 Getters & Setters
    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }


}
