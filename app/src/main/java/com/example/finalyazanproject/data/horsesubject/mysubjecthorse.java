package com.example.finalyazanproject.data.horsesubject;

public interface mysubjecthorse {

    // الأساليب الأساسية للحصول على المعلومات
    String getName();
    void setName(String name);

    int getAge();
    void setAge(int age);

    String getBreed();
    void setBreed(String breed);

    String getColor();
    void setColor(String color);

    double getHeight();
    void setHeight(double height);

    double getWeight();
    void setWeight(double weight);

    boolean isVaccinated();
    void setVaccinated(boolean vaccinated);

    boolean isTrained();
    void setTrained(boolean trained);

    double getSpeed();
    void setSpeed(double speed);

    double getStamina();
    void setStamina(double stamina);

    String getTemperament();
    void setTemperament(String temperament);

    String getOwner();
    void setOwner(String owner);

    String getLocation();
    void setLocation(String location);

    // أساليب إضافية ممكن لكل حصان
    void feed();        // لإطعام الحصان
    void exercise();    // للتمرين أو الركض
    void vaccinate();   // لإعطاء التطعيم
}
