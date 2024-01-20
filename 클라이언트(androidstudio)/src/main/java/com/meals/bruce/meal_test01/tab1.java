package com.meals.bruce.meal_test01;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import static android.content.Context.MODE_PRIVATE;


public class tab1 extends Fragment {
    View v;
    DAO_fcm fcm;
    ServerDownDialog dialog;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.tab1, container, false);

        TextView timeText1 = (TextView) v.findViewById(R.id.time1);
        TextView timeText2 = (TextView) v.findViewById(R.id.time2);

        timeText1.setText("<평일>\n\n아침 08:00 - 09:00 \n점심 11:40 - 13:20 \n저녁 17:50 - 19:00 ");
        timeText2.setText("<주말>\n\n아침 08:00 - 09:00 \n점심 12:00 - 13:00 \n저녁 18:00 - 19:00 ");

        AsyncTask1 tesk = new AsyncTask1();
        tesk.execute();

        return v;
    }

    //AsyncTask
    class AsyncTask1 extends AsyncTask<Object, Void, Void> {

        weather wt;
        ImageView weatherIcon;
        TextView weather;

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected Void doInBackground(Object... objects) {
            super.onPreExecute();
            //weather

//            reIDservice(getContext());

            weather = (TextView) v.findViewById(R.id.weather);
            weatherIcon = (ImageView) v.findViewById(R.id.weatherIcon);
            wt = new weather();
            wt.parsing();

            return null;
        }

        @Override
        protected void onProgressUpdate(Void... params) {

        }

        @Override
        protected void onPostExecute(Void a) {
            try{
                weather.setText(wt.getTmp());
                weatherIcon.setImageResource(wt.getWeather());
                weatherIcon.invalidate();

            }catch(Exception e){

            }

        }
    }

    public void reIDservice(Context c){
        String token;
        SharedPreferences pref = c.getSharedPreferences(
                getString(R.string.client_key), MODE_PRIVATE);
        Log.d("num","1");
        token = pref.getString("token",null);
        try {
            if(fcm.key_check(token) && token != null){
                Log.d("num","2");
                DAO_fcm fcm = new DAO_fcm();
                fcm.key_insert(token);
                Log.d("num","3");
            }
        }catch (Exception e){
            dialog = new ServerDownDialog(getContext());
            dialog.show();
        }
    }

}




