package com.mobile.cpt.cpt_mobileapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mobile.cpt.cpt_mobileapp.R;
import com.mobile.cpt.cpt_mobileapp.model.LoginModel;

import static com.mobile.cpt.cpt_mobileapp.Constant.*;


public class SearchActivity extends Activity {

    public static final int ACTIVITY_SEARCH = R.layout.activity_search;
    private Boolean isAdded;
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
    private Button btn_add;
    private Button btn_scan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(ACTIVITY_SEARCH);
        init();
    }

    private void init() {
        setHeader(FIND_FAULT);
        tv_issuer_id = (TextView) findViewById(TV_ISSUER_ID);
        et_topic = (EditText) findViewById(ET_TOPIC);
        et_phone_number = (EditText) findViewById(ET_PHONE_NUMBER);
        et_obj_no = (EditText) findViewById(ET_OBJ_NO);
        et_description = (EditText) findViewById(ET_DESCRIPTION);
        obj_no = NULL_STRING;
        btn_add = (Button) findViewById(BTN_ADD);
        btn_scan = (Button) findViewById(BTN_SCAN);
    }

    private void setHeader(String headerStr) {
        TextView header = (TextView) findViewById(HEADER);
        header.setText(headerStr);
    }
}
