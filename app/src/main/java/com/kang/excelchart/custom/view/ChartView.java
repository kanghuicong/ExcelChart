package com.kang.excelchart.custom.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;


import com.alibaba.fastjson.JSON;
import com.kang.excelchart.R;
import com.kang.excelchart.bean.ChartBean;
import com.kang.excelchart.config.BaseConfig;
import com.kang.excelchart.config.PaintConfig;
import com.kang.excelchart.config.TextPaintConfig;
import com.kang.excelchart.bean.InputTextBean;
import com.kang.excelchart.utils.TextEmptyUtils;
import com.kang.excelchart.utils.TextPaintUtils;
import com.vondear.rxtool.RxImageTool;
import com.vondear.rxtool.RxLogTool;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 类描述：
 */
public class ChartView extends View {
    private Context context;

    private List<Float> height = new ArrayList<>();//实际高度
    private List<Float> defaultHeight = new ArrayList<>();//初始高度
    private List<Float> width = new ArrayList<>();
    private List<Float> defaultWidth = new ArrayList<>();

    private float multiple = 2.5f; //放大倍数
    private int heightNum;//行数
    private int widthNum;//列数

    public float allHeight = 0;//总高度
    public float allWidth = 0;//总宽度

    private float[] pointX;//x坐标
    private float[] pointY;//y坐标

    private final int chartPadding = 15;//文本内边距

    //起点
    public final int[] startPoint = {80, 80};
    //单元格最小宽度
    public final int minCellX = 200;
    //单元格最小高度
    public final int minCellY = 80;

    private HVScrollView scrollView;
    //画笔
    private PaintConfig paintConfig;
    private Bitmap leftBitmap;
    private Bitmap topBitmap;
    private int bitmapHeight;
    private int bitmapWidth;
    private int barHeight;
    private int[] bitmapX = {0, 0};
    private int[] bitmapY = {0, 0};
    //绿线
    private int[] greenLine = {0, 0};
    //选中框左上角，非坐标而是个数
    public int[] selectCell = {-1, -1};
    //输入的文字集合，包括左上角和内容
    private List<InputTextBean> inputTextList = new ArrayList<>();

    //判断一个字符串为数字(正负、小数)
    private Pattern pattern = Pattern.compile("-?[0-9]+.*[0-9]*");

    public ChartView(Context context) {
        this(context, null);
    }

    public ChartView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ChartView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initConfig();
    }


    public void setChartData(List<Float> width, List<Float> height, List<InputTextBean> inputTextList) {
        this.width.clear();
        this.height.clear();
        this.inputTextList.clear();
        this.defaultWidth.clear();
        this.defaultHeight.clear();
        this.width.addAll(width);
        this.defaultWidth.addAll(width);
        this.height.addAll(height);
        this.defaultHeight.addAll(height);
        this.inputTextList.addAll(inputTextList);

        init();

        invalidate();
    }

    public void initConfig() {
        paintConfig = new PaintConfig();

        leftBitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.merge_left);
        topBitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.merga);
        bitmapHeight = topBitmap.getHeight();
        bitmapWidth = leftBitmap.getWidth();

