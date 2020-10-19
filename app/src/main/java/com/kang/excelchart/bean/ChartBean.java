package com.kang.excelchart.bean;

import com.alibaba.fastjson.annotation.JSONField;
import com.kang.excelchart.config.BaseConfig;
import com.kang.excelchart.config.PaintConfig;
import com.kang.excelchart.config.TextPaintConfig;
import com.kang.excelchart.utils.TextPaintUtils;

import java.util.List;

import static com.kang.excelchart.config.PaintConfig.defaultLineColor;

/**
 * 类描述：
 */
public class ChartBean {

    /**
     * isFx : false
     * isBottomBorder : false
     * fxStr : 求和:左边:取整:不隔断
     * isLeftBorder : false
     * chartOrientation : 0
     * chart : 0
     * tdSizeStr : {1, 1}
     * tdText :
     * isRightBorder : false
     * tdBorderColorStr : #ffdee0e2
     * chartTheme : 0
     * tdBackgroundColorStr : #ffffffff
     * tdTextAttributeModel : {"fontName":"PingFangSC-Regular","isBold":false,"isTilt":false,"colorStr":"#ff000000","textAlignment":1,"pointSize":14,"isCenter":false,"isUnder":false}
     * isFill : true
     * isTopBorder : false
     */
    @JSONField(name = "if")
    private boolean isFx = false;//是否计算
    @JSONField(name = "b")
    private boolean isBottomBorder = false;//下边框
    @JSONField(name = "fx")
    private String fxStr = "";//计算公式
    @JSONField(name = "l")
    private boolean isLeftBorder = false;//左边框
    @JSONField(name = "o")
    private int chartOrientation = 0;//图表对齐方式
    @JSONField(name = "ic")
    private int chart = 0;//是否图表
    @JSONField(name = "s")
    private String tdSizeStr = "{1,1}";//单元格size,没合并的都是1.1
    @JSONField(name = "tx")
    private String tdText = "";//单元格文字
    @JSONField(name = "r")
    private boolean isRightBorder = false;//右边框
    @JSONField(name = "boc")
    private String tdBorderColorStr = PaintConfig.defaultLineColor;//边框颜色
    @JSONField(name = "k")
    private int chartTheme = 0;//图标主题
    @JSONField(name = "j")
    private String chartTitle = "";//图标标题
    @JSONField(name = "bc")
    private String tdBackgroundColorStr = TextPaintConfig.defaultBackgroundColorStr;//背景颜色
    @JSONField(name = "m")
    private String tdTextAttributeModel;
    @JSONField(name = "f")
    private boolean isFill = true;//是否填满
    @JSONField(name = "t")
    private boolean isTopBorder = false;//上边框

    public boolean isFx() {
        return isFx;
    }

    public void setFx(boolean fx) {
        isFx = fx;
    }

    public boolean isBottomBorder() {
        return isBottomBorder;
    }

    public void setBottomBorder(boolean bottomBorder) {
        isBottomBorder = bottomBorder;
    }

    public String getFxStr() {
        return fxStr;
    }

    public void setFxStr(String fxStr) {
        this.fxStr = fxStr;
    }

    public boolean isLeftBorder() {
        return isLeftBorder;
    }

    public void setLeftBorder(boolean leftBorder) {
        isLeftBorder = leftBorder;
    }

    public int getChartOrientation() {
        return chartOrientation;
    }

    public void setChartOrientation(int chartOrientation) {
        this.chartOrientation = chartOrientation;
    }

    public int getChart() {
        return chart;
    }

    public void setChart(int chart) {
        this.chart = chart;
    }

    public String getTdSizeStr() {
        return tdSizeStr;
    }

    public void setTdSizeStr(String tdSizeStr) {
        this.tdSizeStr = tdSizeStr;
    }

    public String getTdText() {
        return tdText;
    }

    public void setTdText(String tdText) {
        this.tdText = tdText;
    }

    public boolean isRightBorder() {
        return isRightBorder;
    }

    public void setRightBorder(boolean rightBorder) {
        isRightBorder = rightBorder;
    }

    public String getTdBorderColorStr() {
        return tdBorderColorStr;
    }

    public void setTdBorderColorStr(String tdBorderColorStr) {
        this.tdBorderColorStr = tdBorderColorStr;
    }

