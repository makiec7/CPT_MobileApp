package com.mobile.cpt.cpt_mobileapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.mobile.cpt.cpt_mobileapp.PresentAdapter;
import com.mobile.cpt.cpt_mobileapp.R;
import com.mobile.cpt.cpt_mobileapp.async.PresentAsync;
import com.mobile.cpt.cpt_mobileapp.model.FaultModel;
import com.mobile.cpt.cpt_mobileapp.model.LoginModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

import static com.mobile.cpt.cpt_mobileapp.Constant.*;

public class PresentActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        List<FaultModel> faults = null;
        Bundle b = getIntent().getExtras();
        Intent fromMain = getIntent();
        LoginModel user = (LoginModel) fromMain.getExtras().get(USER_DATA);
        try {
            Log.i("tutaj", "tutaj1");
            faults = new PresentAsync().execute().get();
            Log.i("tutaj", "tutaj2");
        } catch (InterruptedException e) {
            e.printStackTrace();
            Log.i("tutaj", "tutaj3");
            finish();
        } catch (ExecutionException e) {
            Log.i("tutaj", "tutaj4");
            e.printStackTrace();
            finish();
        }
        Log.i("tutaj", "tutaj5");
        this.setContentView(R.layout.activity_present_faults);
        ListView listView = (ListView) findViewById(R.id.list_fault);
        Log.i("presentactivity", faults.toString());
        PresentAdapter presentAdapter = new PresentAdapter(this, R.layout.activity_present_faults, faults);
        listView.setAdapter(presentAdapter);

    }
}