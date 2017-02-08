package com.mobile.cpt.cpt_mobileapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.mobile.cpt.cpt_mobileapp.adapter.ShortPresentAdapter;
import com.mobile.cpt.cpt_mobileapp.async.PresentAsync;
import com.mobile.cpt.cpt_mobileapp.model.FaultModel;
import com.mobile.cpt.cpt_mobileapp.model.LoginModel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static com.mobile.cpt.cpt_mobileapp.Constant.*;

public class EditActivity extends Activity {

    private List<FaultModel> faults = null;
    private FaultModel fault;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent fromMain = getIntent();
        LoginModel user = (LoginModel) fromMain.getExtras().get(USER_DATA);
        try {
            if (user != null) {
                if (user.isLogged()) {
                    faults = new PresentAsync().execute(user.getIndex_no()).get();
                } else {
                    faults = new PresentAsync().execute().get();
                }
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            finish();
        }

        this.setContentView(USER_PROBLEMS_LAYOUT);
        setHeader("Wybierz zgłoszenie do edycji");
        final ListView listView = (ListView) findViewById(LIST_FAULT);
        listView.setClickable(true);
        faults = clearFinished(faults);
        ShortPresentAdapter presentAdapter = new ShortPresentAdapter(this, SHORT_FAULT_LAYOUT,
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

    private List<FaultModel> clearFinished(List<FaultModel> faults){
        List<FaultModel> faultsList = new ArrayList<>();
        for (FaultModel faultTmp : faults)
            if (faultTmp.getStatus() != 2)
                faultsList.add(faultTmp);
        return faultsList;
    }

    public void setHeader(String headerStr) {
        TextView header = (TextView) findViewById(HEADER);
        header.setText(headerStr);
    }
}
