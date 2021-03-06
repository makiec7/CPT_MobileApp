package com.mobile.cpt.cpt_mobileapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobile.cpt.cpt_mobileapp.R;
import com.mobile.cpt.cpt_mobileapp.model.FaultModel;

import static com.mobile.cpt.cpt_mobileapp.Constant.*;


public class MoreInfoActivity extends Activity {

    public static final int ONGOING = 1;
    public static final int NOT_RESOLVED = 0;
    public static final int FAULT_PRESENT_LAYOUT = R.layout.fault_present;
    private ImageView iv_status;
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
        setContentView(FAULT_PRESENT_LAYOUT);
        init();
        if (fault != null) {
            if (iv_status != null)
                if (fault.getStatus() == NOT_RESOLVED)
                    iv_status.setImageResource(CROSS_LOGO);
                else if (fault.getStatus() == ONGOING)
                    iv_status.setImageResource(ONGOING_ICON);
                else
                    iv_status.setImageResource(TICK_LOGO);
            setFaultInfo();
        }
    }

    private void setFaultInfo() {
        setHeader("Szczególy zgłoszenia " + Integer.toString(fault.getId()));
        tv_id.setText(Integer.toString(fault.getId()));
        tv_datetime.setText(fault.getDate_time());
        tv_description.setText(fault.getDescription());
        tv_issuer.setText(Integer.toString(fault.getIssuer()));
        tv_obj_no.setText(Integer.toString(fault.getObject_number()));
        tv_topic.setText(fault.getTopic());
    }

    private void init() {
        Intent toPresent = getIntent();
        iv_status = (ImageView) findViewById(IV_STATUS);
        fault = (FaultModel) toPresent.getExtras().get(FAULT);
        tv_id = (TextView) findViewById(TV_ID);
        tv_description = (TextView) findViewById(TV_DESCRIPTION);
        tv_datetime = (TextView) findViewById(TV_DATETIME);
        tv_issuer = (TextView) findViewById(TV_ISSUER_ID);
        tv_obj_no = (TextView) findViewById(TV_OBJ_NO);
        tv_topic = (TextView) findViewById(TV_TOPIC);
    }

    public void setHeader(String headerStr) {
        TextView header = (TextView) findViewById(HEADER);
        header.setText(headerStr);
    }
}
