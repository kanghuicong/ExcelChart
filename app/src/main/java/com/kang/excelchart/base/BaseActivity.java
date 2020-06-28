package com.kang.excelchart.base;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;

import com.gyf.immersionbar.ImmersionBar;
import com.kang.excelchart.custom.view.LoadView;
import com.kang.excelchart.http.IActivity;
import com.vondear.rxtool.RxActivityTool;

/**
 * 类描述：基础activity
 */
public abstract class BaseActivity extends FragmentActivity implements IActivity {

    public abstract int initLayout();

    public abstract void initView();

    public abstract void init(Bundle savedInstanceState);

    public Activity activity;
    public Context context;

    //加载动画
    private LoadView loadView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        RxActivityTool.addActivity(this);
        initStatusBarStyle();
        activity = this;
        context = this;
        setContentView(initLayout());

        //是否需要加载动画
        if (needLoad() || needStopView() != null) addLoad();
        initView();
        init(savedInstanceState);

    }

    //是否需要加载动画
    private void addLoad() {
        //动态生成(根布局需要为ConstraintLayout)
        View view = ((ViewGroup) findViewById(android.R.id.content)).getChildAt(0);
        if (view instanceof ConstraintLayout) {
            loadView = new LoadView(this);
            loadView.setVisibility(View.GONE);
            ConstraintLayout layout = (ConstraintLayout) view;
            layout.addView(loadView);
            ConstraintLayout.LayoutParams lp = (ConstraintLayout.LayoutParams) loadView.getLayoutParams();
            lp.startToStart = layout.getId();
            lp.endToEnd = layout.getId();
            lp.topToTop = layout.getId();
            lp.bottomToBottom = layout.getId();
            loadView.setLayoutParams(lp);

        }
    }

    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        if (res.getConfiguration().fontScale != 1) {//非默认值
            Configuration newConfig = new Configuration();
            newConfig.fontScale = 1;
            res.updateConfiguration(newConfig, res.getDisplayMetrics());
        }
        return res;
    }

    //设置app字体不随系统字体的变化而变化
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        if (newConfig.fontScale != 1)//非默认值
            getResources();
        super.onConfigurationChanged(newConfig);
    }

    //状态栏浸入式
    public void initStatusBarStyle() {
        ImmersionBar.with(this).transparentStatusBar().statusBarDarkFont(true).init();
    }

    //语言切换
    @Override
    protected void attachBaseContext(Context newBase) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            super.attachBaseContext(newBase);
        } else {
            super.attachBaseContext(BaseApplication.setLanguage(newBase));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        RxActivityTool.finishActivity(this);
    }


//   //点击空白处关闭键盘
//    @Override
//    public boolean dispatchTouchEvent(MotionEvent event) {
//        if (event.getAction() == MotionEvent.ACTION_DOWN &&
//                getCurrentFocus() != null &&
//                getCurrentFocus().getWindowToken() != null) {
//
//            View v = getCurrentFocus();
//            if (isShouldHideKeyboard(v, event)) {
//                if (v.getWindowToken() != null) {
//                    InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
//                    inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
//                }
//            }
//        }
//        return super.dispatchTouchEvent(event);
//    }
//
//    //根据EditText所在坐标和用户点击的坐标相对比，来判断是否隐藏键盘，因为当用户点击EditText时则不能隐藏
//    private boolean isShouldHideKeyboard(View v, MotionEvent event) {
//        if (v != null && (v instanceof EditText)) {
//            int[] l = {0, 0};
//            v.getLocationOnScreen(l);
//            int left = l[0],
//                    top = l[1],
//                    bottom = top + v.getHeight(),
//                    right = left + v.getWidth();
//            if (event.getRawX() > left && event.getRawX() < right
//                    && event.getRawY() > top && event.getRawY() < bottom) {
//                // 点击EditText的事件，忽略它。
//                return false;
//            } else {
//                return true;
//            }
//        }
//        // 如果焦点不是EditText则忽略，这个发生在视图刚绘制完，第一个焦点不在EditText上，和用户用轨迹球选择其他的焦点
//        return false;
//    }

    //默认需要加载动画
    @Override
    public boolean needLoad() {
        return true;
    }

    @Override
    public void outLogin() {

    }

    //启动加载
    @Override
    public void startLoading() {
        if (loadView != null && !loadView.isStart()) {
            loadView.start();
            if (needStopView() != null) {
                for (View view : needStopView()) {
                    if (view != null) view.setClickable(false);
                }
            }
        }
    }

    //关闭加载
    @Override
    public void stopLoading() {
        if (loadView != null && loadView.isStart()) {
            loadView.stop();
            if (needStopView() != null) {
                for (View view : needStopView()) {
                    if (view != null) view.setClickable(true);
                }
            }
        }
    }

}
