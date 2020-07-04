package com.kang.excelchart.activity.login;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

import androidx.annotation.Nullable;

import com.gyf.immersionbar.ImmersionBar;
import com.kang.excelchart.MainActivity;
import com.kang.excelchart.R;
import com.kang.excelchart.config.UserConfig;
import com.kang.excelchart.utils.LanguageUtils;
import com.vondear.rxtool.RxActivityTool;

/**
 * 类描述：启动页
 */
public class SplashActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImmersionBar.with(this).transparentStatusBar().statusBarDarkFont(true).init();

        View view = View.inflate(SplashActivity.this, R.layout.splash_activity, null);

        /*7.0后台杀死重新启动，无法通过BaseApplication的配置设置语音，蜜汁bug*/
        //7.0独有bug，设置成功后，重新启动APP设置无效，故再次设置一遍
        String language = UserConfig.getLanguage(this);
        if (!"".equals(language)) {
            if (Build.VERSION.SDK_INT == Build.VERSION_CODES.N) {
                LanguageUtils.languageVERSION_N(this, language);
            }
        }

        //可设置动画（目前无动画）
        AlphaAnimation animation = new AlphaAnimation(1.0f, 1.0f);
        animation.setDuration(1000);
        animation.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                setTheme(R.style.AppTheme);
                //本地未保存用户数据，跳转到登陆页面
                if (!UserConfig.hasLogin(SplashActivity.this) ||
                        UserConfig.getUserAccount(SplashActivity.this).isEmpty()) {
                    RxActivityTool.skipActivity(SplashActivity.this, LoginActivity.class);
                } else {
                    //进入首页
                    RxActivityTool.skipActivity(SplashActivity.this, MainActivity.class);
                }

                finish();
            }
        });
        view.setAnimation(animation);
        setContentView(view);
    }

}
