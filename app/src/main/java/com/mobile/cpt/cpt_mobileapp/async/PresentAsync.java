package com.mobile.cpt.cpt_mobileapp.async;

import android.os.AsyncTask;
import android.util.Log;

import com.mobile.cpt.cpt_mobileapp.model.FaultModel;

import org.json.JSONArray;
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
            Log.i("strings[0]", strings[0]);
            return selectUsersFaults(strings[0]);
        } else {
            Log.i("strings[0]", "null");
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
            conn.disconnect();
            if (jsonObj != null) {

                List<FaultModel> faultsJSON = FaultModel.fromJSONArray(jsonObj);
                
                Log.i("faultJSON", faultsJSON.toString());
                if (!faultsJSON.isEmpty()) {
                    Log.i("list", faultsJSON.toString());
                    return faultsJSON;
                } else {
                    Log.i("list", "empty");
                    return new ArrayList<FaultModel>();
                }
            } else {
                return new ArrayList<FaultModel>();
            }
        } catch (Exception e) {
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

                Log.i(URL, url.toString());
                conn.setConnectTimeout(TIMEOUT);
                Log.i(URL, url.toString());
                conn.setDoInput(true);
                Log.i(URL, url.toString());
                conn.connect();
                Log.i(URL, url.toString());
                bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                Log.i(URL, url.toString());
                String jsonStr = bufferedReader.readLine().toString();
                Log.i(URL, url.toString());
                JSONObject jsonObj = new JSONObject(jsonStr);

                Log.i(URL, url.toString());
                Log.i(JSON, jsonObj.toString());

                Log.i(URL, url.toString());
                bufferedReader.close();
                conn.disconnect();
                if (jsonObj != null) {

                    List<FaultModel> faultsJSON = FaultModel.fromJSONArray(jsonObj);

                    Log.i("faultJSON", faultsJSON.toString());
                    if (!faultsJSON.isEmpty()) {
                        Log.i("list", faultsJSON.toString());
                        return faultsJSON;
                    } else {
                        Log.i("list", "empty");
                        return new ArrayList<FaultModel>();
                    }
                } else {
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
