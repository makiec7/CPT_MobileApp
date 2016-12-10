package com.mobile.cpt.cpt_mobileapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fault_present);
        Intent toPresent = getIntent();
        ImageView iv_status = (ImageView) findViewById(R.id.iv_status);
        FaultModel fault = (FaultModel) toPresent.getExtras().get(FAULT);
        TextView tv_id = (TextView) findViewById(TV_ID);
        TextView tv_description = (TextView) findViewById(TV_DESCRIPTION);
        TextView tv_datetime = (TextView) findViewById(TV_DATETIME);
        TextView tv_issuer = (TextView) findViewById(TV_ISSUER);
        TextView tv_obj_no = (TextView) findViewById(TV_OBJ_NO);
        TextView tv_topic = (TextView) findViewById(TV_TOPIC);
        if (fault != null) {
            if (iv_status != null)
                if (fault.getStatus() == 0)
                    iv_status.setImageResource(R.drawable.cross_logo);
                else
                    iv_status.setImageResource(R.drawable.tick_logo);
            tv_id.setText(Integer.toString(fault.getId()));
            tv_datetime.setText(fault.getDate_time());
            tv_description.setText(fault.getDescription());
            tv_issuer.setText(Integer.toString(fault.getIssuer()));
            tv_obj_no.setText(Integer.toString(fault.getObject_number()));
            tv_topic.setText(fault.getTopic());
        }
    }
}
