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
import android.widget.Toast;

import com.mobile.cpt.cpt_mobileapp.async.LoginAsync;
import com.mobile.cpt.cpt_mobileapp.model.LoginModel;

import java.util.concurrent.ExecutionException;

import static com.mobile.cpt.cpt_mobileapp.Constant.BTN_TO_LOG;
import static com.mobile.cpt.cpt_mobileapp.Constant.ET_LOGIN;
import static com.mobile.cpt.cpt_mobileapp.Constant.ET_PASSWORD;
import static com.mobile.cpt.cpt_mobileapp.Constant.INSERT_LOGIN_DATA;
import static com.mobile.cpt.cpt_mobileapp.Constant.LOGGED_AS;
import static com.mobile.cpt.cpt_mobileapp.Constant.LOGIN_LAYOUT;
import static com.mobile.cpt.cpt_mobileapp.Constant.MAIN_ACTIVITY_REQ_CODE;
import static com.mobile.cpt.cpt_mobileapp.Constant.NOT_CONNECTED_TO_INTERNET;
import static com.mobile.cpt.cpt_mobileapp.Constant.NULL_STRING;
import static com.mobile.cpt.cpt_mobileapp.Constant.SERVER_OUT_OF_CONNECTION;
import static com.mobile.cpt.cpt_mobileapp.Constant.USER_DATA;
import static com.mobile.cpt.cpt_mobileapp.Constant.WRONG_LOGIN_DATA;

public class LoginActivity extends AppCompatActivity {
    private Button loginButton;
    private EditText loginEdit;
    private EditText passwordEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(LOGIN_LAYOUT);
        loginButton = (Button) findViewById(BTN_TO_LOG);
        loginEdit = (EditText) findViewById(ET_LOGIN);
        passwordEdit = (EditText) findViewById(ET_PASSWORD);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    if (checkConnection()) {
                        String login = loginEdit.getText().toString();
                        String password = passwordEdit.getText().toString();
                        if (login.equals(NULL_STRING) || password.equals(NULL_STRING)) {
                            Toast.makeText(getApplicationContext(), INSERT_LOGIN_DATA,
                                    Toast.LENGTH_LONG).show();
                        }
                        try {
                            LoginModel lr = new LoginAsync().execute(login,
                                    password).get();
                            if (lr != null) {
                                if (lr.isLogged()) {
                                    Toast.makeText(getApplicationContext(),
                                            LOGGED_AS + lr.getIndex_no(),
                                            Toast.LENGTH_LONG).show();
                                    Intent toMain = new Intent(getApplicationContext(),
                                            MainActivity.class);
                                    toMain.putExtra(USER_DATA, lr);
                                    startActivityForResult(toMain, MAIN_ACTIVITY_REQ_CODE);
                                } else {
                                    Toast.makeText(getApplicationContext(), WRONG_LOGIN_DATA,
                                            Toast.LENGTH_LONG).show();
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), SERVER_OUT_OF_CONNECTION,
                                        Toast.LENGTH_LONG).show();
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), NOT_CONNECTED_TO_INTERNET,
                                Toast.LENGTH_LONG).show();
                    }
            }
        });
    }

    public boolean checkConnection(){
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}
