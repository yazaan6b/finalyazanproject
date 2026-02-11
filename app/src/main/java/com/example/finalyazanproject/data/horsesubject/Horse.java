package com.example.finalyazanproject.data.horsesubject;

import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Horse implements Serializable {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;
    private int age;
    private String breed;
    private int price;
    private boolean trained;
    private String temperament;
    private String owner;
    private String location;

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getOwner() {
        return owner;
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



    public boolean isTrained() {
        return trained;
    }

    public void setTrained(boolean trained) {
        this.trained = trained;
    }

    public String getTemperament() {
        return temperament;
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
    public void setPrice(int price) {
        this.price = price;
    }
    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Horse{" +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", breed='" + breed + '\'' +
                ", trained=" + trained +
                ", temperament='" + temperament + '\'' +
                ", owner='" + owner + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}

