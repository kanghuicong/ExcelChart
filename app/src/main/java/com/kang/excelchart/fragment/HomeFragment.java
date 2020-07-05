package com.kang.excelchart.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.kang.excelchart.R;
import com.kang.excelchart.bean.Tables;
import com.kang.excelchart.bean._User;
import com.kang.excelchart.config.UserConfig;
import com.vondear.rxtool.RxLogTool;
import com.vondear.rxtool.RxTimeTool;
import com.vondear.rxtool.view.RxToast;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobPointer;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * 类描述：
 * author:kanghuicong
 */
public class HomeFragment extends BaseListFragment {
    @Override
    public BmobQuery<Tables> query() {
        //按创建时间倒序排列
        BmobQuery<Tables> query = new BmobQuery<Tables>();
        _User user = new _User();
        user.setObjectId(UserConfig.getUserId(activity));
        query.addWhereRelatedTo("tables",new BmobPointer(user));
        query.order("-taCreateTime");
        return query;
    }

    @Override
    public void _init(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        titleView.setTitle(getString(R.string.home));

    }

    @Override
    public int initFrom() {
        return 0;
    }
}
