package com.kang.excelchart.activity.mine;

import android.os.Bundle;
import android.view.View;

import com.kang.excelchart.MainActivity;
import com.kang.excelchart.R;
import com.kang.excelchart.activity.login.LoginActivity;
import com.kang.excelchart.base.BaseActivity;
import com.kang.excelchart.bean.LogoutEvent;
import com.kang.excelchart.bean._User;
import com.kang.excelchart.config.UserConfig;
import com.kang.excelchart.custom.dialog.BaseDialog;
import com.kang.excelchart.custom.dialog.VerifyDialog;
import com.kang.excelchart.custom.view.SuperItemView;
import com.kang.excelchart.utils.HttpUtils;
import com.kang.excelchart.utils.TextEmptyUtils;
import com.vondear.rxtool.RxActivityTool;
import com.vondear.rxtool.RxLogTool;
import com.vondear.rxtool.view.RxToast;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

public class MineAccountActivity extends BaseActivity implements View.OnClickListener {
    private SuperItemView superAccount;
    private SuperItemView superPwd;
    private SuperItemView superEmail;
    private SuperItemView superUser;
    private SuperItemView superReLogin;
    private SuperItemView superLogout;

    private boolean isVip;
    private BaseDialog logoutDialog;

    @Override
    public int initLayout() {
        return R.layout.mine_account_activity;
    }

    @Override
    public void initView() {
        superAccount = (SuperItemView) findViewById(R.id.super_account);
        superPwd = (SuperItemView) findViewById(R.id.super_pwd);
        superEmail = (SuperItemView) findViewById(R.id.super_email);
        superUser = (SuperItemView) findViewById(R.id.super_user);
        superReLogin = (SuperItemView) findViewById(R.id.super_re_login);
        superLogout = (SuperItemView) findViewById(R.id.super_logout);

        superPwd.setOnClickListener(this);
        superEmail.setOnClickListener(this);
        superUser.setOnClickListener(this);
        superReLogin.setOnClickListener(this);
        superLogout.setOnClickListener(this);
    }

    @Override
    public void init(Bundle savedInstanceState) {
        superAccount.setRightText(UserConfig.getUserAccount(activity));

        if (TextEmptyUtils.isEmpty(UserConfig.getEmail(activity))) {
            superEmail.setRightText(getString(R.string.not_bind));
        } else {
            superEmail.setRightText(UserConfig.getEmail(activity));
        }

        isVip = UserConfig.isVip(activity);
        if (isVip) {
            superUser.setRightText(getString(R.string.vip_forever));
        } else {
            superUser.setRightText(getString(R.string.not_available));
        }
    }

