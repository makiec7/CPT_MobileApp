package com.mobile.cpt.cpt_mobileapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.mobile.cpt.cpt_mobileapp.model.LoginModel;

import static com.mobile.cpt.cpt_mobileapp.Constant.*;

public class MainActivity extends Activity implements View.OnClickListener {

    private ImageButton btnAdd;
    private ImageButton btnEdit;
    private ImageButton btnMyFaults;
    private ImageButton btnShowAll;
    private ImageButton btnEmergency;
    private ImageButton btnAbout;
    private ImageButton btnContact;
    private ImageButton btnLogout;
    private LoginModel user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(MAIN_LAYOUT);
        getUserInfo();
        init();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case BTN_REPORT_FAULT:
                vibrate(ONE_MILLISECOND, getApplicationContext());
                openReportActivity();
                break;
            case BTN_EDIT_FAULT:
                vibrate(ONE_MILLISECOND, getApplicationContext());
                openEditActivity();
                break;
            case BTN_SHOW_FAULTS:
                vibrate(ONE_MILLISECOND, getApplicationContext());
                openPresentActivity(user);
                break;
            case BTN_LOCAL_FAULTS:
                vibrate(ONE_MILLISECOND, getApplicationContext());
                openPresentActivity(new LoginModel(false, NULL_STRING));
                break;
            case BTN_ALARMS:
                vibrate(ONE_MILLISECOND, getApplicationContext());
                openInfoActivity(EMERGENCY);
                break;
            case BTN_ABOUT:
                vibrate(ONE_MILLISECOND, getApplicationContext());
                openInfoActivity(ABOUT);
                break;
            case BTN_CONTACT:
                vibrate(ONE_MILLISECOND, getApplicationContext());
                openInfoActivity(CONTACT);
                break;
            case BTN_LOGOUT:
                vibrate(ONE_MILLISECOND, getApplicationContext());
                finish();
                break;
        }
    }

    private void openPresentActivity(LoginModel user) {
        Intent forUserPresent = new Intent(getApplicationContext(), PresentActivity.class);
        forUserPresent.putExtra(USER_DATA, user);
        startActivity(forUserPresent);
    }

    private void openEditActivity() {
        Intent editIntent = new Intent(getApplicationContext(), EditActivity.class);
        editIntent.putExtra(USER_DATA, user);
        startActivityForResult(editIntent, EDIT_REQUEST_CODE);
    }

    private void openReportActivity() {
        Intent forReport = new Intent(getApplicationContext(), ReportActivity.class);
        forReport.putExtra(USER_DATA, user);
        startActivityForResult(forReport, REPORT_REQUEST_CODE);
    }

    private void openInfoActivity(int stringId) {
        Intent infoIntent = new Intent(getApplicationContext(), InfoActivity.class);
        infoIntent.putExtra(STRING, stringId);
        startActivity(infoIntent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REPORT_REQUEST_CODE)
            if (resultCode == RESULT_OK)
                    if ((Boolean) data.getExtras().get(STATUS)) {
                        showToast(getApplicationContext(), REPORT_SUCCESS);
                    }
                    else
                        showToast(getApplicationContext(), REPORT_ERROR);
    }

    private void init() {
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

    private void getUserInfo() {
        Intent userIntent = getIntent();
        user = (LoginModel) userIntent.getExtras().get(USER_DATA);
    }

}
