package com.mobile.cpt.cpt_mobileapp.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.mobile.cpt.cpt_mobileapp.Constant;
import com.mobile.cpt.cpt_mobileapp.R;
import com.mobile.cpt.cpt_mobileapp.async.ReportAsync;
import com.mobile.cpt.cpt_mobileapp.model.FaultModel;
import com.mobile.cpt.cpt_mobileapp.model.LoginModel;

import java.util.concurrent.ExecutionException;

import static com.mobile.cpt.cpt_mobileapp.Constant.*;

public class ReportActivity extends Activity {

    public static final int NINE_DIGIT_NUBER = 9;
    public static final int ELEVEN_DIGIT_NUMBER = 11;
    private Boolean isAdded;
    private TextView tv_issuer_id;
    private EditText et_topic;
    private EditText et_phone_number;
    private EditText et_obj_no;
    private EditText et_description;
    private String issuer;
    private String topic;
    private String phone_number;
    private String descr;
    private String obj_no;
    private Intent fromMain;
    private LoginModel user;
    private Button btn_add;
    private Button btn_scan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent reportTypeIntent = new Intent(getApplicationContext(), ReportTypeActivity.class);
        startActivityForResult(reportTypeIntent, REPORT_TYPE_REQUEST_CODE);
    }

    private void addingProcess(){
        init();
        if (btn_scan != null)
            btn_scan.setOnClickListener(new View.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.M)
                    @Override
                    public void onClick(View view) {
                        vibrate(ONE_MILLISECOND, getApplicationContext());
                        Intent cameraActivity = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        if (ActivityCompat.checkSelfPermission(getApplicationContext(),
                                Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                            requestPermissions(new String[]{Manifest.permission.CAMERA}, 1010);
                        } else {
                            if (cameraActivity.resolveActivity(getPackageManager()) != null) {
                                new IntentIntegrator(ReportActivity.this).initiateScan();
                            }
                        }
                    }
            });
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibrate(ONE_MILLISECOND, getApplicationContext());
                getDataFromUser();
                if (areFieldsFull()) {
                    if (!isPhoneNumberValid())
                        showToast(getApplicationContext(), BAD_PHONE_NUMBER);
                    else{
                        FaultModel fault = new FaultModel(Integer.parseInt(issuer), phone_number, topic,
                                descr, Integer.parseInt(obj_no));
                        try {
                            isAdded = add(fault);
                        } catch (ExecutionException e) {
                            setError(e);
                        } catch (InterruptedException e) {
                            setError(e);
                        }
                        setResultAndFinish();
                    }
                } else {
                    showToast(getApplicationContext(), FILL_ALL_FIELDS);
                }
            }
        });
    }

    private boolean areFieldsFull() {
        return !topic.equals(NULL_STRING) && !obj_no.equals(NULL_STRING) && !descr.equals(NULL_STRING);
    }

    private void setResultAndFinish() {
        fromMain.putExtra(STATUS, isAdded);
        setResult(RESULT_OK, fromMain);
        finish();
    }

    private boolean isPhoneNumberValid() {
        return phone_number.length() == NINE_DIGIT_NUBER ||
                (phone_number.length() == ELEVEN_DIGIT_NUMBER &&
                phone_number.startsWith(PLUS))
                || phone_number.equals(NULL_STRING);
    }

    private void setError(Exception e) {
        isAdded = false;
        e.printStackTrace();
    }

    private void getDataFromUser(){
        obj_no = et_obj_no.getText().toString();
        issuer = tv_issuer_id.getText().toString();
        topic = et_topic.getText().toString();
        phone_number = et_phone_number.getText().toString();
        descr = et_description.getText().toString();
    }

    private void init() {
        tv_issuer_id = (TextView) findViewById(TV_ISSUER_ID);
        et_topic = (EditText) findViewById(ET_TOPIC);
        et_phone_number = (EditText) findViewById(ET_PHONE_NUMBER);
        et_obj_no = (EditText) findViewById(ET_OBJ_NO);
        et_description = (EditText) findViewById(ET_DESCRIPTION);
        obj_no = "";
        fromMain = getIntent();
        user = (LoginModel) fromMain.getExtras().get(USER_DATA);
        tv_issuer_id = (TextView) findViewById(R.id.tv_issuer_id);
        tv_issuer_id.setText(user.getIndex_no());
        btn_add = (Button) findViewById(R.id.btn_add);
        btn_scan = (Button) findViewById(R.id.btn_scan);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                showToast(this, BARCODE_NOT_DETECTED);
            } else {
                et_obj_no.setText(result.getContents());
                showToast(this, DETECTED_BARCODE + result.getContents());
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
        if (requestCode == REPORT_TYPE_REQUEST_CODE && resultCode == RESULT_OK){
            Bundle extras = data.getExtras();
            if (extras.get(TYPE).equals(Constant.AUTO)){
                PackageManager pm = this.getPackageManager();
                if (pm.hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
                    setContentView(ACTIVITY_ADD_PROBLEM_AUTO);
                    addingProcess();
                } else {
                    showToast(getApplicationContext(), CANNOT_DETECT_CAMERA);
                }
            } else {
                setContentView(ACTIVITY_ADD_PROBLEM_MANUAL);
                addingProcess();
            }
        } else {
            finish();
        }
    }

    private boolean add(FaultModel faultModel) throws ExecutionException, InterruptedException {
        return new ReportAsync().execute(faultModel).get();
    }

    public static class ReportTypeActivity extends Activity implements View.OnClickListener {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_set_report_type);
            ImageButton btn_manual = (ImageButton) findViewById(BTN_MANUAL);
            ImageButton btn_auto = (ImageButton) findViewById(BTN_AUTO);
            btn_auto.setOnClickListener(this);
            btn_manual.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent typeIntent = getIntent();
            switch (view.getId()) {
                case BTN_AUTO:
                    vibrate(ONE_MILLISECOND,getApplicationContext());
                    typeIntent.putExtra(TYPE, AUTO);
                    break;
                case BTN_MANUAL:
                    vibrate(ONE_MILLISECOND,getApplicationContext());
                    typeIntent.putExtra(TYPE, MANUAL);
                    break;
            }
            setResult(RESULT_OK, typeIntent);
            finish();
        }
    }
}