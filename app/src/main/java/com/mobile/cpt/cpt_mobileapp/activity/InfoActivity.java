package com.mobile.cpt.cpt_mobileapp.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.mobile.cpt.cpt_mobileapp.Constant;
import com.mobile.cpt.cpt_mobileapp.R;

import static com.mobile.cpt.cpt_mobileapp.Constant.*;

public class InfoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle b = getIntent().getExtras();
        setContentView(ACTIVITY_INFO);
        if (b != null) {
            TextView info = (TextView) findViewById(INFO_STRING);
            info.setText(b.getInt(STRING));
        } else
            Toast.makeText(getApplicationContext(), Constant.DATA_ERROR,
                    Toast.LENGTH_LONG);
    }
}
