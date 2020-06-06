package com.kang.excelchart.bean;

import android.text.TextPaint;

/**
 * 类描述：
 */
public class InputTextBean {

    private int inputX;
    private int inputY;
    private String content;
    private TextPaint textPaint;

    public InputTextBean(int inputX,int inputY,TextPaint textPaint,String content) {
        this.inputX = inputX;
        this.inputY = inputY;
        this.textPaint = textPaint;
        this.content = content;
    }

    public TextPaint getTextPaint() {
        return textPaint;
    }

    public void setTextPaint(TextPaint textPaint) {
        this.textPaint = textPaint;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
