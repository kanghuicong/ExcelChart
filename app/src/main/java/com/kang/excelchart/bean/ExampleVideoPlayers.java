package com.kang.excelchart.bean;

import cn.bmob.v3.BmobObject;

/**
 * 类描述：
 * author:kanghuicong
 */
public class ExampleVideoPlayers extends BmobObject {

    String videoUrl;

    String name;

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
