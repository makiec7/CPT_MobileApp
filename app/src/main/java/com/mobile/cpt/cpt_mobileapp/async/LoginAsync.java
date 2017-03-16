package com.mobile.cpt.cpt_mobileapp.async;

import android.os.AsyncTask;

import com.mobile.cpt.cpt_mobileapp.JSONFromLink;
import com.mobile.cpt.cpt_mobileapp.model.LoginModel;

import org.json.JSONObject;

import java.net.URLEncoder;

import static com.mobile.cpt.cpt_mobileapp.Constant.*;

public class LoginAsync extends AsyncTask<String, String, LoginModel> {

    @Override
    protected LoginModel doInBackground(String... arg0) {
        String indexNo = arg0[0];
        String password = arg0[1];
        String link;
        try {
            link = HTTP_LOGIN;
            //link = "http://212.191.92.101:6009/login_mobile/";
            link += ASK + INDEX_NO_EQ + URLEncoder.encode(indexNo, UTF_8);
            link += AND + PASSWORD_EQ + URLEncoder.encode(password, UTF_8);
            JSONObject jsonObj = new JSONFromLink(link).getFromLink();
            if (jsonObj != null) {
                String login_status = (String) jsonObj.get(LOGIN_STATUS);
                if (login_status.equals(TRUE)) {
                    return new LoginModel(true, (String) jsonObj.get(USER));
                }
            }
        } catch (Exception ignored) {}
        return new LoginModel(false, NULL_STRING);
    }
}
