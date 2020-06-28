package com.kang.excelchart.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.kang.excelchart.R;
import com.kang.excelchart.base.BaseConfig;
import com.kang.excelchart.base.BaseFragment;
import com.kang.excelchart.bean.InputTextBean;

import static com.kang.excelchart.base.BaseConfig.MathType.ADDITION;

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
    private InputTextBean selectInputTextBean;

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

        tvAdd.setOnClickListener(this);
        tvSub.setOnClickListener(this);
        tvMultiply.setOnClickListener(this);
        tvDivide.setOnClickListener(this);
        tvAverage.setOnClickListener(this);
        tvMax.setOnClickListener(this);
        tvMin.setOnClickListener(this);
        tvLeft.setOnClickListener(this);
        tvTop.setOnClickListener(this);
        tvBottom.setOnClickListener(this);
        tvRight.setOnClickListener(this);
        tvUpLeft.setOnClickListener(this);
        tvAll.setOnClickListener(this);
        tvInteger.setOnClickListener(this);
        tvOne.setOnClickListener(this);
        tvTwo.setOnClickListener(this);
        tvThree.setOnClickListener(this);
        tvFour.setOnClickListener(this);
        tvFive.setOnClickListener(this);
        tvSix.setOnClickListener(this);
        tvNoPartition.setOnClickListener(this);
        tvPartition.setOnClickListener(this);
        btApply.setOnClickListener(this);

    }

    @Override
    protected void init(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

    }

    @Override
    protected void getInputTextBean(InputTextBean event) {
        setDefaultView(event);
    }

    private BaseConfig.MathType selectMathType = ADDITION;
    private BaseConfig.ScopeType selectScopeType = BaseConfig.ScopeType.LEFT;
    private int decimal = 2;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_add:
                selectMathType = BaseConfig.MathType.ADDITION;
                setMathType(BaseConfig.MathType.ADDITION);
                break;
            case R.id.tv_sub:
                selectMathType = BaseConfig.MathType.SUBTRACTION;
                setMathType(BaseConfig.MathType.SUBTRACTION);
                break;
            case R.id.tv_multiply:
                selectMathType = BaseConfig.MathType.MULTIPLY;
                setMathType(BaseConfig.MathType.MULTIPLY);
                break;
            case R.id.tv_divide:
                selectMathType = BaseConfig.MathType.DIVIDE;
                setMathType(BaseConfig.MathType.DIVIDE);
                break;
            case R.id.tv_average:
                selectMathType = BaseConfig.MathType.AVERAGE;
                setMathType(BaseConfig.MathType.AVERAGE);
                break;
            case R.id.tv_max:
                selectMathType = BaseConfig.MathType.MAX;
                setMathType(BaseConfig.MathType.MAX);
                break;
            case R.id.tv_min:
                selectMathType = BaseConfig.MathType.MIN;
                setMathType(BaseConfig.MathType.MIN);
                break;
            case R.id.tv_left:
                selectScopeType = BaseConfig.ScopeType.LEFT;
                setScopeType(BaseConfig.ScopeType.LEFT);
                break;
            case R.id.tv_top:
                selectScopeType = BaseConfig.ScopeType.TOP;
                setScopeType(BaseConfig.ScopeType.TOP);
                break;
            case R.id.tv_bottom:
                selectScopeType = BaseConfig.ScopeType.BOTTOM;
                setScopeType(BaseConfig.ScopeType.BOTTOM);
                break;
            case R.id.tv_right:
                selectScopeType = BaseConfig.ScopeType.RIGHT;
                setScopeType(BaseConfig.ScopeType.RIGHT);
                break;
            case R.id.tv_up_left:
                selectScopeType = BaseConfig.ScopeType.UP_LEFT;
                setScopeType(BaseConfig.ScopeType.UP_LEFT);
                break;
            case R.id.tv_all:
                selectScopeType = BaseConfig.ScopeType.ALL;
                setScopeType(BaseConfig.ScopeType.ALL);
                break;
            case R.id.tv_integer:
                decimal = 0;
                setDecimal(0);
                break;
            case R.id.tv_one:
                decimal = 1;
                setDecimal(1);
                break;
            case R.id.tv_two:
                decimal = 2;
                setDecimal(2);
                break;
            case R.id.tv_three:
                decimal = 3;
                setDecimal(3);
                break;
            case R.id.tv_four:
                decimal = 4;
                setDecimal(4);
                break;
            case R.id.tv_five:
                decimal = 5;
                setDecimal(5);
                break;
            case R.id.tv_six:
                decimal = 6;
                setDecimal(6);
                break;
            case R.id.tv_no_partition:
                break;
            case R.id.tv_partition:
                break;
            case R.id.bt_apply:
                String result = chartView.doMath(selectMathType, selectScopeType, decimal);
                chartView.setTextContent(result);
                etContent.setText(result);
                break;
            default:
                break;
        }
    }

    //初始化
    private void setDefaultView(InputTextBean inputTextBean) {

        setMathType(inputTextBean.getMathType());
        setScopeType(inputTextBean.getScopeType());
        setDecimal(inputTextBean.getDecimal());
    }

    /*-----------------------------------------------------------------------------------------------------*/

    private void isSelectView(TextView textView, boolean isSelect) {
        textView.setBackground(ContextCompat.getDrawable(activity, isSelect ? R.color.select_color : R.color.white));
    }

    private void setScopeType(BaseConfig.ScopeType scopeType) {
        isSelectView(tvTop, BaseConfig.ScopeType.TOP == scopeType);
        isSelectView(tvLeft, BaseConfig.ScopeType.LEFT == scopeType);
        isSelectView(tvRight, BaseConfig.ScopeType.RIGHT == scopeType);
        isSelectView(tvBottom, BaseConfig.ScopeType.BOTTOM == scopeType);
        isSelectView(tvUpLeft, BaseConfig.ScopeType.UP_LEFT == scopeType);
        isSelectView(tvAll, BaseConfig.ScopeType.ALL == scopeType);
    }

    private void setMathType(BaseConfig.MathType mathType) {
        isSelectView(tvAdd, BaseConfig.MathType.ADDITION == mathType);
        isSelectView(tvMin, BaseConfig.MathType.MIN == mathType);
        isSelectView(tvMax, BaseConfig.MathType.MAX == mathType);
        isSelectView(tvAverage, BaseConfig.MathType.AVERAGE == mathType);
        isSelectView(tvDivide, BaseConfig.MathType.DIVIDE == mathType);
        isSelectView(tvMultiply, BaseConfig.MathType.MULTIPLY == mathType);
        isSelectView(tvMultiply, BaseConfig.MathType.MULTIPLY == mathType);
        isSelectView(tvSub, BaseConfig.MathType.SUBTRACTION == mathType);

    }

    private void setDecimal(int decimal) {
        isSelectView(tvInteger, 0 == decimal);
        isSelectView(tvOne, 1 == decimal);
        isSelectView(tvTwo, 2 == decimal);
        isSelectView(tvThree, 3 == decimal);
        isSelectView(tvFour, 4 == decimal);
        isSelectView(tvFive, 5 == decimal);
        isSelectView(tvSix, 6 == decimal);
    }
}
