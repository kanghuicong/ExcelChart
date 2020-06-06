package com.kang.excelchart.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.ethanhua.skeleton.Skeleton;
import com.ethanhua.skeleton.SkeletonScreen;
import com.kang.excelchart.R;
import com.kang.excelchart.base.BaseConfig;
import com.kang.excelchart.bean.BaseBean;
import com.kang.excelchart.http.HttpUtils;
import com.kang.excelchart.http.IHttp;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.vondear.rxtool.RxImageTool;
import com.vondear.rxtool.RxLogTool;

import java.util.ArrayList;
import java.util.List;

/**
 * 类描述：刷新加载封装
 */
public class RefreshUtil<T> implements IHttp {

    private Activity activity;
    private SmartRefreshLayout refreshLayout;
    private RecyclerView.Adapter adapter;
    private TextView tvNoData;
    private IRefresh iRefresh;
    private List<T> list = new ArrayList<>();

    private IDoRefresh iDoRefresh;
    private IDataSuccess iDataSuccess;

    private HttpUtils httpUtils;
    private int page = 1;
    private boolean needLoad = true;
    private boolean isAutoRefresh = true;
    private SkeletonScreen skeletonScreen;

    public RefreshUtil(Activity activity,
                       @NonNull SmartRefreshLayout refreshLayout,
                       TextView tvNoData) {
        this.activity = activity;
        this.refreshLayout = refreshLayout;
        this.tvNoData = tvNoData;
        httpUtils = new HttpUtils(activity);
        init();
    }
    public RefreshUtil(Activity activity,
                       @NonNull SmartRefreshLayout refreshLayout,
                       TextView tvNoData,
                       boolean isAutoRefresh,
                       boolean needLoad) {
        this.activity = activity;
        this.refreshLayout = refreshLayout;
        this.tvNoData = tvNoData;
        this.isAutoRefresh = isAutoRefresh;
        this.needLoad = needLoad;
        httpUtils = new HttpUtils(activity);
        init();
    }
    public RefreshUtil setIRefresh(RecyclerView.Adapter adapter, @NonNull IRefresh iRefresh) {
        this.adapter = adapter;
        this.iRefresh = iRefresh;
        return this;
    }


    public void onDestroy() {
        if (httpUtils != null) httpUtils.detachView();
        activity = null;
    }



