package com.kang.excelchart.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.kang.excelchart.R;
import com.kang.excelchart.bean.Tables;
import com.kang.excelchart.bean.Tables_1;
import com.kang.excelchart.bean._User;
import com.kang.excelchart.config.BaseConfig;
import com.kang.excelchart.config.UserConfig;
import com.vondear.rxtool.RxLogTool;
import com.vondear.rxtool.RxTimeTool;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobPointer;

/**
 * 类描述：
 * author:kanghuicong
 */
public class HomeFragment extends BaseListFragment {
    @Override
    public BmobQuery query() {
        //按创建时间倒序排列
        _User user = new _User();
        user.setObjectId(UserConfig.getUserId(activity));

        BmobQuery<Tables> query = new BmobQuery<>();
        query.addWhereRelatedTo(BaseConfig.getTableName(activity), new BmobPointer(user));
        query.order("-taCreateTime");
        return query;

    }

    @Override
    public void _init(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        titleView.setTitle(getString(R.string.home));

        RxLogTool.d("user_id:" + UserConfig.getUserId(activity));
        RxLogTool.d("user_create:" + UserConfig.getCreateAt(activity));
    }

    @Override
    public int initFrom() {
        return 0;
    }
}
