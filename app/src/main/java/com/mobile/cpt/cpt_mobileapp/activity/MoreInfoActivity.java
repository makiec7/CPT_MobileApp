package com.mobile.cpt.cpt_mobileapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.mobile.cpt.cpt_mobileapp.R;
import com.mobile.cpt.cpt_mobileapp.model.FaultModel;

import static com.mobile.cpt.cpt_mobileapp.Constant.FAULT;
import static com.mobile.cpt.cpt_mobileapp.Constant.TV_DATETIME;
import static com.mobile.cpt.cpt_mobileapp.Constant.TV_DESCRIPTION;
import static com.mobile.cpt.cpt_mobileapp.Constant.TV_ID;
import static com.mobile.cpt.cpt_mobileapp.Constant.TV_ISSUER;
import static com.mobile.cpt.cpt_mobileapp.Constant.TV_OBJ_NO;
import static com.mobile.cpt.cpt_mobileapp.Constant.TV_TOPIC;


public class MoreInfoActivity extends Activity {

    private Intent toPresent;
    private FaultModel fault;
    private TextView tv_id;
    private TextView tv_description;
    private TextView tv_datetime;
    private TextView tv_issuer;
    private TextView tv_obj_no;
    private TextView tv_topic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fault_present);
        toPresent = getIntent();
        fault = (FaultModel) toPresent.getExtras().get(FAULT);
        tv_id = (TextView) findViewById(TV_ID);
        tv_description = (TextView) findViewById(TV_DESCRIPTION);
        tv_datetime = (TextView) findViewById(TV_DATETIME);
        tv_issuer = (TextView) findViewById(TV_ISSUER);
        tv_obj_no = (TextView) findViewById(TV_OBJ_NO);
        tv_topic = (TextView) findViewById(TV_TOPIC);
        tv_id.setText(Integer.toString(fault.getId()));
        tv_datetime.setText(fault.getDate_time());
        tv_description.setText(fault.getDescription());
        tv_issuer.setText(Integer.toString(fault.getIssuer()));
        tv_obj_no.setText(Integer.toString(fault.getObject_number()));
        tv_topic.setText(fault.getTopic());
    }
}
