package com.meals.bruce.meal_test01;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;

/**
 * Created by Bruce on 2018-01-12.
 */

public class heartList {

    boolean sun, mon, tue, wed, thu, fri, sat;
    String date;


    heartList() {
        this.sun = false;
        this.mon = false;
        this.tue = false;
        this.wed = false;
        this.thu = false;
        this.fri = false;
        this.sat = false;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isSun() {
        return sun;
    }

    public void setSun(boolean sun) {
        this.sun = sun;
    }

    public boolean isMon() {
        return mon;
    }

    public void setMon(boolean mon) {
        this.mon = mon;
    }

    public boolean isTue() {
        return tue;
    }

    public void setTue(boolean tue) {
        this.tue = tue;
    }

    public boolean isWed() {
        return wed;
    }

    public void setWed(boolean wed) {
        this.wed = wed;
    }

    public boolean isThu() {
        return thu;
    }

    public void setThu(boolean thu) {
        this.thu = thu;
    }

    public boolean isFri() {
        return fri;
    }

    public void setFri(boolean fri) {
        this.fri = fri;
    }

    public boolean isSat() {
        return sat;
    }

    public void setSat(boolean sat) {
        this.sat = sat;
    }

    public void setHeart(int i, boolean bl) {
        switch (i) {
            case 0:
                setSun(bl);
                break;
            case 1:
                setMon(bl);
                break;
            case 2:
                setTue(bl);
                break;
            case 3:
                setWed(bl);
                break;
            case 4:
                setThu(bl);
                break;
            case 5:
                setFri(bl);
                break;
            case 6:
                setSat(bl);

        }

    }

    public boolean getHeart(int i) {
        ArrayList match = new ArrayList();

        match.add(0, isSun());
        match.add(1, isMon());
        match.add(2, isTue());
        match.add(3, isWed());
        match.add(4, isThu());
        match.add(5, isFri());
        match.add(6, isSat());

        return (boolean) match.get(i);

    }

    public void heartSaving(Context c, int i, DTO a) {
        int[] fileList = {R.string.preference_file_key1, R.string.preference_file_key2, R.string.preference_file_key3};

        SharedPreferences pref = c.getSharedPreferences(
                c.getString(fileList[i]), c.MODE_PRIVATE);

        SharedPreferences.Editor edt = pref.edit();
        edt.putString("date", a.getToday());
        edt.putBoolean("sun", isSun());
        edt.putBoolean("mon", isMon());
        edt.putBoolean("tue", isTue());
        edt.putBoolean("wed", isWed());
        edt.putBoolean("thu", isThu());
        edt.putBoolean("fri", isFri());
        edt.putBoolean("sat", isSat());

        edt.commit();
    }

    public void heartLoad(Context c, int i) {
        int[] fileList = {R.string.preference_file_key1, R.string.preference_file_key2, R.string.preference_file_key3};

        SharedPreferences pref = c.getSharedPreferences(
                c.getString(fileList[i]), c.MODE_PRIVATE);
        setDate(pref.getString("date", "00000000"));
        setSun(pref.getBoolean("sun", false));
        setMon(pref.getBoolean("mon", false));
        setTue(pref.getBoolean("tue", false));
        setWed(pref.getBoolean("wed", false));
        setThu(pref.getBoolean("thu", false));
        setFri(pref.getBoolean("fri", false));
        setSat(pref.getBoolean("sat", false));

    }

    public void heartClear(Context c) {
        int[] fileList = {R.string.preference_file_key1, R.string.preference_file_key2, R.string.preference_file_key3};
        for (int i : fileList) {
            SharedPreferences pref = c.getSharedPreferences(
                    c.getString(i), c.MODE_PRIVATE);
            SharedPreferences.Editor editor = pref.edit();
            editor.clear();
            editor.commit();
        }
    }

    public boolean heartCheck(ArrayList<DTO> a, int count) {

            if (!getDate().equals(a.get(count).getToday())) {
                return true;
            } else {
                return false;
            }

        }
    }





