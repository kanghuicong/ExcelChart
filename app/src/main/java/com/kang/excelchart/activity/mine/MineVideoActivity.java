package com.kang.excelchart.activity.mine;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kang.excelchart.R;
import com.kang.excelchart.adapter.ChartAdapter;
import com.kang.excelchart.adapter.VideoAdapter;
import com.kang.excelchart.base.BaseActivity;
import com.kang.excelchart.bean.ExampleVideoPlayers;
import com.kang.excelchart.bean.Tables;
import com.kang.excelchart.bean._User;
import com.kang.excelchart.config.BaseConfig;
import com.kang.excelchart.config.UserConfig;
import com.vondear.rxtool.view.RxToast;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobPointer;
import cn.bmob.v3.datatype.BmobQueryResult;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SQLQueryListener;

/**
 * 类描述：
 * author:kanghuicong
 */
public class MineVideoActivity extends BaseActivity {
    private RecyclerView recyclerView;
    private List<ExampleVideoPlayers> list = new ArrayList<>();

    @Override
    public int initLayout() {
        return R.layout.mien_video_activity;
    }

    @Override
    public void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
    }

    @Override
    public void init(Bundle savedInstanceState) {
        VideoAdapter adapter = new VideoAdapter(this, list);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        recyclerView.setAdapter(adapter);

        String bql = "select * from ExampleVideoPlayers";
        new BmobQuery<ExampleVideoPlayers>().doSQLQuery(bql, new SQLQueryListener<ExampleVideoPlayers>() {

            @Override
            public void done(BmobQueryResult<ExampleVideoPlayers> result, BmobException e) {
                if (e == null) {
                    list.clear();
                    list.addAll(result.getResults());
                    adapter.notifyDataSetChanged();
                } else {
                    RxToast.error(e.getMessage());
                }
            }
        });

//        BmobQuery<ExampleVideoPlayers> query = new BmobQuery<ExampleVideoPlayers>();
////返回50条数据，如果不加上这条语句，默认返回10条数据
//        query.setLimit(50);
////执行查询方法
//        query.findObjects(new FindListener<ExampleVideoPlayers>() {
//            @Override
//            public void done(List<ExampleVideoPlayers> object, BmobException e) {
//                if(e==null){
//                    list.clear();
//                    list.addAll(object);
//                    adapter.notifyDataSetChanged();
//                }else{
//                    RxToast.error(e.getMessage());
//                }
//            }
//        });
    }

    @Override
    public List<View> needStopView() {
        return null;
    }
}
