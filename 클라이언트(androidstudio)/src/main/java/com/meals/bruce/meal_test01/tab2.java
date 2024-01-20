package com.meals.bruce.meal_test01;


import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.sackcentury.shinebuttonlib.ShineButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

import static android.content.Context.MODE_PRIVATE;


public class tab2 extends Fragment {
    heartList BH, LH, DH;
    DAO dao;

    ArrayList<DTO> breakfast, lunch, dinner;
    View v;
    ServerDownDialog dialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.tab2, container, false);

        AsyncTask2 task2 = new AsyncTask2();
        task2.execute();


        return v;

    }


    //AsyncTask
    class AsyncTask2 extends AsyncTask<Object, Void, Void> {
        LinearLayout ntp1,ntp2,ntp3;
        String key;
        TextView text1, text2, text3;
        TextView date;
        DAO_fcm dao_fcm;
        DTO_fcm noti;
        ImageButton back, next;
        ShineButton heart1, heart2, heart3;
        TextView gtext1, gtext2, gtext3;
        int count;
        int MAX = 6, MIN = 0;
        Calendar cal = Calendar.getInstance();
        @Override
        protected void onPreExecute() {
        }

        @Override
        protected Void doInBackground(Object... objects)  {
            super.onPreExecute();
            count = cal.get(Calendar.DAY_OF_WEEK)-1;

            dao = new DAO();
            dao_fcm = new DAO_fcm();

            breakfast = dao.BF();
            lunch = dao.LC();
            dinner = dao.DN();

            SharedPreferences pref = getContext().getSharedPreferences(
                    getString(R.string.client_key), MODE_PRIVATE);

            key= pref.getString("token","null");
            Log.d("key",key);
            try {
                noti = dao_fcm.noti_select(key);

                if (noti.token == null) {
                    dao_fcm.key_insert(key);
                    noti = dao_fcm.noti_select(key);
                }
            }
            catch(Exception e){

            }


            BH = new heartList();
            LH = new heartList();
            DH = new heartList();

            try {

                BH.heartLoad(getContext(), 0);
                LH.heartLoad(getContext(), 1);
                DH.heartLoad(getContext(), 2);

                if (BH.heartCheck(breakfast,1)) {
                    BH.heartClear(getContext());
                    LH.heartClear(getContext());
                    DH.heartClear(getContext());
                    //heart reload
                    BH.heartLoad(getContext(), 0);
                    LH.heartLoad(getContext(), 1);
                    DH.heartLoad(getContext(), 2);

                }

            }catch(Exception e){
                Log.d("heart?","error!");
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Void... params) {


        }

        @Override
        protected void onPostExecute(Void a) {

            //body
            text1 = (TextView) v.findViewById(R.id.text1);
            text2 = (TextView) v.findViewById(R.id.text2);
            text3 = (TextView) v.findViewById(R.id.text3);

            //menu display
            text1.setText(menuPT(breakfast, count));
            text2.setText(menuPT(lunch, count));
            text3.setText(menuPT(dinner, count));


            gtext1 = (TextView) v.findViewById(R.id.gtext1);
            gtext2 = (TextView) v.findViewById(R.id.gtext2);
            gtext3 = (TextView) v.findViewById(R.id.gtext3);

            // heart count display
            gtext1.setText(goodPT(breakfast, count));
            gtext2.setText(goodPT(lunch, count));
            gtext3.setText(goodPT(dinner, count));

            //heart button setting
            heart1 = setHeart(v.findViewById(R.id.heart1), count, breakfast, BH, gtext1);
            heart2 = setHeart(v.findViewById(R.id.heart2), count, lunch, LH, gtext2);
            heart3 = setHeart(v.findViewById(R.id.heart3), count, dinner, DH, gtext3);

            ntp1 = (LinearLayout)v.findViewById(R.id.ntp_btn1);
            ntp2 = (LinearLayout)v.findViewById(R.id.ntp_btn2);
            ntp3 = (LinearLayout)v.findViewById(R.id.ntp_btn3);
            try{
                setMorningNoti(ntp1,noti,dao_fcm);
                setSunNoti(ntp2,noti,dao_fcm);
                setNightNoti(ntp3,noti,dao_fcm);

            }catch(Exception e){

            }

            //headline
            date = (TextView) v.findViewById(R.id.date);
            date.setText(datePT(breakfast, count));

            back = (ImageButton) v.findViewById(R.id.back);
            next = (ImageButton) v.findViewById(R.id.next);

//            MAX = (dao.BF().size()) / 2 - 1;
            if(count == MIN){
                back.setVisibility(View.INVISIBLE);
            }
            else if(count == MAX){
                next.setVisibility(View.INVISIBLE);
            }
            try {
                if(dinner.get(count).getToday().isEmpty()){
                    heart1.setVisibility(View.INVISIBLE);
                    heart2.setVisibility(View.INVISIBLE);
                    heart3.setVisibility(View.INVISIBLE);
                }

            }catch(Exception e){
                dialog = new ServerDownDialog(getContext());
                dialog.show();
            }



            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    next.setVisibility(View.VISIBLE);

                    heart1.setVisibility(View.VISIBLE);
                    heart2.setVisibility(View.VISIBLE);
                    heart3.setVisibility(View.VISIBLE);

                    if (count > MIN) {
                        count--;
                        if(count == MIN)
                            back.setVisibility(View.INVISIBLE);
                        date.setText(datePT(breakfast, count));

                        text1.setText(menuPT(breakfast, count));
                        text2.setText(menuPT(lunch, count));
                        text3.setText(menuPT(dinner, count));

                        gtext1.setText(goodPT(breakfast, count));
                        gtext2.setText(goodPT(lunch, count));
                        gtext3.setText(goodPT(dinner, count));

                        heart1 = setHeart(heart1, count, breakfast, BH, gtext1);
                        heart2 = setHeart(heart2, count, lunch, LH, gtext2);
                        heart3 = setHeart(heart3, count, dinner, DH, gtext3);

                        try {
                            if (dinner.get(count).getToday().isEmpty()) {
                                heart1.setVisibility(View.INVISIBLE);
                                heart2.setVisibility(View.INVISIBLE);
                                heart3.setVisibility(View.INVISIBLE);
                            }
                        }catch(Exception e){

                        }


                    }

                }
            });


            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    back.setVisibility(View.VISIBLE);

                    heart1.setVisibility(View.VISIBLE);
                    heart2.setVisibility(View.VISIBLE);
                    heart3.setVisibility(View.VISIBLE);

                    if (count < MAX) {
                        count++;
                        if(count == MAX)
                            next.setVisibility(View.INVISIBLE);
                        date.setText(datePT(breakfast, count));

                        text1.setText(menuPT(breakfast, count));
                        text2.setText(menuPT(lunch, count));
                        text3.setText(menuPT(dinner, count));

                        gtext1.setText(goodPT(breakfast, count));
                        gtext2.setText(goodPT(lunch, count));
                        gtext3.setText(goodPT(dinner, count));

                        heart1 = setHeart(heart1, count, breakfast, BH, gtext1);
                        heart2 = setHeart(heart2, count, lunch, LH, gtext2);
                        heart3 = setHeart(heart3, count, dinner, DH, gtext3);

                        try {
                            if (dinner.get(count).getToday().isEmpty()) {
                                heart1.setVisibility(View.INVISIBLE);
                                heart2.setVisibility(View.INVISIBLE);
                                heart3.setVisibility(View.INVISIBLE);
                            }
                        }catch(Exception e){}
                    }



                }
            });



        }
    }

    // 메소드
    public String menuPT(ArrayList<DTO> a, int i) {
        String rice="", soup="", side1="식단", side2="정보가", side3="없습니다", side4="", dessert="";

        try {
            rice = a.get(i).getRice();
            soup = a.get(i).getSoup();
            side1 = a.get(i).getSide1();
            side2 = a.get(i).getSide2();
            side3 = a.get(i).getSide3();
            side4 = a.get(i).getSide4();
            dessert = a.get(i).getDessert();
        }catch(Exception e){
        }
        ArrayList wrapper = new ArrayList<>(Arrays.asList(rice, soup, side1, side2, side3, side4, dessert));
        //null 제거

        for (int num = 0; num < wrapper.size(); num++) {
            Log.d("num:", wrapper.get(num).toString());
            if (wrapper.get(num).equals("null")) {
                wrapper.remove(num);
                Log.d("remove:", "OK!");
            }
            Log.d("data:", wrapper.toString());
        }

        Log.d("tag1:", Integer.toString(wrapper.size()));
        Log.d("tag2:", wrapper.toString());

        Log.d("tag1:", Integer.toString(wrapper.size()));
        if (wrapper.size() == 0) {
            return "준비중입니다.";
        } else {
            String result = "";
            for (Object num : wrapper) {
                result += num.toString() + '\n';
            }
            return result;
        }

    }

    public String datePT(ArrayList<DTO> a, int i) {
        String date;
        String YY, MM, DD;
        ArrayList DAY = new ArrayList(Arrays.asList("일", "월", "화", "수", "목", "금", "토"));
        Calendar cal = Calendar.getInstance();
        try{
            YY = 20 + a.get(i).getToday().substring(0, 2);
            MM = a.get(i).getToday().substring(2, 4);
            DD = a.get(i).getToday().substring(4, 6);
            cal.setFirstDayOfWeek(i);
            date = YY + "." + MM + "." + DD + " " + DAY.get(i);

            return date;

        }catch (Exception e){
            return "준비중입니다";
        }
    }

    public String goodPT(ArrayList<DTO> a, int i) {
        try{
            return a.get(i).getGood();
        } catch (Exception e){
            return "";
        }

    }

    public String goodAdd(DTO a, int i) {
        try{
            int wrapper1;
            String wrapper2;
            wrapper1 = Integer.parseInt(a.getGood());
            wrapper1++;
            wrapper2 = Integer.toString(wrapper1);
            a.setGood(wrapper2);
            Log.d("!!!!!!!!!!!!!good:", wrapper2);
            return wrapper2;
        }
        catch (Exception e) {
            return "";
        }
    }

    public String goodSub(DTO a, int i) {
        try{
            int wrapper1;
            String wrapper2;
            wrapper1 = Integer.parseInt(a.getGood());
            if(wrapper1>0) {
                wrapper1--;
            }
                wrapper2 = Integer.toString(wrapper1);
                a.setGood(wrapper2);
                Log.d("!!!!!!!!!!!!!good:", wrapper2);

            return wrapper2;
        }
        catch (Exception e) {
            return "";
        }
    }

    //like 버튼 세팅
    public ShineButton setHeart(View v, final int i, final ArrayList<DTO> a, final heartList b, final TextView txt) {

        ShineButton btn = (ShineButton) v;
        try {
            if(a.get(i).getToday().isEmpty()){
                Exception e = new Exception();
                throw e;
            }
            btn.setChecked(b.getHeart(i));

            btn.setOnCheckStateChangeListener(new ShineButton.OnCheckedChangeListener() {

                @Override
                public void onCheckedChanged(View view, boolean checked) {
                    b.setHeart(i, checked);
                    if (checked == true) {
                        goodAdd(a.get(i), i);
                        txt.setText(goodPT(a, i));
                        new Thread() {
                            public void run() {
                                dao.insertGood(a, i, 1, a.get(i).getFlag());
                            }
                        }.start();
                        Log.e("clickEvent", Integer.toString(i));
                    } else {
                        goodSub(a.get(i), i);
                        txt.setText(goodPT(a, i));
                        new Thread() {
                            public void run() {
                                dao.insertGood(a, i, 2, a.get(i).getFlag());
                            }
                        }.start();
                        Log.e("clickEvent", Integer.toString(i));
                    }
                }
            });
        } catch(Exception e) {

        }
        return btn;
    }
    //!!!!!!!!!!!!!!!!!!!!!check요망!
    @Override
    public void onPause() {
        super.onPause();
        //app down -> saving heartList
        try{
            if(BH.getDate().isEmpty()) {
                Exception e = new Exception();
                throw e;
            }
            BH.heartSaving(getContext(), 0, breakfast.get(1));
            LH.heartSaving(getContext(), 1, lunch.get(1));
            DH.heartSaving(getContext(), 2, dinner.get(1));

        }catch(Exception e){

        }



    }
    public void setMorningNoti(final LinearLayout notibtn, final DTO_fcm a, final DAO_fcm b) {

        if (a.getMorning() == 0) {
            notibtn.setBackground(getContext().getDrawable(R.color.colorAccent));
        }

        notibtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast;
                if (a.getMorning() == 1) {
                    a.setMorning(0);
                    notibtn.setBackground(getContext().getDrawable(R.color.colorAccent));
                    toast = Toast.makeText(getContext(), "아침 메뉴 알림 OFF", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    a.setMorning(1);
                    notibtn.setBackground(getContext().getDrawable(R.drawable.back_morning));
                    toast = Toast.makeText(getContext(), "아침 메뉴 알림 ON", Toast.LENGTH_SHORT);
                    toast.show();
                }
                new Thread() {
                    @Override
                    public void run() {
                        b.noti_update(a);
                    }
                }.start();

            }
        });


    }
    public void setSunNoti(final LinearLayout notibtn, final DTO_fcm a, final DAO_fcm b) {

        if(a.getSun()==0){
            notibtn.setBackground(getContext().getDrawable(R.color.colorAccent));
        }

        notibtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast;
                if(a.getSun()==1) {
                    a.setSun(0);
                    notibtn.setBackground(getContext().getDrawable(R.color.colorAccent));
                    toast= Toast.makeText(getContext(), "점심 메뉴 알림 OFF", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else {
                    a.setSun(1);
                    notibtn.setBackground(getContext().getDrawable(R.drawable.back_sun));
                    toast = Toast.makeText(getContext(), "점심 메뉴 알림 ON", Toast.LENGTH_SHORT);
                    toast.show();
                }
                new Thread(){
                    @Override
                    public void run() {
                        b.noti_update(a);
                    }
                }.start();
            }
        });


    }
    public void setNightNoti(final LinearLayout notibtn, final DTO_fcm a, final DAO_fcm b) {

        if(a.getNight()==0){
            notibtn.setBackground(getContext().getDrawable(R.color.colorAccent));
        }

        notibtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast;
                if(a.getNight()==1) {
                    a.setNight(0);
                    notibtn.setBackground(getContext().getDrawable(R.color.colorAccent));
                    toast= Toast.makeText(getContext(), "저녁 메뉴 알림 OFF", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else {
                    a.setNight(1);
                    notibtn.setBackground(getContext().getDrawable(R.drawable.back_night));
                    toast = Toast.makeText(getContext(), "저녁 메뉴 알림 ON", Toast.LENGTH_SHORT);
                    toast.show();
                }
                new Thread(){
                    @Override
                    public void run() {
                        b.noti_update(a);
                    }
                }.start();
            }
        });



    }

}