//        barHeight = RxBarTool.getStatusBarHeight(context);
    }


    public void init() {

        heightNum = height.size();
        widthNum = width.size();
        //x坐标
        pointX = new float[widthNum + 1];
        //y坐标
        pointY = new float[heightNum + 1];

        pointX[0] = startPoint[0];
        pointY[0] = startPoint[1];

        //先重置为0，不然新增行列时计算错误
        allHeight = 0;
        allWidth = 0;
        //计算总高度
        for (int i = 0; i < heightNum; i++) {
            allHeight = allHeight + height.get(i) * multiple;
            pointY[i + 1] = pointX[0] + allHeight;
        }
        //计算总宽度
        for (int i = 0; i < widthNum; i++) {
            allWidth = allWidth + width.get(i) * multiple;
            pointX[i + 1] = pointY[0] + allWidth;
        }

        //*2 制造出边距
        this.setMinimumHeight((int) allHeight + startPoint[1] * 2);
        this.setMinimumWidth((int) allWidth + startPoint[0] * 2);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float startX = startPoint[0];
        //画竖线
        for (int i = 0; i < widthNum + 1; i++) {
            canvas.drawLine(startX, startPoint[1], startX, startPoint[1] + allHeight, paintConfig.linePaint);
            if (i != widthNum) {
                startX = startX + width.get(i) * multiple;
            }
        }
        //画横线
        float startY = startPoint[1];
        for (int i = 0; i < heightNum + 1; i++) {
            canvas.drawLine(startPoint[0], startY, startPoint[0] + allWidth, startY, paintConfig.linePaint);
            if (i != heightNum) {
                startY = startY + height.get(i) * multiple;
            }
        }

        //选择边框
        if (selectCell[0] != -1 && selectCell[1] != -1) {
            //上边
            canvas.drawLine(pointX[selectCell[0]], pointY[selectCell[1]], pointX[selectCell[0] + 1], pointY[selectCell[1]], paintConfig.selectPaint);
            //左边
            canvas.drawLine(pointX[selectCell[0]], pointY[selectCell[1]], pointX[selectCell[0]], pointY[selectCell[1] + 1], paintConfig.selectPaint);
            //下边
            canvas.drawLine(pointX[selectCell[0]], pointY[selectCell[1] + 1], pointX[selectCell[0] + 1], pointY[selectCell[1] + 1], paintConfig.selectPaint);
            //右边
            canvas.drawLine(pointX[selectCell[0] + 1], pointY[selectCell[1]], pointX[selectCell[0] + 1], pointY[selectCell[1] + 1], paintConfig.selectPaint);

            //绘制拉伸图标
            canvas.drawBitmap(topBitmap, pointX[selectCell[0] + 1] - (float) 1 / 2 * bitmapWidth, startPoint[1] - bitmapHeight, new Paint());
            canvas.drawBitmap(leftBitmap, startPoint[0] - bitmapWidth, pointY[selectCell[1] + 1] - (float) 1 / 2 * bitmapHeight, new Paint());

            //记录图标的左上角
            bitmapX[0] = (int) (pointX[selectCell[0] + 1] - (float) 1 / 2 * bitmapWidth);
            bitmapX[1] = startPoint[1] - bitmapHeight;

            bitmapY[0] = startPoint[0] - bitmapWidth;
            bitmapY[1] = (int) (pointY[selectCell[1] + 1] - (float) 1 / 2 * bitmapHeight);
        }

        //绘制文字
        for (InputTextBean inputTextBean : inputTextList) {

//            StaticLayout layout = new StaticLayout(inputTextBean.getChartBean().getTdText(),
//                    paintConfig.textPaint,
//                    width.get(inputTextBean.getInputX()),
//                    Layout.Alignment.ALIGN_NORMAL,
//                    1.0F,
//                    0.0F,
//                    false);
//            //从 (20,80)的位置开始绘制
//            canvas.translate(pointX[inputTextBean.getInputX()] + (pointX[inputTextBean.getInputX() + 1] - pointX[inputTextBean.getInputX()]) / 2,
//                    pointY[inputTextBean.getInputY()]);
//            layout.draw(canvas);
            /*---------------------------------*/
            // 单行绘制 （先计算出基线和到文字中间的距离,mPaint不是TextPaint）
//            float offset = Math.abs(inputTextBean.getTextPaint().ascent() + inputTextBean.getTextPaint().descent()) / 2;
//            canvas.drawText(inputTextBean.getChartBean().getTdText(),
//                    pointX[inputTextBean.getInputX()] + (pointX[inputTextBean.getInputX() + 1] - pointX[inputTextBean.getInputX()]) / 2,
//                    pointY[inputTextBean.getInputY()] + (pointY[inputTextBean.getInputY() + 1] - pointY[inputTextBean.getInputY()]) / 2 + offset,
//                    paintConfig.textPaint);

            /*---------------------------------*/
//            float offset = Math.abs(inputTextBean.getTextPaint().ascent() + inputTextBean.getTextPaint().descent()) / 2;
//            int start = 0;
//            Paint.FontMetrics fm = inputTextBean.getTextPaint().getFontMetrics();
//            float paintHeight = fm.bottom - fm.top;
//
//            while (start < inputTextBean.getChartBean().getTdText().length()) {
//                int breakTextCount = inputTextBean.getTextPaint().breakText(
//                        inputTextBean.getChartBean().getTdText(),
//                        true,     // 是否正向绘制
//                        width.get(inputTextBean.getInputX()),      // 最大绘制宽度
//                        measuredWidth);      // 绘制本行文字的宽度存在这个数组中
//
//                canvas.drawText(
//                        inputTextBean.getChartBean().getTdText(),
//                        start,
//                        (start + breakTextCount > inputTextBean.getChartBean().getTdText().length()) ? inputTextBean.getChartBean().getTdText().length() : start + breakTextCount,
//                        pointX[inputTextBean.getInputX()] + (pointX[inputTextBean.getInputX() + 1] - pointX[inputTextBean.getInputX()]) / 2,
//                        pointY[inputTextBean.getInputY()] + paintHeight / 2 + offset,
//                        inputTextBean.getTextPaint()
//                );
//                start += breakTextCount;
//                offset += inputTextBean.getTextPaint().getFontSpacing();
//
//            }
            /*------------------------*/
            //单元格背景
            //不是默认的背景色才进行绘制

            if (!inputTextBean.getChartBean().getTdBackgroundColorStr().equals(TextPaintConfig.defaultBackgroundColorStr)) {
                float startXbg = pointX[inputTextBean.getInputX()];
                float endXbg = pointX[inputTextBean.getInputX() + 1];
                float startYbg = pointY[inputTextBean.getInputY()];
                float endYbg = pointY[inputTextBean.getInputY() + 1];

                Paint p = new Paint();
                p.setColor(TextPaintUtils.hexToColor(inputTextBean.getChartBean().getTdBackgroundColorStr()));
                canvas.drawRect(startXbg + PaintConfig.lineWidth / 2, startYbg + PaintConfig.lineWidth / 2, endXbg - PaintConfig.lineWidth / 2, endYbg - PaintConfig.lineWidth / 2, p);
            }

            // 单行绘制 （先计算出基线和到文字中间的距离,mPaint不是TextPaint）
            float offset = Math.abs(inputTextBean.getTextPaint().ascent() + inputTextBean.getTextPaint().descent()) / 2;
            char[] textChars = inputTextBean.getChartBean().getTdText().toCharArray();
            float ww = 0.0f;

            //计算单行文字高度
            Paint.FontMetricsInt textFm = inputTextBean.getTextPaint().getFontMetricsInt();
            int singleLineHeight = Math.abs(textFm.top - textFm.bottom);
            //绘制起点
            float startL;
            float startT = pointY[inputTextBean.getInputY()] + singleLineHeight / 2 + offset + chartPadding;
            //文字的TextAlign会影响起点X坐标
            switch (inputTextBean.getTextPaint().getTextAlign()) {
                case RIGHT:
                    startL = pointX[inputTextBean.getInputX() + 1]-chartPadding;
                    break;
                case LEFT:
                    startL = pointX[inputTextBean.getInputX()]+chartPadding;
                    break;
                default:
                    startL = pointX[inputTextBean.getInputX()] + (pointX[inputTextBean.getInputX() + 1] - pointX[inputTextBean.getInputX()]) / 2;
                    break;
            }

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < textChars.length; i++) {
                float v = inputTextBean.getTextPaint().measureText(textChars[i] + "");

                if (ww + v < width.get(inputTextBean.getInputX()) * multiple - chartPadding * 2 && textChars[i] != '\n') {
                    //未满一行
                    sb.append(textChars[i]);
                    ww += v;
                } else {
                    //满一行
                    canvas.drawText(sb.toString(), startL, startT, inputTextBean.getTextPaint());
                    //新的一行起始点startT递增
                    startT += singleLineHeight + 0.0f;
                    //重新加入新一行的文字
                    sb = new StringBuilder();
                    ww = 0.0f;
                    ww += v;
                    sb.append(textChars[i]);
                }
            }

            if (sb.toString().length() > 0) {
                canvas.drawText(sb.toString(), startL, startT, inputTextBean.getTextPaint());
            }
        }

        //绘制拉伸的绿线
        if (greenLine[0] != 0 && greenLine[1] == startPoint[1]) {
            canvas.drawLine(greenLine[0], startPoint[1], greenLine[0], startPoint[1] + allHeight, paintConfig.greenPaint);
        }
        if (greenLine[1] != 0 && greenLine[0] == startPoint[0]) {
            canvas.drawLine(startPoint[0], greenLine[1], startPoint[0] + allWidth, greenLine[1], paintConfig.greenPaint);
        }
    }

    //选中的坐标点
    public int[] downPoint = {0, 0};
    public int downType = 0;
    public int moveX = 0;
    public int moveY = 0;
    public boolean isMoveBitmap = false;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = scrollView.getScrollX() + (int) event.getRawX();
