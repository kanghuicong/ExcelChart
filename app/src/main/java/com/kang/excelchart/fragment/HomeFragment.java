package com.kang.excelchart.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.kang.excelchart.R;
import com.kang.excelchart.bean.Tables;
import com.kang.excelchart.bean._User;
import com.kang.excelchart.config.UserConfig;
import com.vondear.rxtool.RxTimeTool;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;

/**
 * 类描述：
 * author:kanghuicong
 */
public class HomeFragment extends BaseListFragment {
    @Override
    public BmobQuery<Tables> query() {
        BmobQuery<Tables> eq1 = new BmobQuery<>();
        eq1.addWhereEqualTo("objectId", UserConfig.getUserId(activity));
        BmobQuery<Tables> eq2 = new BmobQuery<>();
        eq2.addWhereLessThanOrEqualTo("taCreateTime", RxTimeTool.getCurTimeMills());
        List<BmobQuery<Tables>> andQuerys = new ArrayList<>();
        andQuerys.add(eq1);
        andQuerys.add(eq2);
        BmobQuery<Tables> query = new BmobQuery<>();
        query.and(andQuerys);
        return query;
    }

    @Override
    public void _init(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        titleView.setTitle(getString(R.string.home));

    }
}
