package com.kang.excelchart.base;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.TextPaint;

import androidx.core.content.ContextCompat;

import com.kang.excelchart.R;
import com.kang.excelchart.bean.TextColorBean;
import com.vondear.rxtool.RxImageTool;

import java.util.ArrayList;
import java.util.List;

/**
 * 类描述：
 */
public class TextPaintConfig {
    //默认背景
    public static final int defaultBackgroundColor = 0xFFFFFFFF;
    //默认字体颜色
    public static final int defaultTextColor = 0xFF4A4A4A;

    public static TextPaint getTextPaint() {
        TextPaint textPaint = new TextPaint();
        //粗体
        textPaint.setFakeBoldText(false);
        //右斜
        textPaint.setTextSkewX(0f);
        //下划线
        textPaint.setUnderlineText(false);
        //删除线
        textPaint.setStrikeThruText(false);
        //常规字体
        textPaint.setTypeface(Typeface.DEFAULT);

        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setStrokeWidth(8);
        textPaint.setTextSize(RxImageTool.sp2px(14));
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setColor(defaultTextColor);
        return textPaint;
    }

    public static List<TextColorBean> getTextColorList(int color) {
        List<TextColorBean> list = new ArrayList<>();
        final int[] colors = {defaultTextColor, 0xFFF12C20, 0xFF00C0D9, 0xFF00D3BD};
        for (int i = 0; i < colors.length; i++) {
            TextColorBean textColorBean = new TextColorBean();
            textColorBean.setColor(colors[i]);
            textColorBean.setClick(color == colors[i]);
            list.add(textColorBean);
        }
        return list;
    }

    public static List<TextColorBean> getBackgroundColorList(int color) {
        List<TextColorBean> list = new ArrayList<>();
        final int[] colors = {TextPaintConfig.defaultBackgroundColor, 0xFFF12C20, 0xFF00C0D9, 0xFF00D3BD};
        for (int i = 0; i < colors.length; i++) {
            TextColorBean textColorBean = new TextColorBean();
            textColorBean.setColor(colors[i]);
            textColorBean.setClick(color == colors[i]);
            list.add(textColorBean);
        }
        return list;
    }
}
