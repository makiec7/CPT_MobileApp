package com.mobile.cpt.cpt_mobileapp.async;

import android.os.AsyncTask;
import android.util.Log;

import com.mobile.cpt.cpt_mobileapp.Constant;
import com.mobile.cpt.cpt_mobileapp.model.FaultModel;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import static com.mobile.cpt.cpt_mobileapp.Constant.*;

public class EditAsync extends AsyncTask<FaultModel, String, Boolean> {

    @Override
    protected Boolean doInBackground(FaultModel... faultModels) {
        FaultModel fault = faultModels[0];
        String link;
        HttpURLConnection conn;
        BufferedReader bufferedReader;
        try {
            link = EDIT_HTTP;
            link += ASK + ID_EQ + URLEncoder.encode(Integer.toString(fault.getId()),
                    UTF_8);
            link += AND + TOPIC_EQ + URLEncoder.encode(fault.getTopic(), UTF_8);
            link += AND + PHONE_NUMBER_EQ + URLEncoder.encode(fault.getPhone_number(), UTF_8);
            link += AND + DESCRIPTION_EQ + URLEncoder.encode(fault.getDescription(), UTF_8);
            Log.i("descr", fault.getDescription());
            URL url = new URL(link);
            Log.i(Constant.URL, url.toString());
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
                String query_status = (String) jsonObj.get(QUERY_STATUS);
                if (query_status.equals(TRUE)) {
                    Log.i(QUERY_STATUS, query_status);
                    return true;
                } else {
                    Log.i(QUERY_STATUS, query_status);
                    return false;
                }
            } else {
                Log.i(QUERY_STATUS, FALSE);
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }
}
