package com.kang.excelchart.bean;

public class AliPayResultEvent {

    public String code;
    public String order_id;

    public AliPayResultEvent(String code, String order_id) {

        this.code = code;
        this.order_id = order_id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }
}
