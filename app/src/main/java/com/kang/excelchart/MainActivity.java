package com.kang.excelchart;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.kang.excelchart.activity.ChartActivity;
import com.kang.excelchart.base.BaseActivity;
import com.kang.excelchart.fragment.CollectFragment;
import com.kang.excelchart.fragment.BaseListFragment;
import com.kang.excelchart.fragment.HomeFragment;
import com.kang.excelchart.fragment.MineFragment;
import com.kang.excelchart.fragment.RecentFragment;
import com.vondear.rxtool.RxActivityTool;

import java.util.List;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private FragmentManager fm;
    private HomeFragment homeFragment;
    private RecentFragment recentFragment;
    private CollectFragment collectFragment;
    private MineFragment mineFragment;

    private TextView rb1;
    private TextView rb2;
    private ImageView rb3;
    private TextView rb4;
    private TextView rb5;

    @Override
    public int initLayout() {
        return R.layout.main_activity;
    }

    @Override
    public void initView() {
        rb1 = findViewById(R.id.rb1);
        rb2 = findViewById(R.id.rb2);
        rb3 = findViewById(R.id.iv_add);
        rb4 = findViewById(R.id.rb4);
        rb5 = findViewById(R.id.rb5);

        rb1.setOnClickListener(this);
        rb2.setOnClickListener(this);
        rb3.setOnClickListener(this);
        rb4.setOnClickListener(this);
        rb5.setOnClickListener(this);
    }

    @Override
    public void init(Bundle savedInstanceState) {

        fm = getSupportFragmentManager();
        homeFragment = new HomeFragment();
        fm.beginTransaction().replace(R.id.fl_main, homeFragment).addToBackStack(null).commit();

    }

    @Override
    public List<View> needStopView() {
        return null;
    }

    @Override
    public void onClick(View view) {
        FragmentTransaction transaction = fm.beginTransaction();
        if (homeFragment != null) {
            transaction.hide(homeFragment);
        }
        if (recentFragment != null) {
            transaction.hide(recentFragment);
        }
        if (collectFragment != null) {
            transaction.hide(collectFragment);
        }
        if (mineFragment != null) {
            transaction.hide(mineFragment);
        }
        switch (view.getId()) {
            case R.id.rb1:
                setSelect(1);
                if (homeFragment == null) {
                    transaction.add(R.id.fl_main, homeFragment = new HomeFragment());
                } else {
                    transaction.show(homeFragment);
                }
                transaction.commitAllowingStateLoss();
                break;
            case R.id.rb2:
                setSelect(2);
                if (recentFragment == null) {
                    transaction.add(R.id.fl_main, recentFragment = new RecentFragment());
                } else {
                    transaction.show(recentFragment);
                }
                transaction.commitAllowingStateLoss();
                break;
            case R.id.iv_add:
                ChartActivity.doIntent(context, ChartActivity.NORMAL_FROM, null);
                break;
            case R.id.rb4:
                setSelect(4);
                if (collectFragment == null) {
                    transaction.add(R.id.fl_main, collectFragment = new CollectFragment());
                } else {
                    transaction.show(collectFragment);
                }
                transaction.commitAllowingStateLoss();
                break;
            case R.id.rb5:
                setSelect(5);
                if (mineFragment == null) {
                    transaction.add(R.id.fl_main, mineFragment = new MineFragment());
                } else {
                    transaction.show(mineFragment);
                }
                transaction.commitAllowingStateLoss();
                break;
            default:
                break;
        }
    }

    private void setSelect(int position) {
        Drawable drawable1 = ContextCompat.getDrawable(this, position == 1 ? R.mipmap.folder_open_select : R.mipmap.folder_open);
        drawable1.setBounds(0, 0, drawable1.getMinimumWidth(), drawable1.getMinimumHeight());
        rb1.setCompoundDrawables(null, drawable1, null, null);
        rb1.setTextColor(ContextCompat.getColor(this, position == 1 ? R.color.main_color : R.color.blackA6_00));

        Drawable drawable2 = ContextCompat.getDrawable(this, position == 2 ? R.mipmap.time_circle_select : R.mipmap.time_circle);
        drawable2.setBounds(0, 0, drawable2.getMinimumWidth(), drawable1.getMinimumHeight());
        rb2.setCompoundDrawables(null, drawable2, null, null);
        rb2.setTextColor(ContextCompat.getColor(this, position == 2 ? R.color.main_color : R.color.blackA6_00));

        Drawable drawable4 = ContextCompat.getDrawable(this, position == 4 ? R.mipmap.crown_select : R.mipmap.crown);
        drawable4.setBounds(0, 0, drawable4.getMinimumWidth(), drawable1.getMinimumHeight());
        rb4.setCompoundDrawables(null, drawable4, null, null);
        rb4.setTextColor(ContextCompat.getColor(this, position == 4 ? R.color.main_color : R.color.blackA6_00));

        Drawable drawable5 = ContextCompat.getDrawable(this, position == 5 ? R.mipmap.setting_select : R.mipmap.setting);
        drawable5.setBounds(0, 0, drawable5.getMinimumWidth(), drawable1.getMinimumHeight());
        rb5.setCompoundDrawables(null, drawable5, null, null);
        rb5.setTextColor(ContextCompat.getColor(this, position == 5 ? R.color.main_color : R.color.blackA6_00));
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        RxActivityTool.finishAllActivity();
    }
}
