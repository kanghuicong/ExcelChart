package com.kang.excelchart.utils;

import com.alibaba.fastjson.JSON;
import com.kang.excelchart.bean.InputTextBean;
import com.kang.excelchart.config.PaintConfig;
import com.kang.excelchart.config.BaseObjectConfig;

import java.util.HashMap;
import java.util.Map;

/**
 * 类描述：
 * author:kanghuicong
 */
class Test {
    InputTextBean inputTextBean;

    void  ss(){
        Map map = new HashMap();
        if (!inputTextBean.getChartBean().getTdText().equals(""))
            map.put("tx", inputTextBean.getChartBean().getTdText());
        if (inputTextBean.getChartBean().isFx()) map.put("if", true);
        if (inputTextBean.getChartBean().isBottomBorder()) map.put("b", true);
        if (!inputTextBean.getChartBean().getFxStr().equals("js"))
            map.put("fx", inputTextBean.getChartBean().getFxStr());
        if (inputTextBean.getChartBean().isLeftBorder()) map.put("l", true);
        if (inputTextBean.getChartBean().getChartOrientation() != 1)
            map.put("o", inputTextBean.getChartBean().getChartOrientation());
        if (inputTextBean.getChartBean().getChart() != 1)
            map.put("ic", inputTextBean.getChartBean().getChart());
        if (!inputTextBean.getChartBean().getTdSizeStr().equals("{1,1}"))
            map.put("s", inputTextBean.getChartBean().getTdSizeStr());
        if (inputTextBean.getChartBean().isRightBorder()) map.put("r", true);
        if (!inputTextBean.getChartBean().getTdBorderColorStr().equals(PaintConfig.defaultLineColor))
            map.put("boc", inputTextBean.getChartBean().getTdBorderColorStr());
        if (inputTextBean.getChartBean().getChartTheme() != 1)
            map.put("k", inputTextBean.getChartBean().getChartTheme());
        if (!inputTextBean.getChartBean().getTdBackgroundColorStr().equals(BaseObjectConfig.defaultBackgroundColorStr))
            map.put("bc", inputTextBean.getChartBean().getTdBackgroundColorStr());
        if (inputTextBean.getChartBean().isFill()) map.put("f", true);
        if (inputTextBean.getChartBean().isTopBorder()) map.put("t", true);

//
        if (inputTextBean.getChartBean().getTdTextAttributeModel() == null) {
            map.put("m", "{\"f\":\"PingFangSC-Regular\",\"t\":false,\"c\":\"#ff000000\",\"p\":14,\"u\":false,\"a\":1,\"s\":false,\"b\":false}");
        }else {
            map.put("m", inputTextBean.getChartBean().getTdTextAttributeModel());
        }
//                    }
        String json = JSON.toJSONString(map);
    }
}
