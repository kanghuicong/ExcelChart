package com.kang.excelchart.activity.login;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.kang.excelchart.MainActivity;
import com.kang.excelchart.R;
import com.kang.excelchart.base.BaseActivity;
import com.kang.excelchart.bean._User;
import com.kang.excelchart.config.BaseConfig;
import com.kang.excelchart.config.UserConfig;
import com.kang.excelchart.custom.view.TitleView;
import com.kang.excelchart.utils.HttpUtils;
import com.vondear.rxtool.RxActivityTool;
import com.vondear.rxtool.RxLogTool;
import com.vondear.rxtool.RxTimeTool;
import com.vondear.rxtool.view.RxToast;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.http.I;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private TitleView titleView;
    private EditText etAccount;
    private EditText etPassword;
    private TextView btLogin;
    private TextView btForget;

    @Override
    public int initLayout() {
        return R.layout.login_activity;
    }

    @Override
    public void initView() {
        titleView = (TitleView) findViewById(R.id.title_view);
        etAccount = (EditText) findViewById(R.id.et_account);
        etPassword = (EditText) findViewById(R.id.et_password);
        btLogin = (TextView) findViewById(R.id.bt_login);
        btForget = (TextView) findViewById(R.id.bt_forget);

    }

    @Override
    public void init(Bundle savedInstanceState) {
        btLogin.setOnClickListener(this);
        btForget.setOnClickListener(this);

        String account = UserConfig.getUserAccount(activity);
        String pwd = UserConfig.getUserPwd(activity);

        if (!account.equals("") && !pwd.equals("")) {
            etAccount.setText(account);
            etPassword.setText(pwd);
        }
    }

    @Override
    public List<View> needStopView() {
        return null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_login:
                String account = etAccount.getText().toString();
                String password = etPassword.getText().toString();

                if (account.equals("")) {
                    RxToast.error(getString(R.string.error_account));
                    return;
                }

                if (password.equals("")) {
                    RxToast.error(getString(R.string.error_password));
                    return;
                }

                httpUtils.doHttp(() -> {
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
                                                    RxToast.success(getString(R.string.success_login));
                                                    UserConfig.setUserLogin(activity,account,password,object);
                                                    RxActivityTool.skipActivityAndFinish(activity, MainActivity.class);
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


                });

                break;
            case R.id.bt_forget:
                RxActivityTool.skipActivity(activity, ForgetActivity.class);
                break;
            default:
                break;
        }
    }
}
