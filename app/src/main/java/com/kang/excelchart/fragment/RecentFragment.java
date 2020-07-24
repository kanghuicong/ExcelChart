package com.kang.excelchart.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.kang.excelchart.R;
import com.kang.excelchart.bean.Tables_1;
import com.kang.excelchart.bean._User;
import com.kang.excelchart.config.BaseConfig;
import com.kang.excelchart.config.UserConfig;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobPointer;

public class RecentFragment extends BaseListFragment {
    @Override
    public BmobQuery query() {
        //按更新时间倒序排列
        BmobQuery<Tables_1> query = new BmobQuery<Tables_1>();
        _User user = new _User();
        user.setObjectId(UserConfig.getUserId(activity));
        query.addWhereRelatedTo(BaseConfig.getTableName(activity),new BmobPointer(user));
        query.order("-taUpdateTime");
        return query;
    }

    @Override
    public void _init(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        titleView.setTitle(getString(R.string.recently));
    }

    @Override
    public int initFrom() {
        return 1;
    }

}
