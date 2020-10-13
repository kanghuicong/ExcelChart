package com.kang.excelchart.bean;

import cn.bmob.v3.BmobObject;

/**
 * 类描述：
 * author:kanghuicong
 */
public class Tables extends BmobObject {
    private String wordStr;
    private int type;
    private String taUpdateTime;
    private String taCreateTime;
    private String name;
    private boolean isColl;
    private String fileId;
    private String updateUserToken;
    private String sourceData;

    public String getSourceData() {
        return sourceData;
    }

    public void setSourceData(String sourceData) {
        this.sourceData = sourceData;
    }

    public String getWordStr() {
        return wordStr;
    }

    public void setWordStr(String wordStr) {
        this.wordStr = wordStr;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTaUpdateTime() {
        return taUpdateTime;
    }

    public void setTaUpdateTime(String taUpdateTime) {
        this.taUpdateTime = taUpdateTime;
    }

    public String getTaCreateTime() {
        return taCreateTime;
    }

    public void setTaCreateTime(String taCreateTime) {
        this.taCreateTime = taCreateTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isColl() {
        return isColl;
    }

    public void setColl(boolean coll) {
        isColl = coll;
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
                ", type=" + type +
                ", taUpdateTime=" + taUpdateTime +
                ", taCreateTime=" + taCreateTime +
                ", name='" + name + '\'' +
                ", isColl='" + isColl + '\'' +
                ", fileId='" + fileId + '\'' +
                ", updateUserToken='" + updateUserToken + '\'' +
                ", sourceData='" + sourceData + '\'' +
                '}';
    }
}