    private void init() {
        if (isAutoRefresh)
            refreshLayout.autoRefresh();//自动刷新

        //刷新
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshlayout) {
                if (iDoRefresh != null) iDoRefresh.doRefresh();
                doRefresh();
            }
        });

        //加载，如果不需要加载可以关闭
        if (needLoad)
            refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
                @Override
                public void onLoadMore(@NonNull RefreshLayout refreshlayout) {
                    if (iDoRefresh != null) iDoRefresh.doLoad();
                    doLoad();
                }
            });
        else refreshLayout.setEnableLoadMore(false);

    }

    //刷新
    public void doRefresh() {
        if (iRefresh.refreshRequest() == null) {
            refreshLayout.finishRefresh(0);
            return;
        }
        httpUtils.doRequestWithNoLoad(iRefresh.refreshRequest(), BaseConfig.REFRESH, this);
    }

    //加载
    private void doLoad() {
        if (iRefresh.loadRequest() == null) {
            refreshLayout.finishLoadMore(0);
            return;
        }

        httpUtils.doRequestWithNoLoad(iRefresh.loadRequest(), BaseConfig.LOAD, this);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void onSuccess(BaseBean response, String from) {
        RxLogTool.i("---" + from + "---:" + JSON.toJSONString(response));
        switch (from) {
            case BaseConfig.REFRESH:
                page = 2;
                list.clear();
                list.addAll((List<T>) iRefresh.list(response));
                adapter.notifyDataSetChanged();
                refreshLayout.finishRefresh(0);
                if (list.size() == 0) {
                    if (tvNoData != null) {
                        if (!tvNoData.getText().toString().equals(activity.getString(R.string.no_data)))
                            setTopDrawable(activity, tvNoData, 0, activity.getString(R.string.no_data));
                        tvNoData.setVisibility(View.VISIBLE);
                    }
                    if (iDataSuccess != null) iDataSuccess.refreshNoData();
                } else {
                    if (tvNoData != null) tvNoData.setVisibility(View.GONE);
                    if (iDataSuccess != null) iDataSuccess.refreshWithData();
                }
                if (skeletonScreen != null) skeletonScreen.hide();
                break;
            case BaseConfig.LOAD:
                if (((List<T>) iRefresh.list(response)).size() == 0) {
                    refreshLayout.finishLoadMoreWithNoMoreData();
                    if (iDataSuccess != null) iDataSuccess.loadNoData();
                } else {
                    if (iDataSuccess != null) iDataSuccess.loadWithData();
                    page++;
                    list.addAll((List<T>) iRefresh.list(response));
                    adapter.notifyDataSetChanged();
                    refreshLayout.finishLoadMore(0);
                }
                break;
        }

    }


    @Override
    public void onFailure(Response<BaseBean> response, String from) {
        switch (from) {
            case BaseConfig.REFRESH:
                refreshLayout.finishRefresh(false);
                if (tvNoData != null) {
                    if (!tvNoData.getText().toString().equals(activity.getString(R.string.error_network)))
                        setTopDrawable(activity, tvNoData, 0, activity.getString(R.string.error_network));
                    tvNoData.setVisibility(View.VISIBLE);
                }
                if (skeletonScreen != null) skeletonScreen.hide();
                break;
            case BaseConfig.LOAD:
                refreshLayout.finishLoadMore(false);
                break;
        }
    }


    public RefreshUtil setVerticalManager(RecyclerView recyclerView, int layout) {
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);

        //通过加载XML动画设置文件来创建一个Animation对象；
        Animation animation = AnimationUtils.loadAnimation(activity, R.anim.recycler_left);
        //得到一个LayoutAnimationController对象；
        LayoutAnimationController lac = new LayoutAnimationController(animation);
        //设置控件显示的顺序；
        lac.setOrder(LayoutAnimationController.ORDER_NORMAL);
        //设置控件显示间隔时间；
        lac.setDelay(0.2f);
        //为ListView设置LayoutAnimationController属性；
        recyclerView.setLayoutAnimation(lac);

        if (layout != 0) {
            skeletonScreen = Skeleton.bind(recyclerView)
                    .adapter(adapter)
                    .shimmer(false)//光晕
                    .count(15)
                    .duration(1000)
                    .load(layout)
                    .color(R.color.SkeletonShimmerColor)
                    .show();
        }
        return this;
    }

    //刷新加载成功后回调
    public void setIDoRefresh(IDoRefresh iDoRefresh) {
        this.iDoRefresh = iDoRefresh;
    }

    //开始刷新加载的回调
    public void setIRefreshSuccess(IDataSuccess iDataSuccess) {
        this.iDataSuccess = iDataSuccess;
    }

    public int getPage() {
        return page;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list.clear();
        this.list.addAll(list);
        adapter.notifyDataSetChanged();
        if (list.size() > 0) {
            tvNoData.setVisibility(View.GONE);
        } else {
            tvNoData.setVisibility(View.VISIBLE);
        }

    }

    //无数据设置
    public static void setTopDrawable(Context context, TextView textView, int img, String content) {
        Drawable drawable = context.getResources().getDrawable(img == 0 ? R.mipmap.ic_launcher : img);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        textView.setCompoundDrawables(null, drawable, null, null);
        textView.setCompoundDrawablePadding(RxImageTool.dip2px(10));
        textView.setText(content);
    }
    /*-----------------------------------------------------------------------------------------------------------------------------------------*/

    public interface IRefresh {
        Request refreshRequest();

        Request loadRequest();

        List list(BaseBean response);
    }


    public interface IDataSuccess {
        void refreshNoData();

        void refreshWithData();

        void loadNoData();

        void loadWithData();
    }


    //开始刷新/加载时的回调
    public interface IDoRefresh {
        void doRefresh();

        void doLoad();
    }
}