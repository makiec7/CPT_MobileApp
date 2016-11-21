package com.mobile.cpt.cpt_mobileapp.async;

import android.app.Activity;
import static com.mobile.cpt.cpt_mobileapp.Constant.*;
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
        HttpURLConnection conn;
        BufferedReader bufferedReader;
        try {
            link = HTTP_LOGIN;
            link += ASK + INDEX_NO_EQ + URLEncoder.encode(indexNo, UTF_8);
            link += AND + PASSWORD_EQ + URLEncoder.encode(password, UTF_8);
            Log.i(LINK, link);
            URL url = new URL(link);
            Log.i(URL, url.toString());
            conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(TIMEOUT);
            conn.setDoInput(true);
            conn.connect();
            bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String jsonStr = bufferedReader.readLine().toString();
            JSONObject jsonObj = new JSONObject(jsonStr);
            Log.i(JSON, jsonObj.toString());
            bufferedReader.close();
            conn.disconnect();
            if (jsonStr != null) {
                String login_status = (String) jsonObj.get(LOGIN_STATUS);
                if (login_status.equals(TRUE)) {
                    Log.i(IS_LOGGED, login_status);
                    return new LoginModel(true, (String) jsonObj.get(USER));
                } else {
                    Log.i(IS_LOGGED, login_status);
                    return new LoginModel(false, NULL_STRING);
                }
            } else {
                return new LoginModel(false, NULL_STRING);
            }
        } catch (Exception e) {
            return new LoginModel(false, NULL_STRING);
        }
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }
}
