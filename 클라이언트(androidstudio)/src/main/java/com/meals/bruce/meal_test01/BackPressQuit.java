package com.meals.bruce.meal_test01;

import android.app.Activity;
import android.widget.Toast;

/**
 * Created by Bruce on 2018-01-18.
 */

public class BackPressQuit {

    private long backKeyPressedTime = 0;
    private Toast toast;
    private Activity activity;

    public BackPressQuit(Activity context) {
        this.activity = context;
    }
    public void onBackPressed() {
        if (System.currentTimeMillis() > backKeyPressedTime + 1000) {
            backKeyPressedTime = System.currentTimeMillis();
            showGuide();
            return;
        }

        if (System.currentTimeMillis() <= backKeyPressedTime + 1000) {
            activity.finish();
            toast.cancel();
        }
    }

    public void showGuide() {
        toast = Toast.makeText(activity, "'뒤로'버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_LONG);
        toast.show();
    }
}
