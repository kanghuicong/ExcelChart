package com.kang.excelchart.config;

import android.content.Context;

import com.kang.excelchart.base.BaseApplication;
import com.vondear.rxtool.RxSPTool;

/**
 * 类描述：
 */
public class UserConfig {

    public static String HAS_LOGIN = "HAS_LOGIN";
    public static String USER_ACCOUNT = "USER_ACCOUNT";
    public static String USER_PWD = "USER_PWD";
    public static String USER_ID= "USER_ID";
    public static String USER_VIP= "USER_VIP";
    public static String USER_EMAIL= "USER_EMAIL";

    //语言
    public static void setLanguage(Context context, String Language) {
        RxSPTool.putString(context, BaseApplication.LANGUAGE, Language);
    }

    public static String getLanguage(Context context) {
        return RxSPTool.getString(context, BaseApplication.LANGUAGE);
    }

    //是否登录
    public static void setLogin(Context context, boolean hasLogin) {
        RxSPTool.putBoolean(context, UserConfig.HAS_LOGIN, hasLogin);
    }

    public static boolean hasLogin(Context context) {
        return RxSPTool.getBoolean(context, UserConfig.HAS_LOGIN);
    }

    //用户账号
    public static void setUserAccount(Context context, String account) {
        RxSPTool.putString(context, UserConfig.USER_ACCOUNT, account);
    }

    public static String getUserAccount(Context context) {
        return RxSPTool.getString(context, UserConfig.USER_ACCOUNT);
    }

    //用户密码
    public static void setUserPwd(Context context, String pwd) {
        RxSPTool.putString(context, UserConfig.USER_PWD, pwd);
    }

    public static String getUserPwd(Context context) {
        return RxSPTool.getString(context, UserConfig.USER_PWD);
    }
    //用户账号
    public static void setUserId(Context context, String id) {
        RxSPTool.putString(context, UserConfig.USER_ID, id);
    }

    public static String getUserId(Context context) {
        return RxSPTool.getString(context, UserConfig.USER_ID);
    }

    //VIP
    public static void setVip(Context context, boolean isVip) {
        RxSPTool.putBoolean(context, UserConfig.USER_VIP, isVip);
    }

    public static boolean isVip(Context context) {
        return RxSPTool.getBoolean(context, UserConfig.USER_VIP);
    }

    //email
    public static void setEmail(Context context, String email) {
        RxSPTool.putString(context, UserConfig.USER_EMAIL, email);
    }

    public static String getEmail(Context context) {
        return RxSPTool.getString(context, UserConfig.USER_EMAIL);
    }
}
