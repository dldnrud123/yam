package com.meals.bruce.meal_test01;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;

/**
 * Created by Bruce on 2018-03-16.
 */

public class Notification_list {

    boolean morning, sun, night;

    Notification_list(){
        this.morning = true;
        this.sun = true;
        this.night = true;
    }

    public boolean isMorning() {
        return morning;
    }

    public void setMorning(boolean morning) {
        this.morning = morning;
    }

    public boolean isSun() {
        return sun;
    }

    public void setSun(boolean sun) {
        this.sun = sun;
    }

    public boolean isNight() {
        return night;
    }

    public void setNight(boolean night) {
        this.night = night;
    }

    public void setHeart(int i, boolean bl) {
        switch (i) {
            case 0:
                setMorning(bl);
                break;
            case 1:
                setSun(bl);
                break;
            case 2:
                setNight(bl);
                break;
        }

    }

    public boolean getHeart(int i) {
        ArrayList match = new ArrayList();

        match.add(0, isMorning());
        match.add(1, isSun());
        match.add(2, isNight());

        return (boolean) match.get(i);

    }

    public void NotiSaving(Context c) {

        SharedPreferences pref = c.getSharedPreferences(
                c.getString(R.string.notification_list), c.MODE_PRIVATE);

        SharedPreferences.Editor edt = pref.edit();

        edt.putBoolean("morning", isMorning());
        edt.putBoolean("sun", isSun());
        edt.putBoolean("night", isNight());

        edt.commit();
    }

    public void NotiLoad(Context c) {

        SharedPreferences pref = c.getSharedPreferences(
                c.getString(R.string.notification_list), c.MODE_PRIVATE);
        setMorning(pref.getBoolean("morning", true));
        setSun(pref.getBoolean("sun", true));
        setNight(pref.getBoolean("night", true));


    }

    public void heartClear(Context c) {
        SharedPreferences pref = c.getSharedPreferences(
                c.getString(R.string.notification_list), c.MODE_PRIVATE);
        SharedPreferences.Editor edt = pref.edit();

        edt.clear();
        edt.commit();

    }



}
