package com.kang.excelchart.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kang.excelchart.R;
import com.kang.excelchart.base.BaseFragment;
import com.kang.excelchart.bean.Tables;
import com.kang.excelchart.bean._User;
import com.kang.excelchart.config.UserConfig;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobPointer;

public class CollectFragment extends BaseListFragment {
    @Override
    public BmobQuery<Tables> query() {
        //按创建时间倒序排列
        BmobQuery<Tables> eq1 = new BmobQuery<Tables>();
        _User user = new _User();
        user.setObjectId(UserConfig.getUserId(activity));
        eq1.addWhereRelatedTo("tables",new BmobPointer(user));
        eq1.order("-taCreateTime");
        //isCollection=true
        BmobQuery<Tables> eq2 = new BmobQuery<Tables>();
        eq2.addWhereEqualTo("isCollection", true);

        List<BmobQuery<Tables>> queries = new ArrayList<>();
        queries.add(eq1);
        queries.add(eq2);

        BmobQuery<Tables> query = new BmobQuery<>();
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