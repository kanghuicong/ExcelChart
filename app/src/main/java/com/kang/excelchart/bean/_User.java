package com.kang.excelchart.bean;

import java.util.Date;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobPointer;
import cn.bmob.v3.datatype.BmobRelation;

public class _User extends BmobUser {
    private String appleid;

    private boolean isVip;

    private String authData;

    private BmobRelation tables;
    private BmobRelation tables_1;

    public BmobRelation getTables() {
        return tables;
    }

    public void setTables(BmobRelation tables) {
        this.tables = tables;
    }

    public BmobRelation getTables_1() {
        return tables_1;
    }

    public void setTables_1(BmobRelation tables_1) {
        this.tables_1 = tables_1;
    }

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
