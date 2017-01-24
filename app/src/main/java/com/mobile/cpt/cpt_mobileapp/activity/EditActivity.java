package com.mobile.cpt.cpt_mobileapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.mobile.cpt.cpt_mobileapp.adapter.ShortPresentAdapter;
import com.mobile.cpt.cpt_mobileapp.async.PresentAsync;
import com.mobile.cpt.cpt_mobileapp.model.FaultModel;
import com.mobile.cpt.cpt_mobileapp.model.LoginModel;

import java.util.List;
import java.util.concurrent.ExecutionException;

import static com.mobile.cpt.cpt_mobileapp.Constant.*;

public class EditActivity extends Activity {

    private List<FaultModel> faults = null;
    private Intent fromMain;
    private LoginModel user;
    private ShortPresentAdapter presentAdapter;
    private FaultModel fault;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fromMain = getIntent();
        user = (LoginModel) fromMain.getExtras().get(USER_DATA);
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
        this.setContentView(USER_PROBLEMS_LAYOUT);
        final ListView listView = (ListView) findViewById(LIST_FAULT);
        listView.setClickable(true);
        presentAdapter = new ShortPresentAdapter(this, SHORT_FAULT_LAYOUT,
                faults);
        listView.setAdapter(presentAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                vibrate(ONE_MILLISECOND, getApplicationContext());
                Intent edition = new Intent(getApplicationContext(), EditFaultActivity.class);
                fault = (FaultModel) listView.getItemAtPosition(i);
                edition.putExtra(FAULT, fault);
                startActivityForResult(edition, EDIT_REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == EDIT_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                finish();
            }
        }
    }
}
