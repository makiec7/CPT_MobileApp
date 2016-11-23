package com.mobile.cpt.cpt_mobileapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

import static com.mobile.cpt.cpt_mobileapp.Constant.DATA_ERROR;
import static com.mobile.cpt.cpt_mobileapp.Constant.FAULT;
import static com.mobile.cpt.cpt_mobileapp.Constant.TW_DATETIME;
import static com.mobile.cpt.cpt_mobileapp.Constant.TW_ISSUER;
import static com.mobile.cpt.cpt_mobileapp.Constant.TW_OBJ_NO;

public class EditFaultActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_problem);
        Intent toEdit = getIntent();
        final FaultModel fault = (FaultModel) toEdit.getExtras().get(FAULT);
        TextView tw_id = (TextView) findViewById(R.id.tw_id);
        final EditText et_description = (EditText) findViewById(R.id.et_description);
        TextView tw_issuer = (TextView) findViewById(TW_ISSUER);
        TextView tw_obj_no = (TextView) findViewById(TW_OBJ_NO);
        TextView tw_datetime = (TextView) findViewById(TW_DATETIME);
        final EditText et_topic = (EditText) findViewById(R.id.et_topic);
        final EditText et_phone_number = (EditText) findViewById(R.id.et_phone_number);
        Button btn_edit_fault = (Button) findViewById(R.id.btn_edit);
        Log.i("id", tw_id.getText().toString());
        Log.i("fault", fault.toString());
            tw_id.setText(Integer.toString(fault.getId()));
            et_description.setText(fault.getDescription());
            tw_datetime.setText(fault.getDate_time());
            tw_issuer.setText(Integer.toString(fault.getIssuer()));
            et_phone_number.setText(fault.getPhone_number());
            tw_obj_no.setText(Integer.toString(fault.getObject_number()));
            et_topic.setText(fault.getTopic());
            btn_edit_fault.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {

                    fault.setDescription(String.valueOf(et_description.getText()));
                    fault.setTopic(String.valueOf(et_topic));
                    fault.setPhone_number(String.valueOf(et_phone_number));
                    try {
                        new EditAsync().execute(fault).get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(), DATA_ERROR, Toast.LENGTH_LONG).show();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(), DATA_ERROR, Toast.LENGTH_LONG).show();
                    }
                }
            });
    }
}
