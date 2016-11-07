package com.mobile.cpt.cpt_mobileapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

/**
 * Created by Makiec on 03.11.2016.
 */
public class MainActivity extends Activity implements View.OnClickListener{

    ImageButton btnAdd;
    ImageButton btnEdit;
    ImageButton btnMyFaults;
    ImageButton btnShowAll;
    ImageButton btnEmergency;
    ImageButton btnAbout;
    ImageButton btnContact;
    ImageButton btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case Constant.BTN_REPORT_FAULT:
                setContentView(Constant.ADD_LAYOUT);
                break;
            case Constant.BTN_EDIT_FAULT:
                setContentView(Constant.EDIT_LAYOUT);
                break;
            case Constant.BTN_SHOW_FAULTS:
                setContentView(Constant.USER_PROBLEMS_LAYOUT);
                break;
            case Constant.BTN_LOCAL_FAULTS:
                setContentView(Constant.ALL_PROBLEMS_LAYOUT);
                break;
            case Constant.BTN_ALARMS:
                openInfoActivity(Constant.EMERGENCY_LAYOUT);
                break;
            case Constant.BTN_ABOUT:
                openInfoActivity(Constant.ABOUT_LAYOUT);
                break;
            case Constant.BTN_CONTACT:
                openInfoActivity(Constant.CONTACT_LAYOUT);
                break;
            case Constant.BTN_LOGOUT:
                break;
        }
    }

    private void openInfoActivity(int layoutId) {
        Intent InfoIntent = new Intent(getApplicationContext(), InfoActivity.class);
        InfoIntent.putExtra(Constant.LAYOUT, layoutId);
        startActivity(InfoIntent);
    }

    private void initialize(){
        btnAdd = (ImageButton) findViewById(Constant.BTN_REPORT_FAULT);
        btnEdit = (ImageButton) findViewById(Constant.BTN_EDIT_FAULT);
        btnMyFaults = (ImageButton) findViewById(Constant.BTN_SHOW_FAULTS);
        btnShowAll = (ImageButton) findViewById(Constant.BTN_LOCAL_FAULTS);
        btnEmergency = (ImageButton) findViewById(Constant.BTN_ALARMS);
        btnAbout = (ImageButton) findViewById(Constant.BTN_ABOUT);
        btnContact = (ImageButton) findViewById(Constant.BTN_CONTACT);
        btnLogout = (ImageButton) findViewById(Constant.BTN_LOGOUT);
        btnAdd.setOnClickListener(this);
        btnEdit.setOnClickListener(this);
        btnMyFaults.setOnClickListener(this);
        btnShowAll.setOnClickListener(this);
        btnEmergency.setOnClickListener(this);
        btnAbout.setOnClickListener(this);
        btnContact.setOnClickListener(this);
        btnLogout.setOnClickListener(this);
    }
}
