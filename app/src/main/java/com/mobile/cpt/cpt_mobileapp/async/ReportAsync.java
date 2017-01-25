package com.mobile.cpt.cpt_mobileapp.async;

import android.os.AsyncTask;
import android.util.Log;

import com.mobile.cpt.cpt_mobileapp.JSONFromLink;
import com.mobile.cpt.cpt_mobileapp.model.FaultModel;

import org.json.JSONObject;

import java.net.URLEncoder;

import static com.mobile.cpt.cpt_mobileapp.Constant.*;

public class ReportAsync extends AsyncTask<FaultModel, String, Boolean>{

    @Override
    protected Boolean doInBackground(FaultModel... faultModels) {
        FaultModel fault = faultModels[0];
        String link;
        try {
            link = HTTP_REPORT;
            link += ASK + ISSUER_EQ + URLEncoder.encode(Integer.toString(fault.getIssuer()),
                    UTF_8);
            link += AND + TOPIC_EQ + URLEncoder.encode(fault.getTopic(), UTF_8);
            link += AND + OBJECT_NUMBER_EQ +
                    URLEncoder.encode(String.valueOf(fault.getObject_number()), UTF_8);
            link += AND + PHONE_NUMBER_EQ + URLEncoder.encode(fault.getPhone_number(), UTF_8);
            link += AND + DATE_TIME_EQ + URLEncoder.encode(fault.getDate_time(), UTF_8);
            link += AND + DESCRIPTION_EQ + URLEncoder.encode(fault.getDescription(), UTF_8);
            JSONObject jsonObj = new JSONFromLink(link).getFromLink();
            if (jsonObj != null) {
                String query_status = (String) jsonObj.get(QUERY_STATUS);
                if (query_status.equals(TRUE)) {
                    Log.i(QUERY_STATUS, query_status);
                    return true;
                }
            }
        } catch (Exception ignored) {}
        return false;
    }
}

