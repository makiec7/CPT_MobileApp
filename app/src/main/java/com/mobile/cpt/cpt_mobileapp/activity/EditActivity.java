package com.mobile.cpt.cpt_mobileapp.activity;

import android.app.Activity;
import android.os.Bundle;

import com.mobile.cpt.cpt_mobileapp.Constant;

public class EditActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(Constant.EDIT_LAYOUT);
    }
}
