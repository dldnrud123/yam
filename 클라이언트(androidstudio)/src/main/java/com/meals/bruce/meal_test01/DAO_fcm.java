package com.meals.bruce.meal_test01;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Bruce on 2018-03-15.
 */

public class DAO_fcm {
    public static final int TIME_OUT_LANGTH = 1000;

    ArrayList<DTO_fcm> FCMlist = new ArrayList<>();
    DTO_fcm a;
    String token;
    int morning,sun,night;

    String ip = ""; // server ip address
    String port = ""; //server port num
    HttpURLConnection conn;

    DAO_fcm() {

    }

    public ArrayList<DTO_fcm> key_select() {
        String line, page = "";
        try {

            URL url = new URL("http://" + ip + ":" + port + "/mealtest_01/key_select"); //요청 URL을 입력
            conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(TIME_OUT_LANGTH);
            conn.setReadTimeout(TIME_OUT_LANGTH);
            conn.setRequestMethod("POST"); //요청 방식을 설정 (default : GET)
            conn.setDoInput(true); //input을 사용하도록 설정 (default : true)
            conn.setDoOutput(true); //output을 사용하도록 설정 (default : false)


            Log.d("loading", "getload");
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8")); //캐릭터셋 설정
            while ((line = br.readLine()) != null) {
                page += line;
            }
            Log.d("receiveMsg : ", page);
            JSONObject json = new JSONObject(page);
            JSONArray jArr = json.getJSONArray("client_key");

            for (int i = 0; i < jArr.length(); i++) {
                json = jArr.getJSONObject(i);
                token = json.getString("token");
                morning = json.getInt("morning");
                sun = json.getInt("sun");
                night = json.getInt("night");

                FCMlist.add(new DTO_fcm(token, morning ,sun,night));
            }


        } catch (Exception e) {
            Log.e("에러발생!!!", e.toString());
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
        return FCMlist;
    }

    public boolean key_check(String key) {
        String line, page = "";
        try {
            URL url = new URL("http://" + ip + ":" + port + "/mealtest_01/key_select"); //요청 URL을 입력
            conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(TIME_OUT_LANGTH);
            conn.setReadTimeout(TIME_OUT_LANGTH);
            conn.setRequestMethod("POST"); //요청 방식을 설정 (default : GET)
            conn.setDoInput(true); //input을 사용하도록 설정 (default : true)
            conn.setDoOutput(true); //output을 사용하도록 설정 (default : false)
            Log.d("loading", "getload");
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8")); //캐릭터셋 설정
            while ((line = br.readLine()) != null) {
                page += line;
            }

            Log.d("receiveMsg : ", page);
            JSONObject json = new JSONObject(page);
            JSONArray jArr = json.getJSONArray("client_key");

            for (int i = 0; i < jArr.length(); i++) {
                json = jArr.getJSONObject(i);
                token = json.getString("token");
                if (key == token) {
                    return false;
                }
            }


        } catch (Exception e) {
            Log.e("에러발생!!!", e.toString());
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
        return true;
    }

    public void key_insert(String key) {
        try {
            URL url = new URL("http://" + ip + ":" + port + "/mealtest_01/savingFCM");
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestMethod("POST"); //요청 방식을 설정 (default : GET)
            conn.setDoInput(true); //input을 사용하도록 설정 (default : true)
            conn.setDoOutput(true); //output을 사용하도록 설정 (default : false)
            Log.d("token DATA:", key);

            OutputStreamWriter wt = new OutputStreamWriter(conn.getOutputStream());
            wt.write("token=" + key);
            wt.flush();
            Log.d("loading After", conn.getResponseMessage());

        } catch (Exception e) {
            e.printStackTrace();
            Log.d("Token Sending Error", "Notification Error!!!!!!!!!");
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }

    }

    public DTO_fcm noti_select(String key) throws Exception{
        String line, page = "";
        try {

            URL url = new URL("http://" + ip + ":" + port + "/mealtest_01/noti_select"); //요청 URL을 입력
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setConnectTimeout(TIME_OUT_LANGTH);
            conn.setReadTimeout(TIME_OUT_LANGTH);
            conn.setRequestMethod("POST"); //요청 방식을 설정 (default : GET)
            conn.setDoInput(true); //input을 사용하도록 설정 (default : true)
            conn.setDoOutput(true); //output을 사용하도록 설정 (default : false)

            OutputStreamWriter wt = new OutputStreamWriter(conn.getOutputStream());
            wt.write("token=" + key);
            wt.flush();
            Log.d("loading After", conn.getResponseMessage());

            Log.d("loading", "getload");
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8")); //캐릭터셋 설정
            while ((line = br.readLine()) != null) {
                page += line;
            }

            JSONObject json = new JSONObject(page);
            JSONObject jMain = json.getJSONObject("client_key");
            try {
                token = jMain.getString("token");
                morning = jMain.getInt("morning");
                sun = jMain.getInt("sun");
                night = jMain.getInt("night");

                a = new DTO_fcm(token, morning, sun, night);
                Log.d("test3:",Integer.toString(a.getMorning())+','+Integer.toString(a.getSun())+','+Integer.toString(a.getNight()));
            }catch (Exception e) {
                a = new DTO_fcm(null, 1, 1, 1);
            }


        } catch (Exception e) {
            Log.e("에러발생!!!", e.toString());
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }

        return a;
    }

    public void noti_update(DTO_fcm a) {
        try {
            URL url = new URL("http://" + ip + ":"+port+"/mealtest_01/noti_update");
            conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(TIME_OUT_LANGTH);
            conn.setReadTimeout(TIME_OUT_LANGTH);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestMethod("POST"); //요청 방식을 설정 (default : GET)
            conn.setDoInput(true); //input을 사용하도록 설정 (default : true)
            conn.setDoOutput(true); //output을 사용하도록 설정 (default : false)

            OutputStreamWriter wt = new OutputStreamWriter(conn.getOutputStream());
            wt.write("key=" + a.getToken() + "&morning=" + a.getMorning()+ "&sun=" + a.getSun() +"&night=" + a.getNight());
            wt.flush();
            Log.d("loading After", conn.getResponseMessage());

        } catch (Exception e) {
            e.printStackTrace();
            Log.d("Error", "load Error!!!!!!!!!");
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }

    }
}

