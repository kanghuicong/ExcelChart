package com.kang.excelchart.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kang.excelchart.R;
import com.kang.excelchart.adapter.ChartAdapter;
import com.kang.excelchart.base.BaseFragment;
import com.kang.excelchart.bean.Tables;
import com.kang.excelchart.custom.view.TitleView;
import com.kang.excelchart.utils.HttpUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.vondear.rxtool.RxLogTool;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public abstract class BaseListFragment extends BaseFragment {
    public TitleView titleView;
    public List<Tables> list = new ArrayList<>();
    public ChartAdapter adapter;
    private SmartRefreshLayout refreshLayout;
    private RecyclerView recyclerView;
    private LinearLayout llNoData;
    private int page = 0;

    public abstract BmobQuery<Tables> query();

    public abstract void _init(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    public abstract int initFrom();

    @Override
    protected int initLayout(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.list_fragment;
    }

    @Override
    protected void initView(View view) {
        titleView = (TitleView) view.findViewById(R.id.title_view);
        refreshLayout = (SmartRefreshLayout) view.findViewById(R.id.refreshLayout);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        llNoData = (LinearLayout) view.findViewById(R.id.ll_no_data);

    }

    @Override
    protected void init(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        _init(inflater, container, savedInstanceState);

        adapter = new ChartAdapter(activity, initFrom(), list);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        recyclerView.setAdapter(adapter);

        refreshLayout.autoRefresh();//自动刷新
        //刷新
        refreshLayout.setOnRefreshListener((refreshlayout) -> {
            if (query() != null) {
                query().setLimit(1).setSkip(0)
                        .findObjects(new FindListener<Tables>() {
                            @Override
                            public void done(List<Tables> object, BmobException e) {
                                httpUtils.doHttpResult(e, new HttpUtils.IHttpResult() {
                                    @Override
                                    public void success() {
                                        RxLogTool.i("列表刷新：" + object.size() + "-----" + object.get(0).toString());
                                        page = 1;
                                        list.clear();
                                        list.addAll(object);

                                        adapter.notifyDataSetChanged();
                                        refreshLayout.finishRefresh(0);

                                        if (object.size() == 0) {
                                            llNoData.setVisibility(View.VISIBLE);
                                        } else {
                                            llNoData.setVisibility(View.GONE);
                                        }
                                    }

                                    @Override
                                    public void failure() {
                                        refreshLayout.finishRefresh(false);
                                        llNoData.setVisibility(View.VISIBLE);
                                    }
                                });
                            }
                        });
            }
        });

        //加载
        refreshLayout.setOnLoadMoreListener((refreshlayout) -> {
            if (query() != null)
                query().setLimit(1).setSkip(page)
                        .findObjects(new FindListener<Tables>() {
                            @Override
                            public void done(List<Tables> object, BmobException e) {
                                httpUtils.doHttpResult(e, new HttpUtils.IHttpResult() {
                                    @Override
                                    public void success() {
                                        if (object.size() == 0) {
                                            refreshLayout.finishLoadMoreWithNoMoreData();
                                        } else {
                                            page++;
                                            list.addAll(object);
                                            adapter.notifyDataSetChanged();
                                            refreshLayout.finishLoadMore(0);
                                        }
                                    }

                                    @Override
                                    public void failure() {
                                        refreshLayout.finishLoadMore(false);
                                    }
                                });
                            }
                        });
        });
    }

}
