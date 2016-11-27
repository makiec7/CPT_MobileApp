package com.mobile.cpt.cpt_mobileapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import static com.mobile.cpt.cpt_mobileapp.Constant.*;

import com.mobile.cpt.cpt_mobileapp.async.ReportAsync;
import com.mobile.cpt.cpt_mobileapp.model.FaultModel;
import com.mobile.cpt.cpt_mobileapp.model.LoginModel;

import java.util.concurrent.ExecutionException;

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
    private Intent forReport;
    private Intent forUserPresent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(MAIN_LAYOUT);
        forUserPresent = new Intent(getApplicationContext(), PresentActivity.class);
        forReport = new Intent(getApplicationContext(), ReportActivity.class);
        Intent userIntent = getIntent();
        user = (LoginModel) userIntent.getExtras().get(USER_DATA);
        forReport.putExtra(USER_DATA, user);
        initialize();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case BTN_REPORT_FAULT:
                startActivityForResult(forReport, REPORT_REQUEST_CODE);
                break;
            case BTN_EDIT_FAULT:
                Intent editIntent = new Intent(getApplicationContext(), EditActivity.class);
                editIntent.putExtra(USER_DATA, user);
                startActivityForResult(editIntent, EDIT_REQUEST_CODE);
                break;
            case BTN_SHOW_FAULTS:
                forUserPresent.putExtra(USER_DATA, user);
                startActivity(forUserPresent);
                break;
            case BTN_LOCAL_FAULTS:
                forUserPresent.putExtra(USER_DATA, new LoginModel(false, ""));
                startActivity(forUserPresent);
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

    private void openInfoActivity(int layoutId) {
        Intent InfoIntent = new Intent(getApplicationContext(), InfoActivity.class);
        InfoIntent.putExtra(LAYOUT, layoutId);
        startActivity(InfoIntent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REPORT_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {

                try {
                    if (add((FaultModel) data.getExtras().get(FAULT))) {
                        Toast.makeText(getApplicationContext(), REPORT_SUCCESS, Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), REPORT_ERROR, Toast.LENGTH_LONG).show();
                    }
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void initialize() {
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

    private boolean add(FaultModel faultModel) throws ExecutionException, InterruptedException {
        Log.i("fault in main", faultModel.toString());
        return new ReportAsync().execute(faultModel).get();
    }
}
