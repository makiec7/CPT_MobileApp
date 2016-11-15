package com.mobile.cpt.cpt_mobileapp;

/**
 * Created by Makiec on 14.11.2016.
 */

public class LoginResult {
    public boolean isLogged;
    public String index_no;

    public LoginResult(boolean isLogged, String index_no){
        this.isLogged = isLogged;
        this.index_no = index_no;
    }
}
