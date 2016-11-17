package com.mobile.cpt.cpt_mobileapp.model;


import java.io.Serializable;

public class LoginModel implements Serializable{
    private boolean isLogged;
    private String index_no;

    public LoginModel(boolean isLogged, String index_no){
        this.isLogged = isLogged;
        this.index_no = index_no;
    }

    public String getIndex_no() {
        return index_no;
    }

    public boolean isLogged() {
        return isLogged;
    }

    public void setIndex_no(String index_no) {
        this.index_no = index_no;
    }

    public void setLogged(boolean logged) {
        isLogged = logged;
    }
}
