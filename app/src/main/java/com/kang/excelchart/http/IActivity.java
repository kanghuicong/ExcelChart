package com.kang.excelchart.http;

import android.view.View;

import java.util.List;

/**
 * 类描述：基础类activity接口
 */
public interface IActivity {
    //是否需要加载动画
    boolean needLoad();

    //是否需要禁止某些view的点击状态
    List<View> needStopView();

    //登录过期
    void outLogin();

    //启动加载
    void startLoading();

    //关闭加载
    void stopLoading();
}
