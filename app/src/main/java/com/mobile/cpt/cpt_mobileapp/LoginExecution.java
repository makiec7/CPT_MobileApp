package com.mobile.cpt.cpt_mobileapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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

public class LoginExecution extends AsyncTask<String, String, Boolean> {
    private Activity activity;

    public LoginExecution(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected Boolean doInBackground(String... arg0) {

        String indexNo = arg0[0];
        String password = arg0[1];
        String link;
        String data;
        HttpURLConnection conn;
        BufferedReader bufferedReader;
        try {
            data = "?index_no=" + URLEncoder.encode(indexNo, "UTF-8");
            data += "&password=" + URLEncoder.encode(password, "UTF-8");
            link = "http://cpt4cti.000webhostapp.com/login.php" + data;
            Log.i("link", link);
            URL url = new URL(link);
            Log.i("url",url.toString());
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true);
            Log.i("url2",url.toString());
            conn.connect();
            bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            Log.i("url3",url.toString());
            Log.i("content", bufferedReader.readLine().toString());

            String jsonStr = bufferedReader.readLine();
            JSONObject jsonObj = new JSONObject(jsonStr);
            Log.i("json", jsonObj.toString());
            bufferedReader.close();
            conn.disconnect();
            if (jsonStr != null) {
                //JSONObject jsonObj = new JSONObject(jsonStr);
                Log.i("json", jsonObj.toString());
                return false;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }


}
