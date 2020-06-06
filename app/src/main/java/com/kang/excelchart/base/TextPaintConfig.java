package com.kang.excelchart.base;

import android.graphics.Paint;
import android.text.TextPaint;

/**
 * 类描述：
 */
public class TextPaintConfig {

    public enum TextStyle{
         BOLD_STYLE,
         SKEW_X_STYLE,
         UNDERlINE_STYLE,
         STRIKE_THRU_STYLE
    }

    public TextPaintConfig() { }

    public TextPaint getTextPaint() {
        TextPaint textPaint = new TextPaint();
        textPaint.setFakeBoldText(false);//粗体
        textPaint.setTextSkewX(0f);//右斜
        textPaint.setUnderlineText(false);//下划线
        textPaint.setStrikeThruText(false);//删除线

        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setStrokeWidth(8);
        textPaint.setTextSize(50);
        textPaint.setTextAlign(Paint.Align.CENTER);

        return textPaint;
    }


//    public void setTextPaint() {
//        textPaint = new TextPaint();
//        textPaint.setFakeBoldText(isBold);//粗体
//        if (isSkewX) textPaint.setTextSkewX(-0.5f);//右斜
//        else textPaint.setTextSkewX(0f);
//        textPaint.setUnderlineText(isUnder);//下划线
//        textPaint.setStrikeThruText(isStrikeThru);//删除线
//
//        textPaint.setStyle(Paint.Style.FILL);
//        textPaint.setStrokeWidth(8);
//        textPaint.setTextSize(50);
//        textPaint.setTextAlign(Paint.Align.CENTER);
//    }
//
//    //设置粗体
//    public void setFakeBoldText(boolean isBold) {
//        this.isBold = isBold;
//    }
//
//    //设置右斜
//    public void setTextSkewX(boolean isSkewX) {
//        this.isSkewX = isSkewX;
//    }
//
//    //设置下划线
//    public void setUnderlineText(boolean isUnder) {
//        this.isUnder = isUnder;
//    }
//
//    //设置删除线
//    public void setStrikeThru(boolean isStrikeThru) {
//        this.isStrikeThru = isStrikeThru;
//    }
}
