package com.mobile.cpt.cpt_mobileapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static com.mobile.cpt.cpt_mobileapp.Constant.*;
import com.mobile.cpt.cpt_mobileapp.async.DeleteAsync;
import com.mobile.cpt.cpt_mobileapp.async.EditAsync;
import com.mobile.cpt.cpt_mobileapp.model.FaultModel;

import java.util.concurrent.ExecutionException;

public class EditFaultActivity extends Activity {

    private Intent toEdit;
    private FaultModel fault;
    private TextView tv_id;
    private EditText et_description;
    private TextView tv_issuer;
    private TextView tv_obj_no;
    private TextView tv_datetime;
    private EditText et_topic;
    private EditText et_phone_number;
    private Button btn_edit_fault;
    private Button btn_delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        setFields();
        btn_edit_fault.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                fault.setDescription(String.valueOf(et_description.getText().toString()));
                fault.setTopic(String.valueOf(et_topic.getText().toString()));
                fault.setPhone_number(String.valueOf(et_phone_number.getText().toString()));
                edit(fault);
                showToast(getApplicationContext(), EDIT_SUCCESS);
                setResultAndFinish();
            }
        });
        btn_delete.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                delete(fault);
                showToast(getApplicationContext(), DELETE_SUCCESS);
                setResultAndFinish();
            }
        });
    }

    private void init() {
        setContentView(EDIT_LAYOUT);
        setHeader("Edycja zgłoszenia");
        toEdit = getIntent();
        fault = (FaultModel) toEdit.getExtras().get(FAULT);
        tv_id = (TextView) findViewById(TV_ID);
        et_description = (EditText) findViewById(ET_DESCRIPTION);
        tv_issuer = (TextView) findViewById(TV_ISSUER_ID);
        tv_obj_no = (TextView) findViewById(TV_OBJ_NO);
        tv_datetime = (TextView) findViewById(TV_DATETIME);
        et_topic = (EditText) findViewById(ET_TOPIC);
        et_phone_number = (EditText) findViewById(ET_PHONE_NUMBER);
        btn_edit_fault = (Button) findViewById(BTN_EDIT);
        btn_delete = (Button) findViewById(BTN_DELETE);
    }

    private void setFields() {
        tv_id.setText(Integer.toString(fault.getId()));
        et_description.setText(fault.getDescription());
        tv_datetime.setText(fault.getDate_time());
        tv_issuer.setText(Integer.toString(fault.getIssuer()));
        et_phone_number.setText(fault.getPhone_number());
        tv_obj_no.setText(Integer.toString(fault.getObject_number()));
        et_topic.setText(fault.getTopic());
    }

    private void delete(FaultModel fault){
        try {
            new DeleteAsync().execute(fault).get();
        } catch (InterruptedException | ExecutionException e) {
            setResultAndAbort(e);
        }
    }

    private void edit(FaultModel fault){
        try {
            new EditAsync().execute(fault).get();
        } catch (InterruptedException | ExecutionException e) {
            setResultAndAbort(e);
        }
    }

    private void setResultAndAbort(Exception e) {
        e.printStackTrace();
        showToast(getApplicationContext(), DATA_ERROR);
    }

    private void setResultAndFinish() {
        toEdit.putExtra(FINNISH, true);
        setResult(RESULT_OK, toEdit);
        finish();
    }

    public void setHeader(String headerStr) {
        TextView header = (TextView) findViewById(HEADER);
        header.setText(headerStr);
    }
}
