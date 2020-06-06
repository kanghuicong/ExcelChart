package com.kang.excelchart.adapter;


import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

public class TabAdapter extends PagerAdapter {
    private List<Fragment> fragments;
    private FragmentManager manager;
    private List<String> lists;

    public TabAdapter(FragmentManager fm, List<Fragment> fragments, List<String> lists) {
        super();
        manager = fm;
        this.fragments = fragments;
        this.lists = lists;

    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = fragments.get(position);
        //判断当前的fragment是否已经被添加进入Fragmentanager管理器中
        if (!fragment.isAdded()) {
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.add(fragment, fragment.getClass().getSimpleName());
            //不保存系统参数，自己控制加载的参数
            transaction.commitAllowingStateLoss();
            //手动调用,立刻加载Fragment片段
            manager.executePendingTransactions();
        }

        if (fragment.getView().getParent() == null) {
            container.addView(fragment.getView());
        }
        return fragment.getView();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //移除布局
        container.removeView(fragments.get(position).getView());
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (lists != null)
            return lists.get(position);
        else return null;
    }

}
