package com.kang.excelchart.bean;

import java.util.Date;

import cn.bmob.v3.BmobUser;

public class _User extends BmobUser {
    private String appleid;

    private boolean isVip;

    private String authData;


    public String getAppleid() {
        return appleid;
    }

    public void setAppleid(String appleid) {
        this.appleid = appleid;
    }


    public boolean isVip() {
        return isVip;
    }

    public void setVip(boolean vip) {
        isVip = vip;
    }



    public String getAuthData() {
        return authData;
    }

    public void setAuthData(String authData) {
        this.authData = authData;
    }



}
