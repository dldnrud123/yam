package com.meals.bruce.meal_test01;

/**
 * Created by Bruce on 2018-01-24.
 */

public interface DialogListener {
        public void onPositiveClicked(int time,int mood, String comment, String pass);
        public void onNegativeClicked();
}
