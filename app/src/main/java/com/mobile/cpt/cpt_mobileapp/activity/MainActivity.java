package com.mobile.cpt.cpt_mobileapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import static com.mobile.cpt.cpt_mobileapp.Constant.*;
import com.mobile.cpt.cpt_mobileapp.Constant;
import com.mobile.cpt.cpt_mobileapp.R;

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
        setContentView(MAIN_LAYOUT);
        initialize();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case BTN_REPORT_FAULT:
                startActivityForResult(new Intent(getApplicationContext(), ReportActivity.class),
                        REPORT_REQUEST_CODE);
                break;
            case BTN_EDIT_FAULT:
                startActivityForResult(new Intent(getApplicationContext(), ReportActivity.class),
                        EDIT_REQUEST_CODE);
                break;
            case BTN_SHOW_FAULTS:
                openPresentActivity(BTN_SHOW_FAULTS);
                break;
            case BTN_LOCAL_FAULTS:
                openPresentActivity(BTN_LOCAL_FAULTS);
                break;
            case BTN_ALARMS:
                openInfoActivity(EMERGENCY_LAYOUT);
                break;
            case BTN_ABOUT:
                openInfoActivity(ABOUT_LAYOUT);
                break;
            case BTN_CONTACT:
                openInfoActivity(CONTACT_LAYOUT);
                break;
            case BTN_LOGOUT:
                finish();
                break;
        }
    }

    private void openPresentActivity(int layoutId) {
        Intent InfoIntent = new Intent(getApplicationContext(), PresentActivity.class);
        InfoIntent.putExtra(LAYOUT, layoutId);
        startActivity(InfoIntent);
    }

    private void openInfoActivity(int layoutId) {
        Intent InfoIntent = new Intent(getApplicationContext(), InfoActivity.class);
        InfoIntent.putExtra(LAYOUT, layoutId);
        startActivity(InfoIntent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REPORT_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                add();
            } else {
                Toast.makeText(getApplicationContext(), DATA_ERROR, Toast.LENGTH_LONG);
            }
        } else if(requestCode == EDIT_REQUEST_CODE){
            if (resultCode == RESULT_OK) {
                edit();
            } else {
                Toast.makeText(getApplicationContext(), DATA_ERROR, Toast.LENGTH_LONG);
            }
        }
    }

    private void initialize(){
        btnAdd = (ImageButton) findViewById(BTN_REPORT_FAULT);
        btnEdit = (ImageButton) findViewById(BTN_EDIT_FAULT);
        btnMyFaults = (ImageButton) findViewById(BTN_SHOW_FAULTS);
        btnShowAll = (ImageButton) findViewById(BTN_LOCAL_FAULTS);
        btnEmergency = (ImageButton) findViewById(BTN_ALARMS);
        btnAbout = (ImageButton) findViewById(BTN_ABOUT);
        btnContact = (ImageButton) findViewById(BTN_CONTACT);
        btnLogout = (ImageButton) findViewById(BTN_LOGOUT);
        btnAdd.setOnClickListener(this);
        btnEdit.setOnClickListener(this);
        btnMyFaults.setOnClickListener(this);
        btnShowAll.setOnClickListener(this);
        btnEmergency.setOnClickListener(this);
        btnAbout.setOnClickListener(this);
        btnContact.setOnClickListener(this);
        btnLogout.setOnClickListener(this);
    }

    private boolean add(){
        boolean result = false;
        return result;
    }

    private boolean edit() {
        boolean result = false;
        return result;
    }
}
