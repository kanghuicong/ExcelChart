package com.kang.excelchart.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kang.excelchart.R;
import com.kang.excelchart.adapter.ColorAdapter;
import com.kang.excelchart.base.BaseConfig;
import com.kang.excelchart.base.BaseFragment;
import com.kang.excelchart.base.TextPaintConfig;
import com.kang.excelchart.bean.InputTextBean;
import com.kang.excelchart.bean.TextColorBean;

import java.util.List;

/**
 * 类描述：
 */
public class ColorFragment extends BaseFragment {

    private RecyclerView rvColor;
    private List<TextColorBean> colorList;
    public ColorAdapter colorAdapter;

    @Override
    protected int initLayout(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_color;
    }

    @Override
    protected void initView(View view) {
        rvColor = view.findViewById(R.id.rv_color);
    }

    @Override
    protected void init(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        colorList = TextPaintConfig.getBackgroundColorList(TextPaintConfig.defaultBackgroundColor);
        colorAdapter = new ColorAdapter(activity, colorList, new ColorAdapter.ISelectColor() {
            @Override
            public void select(TextColorBean textColorBean) {
                chartView.setBackGroundColor(textColorBean.getColor());
            }
        });

        GridLayoutManager gridLayoutManager = new GridLayoutManager(activity, BaseConfig.getCount(activity));
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvColor.setLayoutManager(gridLayoutManager);
        rvColor.setAdapter(colorAdapter);
    }

    @Override
    protected void getInputTextBean(InputTextBean event) {

    }
}
