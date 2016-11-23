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

import static com.mobile.cpt.cpt_mobileapp.Constant.AND;
import static com.mobile.cpt.cpt_mobileapp.Constant.ASK;
import static com.mobile.cpt.cpt_mobileapp.Constant.DATE_TIME_EQ;
import static com.mobile.cpt.cpt_mobileapp.Constant.DESCRIPTION_EQ;
import static com.mobile.cpt.cpt_mobileapp.Constant.FALSE;
import static com.mobile.cpt.cpt_mobileapp.Constant.HTTP_REPORT;
import static com.mobile.cpt.cpt_mobileapp.Constant.ISSUER_EQ;
import static com.mobile.cpt.cpt_mobileapp.Constant.JSON;
import static com.mobile.cpt.cpt_mobileapp.Constant.OBJECT_NUMBER_EQ;
import static com.mobile.cpt.cpt_mobileapp.Constant.PHONE_NUMBER_EQ;
import static com.mobile.cpt.cpt_mobileapp.Constant.QUERY_STATUS;
import static com.mobile.cpt.cpt_mobileapp.Constant.TIMEOUT;
import static com.mobile.cpt.cpt_mobileapp.Constant.TOPIC_EQ;
import static com.mobile.cpt.cpt_mobileapp.Constant.TRUE;
import static com.mobile.cpt.cpt_mobileapp.Constant.UTF_8;

public class EditAsync extends AsyncTask<FaultModel, String, Boolean> {
    @Override
    protected Boolean doInBackground(FaultModel... faultModels) {
        FaultModel fault = faultModels[0];
        String link;
        HttpURLConnection conn;
        BufferedReader bufferedReader;
        try {
            link = "https://cpt4cti.000webhostapp.com/edit_fault.php";
            link += ASK + "id=" + URLEncoder.encode(Integer.toString(fault.getIssuer()),
                    UTF_8);
            link += AND + TOPIC_EQ + URLEncoder.encode(fault.getTopic(), UTF_8);
            link += AND + PHONE_NUMBER_EQ + URLEncoder.encode(fault.getPhone_number(), UTF_8);
            link += AND + DESCRIPTION_EQ + URLEncoder.encode(fault.getDescription(), UTF_8);
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
