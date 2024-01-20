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
 * Created by Bruce on 2018-01-22.
 */

public class DAO_com {
    ArrayList<DTO_com> comList = new ArrayList<>();
    int time, grade;
    String com, pass;

    String ip = ""; // server ip address
    String port = ""; //server port num


    HttpURLConnection conn;

    public ArrayList<DTO_com> select() {
        String line, page = "";
        try {
            URL url = new URL("http://" + ip + ":"+port+"/mealtest_01/comSelect"); //요청 URL을 입력
            conn = (HttpURLConnection) url.openConnection();
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
            JSONArray jArr = json.getJSONArray("communication");

            for (int i = 0; i < jArr.length(); i++) {
                json = jArr.getJSONObject(i);
                com = json.getString("com");
                time = Integer.parseInt(json.getString("time"));
                grade = Integer.parseInt(json.getString("grade"));
                pass = json.getString("pass");
                comList.add(new DTO_com(time, grade, com, pass));
            }


        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
        return comList;
    }

    public void insert(ArrayList<DTO_com> a) {
        String urladrs = "http://" + ip + ":"+port+"/mealtest_01/comInsert";
        try {
            URL url = new URL(urladrs);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestMethod("POST"); //요청 방식을 설정 (default : GET)
            conn.setDoInput(true); //input을 사용하도록 설정 (default : true)
            conn.setDoOutput(true); //output을 사용하도록 설정 (default : false)

            OutputStreamWriter wt = new OutputStreamWriter(conn.getOutputStream());
            Log.d("insert messege1", "time=" + a.get(0).getTime() + "&grade=" + a.get(0).getGrade() + "&com=" + a.get(0).getCom() + "&pass=" + a.get(0).getPass());
            wt.write("time=" + a.get(0).getTime() + "&grade=" + a.get(0).getGrade() + "&com=" + a.get(0).getCom() + "&pass=" + a.get(0).getPass());
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

    public void delete(ArrayList<DTO_com> a, int i) {
        String urladrs = "http://" + ip + ":"+port+"/mealtest_01/comDelete";
        try {
            URL url = new URL(urladrs);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestMethod("POST"); //요청 방식을 설정 (default : GET)
            conn.setDoInput(true); //input을 사용하도록 설정 (default : true)
            conn.setDoOutput(true); //output을 사용하도록 설정 (default : false)

            OutputStreamWriter wt = new OutputStreamWriter(conn.getOutputStream());
            Log.d("insert messege1", "time=" + a.get(i).getTime() + "&grade=" + a.get(i).getGrade() + "&com=" + a.get(i).getCom() + "&pass=" + a.get(i).getPass());
            wt.write("time=" + a.get(i).getTime() + "&grade=" + a.get(i).getGrade() + "&com=" + a.get(i).getCom() + "&pass=" + a.get(i).getPass());
            wt.flush();
            a.remove(i);
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
