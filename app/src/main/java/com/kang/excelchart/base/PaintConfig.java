package com.kang.excelchart.base;

import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextPaint;

/**
 * 类描述：
 */
public class PaintConfig {
    public Paint linePaint;//默认线条画笔
    public Paint selectPaint;//选中线条画笔
    public Paint greenPaint;//拉伸线条画笔
    public TextPaint textPaint;//文字画笔

    public PaintConfig() {
        setLinePaint();
        setSelectPaint();
        setTextPaint();
        setGreenPaint();
    }

    private void setGreenPaint() {
        // 基本线条画笔
        greenPaint = new Paint();
        // 设置颜色
        greenPaint.setColor(Color.GREEN);
        // 设置抗锯齿
        greenPaint.setAntiAlias(true);
        // 设置线宽
        greenPaint.setStrokeWidth(3);
        // 设置非填充
        greenPaint.setStyle(Paint.Style.STROKE);
    }

    //基本边框设置
    public void setLinePaint() {
        // 基本线条画笔
        linePaint = new Paint();
        // 设置颜色
        linePaint.setColor(Color.BLACK);
        // 设置抗锯齿
        linePaint.setAntiAlias(true);
        // 设置线宽
        linePaint.setStrokeWidth(3);
        // 设置非填充
        linePaint.setStyle(Paint.Style.STROKE);
    }

    //选择边框设置
    public void setSelectPaint() {
        selectPaint = new Paint();
        // 基本线条画笔
        selectPaint = new Paint();
        // 设置颜色
        selectPaint.setColor(Color.RED);
        // 设置抗锯齿
        selectPaint.setAntiAlias(true);
        // 设置线宽
        selectPaint.setStrokeWidth(3);
        // 设置非填充
        selectPaint.setStyle(Paint.Style.STROKE);
    }


    public void setTextPaint() {
        textPaint = new TextPaint();
        textPaint.setFakeBoldText(false);
        textPaint.setTextSkewX(0f);
        textPaint.setUnderlineText(false);
        textPaint.setStrikeThruText(false);

        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setStrokeWidth(8);
        textPaint.setTextSize(50);
        textPaint.setTextAlign(Paint.Align.CENTER);
    }

}
