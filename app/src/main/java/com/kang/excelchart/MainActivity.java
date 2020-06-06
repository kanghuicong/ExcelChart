package com.kang.excelchart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hyb.library.PreventKeyboardBlockUtil;
import com.kang.excelchart.ChartView;
import com.kang.excelchart.HVScrollView;
import com.kang.excelchart.R;
import com.kang.excelchart.adapter.TabAdapter;
import com.kang.excelchart.base.BaseActivity;
import com.kang.excelchart.base.BaseConfig;
import com.kang.excelchart.bean.InputTextBean;
import com.kang.excelchart.custom.TitleView;
import com.kang.excelchart.custom.XViewPager;
import com.kang.excelchart.fragment.ColorFragment;
import com.kang.excelchart.fragment.LineFragment;
import com.kang.excelchart.fragment.MathFragment;
import com.kang.excelchart.fragment.OtherFragment;
import com.kang.excelchart.fragment.TxtFragment;
import com.kang.excelchart.utils.SoftKeyBoardListener;
import com.vondear.rxtool.RxKeyboardTool;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private HVScrollView scrollView;
    public ChartView chartView;
    private TitleView titleView;
    private View viewLine;
    private ConstraintLayout layout;
    private EditText etContent;
    private TextView btConfirm;
    private ConstraintLayout clItem;
    private LinearLayout llItem;
    private ImageButton ivKeyboard;
    private ImageButton ivTxt;
    private ImageButton ivColor;
    private ImageButton ivLine;
    private ImageButton ivMath;
    private ImageButton ivOther;
    private ImageButton ivNewline;
    private XViewPager viewPager;

    @Override
    public int initLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        titleView = (TitleView) findViewById(R.id.title_view);
        scrollView = (HVScrollView) findViewById(R.id.scrollView);
        chartView = (ChartView) findViewById(R.id.chart_view);
        viewLine = (View) findViewById(R.id.view_line);
        layout = (ConstraintLayout) findViewById(R.id.layout);
        etContent = (EditText) findViewById(R.id.et_content);
        btConfirm = (TextView) findViewById(R.id.bt_confirm);
        clItem = (ConstraintLayout) findViewById(R.id.cl_item);
        llItem = (LinearLayout) findViewById(R.id.ll_item);
        ivKeyboard = (ImageButton) findViewById(R.id.iv_keyboard);
        ivTxt = (ImageButton) findViewById(R.id.iv_txt);
        ivColor = (ImageButton) findViewById(R.id.iv_color);
        ivLine = (ImageButton) findViewById(R.id.iv_line);
        ivMath = (ImageButton) findViewById(R.id.iv_math);
        ivOther = (ImageButton) findViewById(R.id.iv_other);
        ivNewline = (ImageButton) findViewById(R.id.iv_newline);
        viewPager = (XViewPager) findViewById(R.id.view_pager);

        layout.setVisibility(View.GONE);

        ivKeyboard.setOnClickListener(this);
        ivTxt.setOnClickListener(this);
        ivColor.setOnClickListener(this);
        ivLine.setOnClickListener(this);
        ivMath.setOnClickListener(this);
        ivOther.setOnClickListener(this);
        ivNewline.setOnClickListener(this);
        btConfirm.setOnClickListener(this);
    }

    @Override
    public void init(Bundle savedInstanceState) {
        titleView.post(new Runnable() {
            @Override
            public void run() {
                chartView.setBarHeight(titleView.getHeight()); // 获取高度
            }
        });
        //HVScrollView
        scrollView.setFlingEnabled(false);

        //ChartView
        chartView.setScrollView(scrollView);
        chartView.setChartData(BaseConfig.getWidthList(), BaseConfig.getHeightList());
        chartView.setISelectChart(new ChartView.ISelectChart() {
            @Override
            public void selectChart(InputTextBean inputTextBean) {
                layout.setVisibility(View.VISIBLE);
                RxKeyboardTool.showSoftInput(activity, etContent);
                etContent.setText(inputTextBean.getContent());
                etContent.setSelection(inputTextBean.getContent().length());
            }
        });


        keyBoardListener();
        initViewPager();

    }

    private List<Fragment> fragmentList = new ArrayList<>();

    private void initViewPager() {
        TxtFragment txtFragment = new TxtFragment();
        fragmentList.add(txtFragment);
        ColorFragment colorFragment = new ColorFragment();
        fragmentList.add(colorFragment);
        LineFragment lineFragment = new LineFragment();
        fragmentList.add(lineFragment);
        MathFragment mathFragment = new MathFragment();
        fragmentList.add(mathFragment);
        OtherFragment otherFragment = new OtherFragment();
        fragmentList.add(otherFragment);

        TabAdapter adapter = new TabAdapter(getSupportFragmentManager(), fragmentList, null);
        viewPager.setPagingEnabled(false);//禁止左右滑动
        viewPager.setAdapter(adapter);
    }


    public void keyBoardListener() {
        SoftKeyBoardListener.setListener(this, new SoftKeyBoardListener.OnSoftKeyBoardChangeListener() {
            @Override
            public void keyBoardShow(int height) {
                ConstraintLayout.LayoutParams lp = (ConstraintLayout.LayoutParams) viewPager.getLayoutParams();
                lp.height = height + llItem.getHeight();
                viewPager.setLayoutParams(lp);
            }

            @Override
            public void keyBoardHide(int height) {
                ConstraintLayout.LayoutParams lp = (ConstraintLayout.LayoutParams) viewPager.getLayoutParams();
                lp.height = height;
                viewPager.setLayoutParams(lp);
            }
        });

        etContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                chartView.setTextContent(etContent, s.toString());
            }
        });
    }

    @Override
    public List<View> needStopView() {
        return null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_keyboard:
                RxKeyboardTool.showSoftInput(this, etContent);
                break;
            case R.id.iv_txt:
                RxKeyboardTool.hideSoftInput(this, etContent);
                viewPager.setCurrentItem(0, true);
                break;
            case R.id.iv_color:
                RxKeyboardTool.hideSoftInput(this, etContent);
                viewPager.setCurrentItem(1, true);
                break;
            case R.id.iv_line:
                RxKeyboardTool.hideSoftInput(this, etContent);
                viewPager.setCurrentItem(2, true);
                break;
            case R.id.iv_math:
                RxKeyboardTool.hideSoftInput(this, etContent);
                viewPager.setCurrentItem(3, true);
                break;
            case R.id.iv_other:
                RxKeyboardTool.hideSoftInput(this, etContent);
                viewPager.setCurrentItem(4, true);
                break;
            case R.id.bt_confirm:
                chartView.setTextContent(etContent, etContent.getText().toString());
                layout.setVisibility(View.GONE);
                RxKeyboardTool.hideSoftInput(this, etContent);
                break;

        }
    }

}
