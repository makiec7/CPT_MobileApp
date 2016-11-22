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

import static com.mobile.cpt.cpt_mobileapp.Constant.*;

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
        this.phone_number=phone_number;
        this.topic=topic;
        this.description=description;
        this.object_number=object_number;
        this.date_time = getDateTime();
        this.status = 0;
    }

    public FaultModel(int issuer, String topic, String description, int object_number){
        this.issuer=issuer;
        this.topic=topic;
        this.phone_number = "";
        this.description=description;
        this.object_number=object_number;
        this.date_time = getDateTime();
        this.phone_number = "";
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

    @Override
    public String toString() {
        return this.issuer+this.topic+this.description;
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

    public void setIssuer(int issuer) {
        this.issuer = issuer;
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

    public void setDate_time(String date_time) {
        this.date_time = date_time;
    }

    public void setObject_number(int object_number) {
        this.object_number = object_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
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
            return faultModels;
        } catch (JSONException e) {
            e.printStackTrace();
            return faultModels;
        }
    }

    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_TIME_FORMAT,
                Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }
}
