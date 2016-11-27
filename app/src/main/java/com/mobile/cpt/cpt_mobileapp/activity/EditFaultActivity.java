package com.mobile.cpt.cpt_mobileapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.*;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mobile.cpt.cpt_mobileapp.R;
import com.mobile.cpt.cpt_mobileapp.async.EditAsync;
import com.mobile.cpt.cpt_mobileapp.model.FaultModel;

import java.util.concurrent.ExecutionException;

import static com.mobile.cpt.cpt_mobileapp.Constant.*;

public class EditFaultActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(EDIT_LAYOUT);
        Intent toEdit = getIntent();
        final FaultModel fault = (FaultModel) toEdit.getExtras().get(FAULT);
        TextView tv_id = (TextView) findViewById(TV_ID);
        final EditText et_description = (EditText) findViewById(ET_DESCRIPTION);
        TextView tv_issuer = (TextView) findViewById(TV_ISSUER);
        TextView tv_obj_no = (TextView) findViewById(TV_OBJ_NO);
        TextView tv_datetime = (TextView) findViewById(TV_DATETIME);
        final EditText et_topic = (EditText) findViewById(ET_TOPIC);
        final EditText et_phone_number = (EditText) findViewById(ET_PHONE_NUMBER);
        Button btn_edit_fault = (Button) findViewById(R.id.btn_edit);
        tv_id.setText(Integer.toString(fault.getId()));
        et_description.setText(fault.getDescription());
        tv_datetime.setText(fault.getDate_time());
        tv_issuer.setText(Integer.toString(fault.getIssuer()));
        et_phone_number.setText(fault.getPhone_number());
        tv_obj_no.setText(Integer.toString(fault.getObject_number()));
        et_topic.setText(fault.getTopic());
        btn_edit_fault.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                fault.setDescription(String.valueOf(et_description.getText().toString()));
                fault.setTopic(String.valueOf(et_topic.getText().toString()));
                fault.setPhone_number(String.valueOf(et_phone_number.getText().toString()));
                try {
                    new EditAsync().execute(fault).get();
                    Toast.makeText(getApplicationContext(), EDIT_SUCCESS, Toast.LENGTH_LONG).show();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), DATA_ERROR, Toast.LENGTH_LONG).show();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), DATA_ERROR, Toast.LENGTH_LONG).show();
                }
                finish();
            }
        });
    }
}
