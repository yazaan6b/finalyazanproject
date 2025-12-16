package com.example.finalyazanproject.data.horsesubject;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Horse {
    @PrimaryKey(autoGenerate = true)
    private int horseId;
    // الصفات الأساسية
    private String name;        // اسم الحصان
    private int age;            // العمر
    private String breed;       // السلالة
    private String color;// اللون
    public String avilable;

    private boolean trained;    // هل الحصان مدرّب

    private String temperament; // الطباع (هادئ، نشيط، عصبي، ...)

    // الصفات الإضافية
    private String owner;       // اسم المالك
    private String location;    // مكان تواجد الحصان

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getOwner() {
        return owner;
    }

    public int getHorseId() {
        return horseId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isTrained() {
        return trained;
    }

    public void setTrained(boolean trained) {
        this.trained = trained;
    }

    public String getTemperament() {
        return temperament;
    }

    public void setHorseId(int horseId) {
        this.horseId = horseId;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setTemperament(String temperament) {
        this.temperament = temperament;
    }

    @Override
    public String toString() {
        return "Horse{" +
                "horseId=" + horseId +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", breed='" + breed + '\'' +
                ", color='" + color + '\'' +
                ", trained=" + trained +
                ", temperament='" + temperament + '\'' +
                ", owner='" + owner + '\'' +
                ", location='" + location + '\'' +
                '}';
    }

    public void setAvilable(String avilable) {
        this.avilable = avilable;
    }

    public String getAvilable() {
        return avilable;
    }
}

