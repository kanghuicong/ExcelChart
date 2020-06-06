package com.kang.excelchart.http;

import android.app.Activity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.kang.excelchart.base.BaseActivity;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.request.base.Request;

import java.io.IOException;
import java.io.InputStream;

import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * 类描述：网络请求
 */
public abstract class HttpCallback<T> extends AbsCallback<T> {

    private Activity activity;
    private TypeReference<T> type;
    private String from;
    private boolean needLoad = true;


    HttpCallback(Activity activity, String from, TypeReference<T> type) {
        this.type = type;
        this.activity = activity;
        this.from = from;
    }

    HttpCallback(Activity activity, String from, boolean needLoad, TypeReference<T> type) {
        this.type = type;
        this.activity = activity;
        this.from = from;
        this.needLoad = needLoad;
    }

    @Override
    public void onStart(Request<T, ? extends Request> request) {
        super.onStart(request);
        showLoad(true);
    }

    @Override
    public void onFinish() {
        showLoad(false);
    }

    @Override
    public void onError(com.lzy.okgo.model.Response<T> response) {
        super.onError(response);
        showLoad(false);
    }

    private void showLoad(boolean isShow) {
        if (needLoad)
            if (activity != null && activity instanceof BaseActivity) {
                if (isShow) ((BaseActivity) activity).startLoading();
                else ((BaseActivity) activity).stopLoading();
            }
    }

    @Override
    public T convertResponse(Response response) throws Throwable {

        //后台框架原因，无需判断
//        if (response.isSuccessful()) {
        String bodyInfo;
        //防止OOM
        try {
            ResponseBody body = response.body();
            bodyInfo = inputStream2String(body.byteStream());
            body.close();
            return JSON.parseObject(bodyInfo, type);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
//        }
    }

    private String inputStream2String(InputStream in) throws IOException {
        StringBuffer out = new StringBuffer();
        byte[] b = new byte[4096];
        for (int n; (n = in.read(b)) != -1; ) {
            out.append(new String(b, 0, n));
        }
        return out.toString();
    }

}