    @Override
    public List<View> needStopView() {
        return null;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.super_pwd://修改密码
                VerifyDialog verifyDialog1 = new VerifyDialog(activity,
                        getString(R.string.change_password),
                        getString(R.string.input_old_pwd),
                        getString(R.string.input_new_pwd),
                        ((str1, str2) -> {

                            httpUtils.doHttp(() -> {
                                BmobQuery<_User> eq1 = new BmobQuery<>();
                                eq1.addWhereEqualTo("username", UserConfig.getUserAccount(this));
                                BmobQuery<_User> eq2 = new BmobQuery<>();
                                eq2.addWhereEqualTo("password", str1);

                                List<BmobQuery<_User>> queries = new ArrayList<>();
                                queries.add(eq1);
                                queries.add(eq2);

                                BmobQuery<_User> query = new BmobQuery<>();
                                query.and(queries);

                                query.findObjects(new FindListener<_User>() {
                                    @Override
                                    public void done(List<_User> object, BmobException e) {
                                        //旧密码输入正确
                                        if (object.size() != 0) {
                                            _User user = new _User();
                                            user.setPassword(str2);
                                            user.update(UserConfig.getUserId(activity), new UpdateListener() {

                                                @Override
                                                public void done(BmobException e) {
                                                    httpUtils.doHttpResult(e, new HttpUtils.IHttpResult() {
                                                        @Override
                                                        public void success() {
                                                            RxToast.success(getString(R.string.success_change_pwd));
                                                        }

                                                        @Override
                                                        public void failure() {
                                                            RxToast.error(getString(R.string.error_change_pwd));
                                                        }
                                                    });
                                                }

                                            });
                                        } else {
                                            RxToast.error(getString(R.string.error_input_old_pwd));
                                            stopLoading();
                                        }
                                    }
                                });
                            });
                        }));
                verifyDialog1.show();
                break;
            case R.id.super_email: //修改邮箱
                VerifyDialog verifyDialog2 = new VerifyDialog(activity,
                        getString(R.string.email),
                        getString(R.string.input_email),
                        null,
                        ((str1, str2) -> {
                            httpUtils.doHttp((() -> {
                                _User user = new _User();
                                user.setEmail(str1);
                                RxLogTool.d("getUserId:" + UserConfig.getUserId(activity));
                                user.update(UserConfig.getUserId(activity), new UpdateListener() {

                                    @Override
                                    public void done(BmobException e) {
                                        httpUtils.doHttpResult(e, new HttpUtils.IHttpResult() {
                                            @Override
                                            public void success() {
                                                RxToast.success(getString(R.string.success_change_email));
                                            }

                                            @Override
                                            public void failure() {
                                                RxToast.error(getString(R.string.error_change_email));
                                            }
                                        });
                                    }
                                });
                            }));
                        }));
                verifyDialog2.show();
                break;
            case R.id.super_user://开通会员
                if (isVip) {
                    RxToast.normal(getString(R.string.you_was_vip));
                } else {
                    RxActivityTool.skipActivity(activity, MineVipActivity.class);
                }
                break;
            case R.id.super_re_login://重新登录
                String account= UserConfig.getUserAccount(this);
                String password= UserConfig.getUserPwd(this);

                BmobUser bmobUser = new BmobUser();
                bmobUser.setUsername(account);
                bmobUser.setPassword(password);
                bmobUser.login(new SaveListener<BmobUser>() {
                    @Override
                    public void done(BmobUser bmobUser, BmobException e) {
                        if (e == null) {
                            BmobQuery<_User> eq1 = new BmobQuery<>();
                            eq1.addWhereEqualTo("username", account);
                            BmobQuery<_User> eq2 = new BmobQuery<>();
                            eq2.addWhereEqualTo("password", password);

                            List<BmobQuery<_User>> queries = new ArrayList<>();
                            queries.add(eq1);
                            queries.add(eq2);

                            BmobQuery<_User> query = new BmobQuery<>();
                            query.and(queries);

                            query.findObjects(new FindListener<_User>() {
                                @Override
                                public void done(List<_User> object, BmobException e) {
                                    httpUtils.doHttpResult(e, new HttpUtils.IHttpResult() {
                                        @Override
                                        public void success() {
                                            if (object.size() != 0) {
                                                UserConfig.setUserLogin(activity,account,password,object);
                                                RxToast.success(getString(R.string.success_login));
                                            } else {
                                                RxToast.error(getString(R.string.error_account_password));
                                            }
                                        }

                                        @Override
                                        public void failure() {

                                        }
                                    });
                                }
                            });
                        }
                    }
                });
                break;
            case R.id.super_logout://退出
                if (logoutDialog == null) {
                    logoutDialog = new BaseDialog(this,
                            null,
                            getString(R.string.hint_logout),
                            getString(R.string.confirm),
                            getString(R.string.cancel),
                            0,
                            0,
                            false,
                            new BaseDialog.IBaseDialog() {
                                @Override
                                public void clickLeft(View v) {
                                    UserConfig.setUserLogout(activity);

                                    RxActivityTool.skipActivityAndFinishAll(activity, LoginActivity.class);

//                                    EventBus.getDefault().post(new LogoutEvent());
                                }

                                @Override
                                public void clickRight(View v) {

                                }
                            });

                }
                logoutDialog.show();
                break;
        }
    }
}
