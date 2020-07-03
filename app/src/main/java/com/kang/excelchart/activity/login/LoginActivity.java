package com.kang.excelchart.activity.login;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.kang.excelchart.R;
import com.kang.excelchart.base.BaseActivity;
import com.kang.excelchart.bean._User;
import com.vondear.rxtool.RxLogTool;
import com.vondear.rxtool.view.RxToast;

import org.json.JSONArray;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListener;

public class LoginActivity extends BaseActivity {
    private EditText etAccount;
    private EditText etPassword;
    private Button btLogin;

    @Override
    public int initLayout() {
        return R.layout.login_activity;
    }

    @Override
    public void initView() {
        etAccount = (EditText) findViewById(R.id.et_account);
        etPassword = (EditText) findViewById(R.id.et_password);
        btLogin = (Button) findViewById(R.id.bt_login);
    }

    @Override
    public void init(Bundle savedInstanceState) {
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BmobQuery<_User> query = new BmobQuery<>();
                query.addWhereEqualTo("username", etAccount.getText().toString())
                        .setLimit(1)
                        .findObjects(new FindListener<_User>() {
                    @Override
                    public void done(List<_User> object, BmobException e) {
                        if (e == null) {
                            _User usrBean = object.get(0);
                            RxLogTool.i(usrBean.toString());
                            if (usrBean.getPassword().equals(etPassword.getText().toString())) {
                                RxToast.success("success");
                            }
                        } else {
                            RxLogTool.i(e.toString());
                        }
                    }
                });
            }
        });
    }

    @Override
    public List<View> needStopView() {
        return null;
    }
}
