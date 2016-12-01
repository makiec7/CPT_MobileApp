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

import static com.mobile.cpt.cpt_mobileapp.Constant.ASK;
import static com.mobile.cpt.cpt_mobileapp.Constant.FALSE;
import static com.mobile.cpt.cpt_mobileapp.Constant.ID_EQ;
import static com.mobile.cpt.cpt_mobileapp.Constant.JSON;
import static com.mobile.cpt.cpt_mobileapp.Constant.QUERY_STATUS;
import static com.mobile.cpt.cpt_mobileapp.Constant.TIMEOUT;
import static com.mobile.cpt.cpt_mobileapp.Constant.TRUE;
import static com.mobile.cpt.cpt_mobileapp.Constant.UTF_8;


public class DeleteAsync extends AsyncTask<FaultModel, String, Boolean> {
    @Override
    protected Boolean doInBackground(FaultModel... faultModels) {
        FaultModel fault = faultModels[0];
        String link;
        HttpURLConnection conn;
        BufferedReader bufferedReader;
        try {
            link = "http://cpt4cti.24tm.pl/delete_fault.php";
            link += ASK + ID_EQ + URLEncoder.encode(Integer.toString(fault.getId()),
                    UTF_8);
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
