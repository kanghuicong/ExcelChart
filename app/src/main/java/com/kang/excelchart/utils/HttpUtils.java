package com.kang.excelchart.utils;

import android.app.Activity;

import com.kang.excelchart.R;
import com.kang.excelchart.base.BaseActivity;
import com.kang.excelchart.bean._User;
import com.vondear.rxtool.RxLogTool;
import com.vondear.rxtool.view.RxToast;

import java.util.List;

import cn.bmob.v3.exception.BmobException;

/**
 * 类描述：
 * author:kanghuicong
 */
public class HttpUtils {

    private BaseActivity activity;

    public HttpUtils(BaseActivity activity) {
        this.activity = activity;
    }

    public void doHttp(IHttp iHttp) {
        activity.startLoading();
        iHttp.doHttp();
    }


    public void doHttpResult(BmobException e, IHttpResult iHttpResult) {
        activity.stopLoading();
        if (e == null) {
            iHttpResult.success();
        }else {
            switch (e.getErrorCode()) {
                //网络原因
                case 9015:
                    break;
                case 211:
                    RxToast.error(activity.getString(R.string.error_login));
                    break;
                default:
                    break;
            }
            RxLogTool.e(e.toString());
            iHttpResult.failure();
        }

    }


    public interface IHttpResult {
        void success();

        void failure();
    }

    public interface IHttp {
        void doHttp();

    }
}
