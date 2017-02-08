package com.mobile.cpt.cpt_mobileapp.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import static com.mobile.cpt.cpt_mobileapp.Constant.*;

public class InfoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle b = getIntent().getExtras();
        setContentView(ACTIVITY_INFO);
        if (b != null) {
            setHeader("Dodatkowe informacje");
            TextView info = (TextView) findViewById(INFO_STRING);
            info.setText(b.getInt(STRING));
        } else
            showToast(getApplicationContext(), DATA_ERROR);
    }

    public void setHeader(String headerStr) {
        TextView header = (TextView) findViewById(HEADER);
        header.setText(headerStr);
    }
}
