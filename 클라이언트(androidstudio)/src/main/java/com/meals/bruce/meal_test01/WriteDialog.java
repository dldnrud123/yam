package com.meals.bruce.meal_test01;

/**
 * Created by Bruce on 2018-01-24.
 */
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import android.support.v4.content.ContextCompat;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;


public class WriteDialog extends Dialog implements View.OnClickListener{

    private DialogListener dialogListener;

    private static final int LAYOUT = R.layout.write_popup;

    private Context context;

    ImageButton morning, sun, night;
    ImageButton mood1, mood2, mood3, mood4, mood5;
    EditText comment,pass;
    Button cancel, save;
    private int mood, time;
    int k=0,j=0;



    public WriteDialog(Context context){
        super(context);
        this.context = context;

    }

    public void setDialogListener(DialogListener dialogListener){
        this.dialogListener = dialogListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);

        morning = (ImageButton) findViewById(R.id.btn_morning);
        sun = (ImageButton) findViewById(R.id.btn_sun);
        night = (ImageButton) findViewById(R.id.btn_night);

        morning.setOnClickListener(setOnClickTime(morning,0));
        sun.setOnClickListener(setOnClickTime(sun,1));
        night.setOnClickListener(setOnClickTime(night,2));

        mood1 = (ImageButton) findViewById(R.id.btn_mood1);
        mood2 = (ImageButton) findViewById(R.id.btn_mood2);
        mood3 = (ImageButton) findViewById(R.id.btn_mood3);
        mood4 = (ImageButton) findViewById(R.id.btn_mood4);
        mood5 = (ImageButton) findViewById(R.id.btn_mood5);

        mood1.setOnClickListener(setOnClickMood(mood1, 0));
        mood2.setOnClickListener(setOnClickMood(mood2, 1));
        mood3.setOnClickListener(setOnClickMood(mood3, 2));
        mood4.setOnClickListener(setOnClickMood(mood4, 3));
        mood5.setOnClickListener(setOnClickMood(mood5, 4));

        comment = (EditText)findViewById(R.id.comment);
        pass = (EditText)findViewById(R.id.pass);

        cancel = (Button) findViewById(R.id.cancel);
        save = (Button) findViewById(R.id.save);

        cancel.setOnClickListener(this);
        save.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cancel:
                cancel();
                break;
            case R.id.save:
                String com = comment.getText().toString();
                String password = pass.getText().toString();

                if(k!=0 && j!=0 && !com.equals("") && !password.equals("")) {
                    dialogListener.onPositiveClicked(time, mood, com, password);
                    dismiss();
                    break;
                }
                Toast toast = Toast.makeText(getContext(), "정보를 모두 입력하세요", Toast.LENGTH_LONG);
                toast.show();
                break;
        }
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //바깥레이어 클릭시 안닫히게
        if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
            return false;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        //안드로이드 백버튼 막기
        return;
    }


    public View.OnClickListener setOnClickMood(final ImageButton a, final int i) {
        final View.OnClickListener moodListner = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                j=0;
                int moodList[] = {R.drawable.mood1, R.drawable.mood2, R.drawable.mood3, R.drawable.mood4, R.drawable.mood5};
                int idList[] = {R.id.btn_mood1_back, R.id.btn_mood2_back, R.id.btn_mood3_back, R.id.btn_mood4_back, R.id.btn_mood5_back};

                for (int mood : moodList) {
                    j++;
                    if (mood == moodList[i]) {
                        findViewById(idList[i]).setBackground(ContextCompat.getDrawable(getContext(), R.drawable.circle_button_back));
                        break;
                    }
                }

                for (int id : idList) {
                    if (id != idList[i]) {
                        findViewById(id).setBackground(ContextCompat.getDrawable(getContext(), R.color.colorAccent));
                    }
                }
                mood = j-1;
            }

        };
        return moodListner;
    }
//time case filter
    public View.OnClickListener setOnClickTime(final ImageButton a, final int i) {
        final View.OnClickListener moodListner = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                k=0;
                int timeList[] = {R.drawable.morning, R.drawable.sun, R.drawable.night};
                int idList[] = {R.id.btn_morning_back, R.id.btn_sun_back, R.id.btn_night_back};

                for (int time : timeList) {
                    k++;
                    if (time == timeList[i]) {
                        findViewById(idList[i]).setBackground(ContextCompat.getDrawable(getContext(), R.drawable.circle_button_back));
                        break;
                    }
                }

                for (int id : idList) {
                    if (id != idList[i]) {
                        findViewById(id).setBackground(ContextCompat.getDrawable(getContext(), R.color.colorAccent));
                    }
                }
                time =k-1;
            }

        };
        return moodListner;
    }

}