package com.meals.bruce.meal_test01;

/**
 * Created by Bruce on 2018-03-15.
 */

public class DTO_fcm {

    String token;
    int morning, sun, night;

    public DTO_fcm(String key){
        this.token = key;
        this.morning = 1;
        this.sun = 1;
        this.night = 1;

    }



    public DTO_fcm(String token, int morning, int sun, int night) {
        this.token = token;
        this.morning = morning;
        this.sun = sun;
        this.night = night;
    }



    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getMorning() { return morning; }

    public void setMorning(int morning) {
        this.morning = morning;
    }

    public int getSun() {
        return sun;
    }

    public void setSun(int sun) {
        this.sun = sun;
    }

    public int getNight() {
        return night;
    }

    public void setNight(int night) {
        this.night = night;
    }

}

