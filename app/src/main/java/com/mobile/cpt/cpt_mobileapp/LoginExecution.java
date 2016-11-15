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

public class LoginExecution extends AsyncTask<String, Void, LoginResult> {

    final String USER_AGENT = "Mozilla/5.0 (Linux; U; Android 1.6; en-us; GenericAndroidDevice) AppleWebKit/528.5+ (KHTML, like Gecko) Version/3.1.2 Mobile Safari/525.20.1";
    private Context activity;

    public LoginExecution(Context activity) {
        this.activity = activity;
    }

    @Override
    protected LoginResult doInBackground(String... arg0) {
        String indexNo = arg0[0];
        String password = arg0[1];
        String link;
        String data;
        BufferedReader bufferedReader;
        String result;

        try {
            data = "?index_no=" + URLEncoder.encode(indexNo, "UTF-8");
            data += "&password=" + URLEncoder.encode(password, "UTF-8");

            link = "http://cpt4cti.000webhostapp.com/login.php" + data;
            String content;
            URL url = new URL(link);
            Log.i("url",url.toString());
            Log.i("here", "1");

            HttpURLConnection connection = (HttpURLConnection) new URL(link).openConnection();
            Log.i("here", "1");
            connection.setRequestMethod("GET");
            Log.i("here", "1");
            connection.connect();
            Log.i("here", "1");
            InputStream inputStream = connection.getInputStream();
            Log.i("here", "1");
            BufferedReader buffer = new BufferedReader(new InputStreamReader(inputStream));
            Log.i("here", "1");
            StringBuffer content1 = new StringBuffer();
            Log.i("here", "1");
            String line = null;

            while ((line = buffer.readLine()) != null) {
                content1.append(line);
            }
            inputStream.close();
            connection.disconnect();
            Log.i("content", content1.toString());

            //conn.disconnect();
           /* Log.i("here", "2");
            Log.i("response code:", conn.getContent().toString());
            int responseCode = conn.getResponseCode();
            Log.i("here", "3");
            if (responseCode == HttpURLConnection.HTTP_OK) {
                Log.i("here", "4");
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line + "\n");
                }
                br.close();
                content =  sb.toString();

                Log.i("here", "5");
            } else {
                Log.i("here", "6");
                content = "null";
            }
            Log.i("content", content);
            conn.disconnect();
            /*HttpURLConnection con = (HttpURLConnection) url.openConnection();
            Log.i("con", con.toString());
            Log.i("getIS", con.getInputStream().toString());
            Log.i("is", new InputStreamReader(con.getInputStream()).toString());
            bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            Log.i("bufferedReader", bufferedReader.toString());
            jsonStr = bufferedReader.readLine();
            Log.i("result", result);*/
            //String jsonStr = result;
            /*
            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);
                    String query_result = jsonObj.getString("login_status");
                    if (query_result.equals("true")) {
                        return new LoginResult(true, jsonObj.getString("user"));
                    } else if (query_result.equals("false")) {
                        return new LoginResult(false, "");
                    } else {
                        return new LoginResult(false, "");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    return new LoginResult(false, "");
                }
            } else {
                return new LoginResult(false, "");
            }*/
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    private String readStream(InputStream in) {
        BufferedReader reader = null;
        StringBuffer response = new StringBuffer();
        try {
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return response.toString();
    }
}
