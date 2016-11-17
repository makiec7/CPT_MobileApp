package com.mobile.cpt.cpt_mobileapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mobile.cpt.cpt_mobileapp.R;
import com.mobile.cpt.cpt_mobileapp.async.LoginAsync;
import com.mobile.cpt.cpt_mobileapp.model.LoginModel;

import java.io.Serializable;
import java.util.concurrent.ExecutionException;

public class LoginActivity extends AppCompatActivity {

    Button loginButton;
    EditText loginEdit;
    EditText passwordEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginButton = (Button) findViewById(R.id.btn_login);
        loginEdit = (EditText) findViewById(R.id.et_login);
        passwordEdit = (EditText) findViewById(R.id.et_password);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String login = loginEdit.getText().toString();
                String password = passwordEdit.getText().toString();

                if(login.equals("") || password.equals("")){
                    Toast.makeText(getApplicationContext(), "Wpisz dane logowania",
                            Toast.LENGTH_LONG).show();
                    return;
                }
                try {
                   LoginModel lr = new LoginAsync(getParent()).execute(login, password).get();
                   if (lr!=null) {
                        if (lr.isLogged){
                            Toast.makeText(getApplicationContext(), "Zalogowany jako: " + lr.index_no,
                                    Toast.LENGTH_LONG).show();
                            Intent toMain = new Intent(getApplicationContext(),
                                    MainActivity.class);
                            toMain.putExtra("userData", (Serializable) lr);
                            startActivityForResult(toMain, 10);
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Złe dane logowania",
                                Toast.LENGTH_LONG).show();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

            }
        });



    }




    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */

    /*
    public native String stringFromJNI();

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }
    */
}
