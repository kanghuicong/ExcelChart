package com.kang.excelchart.fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kang.excelchart.R;
import com.kang.excelchart.activity.mine.MineAccountActivity;
import com.kang.excelchart.activity.mine.MineFeedBackActivity;
import com.kang.excelchart.activity.mine.MineVipActivity;
import com.kang.excelchart.base.BaseFragment;
import com.kang.excelchart.config.UserConfig;
import com.kang.excelchart.custom.view.SuperItemView;
import com.vondear.rxtool.RxActivityTool;
import com.vondear.rxtool.view.RxToast;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

public class MineFragment extends BaseFragment implements View.OnClickListener {
    private SuperItemView superAccount;
    private SuperItemView superUser;
    private SuperItemView superVideo;
    private SuperItemView superTip;
    private SuperItemView superSetting;
    private SuperItemView superBin;
    private SuperItemView superSync;
    private SuperItemView superAboutUs;
    private SuperItemView superPrivacy;
    private SuperItemView superFeedback;

    private boolean isVip;

    @Override
    protected int initLayout(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.mine_fragment;
    }

    @Override
    protected void initView(View view) {
        superAccount = (SuperItemView) view.findViewById(R.id.super_account);
        superUser = (SuperItemView) view.findViewById(R.id.super_user);
        superVideo = (SuperItemView) view.findViewById(R.id.super_video);
        superTip = (SuperItemView) view.findViewById(R.id.super_tip);
        superSetting = (SuperItemView) view.findViewById(R.id.super_setting);
        superBin = (SuperItemView) view.findViewById(R.id.super_bin);
        superSync = (SuperItemView) view.findViewById(R.id.super_sync);
        superAboutUs = (SuperItemView) view.findViewById(R.id.super_about_us);
        superPrivacy = (SuperItemView) view.findViewById(R.id.super_privacy);
        superFeedback = (SuperItemView) view.findViewById(R.id.super_feedback);

        superAccount.setOnClickListener(this);
        superUser.setOnClickListener(this);
        superVideo.setOnClickListener(this);
        superTip.setOnClickListener(this);
        superSetting.setOnClickListener(this);
        superBin.setOnClickListener(this);
        superSync.setOnClickListener(this);
        superAboutUs.setOnClickListener(this);
        superPrivacy.setOnClickListener(this);
        superFeedback.setOnClickListener(this);
    }

    @Override
    protected void init(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        superAccount.setRightText(UserConfig.getUserAccount(activity));

        isVip = UserConfig.isVip(activity);
        if (isVip) {
            superUser.setRightText(getString(R.string.vip_forever));
            superAccount.setRightImageL(R.mipmap.vip);
        } else {
            superUser.setRightText(getString(R.string.not_available));
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.super_account:
                RxActivityTool.skipActivity(activity, MineAccountActivity.class);
                break;
            case R.id.super_user:
                if (isVip) {
                    RxToast.normal(getString(R.string.you_was_vip));
                } else {
                    RxActivityTool.skipActivity(activity, MineVipActivity.class);
                }
                break;
            case R.id.super_video:
                break;

            case R.id.super_feedback:
                RxActivityTool.skipActivity(activity, MineFeedBackActivity.class);
                break;
        }
    }
}