//        int y = scrollView.getScrollY() + (int) event.getRawY();
        //y坐标从屏幕顶部开始计算的，包括状态栏
        int y = scrollView.getScrollY() + (int) event.getRawY() - barHeight;

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //点击的点
                downPoint[0] = x;
                downPoint[1] = y;
                Log.i("ACTION_DOWN", x + "-----" + y);

                //判断是否点击了拉伸图标
                downType = checkBitmap(x, y);
//                if (downType == BaseConfig.TOP_BITMAP) {
//                    //点击了顶部图标
//                    Log.i("TOP_BITMAP", "");
//                    return true;
//                } else if (downType == BaseConfig.LEFT_BITMAP) {
//                    //点击了左边图标
//                    Log.i("LEFT_BITMAP", "");
//                    return true;
//                }else {
//                    return true;
//                }
//                break;
                return true;
            case MotionEvent.ACTION_MOVE:
                moveX = x;
                moveY = y;
                //拉伸滑动
                if (downType == BaseConfig.TOP_BITMAP || downType == BaseConfig.LEFT_BITMAP) {
                    isMoveBitmap = true;
                    setGreenLine(true, downType, x, y);
                    //拦截父组件滑动
                    getParent().requestDisallowInterceptTouchEvent(true);
                    //其他滑动，归还父组件
                } else {
                    getParent().requestDisallowInterceptTouchEvent(false);
                }
                break;
