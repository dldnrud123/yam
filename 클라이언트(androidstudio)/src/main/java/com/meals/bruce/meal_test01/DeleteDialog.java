package com.meals.bruce.meal_test01;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Bruce on 2018-01-24.
 */

public class DeleteDialog extends Dialog implements View.OnClickListener {

    private DeleteDialogListener deleteListener;
    private static final int LAYOUT = R.layout.delete_popup;
    private Context context;

    Button cancel, delete;
    EditText pass;


    public DeleteDialog(Context context) {
        super(context);
        this.context = context;
    }

    public void setDeleteListener(DeleteDialogListener deleteListener) {
        this.deleteListener = deleteListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);

        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);


        pass = (EditText) findViewById(R.id.password);
        cancel = (Button) findViewById(R.id.cancel1);
        delete = (Button) findViewById(R.id.delete);

        cancel.setOnClickListener(this);
        delete.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cancel1:
                InputMethodManager immhide = (InputMethodManager) getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
                immhide.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
                cancel();
                break;
            case R.id.delete:
                String password = pass.getText().toString();

                if (!password.equals("")) {
                    deleteListener.onPositiveClicked(password);
                    dismiss();
                    break;
                }
                Toast toast = Toast.makeText(getContext(), "비밀번호를 입력하세요", Toast.LENGTH_LONG);
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


}
