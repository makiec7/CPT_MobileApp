package com.mobile.cpt.cpt_mobileapp.activity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.mobile.cpt.cpt_mobileapp.async.LoginAsync;
import com.mobile.cpt.cpt_mobileapp.model.LoginModel;

import java.util.concurrent.ExecutionException;

import static com.mobile.cpt.cpt_mobileapp.Constant.*;

public class LoginActivity extends AppCompatActivity {

    private EditText loginEdit;
    private EditText passwordEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(LOGIN_LAYOUT);
        Button loginButton = (Button) findViewById(BTN_TO_LOG);
        loginEdit = (EditText) findViewById(ET_LOGIN);
        passwordEdit = (EditText) findViewById(ET_PASSWORD);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibrate(ONE_MILLISECOND, getApplicationContext());
                    if (checkConnection()) {
                        String login = loginEdit.getText().toString();
                        String password = passwordEdit.getText().toString();
                        if (login.equals(NULL_STRING) || password.equals(NULL_STRING)) {
                            showToast(getApplicationContext(), INSERT_LOGIN_DATA);
                        }
                        try {
                            LoginModel user = login(login, password);
                            if (user != null) {
                                if (user.isLogged()) {
                                    showToast(getApplicationContext(), LOGGED_AS +
                                            user.getIndex_no());
                                    Intent toMain = new Intent(getApplicationContext(),
                                            MainActivity.class);
                                    toMain.putExtra(USER_DATA, user);
                                    startActivityForResult(toMain, MAIN_ACTIVITY_REQ_CODE);
                                } else {
                                    showToast(getApplicationContext(), WRONG_LOGIN_DATA);
                                }
                            } else {
                                showToast(getApplicationContext(), SERVER_OUT_OF_CONNECTION);
                            }
                        } catch (InterruptedException | ExecutionException e) {
                            e.printStackTrace();
                        }
                    } else {
                        showToast(getApplicationContext(), NOT_CONNECTED_TO_INTERNET);
                    }
            }
        });
    }



    private LoginModel login(String login, String password) throws InterruptedException,
            ExecutionException {
        return new LoginAsync().execute(login, password).get();
    }

    public boolean checkConnection(){
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}
