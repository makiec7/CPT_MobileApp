package com.mobile.cpt.cpt_mobileapp.async;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import com.mobile.cpt.cpt_mobileapp.model.LoginModel;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class LoginAsync extends AsyncTask<String, String, LoginModel> {
    private Activity activity;

    public LoginAsync(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected LoginModel doInBackground(String... arg0) {

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
            Log.i("URL",url.toString());
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.connect();
            bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String jsonStr = bufferedReader.readLine().toString();
            JSONObject jsonObj = new JSONObject(jsonStr);
            Log.i("JSON", jsonObj.toString());
            bufferedReader.close();
            conn.disconnect();
            if (jsonStr != null) {
                String login_status = (String) jsonObj.get("login_status");
                if (login_status.equals("true")){
                    Log.i("islogged", login_status);
                    return new LoginModel(true, (String) jsonObj.get("user"));
                } else {
                    Log.i("islogged", login_status);
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
