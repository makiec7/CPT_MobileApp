package com.mobile.cpt.cpt_mobileapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.telephony.TelephonyManager;
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

    ImageButton btnAdd;
    ImageButton btnEdit;
    ImageButton btnMyFaults;
    ImageButton btnShowAll;
    ImageButton btnEmergency;
    ImageButton btnAbout;
    ImageButton btnContact;
    ImageButton btnLogout;
    LoginModel user;
    Intent forReport;
    Intent forUserPresent;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
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

        TelephonyManager tm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        Log.i("phone number", getNumber());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case BTN_REPORT_FAULT:
                startActivityForResult(forReport, REPORT_REQUEST_CODE);
                break;
            case BTN_EDIT_FAULT:
                startActivityForResult(new Intent(getApplicationContext(), ReportActivity.class),
                        EDIT_REQUEST_CODE);
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

                try {
                    if (add((FaultModel) data.getExtras().get(FAULT))) {
                        Toast.makeText(getApplicationContext(), "Zgłoszenie dodane", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Błąd dodawania", Toast.LENGTH_LONG).show();
                    }
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(getApplicationContext(), DATA_ERROR, Toast.LENGTH_LONG).show();
            }
        } else if (requestCode == EDIT_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                edit();
            } else {
                Toast.makeText(getApplicationContext(), DATA_ERROR, Toast.LENGTH_LONG).show();
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

    private boolean edit() {
        boolean result = false;
        return result;
    }

    private String getNumber() {
        String s1 = null;
        String main_data[] = {"data1", "is_primary", "data3", "data2", "data1", "is_primary", "photo_uri", "mimetype"};

        Object object = getContentResolver().query(Uri.withAppendedPath(android.provider.ContactsContract.Profile.CONTENT_URI, "data"),
                main_data, "mimetype=?",
                new String[]{"vnd.android.cursor.item/phone_v2"},
                "is_primary DESC");
        if(object!=null)
    
        {
            do {
                if (!((Cursor) (object)).moveToNext())
                    break;
                s1 = ((Cursor) (object)).getString(4);
            } while (true);
            ((Cursor) (object)).close();
        }
        return s1;
    }
}
