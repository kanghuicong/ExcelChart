package com.kang.excelchart.wxapi;

import com.alibaba.fastjson.annotation.JSONField;

/*
 * Created by kanghuicong on 2018/8/24.
 * Description：
 */
public class WeChatBean {


    /**
     * partnerId : 112967812
     * prepayId : wx07143448054561086e590cc41897252053
     * package : Sign=WXPay
     * nonceStr : 15731084885dc3bb081bc1c
     * timeStamp : 1573108488
     * sign : 2BB2BF8DCFAA8DB4630E601B397FD7DD
     * out_trade_no : 201911071434475dc3bb07d74b8
     */

    private String partnerId;
    private String prepayId;
    @JSONField(name = "package")
    private String packageX;
    private String nonceStr;
    private String timeStamp;
    private String sign;
    private String out_trade_no;

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getPrepayId() {
        return prepayId;
    }

    public void setPrepayId(String prepayId) {
        this.prepayId = prepayId;
    }

    public String getPackageX() {
        return packageX;
    }

    public void setPackageX(String packageX) {
        this.packageX = packageX;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }
}
