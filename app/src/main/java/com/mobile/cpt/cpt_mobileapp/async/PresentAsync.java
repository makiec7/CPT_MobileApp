package com.mobile.cpt.cpt_mobileapp.async;

import android.os.AsyncTask;

import com.mobile.cpt.cpt_mobileapp.JSONFromLink;
import com.mobile.cpt.cpt_mobileapp.model.FaultModel;

import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import static com.mobile.cpt.cpt_mobileapp.Constant.*;

public class PresentAsync extends AsyncTask <String, String, List<FaultModel>> {

    @Override
    protected List<FaultModel> doInBackground(String... strings) {
        try {
            String link;
            if (strings.length == 0){
                link = HTTP_PRESENT_ALL_FAULTS;
            } else {
                String user = strings[0];
                link = HTTP_PRESENT_USER_FAULTS;
                link += ASK + INDEX_NO_EQ + URLEncoder.encode(user, UTF_8);
            }
            JSONObject jsonObj = new JSONFromLink(link).getFromLink();
            if (jsonObj != null) {
                List<FaultModel> faultsJSON = FaultModel.fromJSONArray(jsonObj);
                if (!faultsJSON.isEmpty()) {
                    return faultsJSON;
                }
            }
        } catch (Exception ignored) {}
        return new ArrayList<>();
    }
}
