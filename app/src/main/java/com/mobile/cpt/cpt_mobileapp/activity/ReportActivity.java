package com.mobile.cpt.cpt_mobileapp.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
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

public class ReportActivity extends Activity implements View.OnClickListener {

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
        /*setContentView(ADD_LAYOUT);
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
*/
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
                setContentView(R.layout.activity_add_problem_auto);
                autoType();
                break;
            case R.id.btn_manual:
                setContentView(R.layout.activity_add_problem_manual);
                manualType();
                break;
        }
    }
}
