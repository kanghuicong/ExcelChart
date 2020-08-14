package com.kang.excelchart.bean;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * 类描述：
 * author:kanghuicong
 */
public class ChartInfoBean {
    @JSONField(name = "wa")
    private List<Float> width_list;
    @JSONField(name = "ha")
    private List<Float> height_list;
    @JSONField(name = "h")
    private int height_num;
    @JSONField(name = "w")
    private int width_num;
    @JSONField(name = "m")
    private String chart_list;

    public List<Float> getWidth_list() {
        return width_list;
    }

    public void setWidth_list(List<Float> width_list) {
        this.width_list = width_list;
    }

    public List<Float> getHeight_list() {
        return height_list;
    }

    public void setHeight_list(List<Float> height_list) {
        this.height_list = height_list;
    }

    public int getHeight_num() {
        return height_num;
    }

    public void setHeight_num(int height_num) {
        this.height_num = height_num;
    }

    public int getWidth_num() {
        return width_num;
    }

    public void setWidth_num(int width_num) {
        this.width_num = width_num;
    }

    public String getChart_list() {
        return chart_list;
    }

    public void setChart_list(String chart_list) {
        this.chart_list = chart_list;
    }
}
