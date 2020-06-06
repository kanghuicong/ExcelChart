package com.kang.excelchart.bean;

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

    private boolean isFx;
    private boolean isBottomBorder;
    private String fxStr;
    private boolean isLeftBorder;
    private int chartOrientation;
    private int chart;
    private String tdSizeStr;
    private String tdText;
    private boolean isRightBorder;
    private String tdBorderColorStr;
    private int chartTheme;
    private String tdBackgroundColorStr;
    private TdTextAttributeModelBean tdTextAttributeModel;
    private boolean isFill;
    private boolean isTopBorder;

    public boolean isIsFx() {
        return isFx;
    }

    public void setIsFx(boolean isFx) {
        this.isFx = isFx;
    }

    public boolean isIsBottomBorder() {
        return isBottomBorder;
    }

    public void setIsBottomBorder(boolean isBottomBorder) {
        this.isBottomBorder = isBottomBorder;
    }

    public String getFxStr() {
        return fxStr;
    }

    public void setFxStr(String fxStr) {
        this.fxStr = fxStr;
    }

    public boolean isIsLeftBorder() {
        return isLeftBorder;
    }

    public void setIsLeftBorder(boolean isLeftBorder) {
        this.isLeftBorder = isLeftBorder;
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

    public boolean isIsRightBorder() {
        return isRightBorder;
    }

    public void setIsRightBorder(boolean isRightBorder) {
        this.isRightBorder = isRightBorder;
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

    public String getTdBackgroundColorStr() {
        return tdBackgroundColorStr;
    }

    public void setTdBackgroundColorStr(String tdBackgroundColorStr) {
        this.tdBackgroundColorStr = tdBackgroundColorStr;
    }

    public TdTextAttributeModelBean getTdTextAttributeModel() {
        return tdTextAttributeModel;
    }

    public void setTdTextAttributeModel(TdTextAttributeModelBean tdTextAttributeModel) {
        this.tdTextAttributeModel = tdTextAttributeModel;
    }

    public boolean isIsFill() {
        return isFill;
    }

    public void setIsFill(boolean isFill) {
        this.isFill = isFill;
    }

    public boolean isIsTopBorder() {
        return isTopBorder;
    }

    public void setIsTopBorder(boolean isTopBorder) {
        this.isTopBorder = isTopBorder;
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

        private String fontName;
        private boolean isBold;
        private boolean isTilt;
        private String colorStr;
        private int textAlignment;
        private int pointSize;
        private boolean isCenter;
        private boolean isUnder;

        public String getFontName() {
            return fontName;
        }

        public void setFontName(String fontName) {
            this.fontName = fontName;
        }

        public boolean isIsBold() {
            return isBold;
        }

        public void setIsBold(boolean isBold) {
            this.isBold = isBold;
        }

        public boolean isIsTilt() {
            return isTilt;
        }

        public void setIsTilt(boolean isTilt) {
            this.isTilt = isTilt;
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

        public boolean isIsCenter() {
            return isCenter;
        }

        public void setIsCenter(boolean isCenter) {
            this.isCenter = isCenter;
        }

        public boolean isIsUnder() {
            return isUnder;
        }

        public void setIsUnder(boolean isUnder) {
            this.isUnder = isUnder;
        }
    }
}
