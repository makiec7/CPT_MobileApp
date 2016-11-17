package com.mobile.cpt.cpt_mobileapp.async;

import android.app.Activity;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.mobile.cpt.cpt_mobileapp.model.LoginModel;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLEncoder;

public class LoginAsync extends AsyncTask<String, String, LoginModel> {
    private Activity activity;

    public LoginAsync(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected LoginModel doInBackground(String... arg0) {
        try {
            if (InetAddress.getByName("http://cpt4cti.000webhostapp.com/login.php").isReachable(1000)) {
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
                    Log.i("URL", url.toString());
                    conn = (HttpURLConnection) url.openConnection();
                    conn.setConnectTimeout(1000);
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
                        if (login_status.equals("true")) {
                            Log.i("islogged", login_status);
                            return new LoginModel(true, (String) jsonObj.get("user"));
                        } else {
                            Log.i("islogged", login_status);
                            return new LoginModel(false, "");
                        }
                    } else {
                        return new LoginModel(false, "");
                    }
                } catch (Exception e) {
                    return new LoginModel(false, "");
                }
            } else {
                Toast.makeText(activity.getApplicationContext(), "Brak połączenia z internetem",
                        Toast.LENGTH_LONG).show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }



}
