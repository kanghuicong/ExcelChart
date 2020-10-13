package com.kang.excelchart.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.os.LocaleList;
import android.os.StrictMode;
import android.util.DisplayMetrics;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.multidex.MultiDexApplication;

import cn.bmob.v3.Bmob;

import com.bytedance.sdk.openadsdk.TTAdConfig;
import com.bytedance.sdk.openadsdk.TTAdConstant;
import com.bytedance.sdk.openadsdk.TTAdSdk;
import com.kang.excelchart.R;
import com.kang.excelchart.config.UserConfig;
import com.kang.excelchart.utils.TTAdManagerHolder;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.vondear.rxtool.RxTool;

import java.util.Locale;

/**
 * 类描述：基础BaseApplication
 */
public class BaseApplication extends MultiDexApplication implements Application.ActivityLifecycleCallbacks {

    private boolean isRunInBackground;
    private int appCount;
    public static BaseApplication application;

    public static String LANGUAGE = "LANGUAGE";
    //英语
    public static final String EN = "en";
    //简体中文
    public static final String ZH = "zh";
    //繁体中文
    public static final String TW = "tw";
    //韩文
    public static final String KO = "ko";
    //泰文
    public static final String TH = "th";
    //日文
    public static final String JA = "ja";
    //阿拉伯文
    public static final String AR = "ar";
    //德文
    public static final String DE = "de";


    public static BaseApplication getApplication() {
        return application;
    }

    static {//使用static代码段可以防止内存泄漏

        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @Override
            @NonNull
            public RefreshHeader createRefreshHeader(@NonNull Context context, @NonNull RefreshLayout layout) {
//                layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);//全局设置主题颜色
//                return new MaterialHeader(context);//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
                return new ClassicsHeader(context);
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @Override
            @NonNull
            public RefreshFooter createRefreshFooter(@NonNull Context context, @NonNull RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                return new ClassicsFooter(context).setDrawableSize(20);
            }
        });

    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        RxTool.init(this);

        initLanguage();

        //高版本手机文件路径file：//----->content：//
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            StrictMode.VmPolicy.Builder builderX = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builderX.build());
        }

        Bmob.initialize(this, "80c24431e79606ad4cac39e4b50d0ec9");


        TTAdManagerHolder.init(this);

    }


    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
    }

    @Override
    public void onActivityStarted(Activity activity) {
        appCount++;
        if (isRunInBackground) {
            isRunInBackground = false;
            //防止应用程序切换到后台，然后通过设置设置语言，最后再将应用程序从后台切换回前台的情况
            initLanguage();
        }
    }

    @Override
    public void onActivityResumed(Activity activity) {
    }

    @Override
    public void onActivityPaused(Activity activity) {
    }

    @Override
    public void onActivityStopped(Activity activity) {
        appCount--;
        if (appCount == 0) {
            isRunInBackground = true;
        }
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
    }

    private void initLanguage() {
        if (UserConfig.getLanguage(application).equals("")) {
            setLanguage(getApplication());
        } else {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
                //Application这种方式适用于8.0之前(不包括8.0)的版本
                setLanguage(getApplication());
            }
        }
    }

    /**
     * 如果是第一次启动系统，保存系统语言设置
     */
    public static Context setLanguage(Context activity) {
        String language = UserConfig.getLanguage(activity);
        //第一次启动app
        if (language.equals("") || language == null) {
            //获取系统语言
            String localeSetting = Locale.getDefault().getLanguage();
            //中文
            if (localeSetting.equals(ZH)) {
                //繁体中文
                if (Locale.getDefault().getCountry().equals(TW)) {
                    UserConfig.setLanguage(activity, TW);
                }
                //简体中文
                else {
                    UserConfig.setLanguage(activity, ZH);
                }
            } else if (checkLanguage(localeSetting)) {
                UserConfig.setLanguage(activity, localeSetting);
            } else {//没有我们所翻译的语言，设置为简体中文
                UserConfig.setLanguage(activity, ZH);
            }

            return activity;
        } else {
            Locale locale = returnLocale(language);

            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.N) {
                return createConfiguration(activity, locale);
            } else {
                updateConfiguration(activity, locale);
                return activity;
            }
        }
    }

    public static Locale returnLocale(String language) {
        Locale locale;
        //这里的配置跟strings文件括号里的内容有关，其他语言通常不会像汉语一样区分那么细
        //所以直接new Locale(language)其实也是可以的
        if (language.equals(ZH)) {
            locale = Locale.SIMPLIFIED_CHINESE;
        } else if (language.equals(TW)) {
            locale = Locale.TRADITIONAL_CHINESE;
        } else if (checkLanguage(language)) {
            locale = new Locale(language);
        } else {
            locale = Locale.SIMPLIFIED_CHINESE;
        }
        return locale;
    }

    private static boolean checkLanguage(String language) {
        return language.equals(KO) ||
                language.equals(TH) ||
                language.equals(JA) ||
                language.equals(AR) ||
                language.equals(EN) ||
                language.equals(DE);
    }

    /**
     * 7.0及以上的修改app语言的方法
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    private static Context createConfiguration(Context context, Locale locale) {
        Resources resources = context.getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.setLocale(locale);
        LocaleList localeList = new LocaleList(locale);
        LocaleList.setDefault(localeList);
        configuration.setLocales(localeList);
        return context.createConfigurationContext(configuration);
    }

    /**
     * 7.0以下的修改app语言的方法
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private static void updateConfiguration(Context context, Locale locale) {
        Resources resources = context.getResources();
        Locale.setDefault(locale);
        Configuration configuration = resources.getConfiguration();
        configuration.setLocale(locale);
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        resources.updateConfiguration(configuration, displayMetrics);
    }
}
