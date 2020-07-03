package com.kang.excelchart.activity.login;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kang.excelchart.MainActivity;
import com.kang.excelchart.R;
import com.kang.excelchart.base.BaseActivity;
import com.kang.excelchart.bean._User;
import com.vondear.rxtool.RxActivityTool;
import com.vondear.rxtool.RxLogTool;
import com.vondear.rxtool.view.RxToast;

import org.json.JSONArray;

import java.util.ArrayList;
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
                        if(e==null){
                            if (object.size() != 0) {
                                RxActivityTool.skipActivity(activity, MainActivity.class);
                            }else{
                                RxToast.error(getString(R.string.error_account_password));
                            }
                            RxLogTool.d("查询结果:"+object.size()+"");
                        }else{
                            RxLogTool.e(e.toString());
                            RxToast.error(getString(R.string.error_account_password));
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
