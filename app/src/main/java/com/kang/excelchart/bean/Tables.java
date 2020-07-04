package com.kang.excelchart.bean;

import cn.bmob.v3.BmobObject;

/**
 * 类描述：
 * author:kanghuicong
 */
public class Tables extends BmobObject {
    private String wordStr;
    private String type;
    private long taUpdateTime;
    private long taCreateTime;
    private String name;
    private String isColl;
    private String fileId;
    private String updateUserToken;


    public String getWordStr() {
        return wordStr;
    }

    public void setWordStr(String wordStr) {
        this.wordStr = wordStr;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getTaUpdateTime() {
        return taUpdateTime;
    }

    public void setTaUpdateTime(long taUpdateTime) {
        this.taUpdateTime = taUpdateTime;
    }

    public long getTaCreateTime() {
        return taCreateTime;
    }

    public void setTaCreateTime(long taCreateTime) {
        this.taCreateTime = taCreateTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsColl() {
        return isColl;
    }

    public void setIsColl(String isColl) {
        this.isColl = isColl;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getUpdateUserToken() {
        return updateUserToken;
    }

    public void setUpdateUserToken(String updateUserToken) {
        this.updateUserToken = updateUserToken;
    }

    @Override
    public String toString() {
        return "Tables{" +
                "wordStr='" + wordStr + '\'' +
                ", type='" + type + '\'' +
                ", taUpdateTime=" + taUpdateTime +
                ", taCreateTime=" + taCreateTime +
                ", name='" + name + '\'' +
                ", isColl='" + isColl + '\'' +
                ", fileId='" + fileId + '\'' +
                ", updateUserToken='" + updateUserToken + '\'' +
                '}';
    }
}
