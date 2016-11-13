package com.mobile.cpt.cpt_mobileapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
                if (loginEdit.getText().toString().equals("180122")
                        && passwordEdit.getText().toString().equals("180122")) {
                    Toast.makeText(getApplicationContext(), "Dobre dane logowania",
                            Toast.LENGTH_LONG);
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                } else {
                    Toast.makeText(getApplicationContext(), "Złe dane logowania",
                            Toast.LENGTH_LONG);
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
