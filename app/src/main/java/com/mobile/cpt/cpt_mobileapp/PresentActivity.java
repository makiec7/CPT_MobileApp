package com.mobile.cpt.cpt_mobileapp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by Makiec on 07.11.2016.
 */

public class PresentActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle b = getIntent().getExtras();
        if (b != null)
            setContentView((int) b.get(Constant.LAYOUT));
        else
            Toast.makeText(getApplicationContext(), Constant.DATA_ERROR,
                    Toast.LENGTH_LONG);
    }
}
