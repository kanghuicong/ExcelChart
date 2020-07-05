package com.kang.excelchart.activity.mine;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.kang.excelchart.BuildConfig;
import com.kang.excelchart.R;
import com.kang.excelchart.base.BaseActivity;
import com.kang.excelchart.bean.feedback;
import com.kang.excelchart.utils.HttpUtils;
import com.vondear.rxtool.RxActivityTool;
import com.vondear.rxtool.RxClipboardTool;
import com.vondear.rxtool.RxDeviceTool;
import com.vondear.rxtool.view.RxToast;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.http.I;
import cn.bmob.v3.listener.SaveListener;

public class MineFeedBackActivity extends BaseActivity {
    private EditText etContent;
    private EditText etPhone;
    private TextView btSend;
    private TextView tvEmail;
    private TextView tvQq;

    @Override
    public int initLayout() {
        return R.layout.mine_feedback_activity;
    }

    @Override
    public void initView() {

        etContent = (EditText) findViewById(R.id.et_content);
        etPhone = (EditText) findViewById(R.id.et_phone);
        btSend = (TextView) findViewById(R.id.bt_send);
        tvEmail = (TextView) findViewById(R.id.tv_email);
        tvQq = (TextView) findViewById(R.id.tv_qq);

    }

    @Override
    public void init(Bundle savedInstanceState) {
        btSend.setOnClickListener((view -> {

            httpUtils.doHttp(new HttpUtils.IHttp() {
                @Override
                public void doHttp() {
                    feedback feedback = new feedback();
//注意：不能调用gameScore.setObjectId("")方法
                    feedback.setFeedback(etContent.getText().toString());
                    feedback.setTel(etPhone.getText().toString());
                    feedback.setDevice("Android:Version:" + BuildConfig.VERSION_NAME + ",Device:" + RxDeviceTool.getDeviceInfo(activity));
                    feedback.save(new SaveListener<String>() {

                        @Override
                        public void done(String objectId, BmobException e) {
                            httpUtils.doHttpResult(e, new HttpUtils.IHttpResult() {
                                @Override
                                public void success() {
                                    RxToast.success(getString(R.string.success_send));
                                    RxActivityTool.finishActivity(activity);
                                }

                                @Override
                                public void failure() {

                                }
                            });
                        }
                    });
                }
            });
        }));


        tvEmail.setOnLongClickListener((view -> {
            RxClipboardTool.copyText(activity, getString(R.string.our_email));
            RxToast.success(getString(R.string.copy_email_success));
            return true;
        }));


        tvQq.setOnLongClickListener((view -> {
            RxClipboardTool.copyText(activity, getString(R.string.our_qq));
            RxToast.success(getString(R.string.copy_qq_success));
            return true;
        }));
    }

    @Override
    public List<View> needStopView() {
        List<View> viewList = new ArrayList<>();
        viewList.add(tvEmail);
        viewList.add(tvQq);
        viewList.add(btSend);
        return viewList;
    }
}

