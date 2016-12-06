package com.mobile.cpt.cpt_mobileapp.async;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import com.mobile.cpt.cpt_mobileapp.JSONFromLink;
import com.mobile.cpt.cpt_mobileapp.model.LoginModel;

import org.json.JSONObject;

import java.net.URLEncoder;

import static com.mobile.cpt.cpt_mobileapp.Constant.AND;
import static com.mobile.cpt.cpt_mobileapp.Constant.ASK;
import static com.mobile.cpt.cpt_mobileapp.Constant.HTTP_LOGIN;
import static com.mobile.cpt.cpt_mobileapp.Constant.INDEX_NO_EQ;
import static com.mobile.cpt.cpt_mobileapp.Constant.IS_LOGGED;
import static com.mobile.cpt.cpt_mobileapp.Constant.LOGIN_STATUS;
import static com.mobile.cpt.cpt_mobileapp.Constant.NULL_STRING;
import static com.mobile.cpt.cpt_mobileapp.Constant.PASSWORD_EQ;
import static com.mobile.cpt.cpt_mobileapp.Constant.TRUE;
import static com.mobile.cpt.cpt_mobileapp.Constant.USER;
import static com.mobile.cpt.cpt_mobileapp.Constant.UTF_8;

public class LoginAsync extends AsyncTask<String, String, LoginModel> {

    private Activity activity;

    public LoginAsync(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected LoginModel doInBackground(String... arg0) {
        String indexNo = arg0[0];
        String password = arg0[1];
        String link;
        try {
            link = HTTP_LOGIN;
            link += ASK + INDEX_NO_EQ + URLEncoder.encode(indexNo, UTF_8);
            link += AND + PASSWORD_EQ + URLEncoder.encode(password, UTF_8);
            JSONObject jsonObj = new JSONFromLink(link).getFromLink();
            if (jsonObj != null) {
                String login_status = (String) jsonObj.get(LOGIN_STATUS);
                if (login_status.equals(TRUE)) {
                    Log.i(IS_LOGGED, login_status);
                    return new LoginModel(true, (String) jsonObj.get(USER));
                } else {
                    Log.i(IS_LOGGED, login_status);
                    return new LoginModel(false, NULL_STRING);
                }
            } else {
                return new LoginModel(false, NULL_STRING);
            }
        } catch (Exception e) {
            return new LoginModel(false, NULL_STRING);
        }
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected void onPostExecute(LoginModel loginModel) {
        super.onPostExecute(loginModel);
    }
}
