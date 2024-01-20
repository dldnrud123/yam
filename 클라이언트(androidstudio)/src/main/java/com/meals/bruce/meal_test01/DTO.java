package com.meals.bruce.meal_test01;

public class DTO {
    String  rice, soup, side1, side2, side3, side4, dessert, good, today;
    int flag;
    DTO(){}
    DTO(String today, String rice, String soup, String side1, String side2, String side3, String side4, String dessert, String good,int flag) {
        this.today = today;
        this.rice = rice;
        this.soup = soup;
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
        this.side4 = side4;
        this.dessert = dessert;
        this.good = good;
        this.flag = flag;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getRice() {
        return rice;
    }

    public void setRice(String rice) {
        this.rice = rice;
    }

    public String getSoup() {
        return soup;
    }

    public void setSoup(String soup) {
        this.soup = soup;
    }

    public String getSide1() {
        return side1;
    }

    public void setSide1(String side1) {
        this.side1 = side1;
    }

    public String getSide2() {
        return side2;
    }

    public void setSide2(String side2) {
        this.side2 = side2;
    }

    public String getSide3() {
        return side3;
    }

    public void setSide3(String side3) {
        this.side3 = side3;
    }

    public String getSide4() {
        return side4;
    }

    public void setSide4(String side4) {
        this.side4 = side4;
    }

    public String getDessert() {
        return dessert;
    }

    public void setDessert(String dessert) {
        this.dessert = dessert;
    }

    public String getGood() {
        return good;
    }

    public void setGood(String good) {
        this.good = good;
    }

    public String getToday() {
        return today;
    }

    public void setToday(String today) {
        this.today = today;
    }


    }

