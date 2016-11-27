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

import static com.mobile.cpt.cpt_mobileapp.Constant.FAULT;


public class MoreInfoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fault_present);
        Intent toPresent = getIntent();
        FaultModel fault = (FaultModel) toPresent.getExtras().get(FAULT);
        TextView tv_id = (TextView) findViewById(R.id.tv_id);
        TextView tv_description = (TextView) findViewById(R.id.tv_description);
        TextView tv_datetime = (TextView) findViewById(R.id.tv_datetime);
        TextView tv_issuer = (TextView) findViewById(R.id.tv_issuer);
        TextView tv_obj_no = (TextView) findViewById(R.id.tv_obj_no);
        TextView tv_topic = (TextView) findViewById(R.id.tv_topic);
        tv_id.setText(Integer.toString(fault.getId()));
        tv_datetime.setText(fault.getDate_time());
        tv_description.setText(fault.getDescription());
        tv_issuer.setText(Integer.toString(fault.getIssuer()));
        tv_obj_no.setText(Integer.toString(fault.getObject_number()));
        tv_topic.setText(fault.getTopic());
    }
}
