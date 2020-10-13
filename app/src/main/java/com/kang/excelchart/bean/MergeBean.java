package com.kang.excelchart.bean;

import android.graphics.Paint;

/**
 * 类描述：
 * author:kanghuicong
 */
public class MergeBean {
    int[] topCell;
    int[] bottomCell;
    Paint linePaint;
    InputTextBean inputTextBean;

    public InputTextBean getInputTextBean() {
        return inputTextBean;
    }

    public void setInputTextBean(InputTextBean inputTextBean) {
        this.inputTextBean = inputTextBean;

    }

    public int[] getTopCell() {
        return topCell;
    }

    public void setTopCell(int[] topCell) {
        this.topCell = topCell;
    }

    public int[] getBottomCell() {
        return bottomCell;
    }

    public void setBottomCell(int[] bottomCell) {
        this.bottomCell = bottomCell;
    }

    public Paint getLinePaint() {
        return linePaint;
    }

    public void setLinePaint(Paint linePaint) {
        this.linePaint = linePaint;
    }
}
