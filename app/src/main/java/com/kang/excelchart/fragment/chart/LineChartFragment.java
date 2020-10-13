package com.kang.excelchart.fragment.chart;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.kang.excelchart.custom.view.ChartView;
import com.kang.excelchart.activity.ChartActivity;
import com.kang.excelchart.R;
import com.kang.excelchart.config.BaseConfig;
import com.kang.excelchart.base.BaseChartFragment;
import com.kang.excelchart.bean.InputTextBean;
import com.kang.excelchart.custom.view.SuperItemView;

/**
 * 类描述：
 */
public class LineChartFragment extends BaseChartFragment {
    private SuperItemView addBottom;
    private SuperItemView addRight;
    private SuperItemView mergeCell;
    private SuperItemView addLeft;
    private SuperItemView addTop;
    private SuperItemView deleteLine;
    private SuperItemView deleteColumn;
    private ChartView chartView;
    @Override
    protected int initLayout(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_line;
    }

    @Override
    protected void initView(View view) {
        addBottom = (SuperItemView) view.findViewById(R.id.add_bottom);
        addRight = (SuperItemView) view.findViewById(R.id.add_right);
        mergeCell = (SuperItemView) view.findViewById(R.id.merge_cell);
        addLeft = (SuperItemView) view.findViewById(R.id.add_left);
        addTop = (SuperItemView) view.findViewById(R.id.add_top);
        deleteLine = (SuperItemView) view.findViewById(R.id.delete_line);
        deleteColumn = (SuperItemView) view.findViewById(R.id.delete_column);

        chartView = ((ChartActivity) getActivity()).chartView;
    }

    @Override
    protected void init(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        addLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chartView.addChart(BaseConfig.ADD_LEFT);
            }
        });
        addRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chartView.addChart(BaseConfig.ADD_RIGHT);
            }
        });
        mergeCell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chartView.mergeCard();
            }
        });

        addTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chartView.addChart(BaseConfig.ADD_TOP);
            }
        });
        addBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chartView.addChart(BaseConfig.ADD_BOTTOM);
            }
        });
        deleteColumn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chartView.deleteChart(BaseConfig.DELETE_COLUMN);
            }
        });
        deleteLine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chartView.deleteChart(BaseConfig.DELETE_LINE);
            }
        });
    }

    @Override
    protected void getInputTextBean(InputTextBean event) {

    }
}
