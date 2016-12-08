package com.mobile.cpt.cpt_mobileapp.activity;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.mobile.cpt.cpt_mobileapp.Constant;
import com.mobile.cpt.cpt_mobileapp.R;
import com.mobile.cpt.cpt_mobileapp.async.ReportAsync;
import com.mobile.cpt.cpt_mobileapp.model.FaultModel;
import com.mobile.cpt.cpt_mobileapp.model.LoginModel;

import java.util.concurrent.ExecutionException;

import static com.mobile.cpt.cpt_mobileapp.Constant.*;

public class ReportActivity extends Activity {

    private Image image;
    private ProgressDialog loading = null;
    private int progress;
    private Handler handler = new Handler();
    private Boolean isAdded;
    private int status = 0;
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
        loading = new ProgressDialog(ReportActivity.this);
        loading.setCancelable(true);
        loading.setMessage(LOADING);
        loading.setProgressStyle(ProgressDialog.STYLE_SPINNER);
    }

    private void addingProcess(){
        init();
        if (btn_scan!= null) {
            btn_scan.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.M)
                @Override
                public void onClick(View view) {
                    Intent cameraActivity = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    if (ActivityCompat.checkSelfPermission(getApplicationContext(),
                            Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        requestPermissions(new String[]{Manifest.permission.CAMERA}, 1010);
                    } else {
                        if (cameraActivity.resolveActivity(getPackageManager()) != null) {
                            startActivityForResult(cameraActivity, CAMERA_REQUEST_CODE);
                        }
                    }
                }
            });
        }
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loading.show();
                spinningThread.start();
                if (et_obj_no != null) {
                    obj_no = et_obj_no.getText().toString();
                } else {
                    // NEED TO BE IMPLEMENTED
                    // obj_no = get from image
                }
                if (!topic.equals("") && !obj_no.equals("") && !descr.equals("")) {
                    FaultModel fault;
                    if (!phone_number.equals("")) {
                        fault = new FaultModel(Integer.parseInt(issuer), phone_number, topic,
                                descr, Integer.parseInt(obj_no));
                    } else {
                        fault = new FaultModel(Integer.parseInt(issuer), topic,
                                descr, Integer.parseInt(obj_no));
                    }
                    try {
                        isAdded = add(fault);
                        status = 1;
                    } catch (ExecutionException e) {
                        setError(e);
                    } catch (InterruptedException e) {
                        setError(e);
                    }
                    fromMain.putExtra(STATUS, status);
                    setResult(RESULT_OK, fromMain);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), FILL_ALL_FIELDS,
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void setError(Exception e) {
        status = 2;
        isAdded = false;
        e.printStackTrace();
    }

    private void init() {
        tv_issuer_id = (TextView) findViewById(TV_ISSUER_ID);
        et_topic = (EditText) findViewById(ET_TOPIC);
        et_phone_number = (EditText) findViewById(ET_PHONE_NUMBER);
        et_obj_no = (EditText) findViewById(ET_OBJ_NO);
        et_description = (EditText) findViewById(ET_DESCRIPTION);
        issuer = tv_issuer_id.getText().toString();
        topic = et_topic.getText().toString();
        phone_number = et_phone_number.getText().toString();
        descr = et_description.getText().toString();
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
        if (requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get(DATA);
        } else if (requestCode == REPORT_TYPE_REQUEST_CODE && resultCode == RESULT_OK){
            Bundle extras = data.getExtras();
            if (extras.get("type").equals(Constant.AUTO)){
                PackageManager pm = this.getPackageManager();
                if (pm.hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
                    setContentView(R.layout.activity_add_problem_auto);
                    addingProcess();
                } else {
                    Toast.makeText(getApplicationContext(), CANNOT_DETECT_CAMERA,
                            Toast.LENGTH_LONG).show();
                }
            } else {
                setContentView(R.layout.activity_add_problem_manual);
                addingProcess();
            }
        }
    }

    public static class ReportTypeActivity extends Activity implements View.OnClickListener {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_set_report_type);
            ImageButton btn_manual = (ImageButton) findViewById(R.id.btn_manual);
            ImageButton btn_auto = (ImageButton) findViewById(R.id.btn_auto);
            btn_auto.setOnClickListener(this);
            btn_manual.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            Intent typeIntent;
            switch (view.getId()) {
                case R.id.btn_auto:
                    typeIntent = getIntent();
                    typeIntent.putExtra(TYPE, AUTO);
                    setResult(RESULT_OK, typeIntent);
                    finish();
                    break;
                case R.id.btn_manual:
                    typeIntent = getIntent();
                    typeIntent.putExtra(TYPE, MANUAL);
                    setResult(RESULT_OK, typeIntent);
                    finish();
                    break;
            }
        }
    }

    Thread spinningThread = new Thread(new Runnable() {
        @Override
        public void run() {
            while(status == 0){
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        loading.setProgress(progress);
                        if (status != 0){
                            loading.dismiss();
                        }
                    }
                });
                try{
                    Thread.sleep(20);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    });

    private boolean add(FaultModel faultModel) throws ExecutionException, InterruptedException {
        return new ReportAsync().execute(faultModel).get();
    }
}
