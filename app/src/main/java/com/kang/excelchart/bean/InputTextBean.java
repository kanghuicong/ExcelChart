package com.kang.excelchart.bean;

import android.text.TextPaint;

import com.kang.excelchart.config.BaseConfig;
import com.kang.excelchart.config.TextPaintConfig;

/**
 * 类描述：
 */
public class InputTextBean {

    private int inputX;
    private int inputY;
    //    private String content;
    private TextPaint textPaint;
//    private int backgroundColor = TextPaintConfig.defaultBackgroundColor;
    private ChartBean chartBean;

    private BaseConfig.MathType mathType = BaseConfig.MathType.ADDITION;
    private BaseConfig.ScopeType scopeType = BaseConfig.ScopeType.LEFT;
    private int decimal = 2;

    public InputTextBean(int inputX, int inputY, ChartBean chartBean,TextPaint textPaint) {
        this.inputX = inputX;
        this.inputY = inputY;
        this.chartBean = chartBean;
        this.textPaint = textPaint;
//        this.content = content;
    }

    public ChartBean getChartBean() {
        return chartBean;
    }

    public void setChartBean(ChartBean chartBean) {
        this.chartBean = chartBean;
    }

    public BaseConfig.MathType getMathType() {
        return mathType;
    }

    public void setMathType(BaseConfig.MathType mathType) {
        this.mathType = mathType;
    }

    public BaseConfig.ScopeType getScopeType() {
        return scopeType;
    }

    public void setScopeType(BaseConfig.ScopeType scopeType) {
        this.scopeType = scopeType;
    }

    public int getDecimal() {
        return decimal;
    }

    public void setDecimal(int decimal) {
        this.decimal = decimal;
    }


    public int getInputX() {
        return inputX;
    }

    public void setInputX(int inputX) {
        this.inputX = inputX;
    }

    public int getInputY() {
        return inputY;
    }

    public void setInputY(int inputY) {
        this.inputY = inputY;
    }

//    public String getContent() {
//        return content;
//    }
//
//    public void setContent(String content) {
//        this.content = content;
//    }
//
//    public int getBackgroundColor() {
//        return backgroundColor;
//    }
//
//    public void setBackgroundColor(int backgroundColor) {
//        this.backgroundColor = backgroundColor;
//    }
//
    public TextPaint getTextPaint() {
        return textPaint;
    }

    public void setTextPaint(TextPaint textPaint) {
        this.textPaint = textPaint;
    }

    @Override
    public String toString() {
        return "InputTextBean{" +
                "inputX=" + inputX +
                ", inputY=" + inputY +
                ", textPaint=" + textPaint +
                ", chartBean=" + chartBean +
                ", mathType=" + mathType +
                ", scopeType=" + scopeType +
                ", decimal=" + decimal +
                '}';
    }
}
