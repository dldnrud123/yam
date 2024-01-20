package com.meals.bruce.meal_test01;

import android.app.Application;

import com.tsengvn.typekit.Typekit;

/**
 * Created by Bruce on 2018-01-18.
 */

public class AppBase extends Application {

    public void onCreate() {
        super.onCreate();
        // 폰트 정의
        Typekit.getInstance()
                .addNormal(Typekit.createFromAsset(this, "fonts/NanumBarunpenR.otf"))
                .addBold(Typekit.createFromAsset(this, "fonts/NanumBarunpenB.otf"));
    }

}
