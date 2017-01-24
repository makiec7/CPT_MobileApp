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

public class PresentActivity extends Activity {

    private ListView listView;
    private Intent details;
    private List<FaultModel> faults;
    private Intent fromMain;
    private LoginModel user;
    private FaultModel fault;
    private ShortPresentAdapter presentAdapter;

    public PresentActivity() {
        listView = null;
        faults = null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fromMain = getIntent();
        user = (LoginModel) fromMain.getExtras().get(USER_DATA);
        if (user.isLogged()) {
            faults = presentWithParameters(user.getIndex_no());
        } else {
            faults = presentWithoutParameters();
        }
        this.setContentView(USER_PROBLEMS_LAYOUT);
        listView = (ListView) findViewById(LIST_FAULT);
        listView.setClickable(true);
        presentAdapter = new ShortPresentAdapter(this, SHORT_FAULT_LAYOUT,
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
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<FaultModel> presentWithParameters(String index_no){
        try {
            return new PresentAsync().execute(index_no).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }
}