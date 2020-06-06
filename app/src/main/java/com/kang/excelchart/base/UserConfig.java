package com.kang.excelchart.base;

import android.content.Context;

import com.vondear.rxtool.RxSPTool;

/**
 * 类描述：
 */
public class UserConfig {


    //语言
    public static void setLanguage(Context context, String Language) {
        RxSPTool.putString(context, BaseApplication.LANGUAGE, Language);
    }

    public static String getLanguage(Context context) {
        return RxSPTool.getString(context, BaseApplication.LANGUAGE);
    }
}
