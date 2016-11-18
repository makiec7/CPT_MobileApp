package com.mobile.cpt.cpt_mobileapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static com.mobile.cpt.cpt_mobileapp.Constant.*;

import com.mobile.cpt.cpt_mobileapp.R;
import com.mobile.cpt.cpt_mobileapp.model.FaultModel;
import com.mobile.cpt.cpt_mobileapp.model.LoginModel;

public class ReportActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(ADD_LAYOUT);
        Intent fromMain = getIntent();
        LoginModel user = (LoginModel) fromMain.getExtras().get(USER_DATA);
        TextView tv_issuer_id = (TextView) findViewById(R.id.tv_issuer_id);
        tv_issuer_id.setText(user.getIndex_no());
        Button btn_add = (Button) findViewById(R.id.btn_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView tv_issuer_id = (TextView) findViewById(R.id.tv_issuer_id);
                EditText et_topic = (EditText) findViewById(R.id.et_topic);
                EditText et_obj_name = (EditText) findViewById(R.id.et_obj_name);
                EditText et_floor_no = (EditText) findViewById(R.id.et_floor_no);
                EditText et_room_no = (EditText) findViewById(R.id.et_room_no);
                EditText et_obj_no = (EditText) findViewById(R.id.et_obj_no);
                EditText et_description = (EditText) findViewById(R.id.et_description);
                FaultModel fault = new FaultModel(Integer.parseInt(tv_issuer_id.getText().toString()), "123",
                        et_topic.getText().toString(), et_obj_name.getText().toString(),
                        et_description.getText().toString(), Integer.parseInt(et_obj_no.getText().toString()),
                        Integer.parseInt(et_room_no.getText().toString()),
                        Integer.parseInt(et_floor_no.getText().toString()), 1, "", 1);
                Log.i("fault in report", fault.toString());
                Intent fromMain = getIntent();
                fromMain.putExtra(FAULT, fault);
                setResult(RESULT_OK, fromMain);
                finish();
            }
        });

    }
}
