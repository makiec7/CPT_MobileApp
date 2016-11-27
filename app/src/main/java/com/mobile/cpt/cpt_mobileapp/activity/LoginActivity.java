package com.mobile.cpt.cpt_mobileapp.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import static com.mobile.cpt.cpt_mobileapp.Constant.*;

import com.mobile.cpt.cpt_mobileapp.R;
import com.mobile.cpt.cpt_mobileapp.async.LoginAsync;
import com.mobile.cpt.cpt_mobileapp.model.LoginModel;

import java.util.concurrent.ExecutionException;

public class LoginActivity extends AppCompatActivity {
    Button loginButton;
    EditText loginEdit;
    EditText passwordEdit;

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
                            ProgressDialog progress = new ProgressDialog(getApplicationContext());
                            progress.setTitle("Loading");
                            progress.setMessage("Wait while loading...");
                            progress.setCancelable(true); // disable dismiss by tapping outside of the dialog
                            progress.show();
                            Toast.makeText(getApplicationContext(), INSERT_LOGIN_DATA,
                                    Toast.LENGTH_LONG).show();
                            progress.dismiss();
                        }
                        try {
                            LoginModel lr = new LoginAsync(getParent()).execute(login,
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
