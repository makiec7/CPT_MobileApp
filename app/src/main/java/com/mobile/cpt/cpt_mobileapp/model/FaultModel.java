package com.mobile.cpt.cpt_mobileapp.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static com.mobile.cpt.cpt_mobileapp.Constant.DATE_TIME;
import static com.mobile.cpt.cpt_mobileapp.Constant.DATE_TIME_FORMAT;
import static com.mobile.cpt.cpt_mobileapp.Constant.DESCRIPTION;
import static com.mobile.cpt.cpt_mobileapp.Constant.ID;
import static com.mobile.cpt.cpt_mobileapp.Constant.ISSUER;
import static com.mobile.cpt.cpt_mobileapp.Constant.OBJECT_NUMBER;
import static com.mobile.cpt.cpt_mobileapp.Constant.PHONE_NUMBER;
import static com.mobile.cpt.cpt_mobileapp.Constant.STATUS;
import static com.mobile.cpt.cpt_mobileapp.Constant.TOPIC;

public class FaultModel implements Serializable {

    private int id;
    private int issuer;
    private String phone_number;
    private String topic;
    private String date_time;
    private String description;
    private int object_number;
    private int status;

    public FaultModel(int issuer, String phone_number, String topic, String description,
                      int object_number){
        this.issuer=issuer;
        if (this.phone_number != null)
            this.phone_number=phone_number;
        else
            this.phone_number="";
        this.topic=topic;
        this.description=description;
        this.object_number=object_number;
        this.date_time = getDateTimeFromSystem();
        this.status = 0;
    }

    public FaultModel(int id, int issuer, String phone_number, String topic, String description,
                      int object_number, String date_time, int status){
        this.id = id;
        this.issuer=issuer;
        this.phone_number=phone_number;
        this.topic=topic;
        this.description=description;
        this.object_number=object_number;
        this.date_time = date_time;
        this.status = status;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public int getIssuer() {
        return issuer;
    }

    public int getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }

    public String getTopic() {
        return topic;
    }

    public int getObject_number() {
        return object_number;
    }

    public String getDate_time() {
        return date_time;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    private String getDateTimeFromSystem() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_TIME_FORMAT,
                Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }

    public static FaultModel fromJSONObj(JSONObject jObj){
        try {
            return new FaultModel(jObj.getInt(ID), jObj.getInt(ISSUER),
                    jObj.getString(PHONE_NUMBER), jObj.getString(TOPIC),
                    jObj.getString(DESCRIPTION), jObj.getInt(OBJECT_NUMBER),
                    jObj.getString(DATE_TIME), jObj.getInt(STATUS));
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<FaultModel> fromJSONArray(JSONObject jObj){
        List<FaultModel> faultModels = new ArrayList<FaultModel>();
        try {
            JSONArray jArray = jObj.getJSONArray("list");
            for (int i=0; i<jArray.length(); i++){
                FaultModel tmp = fromJSONObj((JSONObject) jArray.get(i));
                faultModels.add(tmp);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return faultModels;
    }


}
