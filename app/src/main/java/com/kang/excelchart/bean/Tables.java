package com.kang.excelchart.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobPointer;

import org.greenrobot.greendao.annotation.Generated;

/**
 * 类描述：
 * author:kanghuicong
 */
@Entity
public class Tables extends BmobObject {
    @Id
    private long fileId;
    
    private String userId;
    private String wordStr;
    private int type;
    private String taUpdateTime;
    private String taCreateTime;
    private String name;
    private boolean isColl;
    private boolean isCollection;

    private String updateUserToken;
    private String sourceData;


    @Generated(hash = 1937802750)
    public Tables(long fileId, String userId, String wordStr, int type,
            String taUpdateTime, String taCreateTime, String name, boolean isColl,
            boolean isCollection, String updateUserToken, String sourceData) {
        this.fileId = fileId;
        this.userId = userId;
        this.wordStr = wordStr;
        this.type = type;
        this.taUpdateTime = taUpdateTime;
        this.taCreateTime = taCreateTime;
        this.name = name;
        this.isColl = isColl;
        this.isCollection = isCollection;
        this.updateUserToken = updateUserToken;
        this.sourceData = sourceData;
    }

    @Generated(hash = 2011269315)
    public Tables() {
    }


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

    public long getFileId() {
        return fileId;
    }

    public void setFileId(long fileId) {
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

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public boolean getIsColl() {
        return this.isColl;
    }

    public void setIsColl(boolean isColl) {
        this.isColl = isColl;
    }

    public boolean getIsCollection() {
        return this.isCollection;
    }

    public void setIsCollection(boolean isCollection) {
        this.isCollection = isCollection;
    }



}
