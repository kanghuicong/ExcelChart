package com.kang.excelchart;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


import com.kang.excelchart.base.BaseConfig;
import com.kang.excelchart.base.PaintConfig;
import com.kang.excelchart.base.TextPaintConfig;
import com.kang.excelchart.bean.InputTextBean;
import com.vondear.rxtool.RxBarTool;
import com.vondear.rxtool.RxLogTool;

import java.util.ArrayList;
import java.util.List;

/**
 * 类描述：
 */
public class ChartView extends View {
    private Context context;

    private List<Integer> height = new ArrayList<>();
    private List<Integer> width = new ArrayList<>();

    private int multiple = 1; //放大倍数
    private int heightNum;//行数
    private int widthNum;//列数

    public int allHeight = 0;//总高度
    public int allWidth = 0;//总宽度

    private int[] pointX;//x坐标
    private int[] pointY;//y坐标

    public int[] startPoint = {80, 80};//起点
    public final int minCellX = 200;//单元格最小宽度
    public final int minCellY = 80;//单元格最小高度

    private HVScrollView scrollView;
    private PaintConfig paintConfig;//画笔
    private Bitmap arrowBitmap;
    private int bitmapHeight;
    private int bitmapWidth;
    private int barHeight;
    private int[] bitmapX = {0, 0};
    private int[] bitmapY = {0, 0};

    private int[] greenLine = {0, 0};//绿线

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


    public void setChartData(List<Integer> width, List<Integer> height) {
        this.width.clear();
        this.height.clear();
        this.width.addAll(width);
        this.height.addAll(height);

        init();
        invalidate();
    }

