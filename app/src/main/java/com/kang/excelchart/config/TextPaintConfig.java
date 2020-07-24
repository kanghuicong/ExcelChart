package com.kang.excelchart.config;

import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.TextPaint;

import com.alibaba.fastjson.JSON;
import com.kang.excelchart.bean.ChartBean;
import com.kang.excelchart.bean.InputTextBean;
import com.kang.excelchart.bean.TextColorBean;
import com.kang.excelchart.utils.TextPaintUtils;
import com.vondear.rxtool.RxImageTool;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.http.I;

/**
 * 类描述：
 */
public class TextPaintConfig {
    //默认背景
    public static final String defaultBackgroundColorStr = "#FFFFFFFF";
    //默认字体颜色
    public static final String defaultTextColorStr = "#FF4A4A4A";

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
        textPaint.setColor(TextPaintUtils.hexToColor(defaultTextColorStr));
        return textPaint;
    }

    public static List<TextColorBean> getTextColorList(String color) {
        List<TextColorBean> list = new ArrayList<>();
        final String[] colorStr = {TextPaintConfig.defaultBackgroundColorStr, "#FFF12C20", "#FF00C0D9", "#FF00D3BD"};
        for (int i = 0; i < colorStr.length; i++) {
            TextColorBean textColorBean = new TextColorBean();
            textColorBean.setColorStr(colorStr[i]);
            textColorBean.setClick(color.equals(colorStr[i]));
            list.add(textColorBean);
        }
        return list;
    }

    public static List<TextColorBean> getBackgroundColorList(String color) {
        List<TextColorBean> list = new ArrayList<>();

        final String[] colorStr = {TextPaintConfig.defaultBackgroundColorStr, "#FFF12C20", "#FF00C0D9", "#FF00D3BD"};
        for (int i = 0; i < colorStr.length; i++) {
            TextColorBean textColorBean = new TextColorBean();
            textColorBean.setColorStr(colorStr[i]);
            textColorBean.setClick(color.equals(colorStr[i]));
            list.add(textColorBean);
        }
        return list;
    }

    public static InputTextBean getInputTextBean(int x, int y) {
        InputTextBean inputTextBean = new InputTextBean(x, y, null, TextPaintConfig.getTextPaint());
        ChartBean chartBean = new ChartBean();
        ChartBean.TdTextAttributeModelBean tdTextAttributeModelBean = new ChartBean.TdTextAttributeModelBean();
        String tdTextAttributeModel = JSON.toJSONString(tdTextAttributeModelBean);
        chartBean.setTdTextAttributeModel(tdTextAttributeModel);

        inputTextBean.setChartBean(chartBean);
        return inputTextBean;
    }
}
