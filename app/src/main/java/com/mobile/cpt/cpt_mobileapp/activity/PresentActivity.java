package com.mobile.cpt.cpt_mobileapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.mobile.cpt.cpt_mobileapp.adapter.PresentAdapter;
import com.mobile.cpt.cpt_mobileapp.R;
import com.mobile.cpt.cpt_mobileapp.adapter.ShortPresentAdapter;
import com.mobile.cpt.cpt_mobileapp.async.PresentAsync;
import com.mobile.cpt.cpt_mobileapp.model.FaultModel;
import com.mobile.cpt.cpt_mobileapp.model.LoginModel;

import java.util.List;
import java.util.concurrent.ExecutionException;

import static com.mobile.cpt.cpt_mobileapp.Constant.*;

public class PresentActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        List<FaultModel> faults = null;
        Intent fromMain = getIntent();
        LoginModel user = (LoginModel) fromMain.getExtras().get(USER_DATA);
        try {
            if (user.isLogged()) {
                faults = new PresentAsync().execute(user.getIndex_no()).get();
            } else {
                faults = new PresentAsync().execute().get();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            finish();
        } catch (ExecutionException e) {
            e.printStackTrace();
            finish();
        }
        this.setContentView(R.layout.activity_present_faults);
        ListView listView = (ListView) findViewById(R.id.list_fault);
        ShortPresentAdapter presentAdapter = new ShortPresentAdapter(this, R.layout.short_fault_present, faults);
        listView.setAdapter(presentAdapter);
    }
}