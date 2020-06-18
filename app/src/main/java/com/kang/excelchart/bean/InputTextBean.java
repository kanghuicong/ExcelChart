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
    private int backgroundColor;

    public InputTextBean(int inputX,int inputY,TextPaint textPaint,int backgroundColor,String content) {
        this.inputX = inputX;
        this.inputY = inputY;
        this.textPaint = textPaint;
        this.content = content;
        this.backgroundColor = backgroundColor;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
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
