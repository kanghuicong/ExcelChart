package com.kang.excelchart.fragment.chart;

import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kang.excelchart.R;
import com.kang.excelchart.adapter.ColorAdapter;
import com.kang.excelchart.config.BaseConfig;
import com.kang.excelchart.base.BaseChartFragment;
import com.kang.excelchart.config.TextPaintConfig;
import com.kang.excelchart.bean.InputTextBean;
import com.kang.excelchart.bean.TextColorBean;
import com.kang.excelchart.utils.TextPaintUtils;
import com.vondear.rxtool.RxImageTool;

import java.util.List;

/**
 * 类描述：
 */
public class TxtChartFragment extends BaseChartFragment implements View.OnClickListener {
    private LinearLayout ll1;
    private ImageButton tvBold;
    private ImageButton tvItalic;
    private ImageButton tvUnderline;
    private ImageButton tvDeleteLine;
    private LinearLayout ll2;
    private ImageButton tvLeft;
    private ImageButton tvCenter;
    private ImageButton tvRight;
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
    private RecyclerView rvColor;
    //选中单元格的属性
    private InputTextBean selectInputTextBean;
    private ColorAdapter colorAdapter;
    private List<TextColorBean> colorList;

    @Override
    protected int initLayout(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_txt;
    }

    @Override
    protected void initView(View view) {
        ll1 = (LinearLayout) view.findViewById(R.id.ll_1);
        tvBold = (ImageButton) view.findViewById(R.id.tv_bold);
        tvItalic = (ImageButton) view.findViewById(R.id.tv_italic);
        tvUnderline = (ImageButton) view.findViewById(R.id.tv_underline);
        tvDeleteLine = (ImageButton) view.findViewById(R.id.tv_deleteLine);
        ll2 = (LinearLayout) view.findViewById(R.id.ll_2);
        tvLeft = (ImageButton) view.findViewById(R.id.tv_left);
        tvCenter = (ImageButton) view.findViewById(R.id.tv_center);
        tvRight = (ImageButton) view.findViewById(R.id.tv_right);
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
        rvColor = (RecyclerView) view.findViewById(R.id.rv_color);

        tvBold.setOnClickListener(this);
        tvItalic.setOnClickListener(this);
        tvUnderline.setOnClickListener(this);
        tvDeleteLine.setOnClickListener(this);
        tvLeft.setOnClickListener(this);
        tvCenter.setOnClickListener(this);
        tvRight.setOnClickListener(this);
        tvStyle1.setOnClickListener(this);
        tvStyle2.setOnClickListener(this);
        tvStyle3.setOnClickListener(this);

        tv8.setOnClickListener(this);
        tv10.setOnClickListener(this);
        tv12.setOnClickListener(this);
        tv14.setOnClickListener(this);
        tv16.setOnClickListener(this);
        tv18.setOnClickListener(this);
        tv20.setOnClickListener(this);
        tv22.setOnClickListener(this);
    }


    @Override
    protected void init(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        colorList = TextPaintConfig.getTextColorList(activity,TextPaintConfig.defaultTextColorStr);
        colorAdapter = new ColorAdapter(activity, colorList, new ColorAdapter.ISelectColor() {
            @Override
            public void select(TextColorBean textColorBean) {
                chartView.setTextColor(textColorBean.getColorStr());
            }
        });

        GridLayoutManager gridLayoutManager = new GridLayoutManager(activity, BaseConfig.getCount(activity));
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvColor.setLayoutManager(gridLayoutManager);
        rvColor.setAdapter(colorAdapter);

    }

    @Override
    protected void getInputTextBean(InputTextBean event) {
        setDefaultView(event);
    }

