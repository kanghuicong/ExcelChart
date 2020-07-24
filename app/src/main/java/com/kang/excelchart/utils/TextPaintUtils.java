package com.kang.excelchart.utils;

import android.graphics.Color;
import android.graphics.Paint;

import java.util.regex.Pattern;

/**
 * 类描述：
 * author:kanghuicong
 */
public class TextPaintUtils {

    public static int hexToColor(String color) {
        // #ff00CCFF
        String reg = "#[a-f0-9A-F]{8}";
        if (!Pattern.matches(reg, color)) {
            color = "#00ffffff";
        }
        return Color.parseColor(color);
    }
    public static String colorToHex(int color) {
        return String.format("#%06X", color);
    }

    public static Paint.Align getAlign(int align) {
        switch (align) {
            case 1:
                return Paint.Align.LEFT;
            case 3:
                return Paint.Align.RIGHT;
            default:
                return Paint.Align.CENTER;
        }
    }

    public static Paint.Style getFill(boolean isFill) {
        if (isFill) return Paint.Style.FILL;
        else return Paint.Style.STROKE;
    }

    public static float getTextSkewX(boolean isTilt) {
        if (isTilt) return -0.5f;
        else return 0.0f;
    }
}
