package com.meals.bruce.meal_test01;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

public class Loading extends Activity {
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.loading);

            ImageView img = (ImageView)findViewById(R.id.load);
            img.setImageResource(R.drawable.infinity);

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {

                @Override
                public void run() {
                    finish();
                }
            }, 2000);// 3 초
        }


}


