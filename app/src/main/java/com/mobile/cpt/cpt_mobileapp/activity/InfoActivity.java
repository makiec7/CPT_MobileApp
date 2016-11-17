package com.mobile.cpt.cpt_mobileapp.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;
import static com.mobile.cpt.cpt_mobileapp.Constant.*;

import com.mobile.cpt.cpt_mobileapp.Constant;

public class InfoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle b = getIntent().getExtras();
        if (b != null)
            setContentView((int) b.get(LAYOUT));
        else
            Toast.makeText(getApplicationContext(), Constant.DATA_ERROR,
                    Toast.LENGTH_LONG);
    }
}
