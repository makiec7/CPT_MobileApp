package com.mobile.cpt.cpt_mobileapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.mobile.cpt.cpt_mobileapp.R;
import com.mobile.cpt.cpt_mobileapp.adapter.ShortPresentAdapter;
import com.mobile.cpt.cpt_mobileapp.async.PresentAsync;
import com.mobile.cpt.cpt_mobileapp.model.FaultModel;
import com.mobile.cpt.cpt_mobileapp.model.LoginModel;

import java.util.List;
import java.util.concurrent.ExecutionException;

import static com.mobile.cpt.cpt_mobileapp.Constant.*;

public class PresentActivity extends Activity {

    private ListView listView;
    private Intent details;
    private List<FaultModel> faults;
    private FaultModel fault;

    public PresentActivity() {
        listView = null;
        faults = null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(USER_PROBLEMS_LAYOUT);
        Intent fromMain = getIntent();
        LoginModel user = (LoginModel) fromMain.getExtras().get(USER_DATA);
        if (user.isLogged()) {
            setHeader("Zgłoszenia użytkownika " + user.getIndex_no());
            faults = presentWithParameters(user.getIndex_no());
        } else {
            setHeader("Wszystkie zgłoszenia");
            faults = presentWithoutParameters();
        }

        listView = (ListView) findViewById(LIST_FAULT);
        listView.setClickable(true);
        ShortPresentAdapter presentAdapter = new ShortPresentAdapter(this, SHORT_FAULT_LAYOUT,
                faults);
        listView.setAdapter(presentAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                vibrate(ONE_MILLISECOND, getApplicationContext());
                details = new Intent(getApplicationContext(), MoreInfoActivity.class);
                fault = (FaultModel) listView.getItemAtPosition(i);
                details.putExtra(FAULT, fault);
                startActivity(details);
            }
        });
    }

    private List<FaultModel> presentWithoutParameters(){
        try {
            return new PresentAsync().execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<FaultModel> presentWithParameters(String index_no){
        try {
            return new PresentAsync().execute(index_no).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setHeader(String headerStr) {
        TextView header = (TextView) findViewById(HEADER);
        header.setText(headerStr);
    }
}