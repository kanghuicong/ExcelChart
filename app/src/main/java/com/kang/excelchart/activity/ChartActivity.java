package com.kang.excelchart.activity;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextPaint;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.kang.excelchart.bean.ChartBean;
import com.kang.excelchart.bean.ChartInfoBean;
import com.kang.excelchart.bean.Tables;
import com.kang.excelchart.bean.Tables_1;
import com.kang.excelchart.bean.UpdateEvent;
import com.kang.excelchart.bean._User;
import com.kang.excelchart.config.PaintConfig;
import com.kang.excelchart.config.TextPaintConfig;
import com.kang.excelchart.config.UserConfig;
import com.kang.excelchart.custom.view.ChartView;
import com.kang.excelchart.custom.view.HVScrollView;
import com.kang.excelchart.R;
import com.kang.excelchart.adapter.TabAdapter;
import com.kang.excelchart.base.BaseActivity;
import com.kang.excelchart.config.BaseConfig;
import com.kang.excelchart.bean.InputTextBean;
import com.kang.excelchart.custom.view.KeyBackEditText;
import com.kang.excelchart.custom.view.TitleView;
import com.kang.excelchart.custom.view.XViewPager;
import com.kang.excelchart.fragment.chart.ColorChartFragment;
import com.kang.excelchart.fragment.chart.LineChartFragment;
import com.kang.excelchart.fragment.chart.MathChartFragment;
import com.kang.excelchart.fragment.chart.OtherChartFragment;
import com.kang.excelchart.fragment.chart.TxtChartFragment;
import com.kang.excelchart.utils.TextEmptyUtils;
import com.kang.excelchart.utils.TextPaintUtils;
import com.kang.excelchart.utils.SoftKeyBoardListener;
import com.vondear.rxtool.RxActivityTool;
import com.vondear.rxtool.RxImageTool;
import com.vondear.rxtool.RxKeyboardTool;
import com.vondear.rxtool.RxLogTool;
import com.vondear.rxtool.RxTimeTool;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobRelation;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

public class ChartActivity extends BaseActivity implements View.OnClickListener {

    private HVScrollView scrollView;
    public ChartView chartView;
    private TitleView titleView;
    private View viewLine;
    private ConstraintLayout llChart;
    public KeyBackEditText etContent;
    private ImageButton btConfirm;
    private ConstraintLayout clItem;
    private LinearLayout llItem;
    private ImageButton ivKeyboard;
    private ImageButton ivTxt;
    private ImageButton ivColor;
    private ImageButton ivLine;
    private ImageButton ivMath;
    private ImageButton ivOther;
    private ImageButton ivNext;
    private XViewPager viewPager;
    private boolean isShow = false;

    public static final int NORMAL_FROM = 0;
    public static final int ADAPTER_FROM = 1;
    private Tables table;

    ChartInfoBean chartInfoBean;

