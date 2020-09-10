package com.kang.excelchart.bean;

import android.graphics.Paint;

/**
 * 类描述：
 * author:kanghuicong
 */
public class MergeBean {
    int[] topCell;
    int[] bottomCell;
    Paint paint;

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

    public Paint getPaint() {
        return paint;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }
}
