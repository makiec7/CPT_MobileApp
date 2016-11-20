package com.mobile.cpt.cpt_mobileapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

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
        FaultModel fault = (FaultModel) toPresent.getExtras().get("fault");

        TextView tw_id = (TextView) findViewById(R.id.tw_id);
        TextView tw_description = (TextView) findViewById(R.id.tw_description);
        TextView tw_floor_no = (TextView) findViewById(R.id.tw_floor_no);
        TextView tw_issuer = (TextView) findViewById(R.id.tw_issuer);
        TextView tw_obj_name = (TextView) findViewById(R.id.tw_obj_name);
        TextView tw_obj_no = (TextView) findViewById(R.id.tw_obj_no);
        TextView tw_priority = (TextView) findViewById(R.id.tw_priority);
        TextView tw_room_no = (TextView) findViewById(R.id.tw_room_no);
        TextView tw_status = (TextView) findViewById(R.id.tw_status);
        TextView tw_topic = (TextView) findViewById(R.id.tw_topic);
        tw_id.setText(Integer.toString(fault.getId()));
        tw_description.setText(fault.getDescription());
        tw_floor_no.setText(Integer.toString(fault.getFloor_no()));
        tw_issuer.setText(Integer.toString(fault.getIssuer()));
        tw_obj_name.setText(fault.getObj_name());
        tw_obj_no.setText(Integer.toString(fault.getObj_no()));
        tw_priority.setText(Integer.toString(fault.getPriority()));
        tw_room_no.setText(Integer.toString(fault.getRoom_no()));
        tw_status.setText(Integer.toString(fault.getStatus()));
        tw_topic.setText(fault.getTopic());
    }
}
