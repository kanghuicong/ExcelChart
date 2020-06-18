package com.kang.excelchart.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.widget.TextView;

/**
 * 类描述：设置字体样式
 */
public class FontsUtils {

    private static AssetManager mgr;

    public static void init(Context context) {
        if (mgr == null) mgr = context.getAssets();
    }

    public static void setBlackFont(TextView tv) {
        Typeface tf = Typeface.createFromAsset(mgr, "fonts/Montserrat-Black.otf");
        tv.setTypeface(tf);
    }

    public static void setBoldFont(TextView tv) {
        Typeface tf = Typeface.createFromAsset(mgr, "fonts/Montserrat-Bold.otf");
        tv.setTypeface(tf);
    }

    public static void setMediumFont(TextView tv) {
        Typeface tf = Typeface.createFromAsset(mgr, "fonts/Montserrat-Medium.otf");
        tv.setTypeface(tf);
    }
    public static void setRegularFont(TextView tv) {
        Typeface tf = Typeface.createFromAsset(mgr, "fonts/Montserrat-Regular.otf");
        tv.setTypeface(tf);
    }

    public static void setSemiBoldFont(TextView tv) {
        Typeface tf = Typeface.createFromAsset(mgr, "fonts/Montserrat-SemiBold.otf");
        tv.setTypeface(tf);
    }

    public static void setLightFont(TextView tv) {
        Typeface tf = Typeface.createFromAsset(mgr, "fonts/Montserrat-Light.otf");
        tv.setTypeface(tf);
    }


}
