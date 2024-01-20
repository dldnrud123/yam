package com.meals.bruce.meal_test01;

import android.content.SharedPreferences;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import java.net.HttpURLConnection;


public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {

    private static final String TAG = "MyFirebaseIIDService";

    @Override
    public void onTokenRefresh() {
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + refreshedToken);

        sendRegistrationToServer(refreshedToken);

    }

    private void sendRegistrationToServer(String token) {
        // Add custom implementation, as needed.

        SharedPreferences pref = getSharedPreferences(getString(R.string.client_key), MODE_PRIVATE);
        pref.getString("token", null);
        SharedPreferences.Editor editor = pref.edit();
        Log.d("token", token);
        editor.putString("token", token);
        editor.putInt("morning", 1);
        editor.putInt("sun", 1);
        editor.putInt("night", 1);
        editor.commit();


        DAO_fcm fcm = new DAO_fcm();
        fcm.key_insert(token);
        Log.d(TAG, "Success!! token send to server");
    }
    public String getToken(){
        String token = FirebaseInstanceId.getInstance().getToken();
        return token;
    }
}

