package com.kang.excelchart.activity.login;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.kang.excelchart.R;
import com.kang.excelchart.base.BaseActivity;
import com.vondear.rxtool.RxClipboardTool;
import com.vondear.rxtool.view.RxToast;

import java.util.List;

/**
 * 类描述：
 * author:kanghuicong
 */
public class ForgetActivity extends BaseActivity {
    private TextView tvEmail;
    private TextView tvQq;

    @Override
    public int initLayout() {
        return R.layout.forget_activity;
    }

    @Override
    public void initView() {
        tvEmail = (TextView) findViewById(R.id.tv_email);
        tvQq = (TextView) findViewById(R.id.tv_qq);
    }

    @Override
    public void init(Bundle savedInstanceState) {
        tvEmail.setOnLongClickListener((view->{
            RxClipboardTool.copyText(activity, getString(R.string.our_email));
            RxToast.success(getString(R.string.copy_email_success));
            return true;
        }));


        tvQq.setOnLongClickListener((view->{
            RxClipboardTool.copyText(activity, getString(R.string.our_qq));
            RxToast.success(getString(R.string.copy_qq_success));
            return true;
        }));
    }

    @Override
    public List<View> needStopView() {
        return null;
    }
}
