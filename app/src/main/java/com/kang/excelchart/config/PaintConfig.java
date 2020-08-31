package com.kang.excelchart.config;

import android.graphics.Paint;

import com.kang.excelchart.utils.TextPaintUtils;

/**
 * 类描述：
 */
public class PaintConfig {
    public Paint linePaint;//默认线条画笔
    public static final String defaultLineColor = "#ffC0C0C0";

    public Paint selectPaint;//选中线条画笔
    public Paint greenPaint;//拉伸线条画笔
    public Paint circlePaint;//多选单元格时圆的画笔

    public static final float lineWidth = 3;

    public PaintConfig() {
        setLinePaint();
        setSelectPaint();
        setGreenPaint();
        setCirclePaint();
    }

    //基本边框设置
    public void setLinePaint() {
        // 基本线条画笔
        linePaint = new Paint();
        // 设置颜色
        linePaint.setColor(TextPaintUtils.hexToColor(defaultLineColor));
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


    public static final int circleRadius = 30;
    public void setCirclePaint(){
        circlePaint = new Paint();
        // 设置颜色
        circlePaint.setColor(0xFFFFFFFF);
        // 设置抗锯齿
        selectPaint.setAntiAlias(true);
        // 设置为填充
        selectPaint.setStyle(Paint.Style.FILL);
    }
}
