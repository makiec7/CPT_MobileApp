package com.mobile.cpt.cpt_mobileapp.async;

import android.os.AsyncTask;
import android.util.Log;

import com.mobile.cpt.cpt_mobileapp.JSONFromLink;
import com.mobile.cpt.cpt_mobileapp.model.FaultModel;

import org.json.JSONObject;

import java.net.URLEncoder;

import static com.mobile.cpt.cpt_mobileapp.Constant.*;


public class DeleteAsync extends AsyncTask<FaultModel, String, Boolean> {

    @Override
    protected Boolean doInBackground(FaultModel... faultModels) {
        FaultModel fault = faultModels[0];
        String link;
        try {
            link = HTTP_DELETE;
            link += ASK + ID_EQ + URLEncoder.encode(Integer.toString(fault.getId()), UTF_8);
            JSONObject jsonObj = new JSONFromLink(link).getFromLink();
            Log.i(JSON, jsonObj.toString());
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
