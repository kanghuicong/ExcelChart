package com.kang.excelchart.fragment;

import android.graphics.Paint;
import android.os.Bundle;
import android.text.TextPaint;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kang.excelchart.ChartView;
import com.kang.excelchart.MainActivity;
import com.kang.excelchart.R;
import com.kang.excelchart.adapter.ColorAdapter;
import com.kang.excelchart.base.BaseConfig;
import com.kang.excelchart.base.BaseFragment;
import com.kang.excelchart.base.TextPaintConfig;
import com.kang.excelchart.bean.InputTextBean;
import com.kang.excelchart.bean.TextColorBean;
import com.vondear.rxtool.RxDeviceTool;
import com.vondear.rxtool.RxImageTool;
import com.vondear.rxtool.RxKeyboardTool;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

/**
 * 类描述：
 */
public class TxtFragment extends BaseFragment implements View.OnClickListener {
    private LinearLayout ll1;
    private TextView tvBold;
    private TextView tvItalic;
    private TextView tvUnderline;
    private TextView tvDeleteLine;
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
    private RecyclerView rvColor;
    private InputTextBean selectInputTextBean;//选中单元格的属性
    private ColorAdapter colorAdapter;
    private List<TextColorBean> colorList;
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
        tvDeleteLine = (TextView) view.findViewById(R.id.tv_deleteLine);
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
        rvColor = (RecyclerView) view.findViewById(R.id.rv_color);

        tvBold.setOnClickListener(this);
        tvItalic.setOnClickListener(this);
        tvUnderline.setOnClickListener(this);
        tvDeleteLine.setOnClickListener(this);
        tvLeft.setOnClickListener(this);
        tvCenter.setOnClickListener(this);
        tvRight.setOnClickListener(this);
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
        EventBus.getDefault().register(this);

        colorList = TextPaintConfig.getTextColorList(TextPaintConfig.defaultTextColor);
        colorAdapter = new ColorAdapter(activity, colorList, new ColorAdapter.ISelectColor() {
            @Override
            public void select(TextColorBean textColorBean) {
                chartView.setTextColor(textColorBean.getColor());
            }
        });

        GridLayoutManager gridLayoutManager = new GridLayoutManager(activity, BaseConfig.getCount(activity));
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvColor.setLayoutManager(gridLayoutManager);
        rvColor.setAdapter(colorAdapter);

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

        colorList.clear();
        colorList.addAll(TextPaintConfig.getTextColorList(textPaint.getColor()));
        colorAdapter.notifyDataSetChanged();
    }

    private void isSelectView(TextView textView, boolean isSelect) {
        textView.setBackground(ContextCompat.getDrawable(activity, isSelect ? R.color.select_color : R.color.transparent));
    }

    private void setTextSizeView(boolean isDefault, int size) {
        if (!isDefault) chartView.setTextSize(size);

        isSelectView(tv8, size == 8);
        isSelectView(tv10, size == 10);
        isSelectView(tv12, size == 12);
        isSelectView(tv14, size == 14);
        isSelectView(tv16, size == 16);
        isSelectView(tv18, size == 18);
        isSelectView(tv20, size == 20);
    }


    private void setTextGravity(boolean isDefault, Paint.Align align) {
        if (!isDefault) chartView.setGravity(align);

        isSelectView(tvLeft, align == Paint.Align.LEFT);
        isSelectView(tvRight, align == Paint.Align.RIGHT);
        isSelectView(tvCenter, align == Paint.Align.CENTER);
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void InputTextBeanEvent(InputTextBean event) {
        setDefaultView(event);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