    public void initConfig() {
        paintConfig = new PaintConfig();

        arrowBitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.arrow);
        bitmapHeight = arrowBitmap.getHeight();
        bitmapWidth = arrowBitmap.getWidth();

//        barHeight = RxBarTool.getStatusBarHeight(context);
    }


    public void init() {

        heightNum = height.size();
        widthNum = width.size();

        pointX = new int[widthNum + 1];//x坐标
        pointY = new int[heightNum + 1];//y坐标

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
        this.setMinimumHeight(allHeight + startPoint[1] * 2);
        this.setMinimumWidth(allWidth + startPoint[0] * 2);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int startX = startPoint[0];
        //画竖线
        for (int i = 0; i < widthNum + 1; i++) {
            canvas.drawLine(startX, startPoint[1], startX, startPoint[1] + allHeight, paintConfig.linePaint);
            if (i != widthNum) startX = startX + width.get(i) * multiple;
        }
        //画横线
        int startY = startPoint[1];
        for (int i = 0; i < heightNum + 1; i++) {
            canvas.drawLine(startPoint[0], startY, startPoint[0] + allWidth, startY, paintConfig.linePaint);
            if (i != heightNum) startY = startY + height.get(i) * multiple;
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
            canvas.drawBitmap(arrowBitmap, pointX[selectCell[0] + 1] - (float) 1 / 2 * bitmapWidth, startPoint[1] - bitmapHeight, new Paint());
            canvas.drawBitmap(arrowBitmap, startPoint[0] - bitmapWidth, pointY[selectCell[1] + 1] - (float) 1 / 2 * bitmapHeight, new Paint());

            //记录图标的左上角
            bitmapX[0] = (int) (pointX[selectCell[0] + 1] - (float) 1 / 2 * bitmapWidth);
            bitmapX[1] = startPoint[1] - bitmapHeight;

            bitmapY[0] = startPoint[0] - bitmapWidth;
            bitmapY[1] = (int) (pointY[selectCell[1] + 1] - (float) 1 / 2 * bitmapHeight);
        }

        //绘制文字
        for (InputTextBean inputTextBean : inputTextList) {

//            StaticLayout layout = new StaticLayout(inputTextBean.getContent(),
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
//            canvas.drawText(inputTextBean.getContent(),
//                    pointX[inputTextBean.getInputX()] + (pointX[inputTextBean.getInputX() + 1] - pointX[inputTextBean.getInputX()]) / 2,
//                    pointY[inputTextBean.getInputY()] + (pointY[inputTextBean.getInputY() + 1] - pointY[inputTextBean.getInputY()]) / 2 + offset,
//                    paintConfig.textPaint);

            /*---------------------------------*/
//            float offset = Math.abs(inputTextBean.getTextPaint().ascent() + inputTextBean.getTextPaint().descent()) / 2;
//            int start = 0;
//            Paint.FontMetrics fm = inputTextBean.getTextPaint().getFontMetrics();
//            float paintHeight = fm.bottom - fm.top;
//
//            while (start < inputTextBean.getContent().length()) {
//                int breakTextCount = inputTextBean.getTextPaint().breakText(
//                        inputTextBean.getContent(),
//                        true,     // 是否正向绘制
//                        width.get(inputTextBean.getInputX()),      // 最大绘制宽度
//                        measuredWidth);      // 绘制本行文字的宽度存在这个数组中
//
//                canvas.drawText(
//                        inputTextBean.getContent(),
//                        start,
//                        (start + breakTextCount > inputTextBean.getContent().length()) ? inputTextBean.getContent().length() : start + breakTextCount,
//                        pointX[inputTextBean.getInputX()] + (pointX[inputTextBean.getInputX() + 1] - pointX[inputTextBean.getInputX()]) / 2,
//                        pointY[inputTextBean.getInputY()] + paintHeight / 2 + offset,
//                        inputTextBean.getTextPaint()
//                );
//                start += breakTextCount;
//                offset += inputTextBean.getTextPaint().getFontSpacing();
//
//            }
            /*------------------------*/
            // 单行绘制 （先计算出基线和到文字中间的距离,mPaint不是TextPaint）
            float offset = Math.abs(inputTextBean.getTextPaint().ascent() + inputTextBean.getTextPaint().descent()) / 2;
            char[] textChars = inputTextBean.getContent().toCharArray();
            float ww = 0.0f;

            //计算单行文字高度
            Paint.FontMetricsInt textFm = inputTextBean.getTextPaint().getFontMetricsInt();
            int singleLineHeight = Math.abs(textFm.top - textFm.bottom);
            //绘制起点
            float startL = pointX[inputTextBean.getInputX()] + (pointX[inputTextBean.getInputX() + 1] - pointX[inputTextBean.getInputX()]) / 2;
            float startT = pointY[inputTextBean.getInputY()] + singleLineHeight / 2 + offset;

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < textChars.length; i++) {
                float v = inputTextBean.getTextPaint().measureText(textChars[i] + "");
                if (ww + v < width.get(inputTextBean.getInputX())) {
                    sb.append(textChars[i]);
                    ww += v;
                } else {
                    canvas.drawText(sb.toString(), startL, startT, inputTextBean.getTextPaint());
                    startT += singleLineHeight + 0.0f;
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

    private float[] measuredWidth = new float[1];
    public int[] downPoint = {0, 0};//选中的坐标点
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
                return true;
//                break;
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
                } else getParent().requestDisallowInterceptTouchEvent(false);
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
                    }
                    init();
                    invalidate();
                } else {
                    //确定点击和抬起时同一个位置，即这是点击操作，增加一点容错率 +-5
                    if ((x <= downPoint[0] + 5 || x >= downPoint[0] - 5) && (y <= downPoint[1] + 5 || y >= downPoint[1] - 5))
                        selectCell(x, y);
                }

                setGreenLine(false, 0, 0, 0);
                isMoveBitmap = false;
                break;
        }
        return super.onTouchEvent(event);
    }

    public int[] selectCell = {-1, -1};//选中框左上角，非坐标而是个数

    //点击单元格时
    private void selectCell(int x, int y) {
        //单元格选中效果
        if (x < startPoint[0] || y < startPoint[1]) return;

        for (int i = 0; i < pointX.length; i++) {
            if (x > pointX[i]) {
                //避免越界
                if (i == pointX.length - 1) {
                    break;
                } else {
                    selectCell[0] = i;
                }
            } else break;
        }
        for (int i = 0; i < pointY.length; i++) {
            if (y > pointY[i]) {
                if (i == pointY.length - 1) {
                    break;
                } else {
                    selectCell[1] = i;
                }
            } else break;
        }
        invalidate();

        for (InputTextBean inputTextBean : inputTextList) {
            if (inputTextBean.getInputX() == selectCell[0] && inputTextBean.getInputY() == selectCell[1]) {
                if (iSelectChart != null) {
                    iSelectChart.selectChart(inputTextBean);
                }
                return;
            }
//            else if (iSelectChart != null)
//                iSelectChart.selectChart(new InputTextBean(selectCell[0], selectCell[1], ""));
        }


        iSelectChart.selectChart(new InputTextBean(selectCell[0], selectCell[1], paintConfig.textPaint, ""));
    }

    //用于获取滑动的距离
    public void setScrollView(HVScrollView scrollView) {
        this.scrollView = scrollView;
    }

    //标题栏高度
    public void setBarHeight(int barHeight) {
        this.barHeight = barHeight;
    }

    private List<InputTextBean> inputTextList = new ArrayList<>();//输入的文字集合，包括左上角和内容

    //绘制文字
    public void setTextContent(EditText et, String content) {

        //如果文字长度超出单元格长度，加长单元格
        if (selectCell[0] == -1 && selectCell[1] == -1) return;

        boolean isSelect = false;
        TextPaint paint = null;

        for (int i = 0; i < inputTextList.size(); i++) {
            //选中单元格已有内容
            if (inputTextList.get(i).getInputX() == selectCell[0] && inputTextList.get(i).getInputY() == selectCell[1]) {
                paint = inputTextList.get(i).getTextPaint();
                //更换该单元格内容
                inputTextList.set(i, new InputTextBean(selectCell[0], selectCell[1], inputTextList.get(i).getTextPaint(), content));
                isSelect = true;
                break;
            }
        }

        //选中新的单元格，则新增
        if (!isSelect) {
            paint = paintConfig.textPaint;
            inputTextList.add(new InputTextBean(selectCell[0], selectCell[1], paintConfig.textPaint, content));
        }

        //计算单行文字高度
        Paint.FontMetricsInt textFm = paint.getFontMetricsInt();
        int paintHeight = Math.abs(textFm.top - textFm.bottom);

        //计算行数
        char[] textChars = content.toCharArray();
        float ww = 0.0f; //当超过单元格宽度时，行数n +1，ww=0.0f;
        int n = 1;//行数
        for (int i = 0; i < textChars.length; i++) {
            float v = paint.measureText(textChars[i] + "");
            if (ww + v < width.get(selectCell[0])) {
                ww += v;
            } else {
                ww = 0.0f;
                ww += v;
                n++;
            }
        }

        //当文字行数高度大于单元格高度时，则将单元格高度置为文字高度
        if (paintHeight * n >= height.get(selectCell[1])) {
            height.set(selectCell[1], (paintHeight) * n);
        }
        init();

        invalidate();
    }

    //添加行列
    public void addChart(int gravity) {
        if (selectCell[0] == -1 || selectCell[1] == -1) {
            return;
        }

        switch (gravity) {
            case BaseConfig.ADD_LEFT:
                width.add(selectCell[0], width.get(selectCell[0]));
                for (InputTextBean inputTextBean : inputTextList) {
                    if (inputTextBean.getInputX() >= selectCell[0])
                        inputTextBean.setInputX(inputTextBean.getInputX() + 1);
                }
                selectCell[0] = selectCell[0] + 1;
                break;
            case BaseConfig.ADD_RIGHT:
                width.add(selectCell[0] + 1, width.get(selectCell[0]));
                for (InputTextBean inputTextBean : inputTextList) {
                    if (inputTextBean.getInputX() > selectCell[0])
                        inputTextBean.setInputX(inputTextBean.getInputX() + 1);
                }
                break;
            case BaseConfig.ADD_TOP:
                height.add(selectCell[1], height.get(selectCell[1]));

                for (InputTextBean inputTextBean : inputTextList) {
                    if (inputTextBean.getInputY() >= selectCell[1])
                        inputTextBean.setInputY(inputTextBean.getInputY() + 1);
                }
                selectCell[1] = selectCell[1] + 1;
                break;
            case BaseConfig.ADD_BOTTOM:
                height.add(selectCell[1] + 1, height.get(selectCell[1]));
                for (InputTextBean inputTextBean : inputTextList) {
                    if (inputTextBean.getInputY() > selectCell[1])
                        inputTextBean.setInputY(inputTextBean.getInputY() + 1);
                }
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
                //需要删除的InputTextBean
                List<InputTextBean> removeLineList = new ArrayList<>();

                for (InputTextBean inputTextBean : inputTextList) {
                    //删除的行等于InputY时，需要删除inputTextBean
                    if (inputTextBean.getInputY() == selectCell[1]) {
                        removeLineList.add(inputTextBean);
                    }
                    //删除的行比InputY小时，InputY-1
                    if (inputTextBean.getInputY() > selectCell[1])
                        inputTextBean.setInputY(inputTextBean.getInputY() - 1);

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
                //需要删除的InputTextBean
                List<InputTextBean> removeColumnList = new ArrayList<>();
                for (InputTextBean inputTextBean : inputTextList) {
                    //删除的列等于InputX时，需要删除inputTextBean
                    if (inputTextBean.getInputX() == selectCell[0]) {
                        removeColumnList.add(inputTextBean);
                    }
                    //删除的列比InputX小时，InputX-1
                    if (inputTextBean.getInputX() > selectCell[0])
                        inputTextBean.setInputX(inputTextBean.getInputX() - 1);
                }
                //删除的列刚好就是同一列时，删除该InputTextBean
                if (removeColumnList.size() != 0) {
                    for (InputTextBean inputTextBean : removeColumnList) {
                        inputTextList.remove(inputTextBean);
                    }
                }
                break;
        }
        selectCell[0] = -1;
        selectCell[1] = -1;
        init();
        invalidate();
    }

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
            Log.i("stretchCell--X:", moveX + "");

            int moveWidth = width.get(selectCell[0]) + moveX;

            Log.i("stretchCell--moveWidth:", width.get(selectCell[0]) + "----" + moveX);
            if (moveWidth < minCellX) moveWidth = minCellX;
            width.set(selectCell[0], moveWidth);
        }

        if (moveY != 0) {
            Log.i("stretchCell--Y:", moveY + "");
            int moveHeight = height.get(selectCell[1]) + moveY;
            if (moveHeight < minCellY) moveHeight = minCellY;
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
            }
        } else {
            greenLine[0] = 0;
            greenLine[1] = 0;
        }
        invalidate();
    }

    /*----------------------------------------------------文字属性--------------------------------------------------------*/
    //粗体
    public void setFakeBoldText(boolean isBold) {
        //选中了单元格

        if (selectCell[0] != -1 && selectCell[1] != -1) {
            for (int i = 0; i < inputTextList.size(); i++) {
                //已经在inputTextList里，更新其TextPaint
                if (inputTextList.get(i).getInputX() == selectCell[0] && inputTextList.get(i).getInputY() == selectCell[1]) {
                    TextPaint textPaint = inputTextList.get(i).getTextPaint();
                    textPaint.setFakeBoldText(isBold);
                    inputTextList.get(i).setTextPaint(textPaint);
                    invalidate();
                    return;
                }
            }
        }
        TextPaintConfig textPaintConfig = new TextPaintConfig();
        TextPaint textPaint = textPaintConfig.getTextPaint();
        textPaint.setFakeBoldText(isBold);
        inputTextList.add(new InputTextBean(selectCell[0], selectCell[1], textPaint, ""));
        invalidate();
    }


    //选择的单元格后回调，目前用来返回单元格的内容
    ISelectChart iSelectChart;

    public void setISelectChart(ISelectChart iSelectChart) {
        this.iSelectChart = iSelectChart;
    }

    public interface ISelectChart {
        void selectChart(InputTextBean inputTextBean);
    }
}
