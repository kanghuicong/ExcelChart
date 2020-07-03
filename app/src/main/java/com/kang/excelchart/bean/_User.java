package com.kang.excelchart.bean;

import java.util.Date;

import cn.bmob.v3.BmobObject;

public class _User extends BmobObject {
    private String username;
    private String password;
    private boolean mobilePhoneNumberVerified;
    private String mobilePhoneNumber;
    private String appleid;
    private Date dueTime;
    private boolean isVip;
    private Date rechargeTime;
    private boolean emailVerified;
    private String email;
    private String authData;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isMobilePhoneNumberVerified() {
        return mobilePhoneNumberVerified;
    }

    public void setMobilePhoneNumberVerified(boolean mobilePhoneNumberVerified) {
        this.mobilePhoneNumberVerified = mobilePhoneNumberVerified;
    }

    public String getMobilePhoneNumber() {
        return mobilePhoneNumber;
    }

    public void setMobilePhoneNumber(String mobilePhoneNumber) {
        this.mobilePhoneNumber = mobilePhoneNumber;
    }

    public String getAppleid() {
        return appleid;
    }

    public void setAppleid(String appleid) {
        this.appleid = appleid;
    }

    public Date getDueTime() {
        return dueTime;
    }

    public void setDueTime(Date dueTime) {
        this.dueTime = dueTime;
    }

    public boolean isVip() {
        return isVip;
    }

    public void setVip(boolean vip) {
        isVip = vip;
    }

    public Date getRechargeTime() {
        return rechargeTime;
    }

    public void setRechargeTime(Date rechargeTime) {
        this.rechargeTime = rechargeTime;
    }

    public boolean isEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAuthData() {
        return authData;
    }

    public void setAuthData(String authData) {
        this.authData = authData;
    }

    @Override
    public String toString() {
        return "_User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", mobilePhoneNumberVerified=" + mobilePhoneNumberVerified +
                ", mobilePhoneNumber='" + mobilePhoneNumber + '\'' +
                ", appleid='" + appleid + '\'' +
                ", dueTime=" + dueTime +
                ", isVip=" + isVip +
                ", rechargeTime=" + rechargeTime +
                ", emailVerified=" + emailVerified +
                ", email='" + email + '\'' +
                ", authData='" + authData + '\'' +
                '}';
    }
}
