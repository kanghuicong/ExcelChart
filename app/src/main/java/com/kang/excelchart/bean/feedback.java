package com.kang.excelchart.bean;

import cn.bmob.v3.BmobObject;

public class feedback extends BmobObject {

    private String device;
    private String tel;
    private String feedback;

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
