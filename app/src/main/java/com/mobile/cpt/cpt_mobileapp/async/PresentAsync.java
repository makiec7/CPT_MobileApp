package com.mobile.cpt.cpt_mobileapp.async;

import android.os.AsyncTask;
import android.util.Log;

import com.mobile.cpt.cpt_mobileapp.model.FaultModel;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import static com.mobile.cpt.cpt_mobileapp.Constant.*;

public class PresentAsync extends AsyncTask <String, String, List<FaultModel>> {

    @Override
    protected List<FaultModel> doInBackground(String... strings) {
        try {
            String link;
            if (strings.length > 0){
                link = HTTP_PRESENT_ALL_FAULTS;
            } else {
                String user = strings[0];
                link = HTTP_PRESENT_USER_FAULTS;
                link += ASK + INDEX_NO_EQ + URLEncoder.encode(user, UTF_8);

            }
            HttpURLConnection conn;
            BufferedReader bufferedReader;
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
                if (!faultsJSON.isEmpty()) {
                    return faultsJSON;
                } else {
                    return new ArrayList<>();
                }
            } else {
                return new ArrayList<>();
            }
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}
