package com.mobile.cpt.cpt_mobileapp.async;

import android.os.AsyncTask;
import android.util.Log;

import com.mobile.cpt.cpt_mobileapp.model.FaultModel;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import static com.mobile.cpt.cpt_mobileapp.Constant.*;

public class PresentAsync extends AsyncTask <String, String, List<FaultModel>> {

    @Override
    protected List<FaultModel> doInBackground(String... strings) {

        if (strings.length > 0){
            List<FaultModel> list = selectUsersFaults(strings[0]);
            Log.i("list user async", list.toString());
            return selectUsersFaults(strings[0]);
        } else {
            List<FaultModel> list =selectAllFaults();
            Log.i("list user async", list.toString());
            return selectAllFaults();
        }
    }

    private List<FaultModel> selectAllFaults() {
        String link;
        HttpURLConnection conn;
        BufferedReader bufferedReader;
        try {
            link = HTTP_PRESENT_ALL_FAULTS;
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

            Log.i("list", "1");
            conn.disconnect();

            Log.i("list", "2");
            if (jsonObj != null) {
                Log.i("list", "4");

                List<FaultModel> faultsJSON = new ArrayList<FaultModel>();
                
                Log.i("faultJSON", faultsJSON.toString());
                if (!faultsJSON.isEmpty()) {
                    Log.i("list", faultsJSON.toString());
                    return faultsJSON;
                } else {
                    Log.i("list", "empty");
                    return new ArrayList<FaultModel>();
                }
            } else {
                Log.i(QUERY_STATUS, "false");
                return new ArrayList<FaultModel>();
            }
        } catch (Exception e) {
            Log.i("list", "3");
            return new ArrayList<FaultModel>();
        }
    }

    private List<FaultModel> selectUsersFaults(String user){
        if (true) {
            String link;
            HttpURLConnection conn;
            BufferedReader bufferedReader;
            try {
                link = HTTP_PRESENT_USER_FAULTS;
                link += ASK + "index_no=" + URLEncoder.encode(user, UTF_8);
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
                    List<FaultModel> faultsJSON = (List<FaultModel>) jsonObj.get("list");
                    if (!faultsJSON.isEmpty()) {
                        Log.i("list", faultsJSON.toString());
                        return faultsJSON;
                    } else {
                        Log.i("list", "empty");
                        return new ArrayList<FaultModel>();
                    }
                } else {
                    Log.i(QUERY_STATUS, "false");
                    return new ArrayList<FaultModel>();
                }
            } catch (Exception e) {
                return new ArrayList<FaultModel>();
            }
        } else {
            return new ArrayList<FaultModel>();
        }
    }
}
