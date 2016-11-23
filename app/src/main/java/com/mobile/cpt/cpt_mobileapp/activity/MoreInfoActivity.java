package com.mobile.cpt.cpt_mobileapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.mobile.cpt.cpt_mobileapp.Constant;
import com.mobile.cpt.cpt_mobileapp.Constant.*;
import com.mobile.cpt.cpt_mobileapp.R;
import com.mobile.cpt.cpt_mobileapp.model.FaultModel;

import java.util.List;


public class MoreInfoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fault_present);
        Intent toPresent = getIntent();
        FaultModel fault = (FaultModel) toPresent.getExtras().get(Constant.FAULT);
        TextView tw_id = (TextView) findViewById(R.id.tw_id);
        TextView tw_description = (TextView) findViewById(R.id.tw_description);
        TextView tw_datetime = (TextView) findViewById(R.id.tw_datetime);
        TextView tw_issuer = (TextView) findViewById(R.id.tw_issuer);
        TextView tw_obj_no = (TextView) findViewById(R.id.tw_obj_no);
        TextView tw_status = (TextView) findViewById(R.id.tw_status);
        TextView tw_topic = (TextView) findViewById(R.id.tw_topic);
        tw_id.setText(Integer.toString(fault.getId()));
        tw_datetime.setText(fault.getDate_time());
        tw_description.setText(fault.getDescription());
        tw_issuer.setText(Integer.toString(fault.getIssuer()));
        tw_obj_no.setText(Integer.toString(fault.getObject_number()));
        tw_status.setText(Integer.toString(fault.getStatus()));
        tw_topic.setText(fault.getTopic());
    }
}
