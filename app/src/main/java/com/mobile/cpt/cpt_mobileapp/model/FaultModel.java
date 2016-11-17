package com.mobile.cpt.cpt_mobileapp.model;

import static com.mobile.cpt.cpt_mobileapp.Constant.*;

public class FaultModel {

    private int id;
    private int issuer;
    private String phone_no;
    private String topic;
    private String obj_name;
    private String description;
    private int obj_no;
    private int room_no;
    private int floor_no;
    private int status;
    private String handler;
    private int priority;

    public FaultModel(int id, int issuer, String phone_no, String topic, String obj_name,
                      String description, int obj_no, int room_no, int floor_no, int status,
                      String handler, int priority){
        this.id=id;
        this.issuer=issuer;
        this.phone_no=phone_no;
        this.topic=topic;
        this.obj_name=obj_name;
        this.description=description;
        this.obj_no=obj_no;
        this.room_no=room_no;
        this.floor_no=floor_no;
        this.status=status;
        this.handler=handler;
        this.priority=priority;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setFloor_no(int floor_no) {
        this.floor_no = floor_no;
    }

    public void setHandler(String handler) {
        this.handler = handler;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIssuer(int issuer) {
        this.issuer = issuer;
    }

    public void setObj_name(String obj_name) {
        this.obj_name = obj_name;
    }

    public void setObj_no(int obj_no) {
        this.obj_no = obj_no;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setRoom_no(int room_no) {
        this.room_no = room_no;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public int getFloor_no() {
        return floor_no;
    }

    public int getId() {
        return id;
    }

    public int getIssuer() {
        return issuer;
    }

    public int getObj_no() {
        return obj_no;
    }

    public int getPriority() {
        return priority;
    }

    public int getRoom_no() {
        return room_no;
    }

    public int getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }

    public String getHandler() {
        return handler;
    }

    public String getObj_name() {
        return obj_name;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public String getTopic() {
        return topic;
    }

}
