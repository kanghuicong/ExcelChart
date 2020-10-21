package com.kang.excelchart.config;

import android.app.Activity;
import android.content.Context;

import com.kang.excelchart.base.BaseApplication;
import com.kang.excelchart.bean._User;
import com.vondear.rxtool.RxLogTool;
import com.vondear.rxtool.RxSPTool;
import com.vondear.rxtool.RxTimeTool;

import java.util.List;

/**
 * 类描述：
 */
public class UserConfig {

    public static String HAS_LOGIN = "HAS_LOGIN";
    public static String USER_ACCOUNT = "USER_ACCOUNT";
    public static String USER_PWD = "USER_PWD";
    public static String USER_ID = "USER_ID";
    public static String USER_VIP = "USER_VIP";
    public static String USER_EMAIL = "USER_EMAIL";
    public static String USER_CREATE = "USER_CREATE";


    //登录成功保存用户信息
    public static void setUserLogin(Activity activity, String account, String password, List<_User> object) {
        RxLogTool.d("user_data:" + object.toString());

        //本地保存账号/密码/用户id
        UserConfig.setLogin(activity, true);
        UserConfig.setUserAccount(activity, account);
        UserConfig.setUserPwd(activity, password);
        UserConfig.setUserId(activity, object.get(0).getObjectId());
        UserConfig.setVip(activity, object.get(0).isVip());
        UserConfig.setEmail(activity, object.get(0).getEmail());

        int userCreateAt;
        if (RxTimeTool.string2Milliseconds(object.get(0).getCreatedAt()) / 1000 < 1594300372) {
            userCreateAt = 0;
        } else {
            userCreateAt = 1;
        }
        UserConfig.setCreateAt(activity, userCreateAt);
    }

    //退出清除用户信息
    public static void setUserLogout(Activity activity) {
        UserConfig.setLogin(activity, false);
        UserConfig.setUserId(activity, "");
        UserConfig.setVip(activity, false);
        UserConfig.setEmail(activity, "");
        UserConfig.setCreateAt(activity, 0);
    }

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

    //用户id
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

    //email
    public static void setCreateAt(Context context, int createAt) {
        RxSPTool.putInt(context, UserConfig.USER_CREATE, createAt);
    }

    public static int getCreateAt(Context context) {
        return RxSPTool.getInt(context, UserConfig.USER_CREATE);
    }
}
