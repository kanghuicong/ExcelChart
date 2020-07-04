package com.kang.excelchart.config;

import android.graphics.Paint;

/**
 * 类描述：
 */
public class PaintConfig {
    public Paint linePaint;//默认线条画笔
    public Paint selectPaint;//选中线条画笔
    public Paint greenPaint;//拉伸线条画笔

    public static final float lineWidth = 4;

    public PaintConfig() {
        setLinePaint();
        setSelectPaint();
        setGreenPaint();
    }

    //基本边框设置
    public void setLinePaint() {
        // 基本线条画笔
        linePaint = new Paint();
        // 设置颜色
        linePaint.setColor(0xFF8F8F8F);
        // 设置抗锯齿
        linePaint.setAntiAlias(true);
        // 设置线宽
        linePaint.setStrokeWidth(lineWidth);
        // 设置非填充
        linePaint.setStyle(Paint.Style.STROKE);
    }

    private void setGreenPaint() {
        // 基本线条画笔
        greenPaint = new Paint();
        // 设置颜色
        greenPaint.setColor(0xFF02C185);
        // 设置抗锯齿
        greenPaint.setAntiAlias(true);
        // 设置线宽
        greenPaint.setStrokeWidth(lineWidth);
        // 设置非填充
        greenPaint.setStyle(Paint.Style.STROKE);
    }


    //选择边框设置
    public void setSelectPaint() {
        selectPaint = new Paint();
        // 基本线条画笔
        selectPaint = new Paint();
        // 设置颜色
        selectPaint.setColor(0xFF02C185);
        // 设置抗锯齿
        selectPaint.setAntiAlias(true);
        // 设置线宽
        selectPaint.setStrokeWidth(lineWidth);
        // 设置非填充
        selectPaint.setStyle(Paint.Style.STROKE);
    }


}
