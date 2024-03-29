package com.kang.excelchart.config;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.kang.excelchart.R;
import com.kang.excelchart.bean.ChartBean;
import com.kang.excelchart.bean.InputTextBean;
import com.kang.excelchart.bean.Tables;
import com.kang.excelchart.bean.Tables_1;
import com.vondear.rxtool.RxDeviceTool;

import java.util.ArrayList;
import java.util.List;

/**
 * 类描述：
 */
public class BaseConfig {
    public static final String REFRESH = "REFRESH";
    public static final String LOAD = "LOAD";

    public static final int ADD_LEFT = 1;
    public static final int ADD_RIGHT = 2;
    public static final int ADD_TOP = 3;
    public static final int ADD_BOTTOM = 4;
    //删除行
    public static final int DELETE_LINE = 5;
    //删除列
    public static final int DELETE_COLUMN = 6;

    //拉伸单元格
    public static final int TOP_BITMAP = 7;
    public static final int LEFT_BITMAP = 8;
    //扩充单元格的圆
    public static final int TOP_CIRCLE = 9;
    public static final int BOTTOM_CIRCLE = 10;

    public enum MathType {
        ADDITION,
        SUBTRACTION,
        MULTIPLY,
        DIVIDE,
        AVERAGE,
        MAX,
        MIN,
    }

    public enum ScopeType {
        LEFT,
        TOP,
        RIGHT,
        BOTTOM,
        UP_LEFT,
        ALL
    }


    public static List<Float> getWidthList() {
        List<Float> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            list.add(90f);
        }
        return list;
    }

    public static List<Float> getHeightList() {
        List<Float> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            list.add(40f);
        }
        return list;
    }

    public static int getCount(Context context) {
        final View v = View.inflate(context, R.layout.item_txt_color, null);
        //制定测量规则 参数表示size + mode
        int width = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int height = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        //调用measure方法之后就可以获取宽高
        v.measure(width, height);
        int count = RxDeviceTool.getScreenWidth(context) / v.getMeasuredWidth();

        return count;
    }

    public static int[] getArr(String str){
        String arrStr = str.substring(1,str.length()-1);
        String[] splitArr= arrStr.split(",");
        int[] aa={Integer.valueOf(splitArr[0].trim()),Integer.valueOf(splitArr[1].trim())};
        return aa;
    }

    public static String getTableName(Context context) {
        switch (UserConfig.getCreateAt(context)) {
            case 1:
                return "tables_1";
            case 0:
            default:
                return "tables";
        }
    }

    public static <T> Tables getTableClass(Context context, T table) {
        if (table == null) {
            if (UserConfig.getCreateAt(context) == 0) {
                return new Tables();
            } else {
                return new Tables_1();
            }
        } else {
            if (UserConfig.getCreateAt(context) == 0) {
                return (Tables) table;
            } else {
                return (Tables_1) table;
            }
        }
    }

    public static List<ChartBean> getChartList(List<InputTextBean> list) {
        List<ChartBean> chartBeanList = new ArrayList<>();
        for (InputTextBean inputTextBean : list) {
            if(inputTextBean==null)chartBeanList.add(null);
            else {
                ChartBean chartBean = inputTextBean.getChartBean();
                chartBeanList.add(chartBean);
            }
        }
        return chartBeanList;

    }
}