    public static void doIntent(Context context, int from, Tables tables) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("tables", tables);
        bundle.putInt("from", from);
        RxActivityTool.skipActivity(context, ChartActivity.class, bundle);
    }

    @Override
    public int initLayout() {
        return R.layout.chart_activity;
    }

    @Override
    public void initView() {
        titleView = (TitleView) findViewById(R.id.title_view);
        scrollView = (HVScrollView) findViewById(R.id.scrollView);
        chartView = (ChartView) findViewById(R.id.chart_view);
        viewLine = (View) findViewById(R.id.view_line);
        llChart = (ConstraintLayout) findViewById(R.id.ll_chart);
        etContent = (KeyBackEditText) findViewById(R.id.et_content);
        btConfirm = (ImageButton) findViewById(R.id.bt_confirm);
        clItem = (ConstraintLayout) findViewById(R.id.cl_item);
        llItem = (LinearLayout) findViewById(R.id.ll_item);
        ivKeyboard = (ImageButton) findViewById(R.id.iv_keyboard);
        ivTxt = (ImageButton) findViewById(R.id.iv_txt);
        ivColor = (ImageButton) findViewById(R.id.iv_color);
        ivLine = (ImageButton) findViewById(R.id.iv_line);
        ivMath = (ImageButton) findViewById(R.id.iv_math);
        ivOther = (ImageButton) findViewById(R.id.iv_other);
        ivNext = (ImageButton) findViewById(R.id.iv_next);
        viewPager = (XViewPager) findViewById(R.id.view_pager);

        llChart.setVisibility(View.GONE);

        ivKeyboard.setOnClickListener(this);
        ivTxt.setOnClickListener(this);
        ivColor.setOnClickListener(this);
        ivLine.setOnClickListener(this);
        ivMath.setOnClickListener(this);
        ivOther.setOnClickListener(this);
        ivNext.setOnClickListener(this);
        btConfirm.setOnClickListener(this);
    }

    @Override
    public void init(Bundle savedInstanceState) {
        int from = getIntent().getExtras().getInt("from", NORMAL_FROM);

        List<InputTextBean> lcs = new ArrayList<>();
        switch (from) {
            case ADAPTER_FROM:
                table = (Tables) getIntent().getExtras().getSerializable("tables");
                titleView.setTitle(table.getName());
                if (table == null || TextEmptyUtils.isEmpty(table.getSourceData())) {
                    chartView.setChartData(BaseConfig.getWidthList(), BaseConfig.getHeightList(), lcs);
                    return;
                }
                String date = table.getSourceData();
                RxLogTool.d("表格总数据：" + table);
                RxLogTool.d("单元格数据：" + date);


                chartInfoBean = JSON.parseObject(date, ChartInfoBean.class);

                List<ChartBean> chartBeanList = JSON.parseObject(chartInfoBean.getChart_list(), new TypeReference<List<ChartBean>>() {
                });
                int y = 0;
                for (int i = 0; i < chartBeanList.size(); i++) {

                    ChartBean chartBean = chartBeanList.get(i);
                    ChartBean.TdTextAttributeModelBean tdTextAttributeModelBean = JSON.parseObject(chartBean.getTdTextAttributeModel(), ChartBean.TdTextAttributeModelBean.class);
                    TextPaint textPaint;
                    if (tdTextAttributeModelBean != null) {
                        textPaint = new TextPaint();
                        //粗体
                        textPaint.setFakeBoldText(tdTextAttributeModelBean.isBold());
                        //右斜
                        textPaint.setTextSkewX(TextPaintUtils.getTextSkewX(tdTextAttributeModelBean.isTilt()));
                        //下划线
                        textPaint.setUnderlineText(tdTextAttributeModelBean.isUnder());
                        //删除线
                        textPaint.setStrikeThruText(tdTextAttributeModelBean.isDeleteLine());
                        //常规字体
                        textPaint.setTypeface(Typeface.DEFAULT);

                        textPaint.setStyle(TextPaintUtils.getFill(chartBean.isFill()));
                        textPaint.setStrokeWidth(8);
                        textPaint.setTextSize(RxImageTool.sp2px(tdTextAttributeModelBean.getPointSize()));
                        textPaint.setTextAlign(TextPaintUtils.getAlign(tdTextAttributeModelBean.getTextAlignment()));
                        textPaint.setColor(TextPaintUtils.hexToColor(tdTextAttributeModelBean.getColorStr()));
                    } else {
                        textPaint = TextPaintConfig.getTextPaint();
                    }


                    int x = i / chartInfoBean.getHeight_list().size();
                    InputTextBean inputTextBean = new InputTextBean(x, y, chartBean, textPaint, tdTextAttributeModelBean);
                    if (y < chartInfoBean.getHeight_list().size() - 1) {
                        y++;
                    } else y = 0;

                    lcs.add(inputTextBean);

//                    RxLogTool.d("chart:-------" + i + "----" + chartBean.toString());
                }

                chartView.setChartData(chartInfoBean.getWidth_list(), chartInfoBean.getHeight_list(), lcs);

                break;
            default:
                titleView.setTitle("无标题排班表(" + RxTimeTool.getCurTimeMills() + ")");
                chartInfoBean = new ChartInfoBean();
                lcs.clear();
                for (int i = 0; i < BaseConfig.getHeightList().size(); i++) {
                    for (int n = 0; n < BaseConfig.getWidthList().size(); n++) {
                        lcs.add(TextPaintConfig.getInputTextBean(i, n));
                    }
                }
                chartView.setChartData(BaseConfig.getWidthList(), BaseConfig.getHeightList(), lcs);
                break;
        }

        titleView.post((() -> {
            chartView.setBarHeight(titleView.getHeight()); // 获取高度
        }));

        keyBoardListener();
        initViewPager();

        //HVScrollView
        scrollView.setFlingEnabled(false);
        //ChartView
        chartView.setScrollView(scrollView);

        chartView.setISelectChart((inputTextBean) -> {
            if (llChart.getVisibility() != View.VISIBLE) {
                llChart.setVisibility(View.VISIBLE);
                RxKeyboardTool.showSoftInput(activity, etContent);
            }

            etContent.setText(inputTextBean.getChartBean().getTdText());
            etContent.setSelection(inputTextBean.getChartBean().getTdText().length());

            //通知各个fragment，选中框已改变
            EventBus.getDefault().postSticky(inputTextBean);
        });

        titleView.setClickFinishLister((view) -> {
            onBackPressed();
        });
    }

    private List<Fragment> fragmentList = new ArrayList<>();

    private void initViewPager() {
        TxtChartFragment txtFragment = new TxtChartFragment();
        fragmentList.add(txtFragment);
        ColorChartFragment colorFragment = new ColorChartFragment();
        fragmentList.add(colorFragment);
        LineChartFragment lineFragment = new LineChartFragment();
        fragmentList.add(lineFragment);
        MathChartFragment mathFragment = new MathChartFragment();
        fragmentList.add(mathFragment);
        OtherChartFragment otherFragment = new OtherChartFragment();
        fragmentList.add(otherFragment);

        TabAdapter adapter = new TabAdapter(getSupportFragmentManager(), fragmentList, null);
        //禁止左右滑动
        viewPager.setPagingEnabled(false);
        viewPager.setAdapter(adapter);

        viewPager.setCurrentItem(0, true);
    }


    public void keyBoardListener() {
        SoftKeyBoardListener.setListener(this, new SoftKeyBoardListener.OnSoftKeyBoardChangeListener() {
            @Override
            public void keyBoardShow(int height) {
                ConstraintLayout.LayoutParams lp = (ConstraintLayout.LayoutParams) viewPager.getLayoutParams();
                lp.height = height + llItem.getHeight();
                viewPager.setLayoutParams(lp);
                isShow = true;
            }

            @Override
            public void keyBoardHide(int height) {
                ConstraintLayout.LayoutParams lp = (ConstraintLayout.LayoutParams) viewPager.getLayoutParams();
                lp.height = height;
                viewPager.setLayoutParams(lp);
                isShow = false;
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
                chartView.setTextContent(s.toString());
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
            case R.id.iv_next:
                chartView.selectNexCell();
                break;
            case R.id.bt_confirm:
                chartView.setTextContent(etContent.getText().toString());
                llChart.setVisibility(View.GONE);
                RxKeyboardTool.hideSoftInput(this, etContent);
                break;
            default:
                break;
        }
    }

    /**
     * 当键盘弹起时，返回键优先处理关闭键盘，需要再次点击才会走onBackPressed()
     * 重写edittext和onBackPressed（）
     */
    @Override
    public void onBackPressed() {
        if (isShow || llChart.getVisibility() == View.VISIBLE) {
            llChart.setVisibility(View.GONE);
            RxKeyboardTool.hideSoftInput(this, etContent);
        } else {
            if (chartView != null) {
                Tables_1 p2 = new Tables_1();

                List<String> list = new ArrayList<>();
                for (InputTextBean inputTextBean : chartView.getInputTextList()) {
                    list.add(JSON.toJSONString(inputTextBean.getChartBean()));
                }

//                chartInfoBean.setChart_list(list.toString());
//                chartInfoBean.setHeight_list(chartView.getHeightList());
//                chartInfoBean.setWidth_list(chartView.getWidthList());
//                chartInfoBean.setHeight_num(chartView.getHeightList().size());
//                chartInfoBean.setWidth_num(chartView.getWidthList().size());

                String json = "{\"w\":" + chartView.getWidthList().size() +
                        ",\"h\":" + chartView.getHeightList().size() +
                        ",\"ha\":" + chartView.getHeightList() +
                        ",\"wa\":" + chartView.getWidthList() +
                        ",\"m\":" + list.toString() +
                        ",\"t\":" + 0 +
                        "}";
//                json.replace("\\", "");
                RxLogTool.d("chart转换数据：" + json);

                p2.setSourceData(json);
                p2.setName(titleView.getTitleText().getText().toString());

                if (table != null && !TextEmptyUtils.isEmpty(table.getSourceData())) {
                    //已存在的表格——>更新
                    //json = JSON.toJSONString(chartInfoBean);  //数据会 ///"
                    p2.update(this.table.getObjectId(), new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                            if (e == null) {
                                RxLogTool.d("更新成功！");
                            } else {
                                RxLogTool.d("更新失败！----" + e.toString());
                            }
                        }
                    });
                } else {
                    //新表格——>新增
                    p2.setFileId(-1);
                    p2.setType(0);
                    p2.setIsCollection(false);
                    p2.setTaCreateTime(RxTimeTool.getCurTimeMills() / 1000 + "");
                    p2.setTaUpdateTime(RxTimeTool.getCurTimeMills() / 1000 + "");
                    p2.save(new SaveListener<String>() {
                        @Override
                        public void done(String objectId, BmobException e) {
                            if (e == null) {
                                RxLogTool.d("新增成功！");
                                _User user = BmobUser.getCurrentUser(_User.class);
                                user.setObjectId(UserConfig.getUserId(activity));

                                BmobRelation relation = new BmobRelation();
                                Tables_1 tables_1 = new Tables_1();
                                tables_1.setObjectId(objectId);
                                relation.add(tables_1);
                                user.setTables_1(relation);
                                user.update(new UpdateListener() {
                                    @Override
                                    public void done(BmobException e) {
                                        if(e==null){
                                            RxLogTool.d("bmob","多对多关联添加成功");
                                        }else{
                                            RxLogTool.d("bmob","失败："+e.getMessage());
                                        }
                                    }
                                });
                            } else {
                                RxLogTool.d("新增失败！----" + e.toString());
                            }
                        }
                    });
                }
            }
            EventBus.getDefault().post(new UpdateEvent());
            super.onBackPressed();
        }
    }

    @Override
    public boolean needCloseKeyword() {
        return false;
    }
}
