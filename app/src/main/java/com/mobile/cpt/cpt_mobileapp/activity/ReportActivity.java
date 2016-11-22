package com.mobile.cpt.cpt_mobileapp.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import static com.mobile.cpt.cpt_mobileapp.Constant.*;

import com.mobile.cpt.cpt_mobileapp.R;
import com.mobile.cpt.cpt_mobileapp.model.FaultModel;
import com.mobile.cpt.cpt_mobileapp.model.LoginModel;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReportActivity extends Activity implements View.OnClickListener {

    Image image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_report_type);
        ImageButton btn_manual = (ImageButton) findViewById(R.id.btn_manual);
        ImageButton btn_auto = (ImageButton) findViewById(R.id.btn_auto);
        btn_auto.setOnClickListener(this);
        btn_manual.setOnClickListener(this);
    }

    private void autoType(){
        Intent fromMain = getIntent();
        LoginModel user = (LoginModel) fromMain.getExtras().get(USER_DATA);
        TextView tv_issuer_id = (TextView) findViewById(R.id.tv_issuer_id);
        tv_issuer_id.setText(user.getIndex_no());
        Button btn_add = (Button) findViewById(R.id.btn_add);
        Button btn_scan = (Button) findViewById(R.id.btn_scan);
        btn_scan.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                Intent cameraActivity = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (ActivityCompat.checkSelfPermission(getApplicationContext(),
                        Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.CAMERA}, 1010);
                }else {
                    if (cameraActivity.resolveActivity(getPackageManager()) != null) {
                        startActivityForResult(cameraActivity, CAMERA_REQUEST_CODE);
                    }
                }
            }
        });
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView tv_issuer_id = (TextView) findViewById(TV_ISSUER_ID);
                EditText et_topic = (EditText) findViewById(ET_TOPIC);
                EditText et_phone_number = (EditText) findViewById(ET_PHONE_NUMBER);
                EditText et_obj_no = (EditText) findViewById(ET_OBJ_NO);
                EditText et_description = (EditText) findViewById(ET_DESCRIPTION);
                String issuer = tv_issuer_id.getText().toString();
                String topic = et_topic.getText().toString();
                String phone_number = et_phone_number.getText().toString();
                String descr = et_description.getText().toString();
                String obj_no = et_obj_no.getText().toString();
                if (!topic.equals("") && !obj_no.equals("") && !descr.equals("")) {
                    FaultModel fault;
                    if (!phone_number.equals("")) {
                        fault = new FaultModel(Integer.parseInt(issuer), phone_number, topic,
                                descr, Integer.parseInt(obj_no));
                    } else {
                        fault = new FaultModel(Integer.parseInt(issuer), topic,
                                descr, Integer.parseInt(obj_no));
                    }
                    Intent fromMain = getIntent();
                    fromMain.putExtra(FAULT, fault);
                    setResult(RESULT_OK, fromMain);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), FILL_ALL_FIELDS,
                            Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private void manualType(){
        setContentView(ADD_LAYOUT);
        Intent fromMain = getIntent();
        LoginModel user = (LoginModel) fromMain.getExtras().get(USER_DATA);
        TextView tv_issuer_id = (TextView) findViewById(R.id.tv_issuer_id);
        tv_issuer_id.setText(user.getIndex_no());
        Button btn_add = (Button) findViewById(R.id.btn_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView tv_issuer_id = (TextView) findViewById(TV_ISSUER_ID);
                EditText et_topic = (EditText) findViewById(ET_TOPIC);
                EditText et_phone_number = (EditText) findViewById(ET_PHONE_NUMBER);
                EditText et_obj_no = (EditText) findViewById(ET_OBJ_NO);
                EditText et_description = (EditText) findViewById(ET_DESCRIPTION);
                String issuer = tv_issuer_id.getText().toString();
                String topic = et_topic.getText().toString();
                String phone_number = et_phone_number.getText().toString();
                String descr = et_description.getText().toString();
                String obj_no = et_obj_no.getText().toString();
                if (!topic.equals("") && !obj_no.equals("") && !descr.equals("")) {
                    FaultModel fault;
                    if (!phone_number.equals("")) {
                        fault = new FaultModel(Integer.parseInt(issuer), phone_number, topic,
                                descr, Integer.parseInt(obj_no));
                    } else {
                        fault = new FaultModel(Integer.parseInt(issuer), topic,
                                descr, Integer.parseInt(obj_no));
                    }
                    Intent fromMain = getIntent();
                    fromMain.putExtra(FAULT, fault);
                    setResult(RESULT_OK, fromMain);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), FILL_ALL_FIELDS,
                            Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_auto:
                PackageManager pm = this.getPackageManager();
                if (pm.hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
                    setContentView(R.layout.activity_add_problem_auto);
                    autoType();
                    break;
                } else {
                    Toast.makeText(getApplicationContext(), CANNOT_DETECT_CAMERA,
                            Toast.LENGTH_LONG).show();
                }
            case R.id.btn_manual:
                setContentView(R.layout.activity_add_problem_manual);
                manualType();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
        }
    }

}
