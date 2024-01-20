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
 * Created by Bruce on 2018-01-05.
 */

public class DAO extends Thread {
    public static final int TIME_OUT_LANGTH = 1000;

    ArrayList<DTO> BFlist = new ArrayList<>();
    ArrayList<DTO> LClist = new ArrayList<>();
    ArrayList<DTO> DNlist = new ArrayList<>();
    String today, rice, soup, side1, side2, side3, side4, dessert, good;

    String ip = ""; // server ip address
    String port = ""; //server port num

    HttpURLConnection conn;

    DAO() {

    }

    public ArrayList<DTO> BF()  {
        String line, page = "";
        try {

            URL url = new URL("http://" + ip + ":"+port+"/mealtest_01/BF"); //요청 URL을 입력
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
            JSONArray jArr = json.getJSONArray("BF");

            for (int i = 0; i < jArr.length(); i++) {
                json = jArr.getJSONObject(i);
                today = json.getString("today");
                rice = json.getString("rice");
                soup = json.getString("soup");
                side1 = json.getString("side1");
                side2 = json.getString("side2");
                side3 = json.getString("side3");
                side4 = json.getString("side4");
                dessert = json.getString("dessert");
                good = json.getString("good");

                BFlist.add(new DTO(today, rice, soup, side1, side2, side3, side4, dessert, good, 0));
            }


        } catch (Exception e) {
            Log.e("에러발생!!!",e.toString());
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
        return BFlist;
    }

    public ArrayList<DTO> LC() {
        String line, page = "";
        try {
            URL url = new URL("http://" + ip + ":"+port+"/mealtest_01/LC"); //요청 URL을 입력
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
            JSONArray jArr = json.getJSONArray("LC");

            for (int i = 0; i < jArr.length(); i++) {
                json = jArr.getJSONObject(i);
                today = json.getString("today");
                rice = json.getString("rice");
                soup = json.getString("soup");
                side1 = json.getString("side1");
                side2 = json.getString("side2");
                side3 = json.getString("side3");
                side4 = json.getString("side4");
                dessert = json.getString("dessert");
                good = json.getString("good");

                LClist.add(new DTO(today, rice, soup, side1, side2, side3, side4, dessert, good, 1));

            }


        } catch (Exception e) {
            Log.e("에러발생!!!",e.toString());

        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
        return LClist;
    }

    public ArrayList<DTO> DN() {
        String line, page = "";
        try {
            URL url = new URL("http://" + ip + ":"+port+"/mealtest_01/DN"); //요청 URL을 입력
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
            JSONArray jArr = json.getJSONArray("DN");

            for (int i = 0; i < jArr.length(); i++) {
                json = jArr.getJSONObject(i);

                today = json.getString("today");
                rice = json.getString("rice");
                soup = json.getString("soup");
                side1 = json.getString("side1");
                side2 = json.getString("side2");
                side3 = json.getString("side3");
                side4 = json.getString("side4");
                dessert = json.getString("dessert");
                good = json.getString("good");
                DNlist.add(new DTO(today, rice, soup, side1, side2, side3, side4, dessert, good, 2));

            }


        } catch (Exception e) {
            Log.e("에러발생!!!",e.toString());
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
        return DNlist;
    }

    public void insertGood(ArrayList<DTO> a, int i, int type, int f) {
        String urlList[] = {"http://" + ip + ":"+port+"/mealtest_01/BF_heart", "http://" + ip + ":"+port+"/mealtest_01/LC_heart", "http://" + ip + ":"+port+"/mealtest_01/DN_heart"};
        try {
            URL url = new URL(urlList[f]);
            conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(TIME_OUT_LANGTH);
            conn.setReadTimeout(TIME_OUT_LANGTH);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestMethod("POST"); //요청 방식을 설정 (default : GET)
            conn.setDoInput(true); //input을 사용하도록 설정 (default : true)
            conn.setDoOutput(true); //output을 사용하도록 설정 (default : false)
            Log.d("f:", Integer.toString(f));

            OutputStreamWriter wt = new OutputStreamWriter(conn.getOutputStream());
            wt.write("date=" + a.get(i).getToday() + "&type=" + type);
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
