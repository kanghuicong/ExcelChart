package com.kang.excelchart.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.kang.excelchart.ChartView;
import com.kang.excelchart.MainActivity;
import com.kang.excelchart.R;
import com.kang.excelchart.base.BaseFragment;

/**
 * 类描述：
 */
public class TxtFragment extends BaseFragment implements View.OnClickListener {
    private LinearLayout ll1;
    private TextView tvBold;
    private TextView tvItalic;
    private TextView tvUnderline;
    private LinearLayout ll2;
    private TextView tvLeft;
    private TextView tvCenter;
    private TextView tvRight;
    private LinearLayout ll3;
    private TextView tv8;
    private TextView tv10;
    private TextView tv12;
    private TextView tv14;
    private TextView tv16;
    private TextView tv18;
    private TextView tv20;
    private TextView tv22;
    private LinearLayout ll4;
    private TextView tvStyle1;
    private TextView tvStyle2;
    private TextView tvStyle3;
    private TextView tvStyle4;
    private LinearLayout llColor1;
    private LinearLayout llColor2;
    private LinearLayout llColor3;
    private ChartView chartView;
    @Override
    protected int initLayout(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_txt;
    }

    @Override
    protected void initView(View view) {
        ll1 = (LinearLayout) view.findViewById(R.id.ll_1);
        tvBold = (TextView) view.findViewById(R.id.tv_bold);
        tvItalic = (TextView) view.findViewById(R.id.tv_italic);
        tvUnderline = (TextView) view.findViewById(R.id.tv_underline);
        ll2 = (LinearLayout) view.findViewById(R.id.ll_2);
        tvLeft = (TextView) view.findViewById(R.id.tv_left);
        tvCenter = (TextView) view.findViewById(R.id.tv_center);
        tvRight = (TextView) view.findViewById(R.id.tv_right);
        ll3 = (LinearLayout) view.findViewById(R.id.ll_3);
        tv8 = (TextView) view.findViewById(R.id.tv_8);
        tv10 = (TextView) view.findViewById(R.id.tv_10);
        tv12 = (TextView) view.findViewById(R.id.tv_12);
        tv14 = (TextView) view.findViewById(R.id.tv_14);
        tv16 = (TextView) view.findViewById(R.id.tv_16);
        tv18 = (TextView) view.findViewById(R.id.tv_18);
        tv20 = (TextView) view.findViewById(R.id.tv_20);
        tv22 = (TextView) view.findViewById(R.id.tv_22);
        ll4 = (LinearLayout) view.findViewById(R.id.ll_4);
        tvStyle1 = (TextView) view.findViewById(R.id.tv_style1);
        tvStyle2 = (TextView) view.findViewById(R.id.tv_style2);
        tvStyle3 = (TextView) view.findViewById(R.id.tv_style3);
        tvStyle4 = (TextView) view.findViewById(R.id.tv_style4);
        llColor1 = (LinearLayout) view.findViewById(R.id.ll_color_1);
        llColor2 = (LinearLayout) view.findViewById(R.id.ll_color_2);
        llColor3 = (LinearLayout) view.findViewById(R.id.ll_color_3);

        chartView = ((MainActivity) getActivity()).chartView;
    }

    @Override
    protected void init(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_bold:
                chartView.setFakeBoldText(true);
                break;
            case R.id.tv_italic:

                break;
            case R.id.tv_underline:

                break;
        }
    }
}