    @Override
    public void onClick(View v) {
        selectInputTextBean = chartView.getSelectInputTextBean();
        switch (v.getId()) {
            case R.id.tv_bold:
                boolean isBold = selectInputTextBean.getTextPaint().isFakeBoldText();
                chartView.setFakeBoldText(!isBold);
                isSelectView(tvBold, !isBold);
                break;
            case R.id.tv_italic:
                float skewX = selectInputTextBean.getTextPaint().getTextSkewX();
                chartView.setTextSkewX(skewX == 0f);
                isSelectView(tvItalic, skewX == 0f);
                break;
            case R.id.tv_underline:
                boolean isUnderline = selectInputTextBean.getTextPaint().isUnderlineText();
                chartView.setUnderline(!isUnderline);
                isSelectView(tvUnderline, !isUnderline);
                break;
            case R.id.tv_deleteLine:
                boolean isDeleteLine = selectInputTextBean.getTextPaint().isStrikeThruText();
                chartView.setDeleteLine(!isDeleteLine);
                isSelectView(tvDeleteLine, !isDeleteLine);
            case R.id.tv_style1:
                setTypeface(false, Typeface.DEFAULT);
                break;
            case R.id.tv_style2:
                setTypeface(false, Typeface.DEFAULT_BOLD);
                break;
            case R.id.tv_style3:
                setTypeface(false, Typeface.SERIF);
                break;
            case R.id.tv_8:
                setTextSizeView(false, 8);
                break;
            case R.id.tv_10:
                setTextSizeView(false, 10);
                break;
            case R.id.tv_12:
                setTextSizeView(false, 12);
                break;
            case R.id.tv_14:
                setTextSizeView(false, 14);
                break;
            case R.id.tv_16:
                setTextSizeView(false, 16);
                break;
            case R.id.tv_18:
                setTextSizeView(false, 18);
                break;
            case R.id.tv_20:
                setTextSizeView(false, 20);
                break;
            case R.id.tv_22:
                setTextSizeView(false, 22);
                break;
            case R.id.tv_left:
                setTextGravity(false, Paint.Align.LEFT);
                break;
            case R.id.tv_right:
                setTextGravity(false, Paint.Align.RIGHT);
                break;
            case R.id.tv_center:
                setTextGravity(false, Paint.Align.CENTER);
                break;
            default:
                break;

        }
    }

    //初始化
    private void setDefaultView(InputTextBean inputTextBean) {
        TextPaint textPaint = inputTextBean.getTextPaint();
        isSelectView(tvBold, textPaint.isFakeBoldText());
        isSelectView(tvItalic, textPaint.getTextSkewX() != 0f);
        isSelectView(tvUnderline, textPaint.isUnderlineText());
        isSelectView(tvDeleteLine, textPaint.isStrikeThruText());

        int size = RxImageTool.px2sp(textPaint.getTextSize());
        setTextSizeView(true, size);

        setTextGravity(true, textPaint.getTextAlign());
        setTypeface(true, textPaint.getTypeface());

        if (colorAdapter != null && colorList != null) {
            colorList.clear();
            colorList.addAll(TextPaintConfig.getTextColorList(activity,TextPaintUtils.colorToHex(textPaint.getColor())));
            colorAdapter.notifyDataSetChanged();
        }
    }

    private void isSelectView(View view, boolean isSelect) {
        view.setBackground(ContextCompat.getDrawable(activity, isSelect ? R.color.select_color : R.color.transparent));
    }

    private void setTextSizeView(boolean isDefault, int size) {
        //如果不是初始化，在改变界面的同时改变chartView
        if (!isDefault) {
            chartView.setTextSize(size);
        }

        isSelectView(tv8, size == 8);
        isSelectView(tv10, size == 10);
        isSelectView(tv12, size == 12);
        isSelectView(tv14, size == 14);
        isSelectView(tv16, size == 16);
        isSelectView(tv18, size == 18);
        isSelectView(tv20, size == 20);
    }


    private void setTextGravity(boolean isDefault, Paint.Align align) {
        if (!isDefault) {
            chartView.setGravity(align);
        }

        isSelectView(tvLeft, align == Paint.Align.LEFT);
        isSelectView(tvRight, align == Paint.Align.RIGHT);
        isSelectView(tvCenter, align == Paint.Align.CENTER);
    }


    private void setTypeface(boolean isDefault, Typeface typeface) {
        if (!isDefault) {
            chartView.setTypeface(typeface);
        }

        isSelectView(tvStyle1, typeface == Typeface.DEFAULT);
        isSelectView(tvStyle2, typeface == Typeface.DEFAULT_BOLD);
        isSelectView(tvStyle3, typeface == Typeface.SERIF);
    }
}