//            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                if (isMoveBitmap) {
                    switch (downType) {
                        case BaseConfig.TOP_BITMAP:
                            Log.i("stretchCell--TOP:", x + "----" + downPoint[0]);
                            stretchCell(x - downPoint[0], 0);
                            break;
                        case BaseConfig.LEFT_BITMAP:
                            stretchCell(0, y - downPoint[1]);
                            break;
                        default:
                            break;
                    }
                    init();
                    invalidate();
                } else {
                    //确定点击和抬起时同一个位置，即这是点击操作，增加一点容错率 ±5
                    if ((x <= downPoint[0] + 5 || x >= downPoint[0] - 5) && (y <= downPoint[1] + 5 || y >= downPoint[1] - 5)) {
                        selectCell(x, y);
                    }
                }

                setGreenLine(false, 0, 0, 0);
                isMoveBitmap = false;
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }


    //点击单元格时
    private void selectCell(int x, int y) {
        //单元格选中效果
        if (x < startPoint[0] || y < startPoint[1]) {
            return;
        }

        for (int i = 0; i < pointX.length; i++) {
            if (x > pointX[i]) {
                //避免越界
                if (i == pointX.length - 1) {
                    break;
                } else {
                    selectCell[0] = i;
                }
            } else {
                break;
            }
        }
        for (int i = 0; i < pointY.length; i++) {
            if (y > pointY[i]) {
                if (i == pointY.length - 1) {
                    break;
                } else {
                    selectCell[1] = i;
                }
            } else {
                break;
            }
        }
        invalidate();

        selectInputTextBean();
    }

    public void selectNexCell() {
        if (startPoint[0] == -1 || startPoint[1] == -1) {
            return;
        }
        //说明是最下边的单元格了
        if (selectCell[1] + 1 < pointY.length - 1) {
            selectCell[1] = selectCell[1] + 1;
            invalidate();

            selectInputTextBean();
        }
    }

    //选择单元格回调
    private void selectInputTextBean() {
        //已编辑的单元格
        for (InputTextBean inputTextBean : inputTextList) {
            if (inputTextBean.getInputX() == selectCell[0] && inputTextBean.getInputY() == selectCell[1]) {
                if (iSelectChart != null) {
                    iSelectChart.selectChart(inputTextBean);
                }
                return;
            }
        }
        //新的单元格
//        InputTextBean inputTextBean = new InputTextBean(selectCell[0], selectCell[1], TextPaintConfig.getTextPaint(), "");
//        iSelectChart.selectChart(inputTextBean);
    }

    //用于获取滑动的距离
    public void setScrollView(HVScrollView scrollView) {
        this.scrollView = scrollView;
    }

    //标题栏高度
    public void setBarHeight(int barHeight) {
        this.barHeight = barHeight;
    }

    //绘制文字
    public void setTextContent(String content) {

        if (selectCell[0] == -1 && selectCell[1] == -1) {
            return;
        }

//        boolean isSelect = false;
        TextPaint paint = null;

        for (int i = 0; i < inputTextList.size(); i++) {
            //选中单元格已有内容
            if (inputTextList.get(i).getInputX() == selectCell[0] && inputTextList.get(i).getInputY() == selectCell[1]) {
                paint = inputTextList.get(i).getTextPaint();
                //更换该单元格内容
                ChartBean chartBean = inputTextList.get(i).getChartBean();
                chartBean.setTdText(content);
                chartBean.setTdBackgroundColorStr(inputTextList.get(i).getChartBean().getTdBackgroundColorStr());
                InputTextBean inputTextBean = new InputTextBean(selectCell[0], selectCell[1], chartBean, inputTextList.get(i).getTextPaint(), inputTextList.get(i).getTdTextAttributeModelBean());
                inputTextList.set(i, inputTextBean);
//                isSelect = true;
                break;
            }
        }


        initLines(content, paint, selectCell[0], selectCell[1]);

        init();

        invalidate();
    }

    //添加行列
    public void addChart(int gravity) {
        if (selectCell[0] == -1 || selectCell[1] == -1) {
            return;
        }

        switch (gravity) {
            //左边
            case BaseConfig.ADD_LEFT:
                width.add(selectCell[0], width.get(selectCell[0]));
                defaultWidth.add(selectCell[0], width.get(selectCell[0]));
                for (InputTextBean inputTextBean : inputTextList) {
                    if (inputTextBean.getInputX() >= selectCell[0]) {
                        inputTextBean.setInputX(inputTextBean.getInputX() + 1);
                    }
                }

                //左边列补足数据
                for (int i = 0; i < heightNum; i++) {
                    InputTextBean inputTextBean = TextPaintConfig.getInputTextBean(selectCell[0], i);

                    inputTextList.add(selectCell[0] * heightNum + i, inputTextBean);
                }
                selectCell[0] = selectCell[0] + 1;

                break;
            //右边
            case BaseConfig.ADD_RIGHT:
                width.add(selectCell[0] + 1, width.get(selectCell[0]));
                defaultWidth.add(selectCell[0] + 1, width.get(selectCell[0]));
                for (InputTextBean inputTextBean : inputTextList) {
                    if (inputTextBean.getInputX() > selectCell[0]) {
                        inputTextBean.setInputX(inputTextBean.getInputX() + 1);
                    }
                }
                //右边列补足数据
                for (int i = 0; i < heightNum; i++) {
                    InputTextBean inputTextBean = TextPaintConfig.getInputTextBean(selectCell[0] + 1, i);
                    inputTextList.add((selectCell[0] + 1) * heightNum + i, inputTextBean);
                }
                break;
            //上边
            case BaseConfig.ADD_TOP:
                height.add(selectCell[1], height.get(selectCell[1]));
                defaultHeight.add(selectCell[1], height.get(selectCell[1]));

                for (InputTextBean inputTextBean : inputTextList) {
                    if (inputTextBean.getInputY() >= selectCell[1]) {
                        inputTextBean.setInputY(inputTextBean.getInputY() + 1);
                    }
                }

                //上边行补足数据
                for (int i = 0; i < widthNum; i++) {
                    InputTextBean inputTextBean = TextPaintConfig.getInputTextBean(i, selectCell[1]);
                    inputTextList.add(selectCell[1] + heightNum * i, inputTextBean);
                }
                selectCell[1] = selectCell[1] + 1;
                break;
            //下边
            case BaseConfig.ADD_BOTTOM:
                height.add(selectCell[1] + 1, height.get(selectCell[1]));
                defaultHeight.add(selectCell[1] + 1, height.get(selectCell[1]));
                for (InputTextBean inputTextBean : inputTextList) {
                    if (inputTextBean.getInputY() > selectCell[1]) {
                        inputTextBean.setInputY(inputTextBean.getInputY() + 1);
                    }
                }

                //下边行补足数据
                for (int i = 0; i < widthNum; i++) {
                    InputTextBean inputTextBean = TextPaintConfig.getInputTextBean(i, selectCell[1] + 1);
                    inputTextList.add((selectCell[1] + 1) + heightNum * i, inputTextBean);
                }
                break;
            default:
                break;
        }


        init();
        invalidate();
    }

    //删除行列
    public void deleteChart(int delete) {
        if (selectCell[0] == -1 || selectCell[1] == -1) {
            return;
        }

        switch (delete) {
            case BaseConfig.DELETE_LINE:
                //删除行
                height.remove(selectCell[1]);
                defaultHeight.remove(selectCell[1]);
                //需要删除的InputTextBean
                List<InputTextBean> removeLineList = new ArrayList<>();

                for (InputTextBean inputTextBean : inputTextList) {
                    //删除的行等于InputY时，需要删除inputTextBean
                    if (inputTextBean.getInputY() == selectCell[1]) {
                        removeLineList.add(inputTextBean);
                    }
                    //删除的行比InputY小时，InputY-1
                    if (inputTextBean.getInputY() > selectCell[1]) {
                        inputTextBean.setInputY(inputTextBean.getInputY() - 1);
                    }

                }
                //删除的行刚好就是同一行时，删除该InputTextBean
                if (removeLineList.size() != 0) {
                    for (InputTextBean inputTextBean : removeLineList) {
                        inputTextList.remove(inputTextBean);
                    }
                }

                break;
            case BaseConfig.DELETE_COLUMN:
                //删除列
                width.remove(selectCell[0]);
                defaultWidth.remove(selectCell[0]);
                //需要删除的InputTextBean
                List<InputTextBean> removeColumnList = new ArrayList<>();
                for (InputTextBean inputTextBean : inputTextList) {
                    //删除的列等于InputX时，需要删除inputTextBean
                    if (inputTextBean.getInputX() == selectCell[0]) {
                        removeColumnList.add(inputTextBean);
                    }
                    //删除的列比InputX小时，InputX-1
                    if (inputTextBean.getInputX() > selectCell[0]) {
                        inputTextBean.setInputX(inputTextBean.getInputX() - 1);
                    }
                }
                //删除的列刚好就是同一列时，删除该InputTextBean
                if (removeColumnList.size() != 0) {
                    for (InputTextBean inputTextBean : removeColumnList) {
                        inputTextList.remove(inputTextBean);
                    }
                }
                break;
            default:
                break;
        }
        selectCell[0] = -1;
        selectCell[1] = -1;
        init();
        invalidate();
    }

    /*----------------------------------------------------拉伸相关--------------------------------------------------------*/

    //检查是否点击了图标
    public int checkBitmap(int x, int y) {
        //上边
        if (x >= bitmapX[0] && x <= bitmapX[0] + bitmapWidth && y >= bitmapX[1] && y <= bitmapX[1] + bitmapHeight) {
            return BaseConfig.TOP_BITMAP;
        }
        //左边
        if (x >= bitmapY[0] && x <= bitmapY[0] + bitmapWidth && y >= bitmapY[1] && y <= bitmapY[1] + bitmapHeight) {
            return BaseConfig.LEFT_BITMAP;
        }
        return 0;
    }

    //拉伸单元格
    public void stretchCell(int moveX, int moveY) {
        if (moveX != 0) {

            float moveWidth = width.get(selectCell[0]) + moveX / multiple;

            if (moveWidth < defaultWidth.get(selectCell[0])) {
                moveWidth = defaultWidth.get(selectCell[0]);
            }
            width.set(selectCell[0], moveWidth);
        }

        if (moveY != 0) {
            float moveHeight = (height.get(selectCell[1]) + moveY / multiple);
            if (moveHeight < defaultHeight.get(selectCell[1])) {
                moveHeight = defaultHeight.get(selectCell[1]);
            }
            height.set(selectCell[1], moveHeight);
        }
    }

    //拉伸时的绿线
    private void setGreenLine(boolean isShow, int type, int x, int y) {
        if (isShow) {
            switch (type) {
                case BaseConfig.TOP_BITMAP:
                    greenLine[0] = x;
                    greenLine[1] = startPoint[1];
                    break;
                case BaseConfig.LEFT_BITMAP:
                    greenLine[0] = startPoint[0];
                    greenLine[1] = y;
                    break;
                default:
                    break;
            }
        } else {
            greenLine[0] = 0;
            greenLine[1] = 0;
        }
        invalidate();
    }

    /*----------------------------------------------------文字属性--------------------------------------------------------*/
    //粗体
    public void setFakeBoldText(final boolean isBold) {
        doTextType((inputTextBean, tdTextAttributeModelBean, textPaint) -> {
            textPaint.setFakeBoldText(isBold);
            tdTextAttributeModelBean.setBold(isBold);
        });
    }

    //斜体
    public void setTextSkewX(final boolean isSkew) {
        doTextType((inputTextBean, tdTextAttributeModelBean, textPaint) -> {
            textPaint.setTextSkewX(isSkew ? -0.5f : 0f);
            tdTextAttributeModelBean.setTilt(isSkew);
        });
    }

    //下划线
    public void setUnderline(final boolean isUnderline) {
        doTextType((inputTextBean, tdTextAttributeModelBean, textPaint) -> {
            textPaint.setUnderlineText(isUnderline);
            tdTextAttributeModelBean.setUnder(isUnderline);
        });
    }

    //删除线
    public void setDeleteLine(final boolean isDeleteLine) {
        doTextType((inputTextBean, tdTextAttributeModelBean, textPaint) -> {
            textPaint.setStrikeThruText(isDeleteLine);
            tdTextAttributeModelBean.setDeleteLine(isDeleteLine);
        });
    }

    //设置字体大小
    public void setTextSize(final int size) {
        doTextType((inputTextBean, tdTextAttributeModelBean, textPaint) -> {
            textPaint.setTextSize(RxImageTool.sp2px(size));
            tdTextAttributeModelBean.setPointSize(size);
        });
    }

    /**
     * 设置字体颜色
     */
    public void setTextColor(final String color) {
        doTextType((inputTextBean, tdTextAttributeModelBean, textPaint) -> {
            textPaint.setColor(TextPaintUtils.hexToColor(color));
            tdTextAttributeModelBean.setColorStr(color);
        });
    }

    /**
     * 设置字体类型
     */
    public void setTypeface(final Typeface typeface) {
        doTextType((inputTextBean, tdTextAttributeModelBean, textPaint) -> {
            textPaint.setTypeface(typeface);

            if (Typeface.SANS_SERIF.equals(typeface)) {
                tdTextAttributeModelBean.setFontName("PingFangSC-Regular");
            } else if (Typeface.DEFAULT_BOLD.equals(typeface)) {
                tdTextAttributeModelBean.setFontName("PingFangSC-Regular");
            } else if (Typeface.SERIF.equals(typeface)) {
                tdTextAttributeModelBean.setFontName("PingFangSC-Regular");
            }
        });
    }

    /**
     * 设置背景颜色
     */
    public void setBackGroundColor(final String color) {
        doTextType((xInputTextBean, tdTextAttributeModelBean, textPaint) -> {
            //选中了单元格
            if (selectCell[0] != -1 && selectCell[1] != -1) {
                for (int i = 0; i < inputTextList.size(); i++) {
                    InputTextBean inputTextBean = inputTextList.get(i);
                    //已经在inputTextList里，更新其TdBackgroundColorStr
                    if (inputTextBean.getInputX() == selectCell[0] && inputTextBean.getInputY() == selectCell[1]) {
                        ChartBean chartBean1 = inputTextBean.getChartBean();
                        chartBean1.setTdBackgroundColorStr(color);
                        inputTextBean.setChartBean(chartBean1);
                        inputTextList.set(i, inputTextBean);
                        invalidate();
                        return;
                    }
                }
            }
//                //新的单元格
//                InputTextBean inputTextBean = new InputTextBean(selectCell[0], selectCell[1], textPaint, "");
//                inputTextBean.getChartBean().setTdBackgroundColorStr(color);
//                inputTextList.add(inputTextBean);
//                invalidate();
        });

    }

    //设置gravity
    public void setGravity(final Paint.Align align) {
        doTextType((inputTextBean, tdTextAttributeModelBean, textPaint) -> {
            textPaint.setTextAlign(align);
            switch (align) {
                case LEFT:
                    tdTextAttributeModelBean.setTextAlignment(1);
                    break;
                case RIGHT:
                    tdTextAttributeModelBean.setTextAlignment(3);
                    break;
                case CENTER:
                    tdTextAttributeModelBean.setTextAlignment(2);
                    break;
            }
        });

    }

    private void doTextType(ITextType iTextType) {
        //选中了单元格
        if (selectCell[0] != -1 && selectCell[1] != -1) {
            for (int i = 0; i < inputTextList.size(); i++) {
                InputTextBean inputTextBean = inputTextList.get(i);
                //已经在inputTextList里，更新其TextPaint
                if (inputTextBean.getInputX() == selectCell[0] && inputTextBean.getInputY() == selectCell[1]) {
                    iTextType.doType(inputTextBean, inputTextBean.getTdTextAttributeModelBean(), inputTextBean.getTextPaint());

//                    ChartBean chartBean = inputTextBean.getChartBean();
//                    TextPaint textPaint = inputTextBean.getTextPaint();
//                    ChartBean.TdTextAttributeModelBean tdTextAttributeModelBean = new ChartBean.TdTextAttributeModelBean();

                    inputTextList.set(i, inputTextBean);
                    invalidate();
                    return;
                }
            }
        }
//        //新的单元格
//        TextPaint textPaint = TextPaintConfig.getTextPaint();
//        InputTextBean inputTextBean = new InputTextBean(selectCell[0], selectCell[1], textPaint, "");
//        iTextType.doType(inputTextBean, inputTextBean.getChartBean(),textPaint);
//        inputTextList.add(inputTextBean);
//        invalidate();
    }

    /*----------------------------------------------------算法--------------------------------------------------------*/


    //算法
    public String doMath(final BaseConfig.MathType mathType, final BaseConfig.ScopeType scopeType, final int decimal) {
        doTextType((inputTextBean, tdTextAttributeModelBean, textPaint) -> {
                    inputTextBean.setMathType(mathType);
                    inputTextBean.setScopeType(scopeType);
                    inputTextBean.setDecimal(decimal);
                }
        );

        List<InputTextBean> beanList = new ArrayList<>();
        //先获取要计算的内容

        for (InputTextBean inputTextBean : inputTextList) {
            switch (scopeType) {
                case LEFT:
                    if (inputTextBean.getInputY() == selectCell[1] && inputTextBean.getInputX() < selectCell[0]) {
                        beanList.add(inputTextBean);
                    }

                    break;
                case RIGHT:
                    if (inputTextBean.getInputY() == selectCell[1] && inputTextBean.getInputX() > selectCell[0]) {
                        beanList.add(inputTextBean);
                    }
                    break;
                case TOP:
                    if (inputTextBean.getInputY() < selectCell[1] && inputTextBean.getInputX() == selectCell[0]) {
                        beanList.add(inputTextBean);
                    }
                    break;
                case BOTTOM:
                    if (inputTextBean.getInputY() > selectCell[1] && inputTextBean.getInputX() == selectCell[0]) {
                        beanList.add(inputTextBean);
                    }
                    break;
                case UP_LEFT:
                    //左边
                    if (inputTextBean.getInputY() == selectCell[1] && inputTextBean.getInputX() == selectCell[0] - 1) {
                        beanList.add(inputTextBean);
                    }
                    //上边
                    if (inputTextBean.getInputY() == selectCell[1] - 1 && inputTextBean.getInputX() == selectCell[0]) {
                        beanList.add(inputTextBean);
                    }
                    break;
                default:
                    break;
            }
        }

        //二分法
        for (int i = 0; i < beanList.size(); i++) {
            for (int j = 0; j < beanList.size() - 1 - i; j++) {
                switch (scopeType) {
                    case LEFT:
                    case RIGHT:
                    case UP_LEFT:
                        if (beanList.get(j + 1).getInputX() < beanList.get(j).getInputX()) {
                            InputTextBean temp = beanList.get(j + 1);
                            beanList.set(j + 1, beanList.get(j));
                            beanList.set(j, temp);
                        }
                        break;
                    case TOP:
                    case BOTTOM:
                        if (beanList.get(j + 1).getInputY() < beanList.get(j).getInputY()) {
                            InputTextBean temp = beanList.get(j + 1);
                            beanList.set(j + 1, beanList.get(j));
                            beanList.set(j, temp);
                        }
                        break;
                    default:
                        break;
                }
            }
        }
        //UP_LEFT单独处理，因为只有两个数据

        List<String> strList = new ArrayList<>();
        for (InputTextBean inputTextBean : beanList) {
            strList.add(inputTextBean.getChartBean().getTdText());
        }
        //筛选出数字
        List<Float> numList = new ArrayList<>();
        for (String number : strList) {

            Matcher isNum = pattern.matcher(number);
            if (isNum.matches()) {
                numList.add(Float.valueOf(number));
            }
        }
//        ADDITION, SUBTRACTION, MULTIPLY, DIVIDE, AVERAGE, MAX, MIN,
        String numStr = "";
        if (numList.size() > 0) {
            float num;
            switch (mathType) {
                case ADDITION:
                    num = 0;
                    for (float number : numList) {
                        num = num + number;
                    }
                    break;
                case SUBTRACTION:
                    num = numList.get(0);
                    for (int i = 1; i < numList.size(); i++) {
                        num = num - numList.get(i);
                    }
                    break;
                case MULTIPLY:
                    num = 1;
                    for (float number : numList) {
                        num = num * number;
                    }
                    break;
                case DIVIDE:
                    num = numList.get(0);

                    if (numList.size() == 1) {
                        return numList.get(0) + "";
                    }

                    for (int i = 1; i < numList.size(); i++) {
                        if (numList.get(i) == 0) {
                            return "inf";
                        } else {
                            num = (float) (num / numList.get(i));
                        }
                    }
                    break;
                case AVERAGE:
                    num = 0;
                    for (float number : numList) {
                        num = num + number;
                    }
                    num = num / numList.size();
                    break;
                case MAX:
                    num = numList.get(0);
                    for (int i = 1; i < numList.size(); i++) {
                        if (num < numList.get(i)) {
                            num = numList.get(i);
                        }
                    }
                    break;
                case MIN:
                    num = numList.get(0);
                    for (int i = 1; i < numList.size(); i++) {
                        if (num > numList.get(i)) {
                            num = numList.get(i);
                        }
                    }
                    break;
                default:
                    num = 0;
                    break;
            }

            numStr = num + "";
        } else {
            return "inf";
        }

        BigDecimal bd = new BigDecimal(numStr);
        bd = bd.setScale(decimal, BigDecimal.ROUND_HALF_UP);
        return bd.toString();
    }

    //查找替换
    public void setReplace(String content, String replaceStr) {
        for (InputTextBean inputTextBean : inputTextList) {
            String text = inputTextBean.getChartBean().getTdText();
            if (text.contains(content)) {
                String replaceText = text.replace(content, replaceStr);
                ChartBean chartBean = inputTextBean.getChartBean();
                chartBean.setTdText(replaceText);
                inputTextBean.setChartBean(chartBean);

                initLines(replaceText, inputTextBean.getTextPaint(), inputTextBean.getInputX(), inputTextBean.getInputY());

            }
        }
        selectInputTextBean();
        init();
        invalidate();
    }

    //计算文字行数
    private void initLines(String text, TextPaint textPaint, int x, int y) {
        //计算单行文字高度
        Paint.FontMetricsInt textFm = textPaint.getFontMetricsInt();
        float paintHeight = Math.abs(textFm.top - textFm.bottom);
        //计算行数
        char[] textChars = text.toCharArray();
        //当超过单元格宽度时，行数n +1，ww=0.0f;
        float ww = 0.0f;
        //行数
        int n = 1;
        for (int i = 0; i < textChars.length; i++) {
            float v = textPaint.measureText(textChars[i] + "");
            if (ww + v < width.get(x) * multiple - chartPadding * 2 && textChars[i] != '\n') {
                ww += v;
            } else {
                ww = 0.0f;
                ww += v;
                n++;
            }
        }
        float textHeight = paintHeight * n + chartPadding * 2;
        //当文字行数高度大于单元格高度时，则将单元格高度置为文字高度
        if (textHeight >= height.get(y) * multiple) {
            height.set(y, textHeight / multiple);
        }
    }

    //统一宽高
    private List<Float> mWidth = new ArrayList<>();
    private List<Float> mHeight = new ArrayList<>();
    //是否是统一宽高
    public boolean isUnifiedWidthHeight = false;

    public void unifiedWidthHeight() {
        mWidth.clear();
        mWidth.addAll(width);

        mHeight.clear();
        mHeight.addAll(height);

        float maxWidth = width.get(0);
        for (int i = 1; i < width.size(); i++) {
            if (maxWidth < width.get(i)) {
                maxWidth = width.get(i);
            }
        }
        float maxHeight = height.get(0);
        for (int i = 1; i < height.size(); i++) {
            if (maxHeight < height.get(i)) {
                maxHeight = height.get(i);
            }
        }

        for (int i = 0; i < width.size(); i++) {
            width.set(i, maxWidth);
        }
        for (int i = 0; i < height.size(); i++) {
            height.set(i, maxHeight);
        }

        init();
        invalidate();

        isUnifiedWidthHeight = true;
    }

    //撤销统一宽高
    public void undoWidthHeight() {
        width.clear();
        width.addAll(mWidth);

        height.clear();
        height.addAll(mHeight);

        init();
        invalidate();

        isUnifiedWidthHeight = false;
    }

    /*----------------------------------------------------get--------------------------------------------------------*/
    //获取当前选择的单元格InputTextBean
    public InputTextBean getSelectInputTextBean() {
        if (selectCell[0] != -1 && selectCell[1] != -1) {
            for (int i = 0; i < inputTextList.size(); i++) {
                if (inputTextList.get(i).getInputX() == selectCell[0] &&
                        inputTextList.get(i).getInputY() == selectCell[1]) {
                    return inputTextList.get(i);
                }
            }
        }
        return null;
    }

    public List<InputTextBean> getInputTextList() {
        return inputTextList;
    }

    public List<Float> getHeightList() {
        return height;
    }

    public List<Float> getWidthList() {
        return width;
    }

    /*----------------------------------------------------回调--------------------------------------------------------*/
    //选择的单元格后回调，目前用来返回单元格的内容
    ISelectChart iSelectChart;

    public void setISelectChart(ISelectChart iSelectChart) {
        this.iSelectChart = iSelectChart;
    }

    public interface ISelectChart {
        void selectChart(InputTextBean inputTextBean);
    }

    private interface ITextType {
        void doType(InputTextBean inputTextBean, ChartBean.TdTextAttributeModelBean tdTextAttributeModelBean, TextPaint textPaint);
    }
}
