package com.mobile.cpt.cpt_mobileapp;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.logging.Logger;

public class LoginExecution extends AsyncTask<String, String, LoginResult> {
    private Activity activity;

    public LoginExecution(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected LoginResult doInBackground(String... arg0) {
        String indexNo = arg0[0];
        String password = arg0[1];
        String link;
        String data;
        BufferedReader bufferedReader;
        try {
            data = "?index_no=" + URLEncoder.encode(indexNo, "UTF-8");
            data += "&password=" + URLEncoder.encode(password, "UTF-8");
            link = "http://cpt4cti.000webhostapp.com/login.php" + data;
            URL url = new URL(link);
            Log.i("url",url.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setUseCaches(false);
            conn.setConnectTimeout(3000);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            Log.i("url2",url.toString());
            bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            Log.i("url3",url.toString());
            String jsonStr = bufferedReader.readLine();

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);
                    String query_result = jsonObj.getString("login_status");
                    if (query_result.equals("true")) {
                        Log.i("true","true");
                        return new LoginResult(true, jsonObj.getString("user"));
                    } else if (query_result.equals("false")) {
                        Log.i("false","false");
                        return null;
                    } else {
                        return null;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    return null;
                }
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }

    }


}