    public int getChartTheme() {
        return chartTheme;
    }

    public void setChartTheme(int chartTheme) {
        this.chartTheme = chartTheme;
    }

    public String getChartTitle() {
        return chartTitle;
    }

    public void setChartTitle(String chartTitle) {
        this.chartTitle = chartTitle;
    }

    public String getTdBackgroundColorStr() {
        return tdBackgroundColorStr;
    }

    public void setTdBackgroundColorStr(String tdBackgroundColorStr) {
        this.tdBackgroundColorStr = tdBackgroundColorStr;
    }

    public String getTdTextAttributeModel() {
        return tdTextAttributeModel;
    }

    public void setTdTextAttributeModel(String tdTextAttributeModel) {
        this.tdTextAttributeModel = tdTextAttributeModel;
    }

    public boolean isFill() {
        return isFill;
    }

    public void setFill(boolean fill) {
        isFill = fill;
    }

    public boolean isTopBorder() {
        return isTopBorder;
    }

    public void setTopBorder(boolean topBorder) {
        isTopBorder = topBorder;
    }

    public static class TdTextAttributeModelBean {
        /**
         * fontName : PingFangSC-Regular
         * isBold : false
         * isTilt : false
         * colorStr : #ff000000
         * textAlignment : 1
         * pointSize : 14
         * isCenter : false
         * isUnder : false
         */
        @JSONField(name = "f")
        private String fontName = "PingFangSC-Regular";//字体名称
        @JSONField(name = "b")
        private boolean isBold = false;//是否加粗
        @JSONField(name = "t")
        private boolean isTilt = false;//是否倾斜
        @JSONField(name = "c")
        private String colorStr = TextPaintConfig.defaultTextColorStr;//字体颜色
        @JSONField(name = "a")
        private int textAlignment = 1;//对齐方式
        @JSONField(name = "p")
        private int pointSize = 14;//文字大小
        @JSONField(name = "u")
        private boolean isUnder = false;//是否底部下划线
        @JSONField(name = "s")
        private boolean isDeleteLine = false;//是否删除线

        public boolean isDeleteLine() {
            return isDeleteLine;
        }

        public void setDeleteLine(boolean deleteLine) {
            isDeleteLine = deleteLine;
        }

        public String getFontName() {
            return fontName;
        }

        public void setFontName(String fontName) {
            this.fontName = fontName;
        }

        public boolean isBold() {
            return isBold;
        }

        public void setBold(boolean bold) {
            isBold = bold;
        }

        public boolean isTilt() {
            return isTilt;
        }

        public void setTilt(boolean tilt) {
            isTilt = tilt;
        }

        public String getColorStr() {
            return colorStr;
        }

        public void setColorStr(String colorStr) {
            this.colorStr = colorStr;
        }

        public int getTextAlignment() {
            return textAlignment;
        }

        public void setTextAlignment(int textAlignment) {
            this.textAlignment = textAlignment;
        }

        public int getPointSize() {
            return pointSize;
        }

        public void setPointSize(int pointSize) {
            this.pointSize = pointSize;
        }

        public boolean isUnder() {
            return isUnder;
        }

        public void setUnder(boolean under) {
            isUnder = under;
        }
    }

    @Override
    public String toString() {
        return "ChartBean{" +
                "isFx=" + isFx +
                ", isBottomBorder=" + isBottomBorder +
                ", fxStr='" + fxStr + '\'' +
                ", isLeftBorder=" + isLeftBorder +
                ", chartOrientation=" + chartOrientation +
                ", chart=" + chart +
                ", tdSizeStr='" + tdSizeStr + '\'' +
                ", tdText='" + tdText + '\'' +
                ", isRightBorder=" + isRightBorder +
                ", tdBorderColorStr='" + tdBorderColorStr + '\'' +
                ", chartTheme=" + chartTheme +
                ", chartTitle='" + chartTitle + '\'' +
                ", tdBackgroundColorStr='" + tdBackgroundColorStr + '\'' +
                ", tdTextAttributeModel=" + tdTextAttributeModel +
                ", isFill=" + isFill +
                ", isTopBorder=" + isTopBorder +
                '}';
    }
}
