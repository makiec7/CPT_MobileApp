package com.mobile.cpt.cpt_mobileapp;


import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.mobile.cpt.cpt_mobileapp.Constant.TIMEOUT;

public class JSONFromLink {
    private HttpURLConnection conn;
    private BufferedReader bufferedReader;
    private final String link;

    public JSONFromLink(String link){
        this.link = link;
    }

    public JSONObject getFromLink() throws IOException, JSONException {
        URL url = new URL(link);
        Log.i(Constant.URL, url.toString());
        conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(TIMEOUT);
        conn.setDoInput(true);
        conn.connect();
        bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String jsonStr = bufferedReader.readLine().toString();
        bufferedReader.close();
        conn.disconnect();
        return new JSONObject(jsonStr);
    }
}
