package com.kang.excelchart.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.util.List;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 类描述：
 * author:kanghuicong
 */
@Entity
public class HomeListDao extends Tables{

    @Id
    private String userId;

    @Generated(hash = 1982693462)
    public HomeListDao(String userId) {
        this.userId = userId;
    }

    @Generated(hash = 1413328769)
    public HomeListDao() {
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }



}
