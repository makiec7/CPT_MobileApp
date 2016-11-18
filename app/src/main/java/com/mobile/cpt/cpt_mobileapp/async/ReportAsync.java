package com.mobile.cpt.cpt_mobileapp.async;

import android.os.AsyncTask;
import android.util.Log;

import com.mobile.cpt.cpt_mobileapp.model.FaultModel;
import com.mobile.cpt.cpt_mobileapp.model.LoginModel;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import static com.mobile.cpt.cpt_mobileapp.Constant.*;

public class ReportAsync extends AsyncTask<FaultModel, String, Boolean>{

    @Override
    protected Boolean doInBackground(FaultModel... faultModels) {
        // if (!InetAddress.getByName(HTTP_LOGIN).isReachable(TIMEOUT)) {
        if (true) {
            FaultModel fault = faultModels[0];
            Log.i("fault in async", fault.toString());
            String link;
            HttpURLConnection conn;
            BufferedReader bufferedReader;
            try {
                link = HTTP_REPORT;
                link += ASK + "issuer=" + URLEncoder.encode(Integer.toString(fault.getIssuer()), UTF_8);
                link += AND + "phone_no=" + URLEncoder.encode(fault.getPhone_no(), UTF_8);
                link += AND + "topic=" + URLEncoder.encode(fault.getTopic(), UTF_8);
                link += AND + "obj_name=" + URLEncoder.encode(fault.getObj_name(), UTF_8);
                link += AND + "description=" + URLEncoder.encode(fault.getDescription(), UTF_8);
                link += AND + "obj_no=" + URLEncoder.encode(Integer.toString(fault.getObj_no()), UTF_8);
                link += AND + "room_no=" + URLEncoder.encode(Integer.toString(fault.getRoom_no()), UTF_8);
                link += AND + "floor_no=" + URLEncoder.encode(Integer.toString(fault.getFloor_no()), UTF_8);
                link += AND + "status=" + URLEncoder.encode(Integer.toString(fault.getStatus()), UTF_8);
                link += AND + "handler=" + URLEncoder.encode(fault.getHandler(), UTF_8);
                link += AND + "priority=" + URLEncoder.encode(Integer.toString(fault.getPriority()), UTF_8);
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
                    String query_status = (String) jsonObj.get(QUERY_STATUS);
                    if (query_status.equals(TRUE)) {
                        Log.i(QUERY_STATUS, query_status);
                        return true;
                    } else {
                        Log.i(QUERY_STATUS, query_status);
                        return false;
                    }
                } else {
                    Log.i(QUERY_STATUS, "false");
                    return false;
                }
            } catch (Exception e) {
                return false;
            }
        } else {
            return false;
        }
    }
}

