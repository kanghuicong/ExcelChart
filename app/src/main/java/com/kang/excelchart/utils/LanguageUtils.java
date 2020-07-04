package com.kang.excelchart.utils;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.util.DisplayMetrics;

import com.kang.excelchart.MainActivity;
import com.kang.excelchart.base.BaseApplication;
import com.kang.excelchart.config.UserConfig;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.vondear.rxtool.RxActivityTool;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * 1.首先8.0区分context.getResources()
 * 2.其次7.0区分Android7.0及以前版本，Configuration中的语言相当于是App的全局设置。
 *   Android7.0及以后版本，使用了LocaleList。Configuration中的语言设置可能获取的不同，而是生效于各自的Context。也就是语言需要植入到Context中，每个Context都植入一遍。
 * 3.包名命名，zh-rHk-->7.0以上用Locale.SIMPLIFIED_CHINESE可以识别，7.0以下无法识别;zh-rTW都可以识别
 * 4.启动页，paypal支付，更新语言接口，选择国家码,语言切换
*/
public class LanguageUtils {

    public static void switchLang(Context context, String language) {
        if (language.equals("".equals(UserConfig.getLanguage(context)) ? BaseApplication.EN : UserConfig.getLanguage(context))) {
            return;
        }

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.N) {
            //不需要做什么因为7.0以上的设置在activity初始化时会完成语言适配
        } else {
            languageVERSION_N(context, language);
        }

        UserConfig.setLanguage(context, language);

        RxActivityTool.skipActivityAndFinishAll(context, MainActivity.class);

    }

    /*7.0设置语言方法*/
    public static void languageVERSION_N(Context context, String language) {
        Resources resources = context.getResources();
        Configuration config = resources.getConfiguration();
        config.locale = BaseApplication.returnLocale(language);
        DisplayMetrics dm = resources.getDisplayMetrics();
        resources.updateConfiguration(config, dm);

    }

}
