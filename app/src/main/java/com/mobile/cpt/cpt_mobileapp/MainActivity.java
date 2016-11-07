package com.mobile.cpt.cpt_mobileapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

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
                startActivityForResult(new Intent(getApplicationContext(), ReportActivity.class),
                        Constant.REPORT_REQUEST_CODE);
                break;
            case Constant.BTN_EDIT_FAULT:
                startActivityForResult(new Intent(getApplicationContext(), ReportActivity.class),
                        Constant.EDIT_REQUEST_CODE);
                break;
            case Constant.BTN_SHOW_FAULTS:
                openPresentActivity(Constant.BTN_SHOW_FAULTS);
                break;
            case Constant.BTN_LOCAL_FAULTS:
                openPresentActivity(Constant.BTN_LOCAL_FAULTS);
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

    private void openPresentActivity(int layoutId) {
        Intent InfoIntent = new Intent(getApplicationContext(), PresentActivity.class);
        InfoIntent.putExtra(Constant.LAYOUT, layoutId);
        startActivity(InfoIntent);
    }

    private void openInfoActivity(int layoutId) {
        Intent InfoIntent = new Intent(getApplicationContext(), InfoActivity.class);
        InfoIntent.putExtra(Constant.LAYOUT, layoutId);
        startActivity(InfoIntent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constant.REPORT_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                // adding to database
            } else {
                Toast.makeText(getApplicationContext(), Constant.DATA_ERROR,
                        Toast.LENGTH_LONG);
            }
        } else if(requestCode == Constant.EDIT_REQUEST_CODE){
            if (resultCode == RESULT_OK) {
                // editing database
            } else {
                Toast.makeText(getApplicationContext(), Constant.DATA_ERROR,
                        Toast.LENGTH_LONG);
            }
        }
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
