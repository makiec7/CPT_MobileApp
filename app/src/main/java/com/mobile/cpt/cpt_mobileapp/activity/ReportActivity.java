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
import android.widget.TextView;
import android.widget.Toast;

import static com.mobile.cpt.cpt_mobileapp.Constant.*;

import com.mobile.cpt.cpt_mobileapp.R;
import com.mobile.cpt.cpt_mobileapp.model.FaultModel;
import com.mobile.cpt.cpt_mobileapp.model.LoginModel;

public class ReportActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(ADD_LAYOUT);
        Intent fromMain = getIntent();
        LoginModel user = (LoginModel) fromMain.getExtras().get(USER_DATA);
        TextView tv_issuer_id = (TextView) findViewById(R.id.tv_issuer_id);
        tv_issuer_id.setText(user.getIndex_no());
        Button btn_add = (Button) findViewById(R.id.btn_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView tv_issuer_id = (TextView) findViewById(R.id.tv_issuer_id);
                EditText et_topic = (EditText) findViewById(R.id.et_topic);
                EditText et_obj_name = (EditText) findViewById(R.id.et_obj_name);
                EditText et_floor_no = (EditText) findViewById(R.id.et_floor_no);
                EditText et_room_no = (EditText) findViewById(R.id.et_room_no);
                EditText et_obj_no = (EditText) findViewById(R.id.et_obj_no);
                EditText et_description = (EditText) findViewById(R.id.et_description);
                TelephonyManager tm = (TelephonyManager)getSystemService(TELEPHONY_SERVICE);
                //String number = tm.getLine1Number();
                String issuer = tv_issuer_id.getText().toString();
                String topic = et_topic.getText().toString();
                String obj_name = et_obj_name.getText().toString();
                String descr = et_description.getText().toString();
                String obj_no = et_obj_no.getText().toString();
                String room_no = et_room_no.getText().toString();
                String floor_no = et_floor_no.getText().toString();
                Log.i("phone_no", tm.toString());
                if (!topic.equals("") && !obj_name.equals("") && !obj_no.equals("") &&
                        !descr.equals("") && !room_no.equals("") && !floor_no.equals("")) {
                    FaultModel fault =
                            new FaultModel(Integer.parseInt(issuer), "123", topic, obj_name,
                                    descr, Integer.parseInt(obj_no), Integer.parseInt(room_no),
                                    Integer.parseInt(floor_no), 1, "", 1);
                    Log.i("fault in report", fault.toString());
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
}
