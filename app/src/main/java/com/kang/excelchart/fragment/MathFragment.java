package com.kang.excelchart.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.kang.excelchart.R;
import com.kang.excelchart.base.BaseFragment;

/**
 * 类描述：计算页面
 */
public class MathFragment extends BaseFragment implements View.OnClickListener {
    private TextView tvAdd;
    private TextView tvSub;
    private TextView tvMultiply;
    private TextView tvDivide;
    private TextView tvAverage;
    private TextView tvMax;
    private TextView tvMin;
    private TextView tvLeft;
    private TextView tvTop;
    private TextView tvBottom;
    private TextView tvRight;
    private TextView tvUpLeft;
    private TextView tvAll;
    private TextView tvInteger;
    private TextView tvOne;
    private TextView tvTwo;
    private TextView tvThree;
    private TextView tvFour;
    private TextView tvFive;
    private TextView tvSix;
    private TextView tvNoPartition;
    private TextView tvPartition;
    private Button btApply;

    @Override
    protected int initLayout(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_math;
    }

    @Override
    protected void initView(View view) {

        tvAdd = (TextView) view.findViewById(R.id.tv_add);
        tvSub = (TextView) view.findViewById(R.id.tv_sub);
        tvMultiply = (TextView) view.findViewById(R.id.tv_multiply);
        tvDivide = (TextView) view.findViewById(R.id.tv_divide);
        tvAverage = (TextView) view.findViewById(R.id.tv_average);
        tvMax = (TextView) view.findViewById(R.id.tv_max);
        tvMin = (TextView) view.findViewById(R.id.tv_min);
        tvLeft = (TextView) view.findViewById(R.id.tv_left);
        tvTop = (TextView) view.findViewById(R.id.tv_top);
        tvBottom = (TextView) view.findViewById(R.id.tv_bottom);
        tvRight = (TextView) view.findViewById(R.id.tv_right);
        tvUpLeft = (TextView) view.findViewById(R.id.tv_up_left);
        tvAll = (TextView) view.findViewById(R.id.tv_all);
        tvInteger = (TextView) view.findViewById(R.id.tv_integer);
        tvOne = (TextView) view.findViewById(R.id.tv_one);
        tvTwo = (TextView) view.findViewById(R.id.tv_two);
        tvThree = (TextView) view.findViewById(R.id.tv_three);
        tvFour = (TextView) view.findViewById(R.id.tv_four);
        tvFive = (TextView) view.findViewById(R.id.tv_five);
        tvSix = (TextView) view.findViewById(R.id.tv_six);
        tvNoPartition = (TextView) view.findViewById(R.id.tv_no_partition);
        tvPartition = (TextView) view.findViewById(R.id.tv_partition);
        btApply = (Button) view.findViewById(R.id.bt_apply);

    }

    @Override
    protected void init(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_add:
                break;
            case R.id.tv_sub:
                break;
            case R.id.tv_multiply:
                break;
            case R.id.tv_divide:
                break;
            case R.id.tv_average:
                break;
            case R.id.tv_max:
                break;
            case R.id.tv_min:
                break;
            case R.id.tv_left:
                break;
            case R.id.tv_top:
                break;
            case R.id.tv_bottom:
                break;
            case R.id.tv_right:
                break;
            case R.id.tv_up_left:
                break;
            case R.id.tv_all:
                break;
            case R.id.tv_integer:
                break;
            case R.id.tv_one:
                break;
            case R.id.tv_two:
                break;
            case R.id.tv_three:
                break;
            case R.id.tv_four:
                break;
            case R.id.tv_five:
                break;
            case R.id.tv_six:
                break;
            case R.id.tv_no_partition:
                break;
            case R.id.tv_partition:
                break;
            case R.id.bt_apply:
                break;
        }
    }
}
