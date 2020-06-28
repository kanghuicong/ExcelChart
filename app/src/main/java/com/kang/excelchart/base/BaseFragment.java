package com.kang.excelchart.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.kang.excelchart.custom.view.ChartView;
import com.kang.excelchart.activity.ChartActivity;
import com.kang.excelchart.bean.InputTextBean;
import com.kang.excelchart.custom.view.KeyBackEditText;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * 类描述：基础fragment
 */
public abstract class BaseFragment extends Fragment {

    protected BaseActivity activity;
    private View view;
    public ChartView chartView;
    public KeyBackEditText etContent;;

    protected abstract int initLayout(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState);

    protected abstract void initView(View view);

    protected abstract void init(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    protected abstract void getInputTextBean(InputTextBean event);

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = View.inflate(activity, initLayout(inflater, container, savedInstanceState), null);
            initView(view);

            EventBus.getDefault().register(this);

            chartView = ((ChartActivity) activity).chartView;
            etContent = ((ChartActivity) activity).etContent;
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

        EventBus.getDefault().unregister(this);

    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void InputTextBeanEvent(InputTextBean event) {
        getInputTextBean(event);
    }


}
