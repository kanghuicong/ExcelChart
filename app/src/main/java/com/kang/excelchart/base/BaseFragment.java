package com.kang.excelchart.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.kang.excelchart.http.HttpUtils;
import com.kang.excelchart.utils.RefreshUtil;

/**
 * 类描述：基础fragment
 */
public abstract class BaseFragment extends Fragment {

    protected BaseActivity activity;
    private View view;
    public RefreshUtil refreshUtil;
    public HttpUtils httpUtils;

    protected abstract int initLayout(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState);

    protected abstract void initView(View view);

    protected abstract void init(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = View.inflate(activity, initLayout(inflater, container, savedInstanceState), null);
            initView(view);

            httpUtils = new HttpUtils(activity);
            init(inflater,container,savedInstanceState);
        } else {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null) {
                parent.removeView(view);
            }
        }
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.activity = (BaseActivity) context;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(refreshUtil!=null)refreshUtil.onDestroy();
        if (httpUtils != null) httpUtils.detachView();

    }

}
