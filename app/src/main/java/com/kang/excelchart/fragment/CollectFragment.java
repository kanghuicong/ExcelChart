package com.kang.excelchart.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.kang.excelchart.R;
import com.kang.excelchart.bean.Tables_1;
import com.kang.excelchart.bean._User;
import com.kang.excelchart.config.BaseConfig;
import com.kang.excelchart.config.UserConfig;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobPointer;

public class CollectFragment extends BaseListFragment {
    @Override
    public BmobQuery query() {
        //按创建时间倒序排列
        BmobQuery<Tables_1> eq1 = new BmobQuery<Tables_1>();
        _User user = new _User();
        user.setObjectId(UserConfig.getUserId(activity));
        eq1.addWhereRelatedTo(BaseConfig.getTableName(activity),new BmobPointer(user));
        eq1.order("-taCreateTime");
        //isCollection=true
        BmobQuery<Tables_1> eq2 = new BmobQuery<Tables_1>();
        eq2.addWhereEqualTo("isCollection", true);

        List<BmobQuery<Tables_1>> queries = new ArrayList<>();
        queries.add(eq1);
        queries.add(eq2);

        BmobQuery<Tables_1> query = new BmobQuery<>();
        query.and(queries);
        return query;
    }

    @Override
    public void _init(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        titleView.setTitle(getString(R.string.collect));
    }

    @Override
    public int initFrom() {
        return 2;
    }


}