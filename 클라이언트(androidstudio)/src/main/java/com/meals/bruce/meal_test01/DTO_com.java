package com.meals.bruce.meal_test01;

/**
 * Created by Bruce on 2018-01-22.
 */

public class DTO_com {
    int time, grade;
    String com,pass;

    DTO_com(){}

    public DTO_com(int time, int grade, String com,String pass) {
        this.time = time;
        this.grade = grade;
        this.com = com;
        this.pass = pass;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getCom() {
        return com;
    }

    public void setCom(String com) {
        this.com = com;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
