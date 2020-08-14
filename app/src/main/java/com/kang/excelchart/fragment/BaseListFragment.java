package com.kang.excelchart.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.kang.excelchart.R;
import com.kang.excelchart.adapter.ChartAdapter;
import com.kang.excelchart.base.BaseFragment;
import com.kang.excelchart.bean.Tables;
import com.kang.excelchart.bean.Tables_1;
import com.kang.excelchart.config.BaseConfig;
import com.kang.excelchart.config.UserConfig;
import com.kang.excelchart.custom.view.TitleView;
import com.kang.excelchart.utils.HttpUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.vondear.rxtool.RxLogTool;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.http.I;
import cn.bmob.v3.listener.FindListener;

public abstract class BaseListFragment<T> extends BaseFragment {
    public TitleView titleView;
    public List list = new ArrayList<>();
    public ChartAdapter adapter;
    private SmartRefreshLayout refreshLayout;
    private RecyclerView recyclerView;
    private LinearLayout llNoData;
    private int page = 0;
    public int createAt;


    public abstract BmobQuery query();


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

        createAt = UserConfig.getCreateAt(activity);
        adapter = new ChartAdapter(activity, initFrom(), list);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        recyclerView.setAdapter(adapter);

        refreshLayout.autoRefresh();//自动刷新
        //刷新

        refreshLayout.setOnRefreshListener((refreshlayout) -> {
            if (query() != null) {
                BmobQuery query = query().setLimit(1).setSkip(0);
                switch (createAt) {
                    case 0:
                        query.findObjects(new FindListener<Tables>() {
                            @Override
                            public void done(List<Tables> object, BmobException e) {
                                refresh(object, e);
                            }
                        });
                        break;
                    case 1:
                        query.findObjects(new FindListener<Tables_1>() {
                            @Override
                            public void done(List<Tables_1> object, BmobException e) {
                                refresh(object, e);
                            }
                        });
                        break;
                }
            }
        });

        //加载
        refreshLayout.setOnLoadMoreListener((refreshlayout) -> {
            doLoad((object, e) -> {
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

            });
        });
    }

    private <T> void refresh(List<T> object, BmobException e) {
        httpUtils.doHttpResult(e, new HttpUtils.IHttpResult() {
            @Override
            public void success() {
                RxLogTool.i("列表刷新：-----" + object.get(0).toString());
                page = 1;
                list.clear();
                list.addAll(object);

                adapter.notifyDataSetChanged();
                refreshLayout.finishRefresh(0);

                recursiveLoad();

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


    void recursiveLoad() {
        doLoad(new ILoadResult() {
            @Override
            public void done(List object, BmobException e) {
                if (object.size() == 0) {

                } else {
                    page++;
                    RxLogTool.i("列表刷新：-----" + object.get(0).toString());
                    list.addAll(object);
                    adapter.notifyDataSetChanged();
                    recursiveLoad();
                }
            }
        });
    }


    private void doLoad(ILoadResult iLoadResult) {
        if (query() != null) {
            BmobQuery query = query().setLimit(1).setSkip(page);
            switch (createAt) {
                case 0:
                    query.findObjects(new FindListener<Tables>() {
                        @Override
                        public void done(List<Tables> object, BmobException e) {
                            iLoadResult.done(object, e);
                        }
                    });
                    break;
                case 1:
                    query.findObjects(new FindListener<Tables_1>() {
                        @Override
                        public void done(List<Tables_1> object, BmobException e) {
                            iLoadResult.done(object, e);
                        }
                    });
                    break;
            }
        }
    }

    private interface ILoadResult<T> {
        void done(List<T> object, BmobException e);
    }
}
