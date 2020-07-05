package com.kang.excelchart.utils;

public class TextEmptyUtils {

    public static boolean isEmpty(String text) {
        if (text == null || text.equals("")) {
            return true;
        } else return false;
    }
}
