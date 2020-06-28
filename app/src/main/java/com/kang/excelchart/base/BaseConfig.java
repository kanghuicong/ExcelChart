package com.kang.excelchart.base;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;

import com.kang.excelchart.R;
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

    public static final int TOP_BITMAP  = 7;
    public static final int LEFT_BITMAP  = 8;

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

    public static List<Integer> getWidthList() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            list.add(200);
        }
        return list;
    }

    public static List<Integer> getHeightList() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            list.add(80);
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
}
