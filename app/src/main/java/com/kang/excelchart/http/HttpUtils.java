package com.kang.excelchart.http;

import android.app.Activity;
import android.os.Build;

import androidx.annotation.NonNull;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.kang.excelchart.R;
import com.kang.excelchart.base.BaseActivity;
import com.kang.excelchart.bean.BaseBean;
import com.lzy.okgo.OkGo;

import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.vondear.rxtool.RxAppTool;
import com.vondear.rxtool.RxLogTool;
import com.vondear.rxtool.RxTimeTool;
import com.vondear.rxtool.view.RxToast;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

import static com.kang.excelchart.base.BaseApplication.getApplication;


/**
 * 类描述：网络请求工具类
 */
public class HttpUtils {

    private Activity activity;
    private long touchTime = 0;

    public HttpUtils(@NonNull Activity activity) {
        this.activity = activity;
    }

    public void detachView() {
        //网络请求未返回时，界面已销毁，需及时关闭请求
        OkGo.getInstance().cancelTag(activity);
        if (activity != null) activity = null;
    }

    public void doRequest(Request request, final String from, @NonNull final IHttp iHttp) {
        doXRequest(request, from, true, iHttp);
    }

    public void doRequestWithNoLoad(Request request, final String from, @NonNull final IHttp iHttp) {
        doXRequest(request, from, false, iHttp);
    }

    private void doXRequest(Request request, final String from, boolean needLoad, @NonNull final IHttp iHttp) {
        if (request == null) return;
        RxLogTool.i("url:" + request.getUrl());
        RxLogTool.i("params:" + request.getParams());
        RxLogTool.i("method:" + request.getMethod());

//        request.cacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST)//设置缓存模式
//                .cacheKey(request.getUrl());//作为缓存的key
        request.execute(new HttpCallback<BaseBean>(activity, from, needLoad, new TypeReference<BaseBean>() {
        }) {
            @Override
            public void onSuccess(Response<BaseBean> response) {
                try {
                    RxLogTool.i("---response.code()---:" + response.code());
                    RxLogTool.i("---response.body()---:" + response.body());
                    RxLogTool.i("---response.message()---:" + response.message());
                    RxLogTool.i("---response.isSuccessful()---:" + response.isSuccessful());
                    RxLogTool.i("---" + from + "---:" + JSON.toJSONString(response.body()));
                    switch (response.body().getEc()) {
                        case -120:
                            RxLogTool.i("------401------:" + from);
                            long currentTime = System.currentTimeMillis();
                            if ((currentTime - touchTime) >= 1000) {
                                touchTime = currentTime;
                                if (activity != null && activity instanceof BaseActivity)
                                    ((BaseActivity) activity).outLogin();
                            }
                            break;
                        case 200:
                            RxLogTool.i("------200------:" + from);
                            iHttp.onSuccess(response.body(), from);
                            break;
                        default:
                            iHttp.onFailure(response, from);
                            break;
                    }
                } catch (Exception e) {
                    if (activity != null) RxToast.error(activity.getString(R.string.error));
                    RxLogTool.i("Exception:" + e.toString());
                    iHttp.onFailure(response, from);
                }
            }


            @Override
            public void onError(Response<BaseBean> response) {
                super.onError(response);
                RxLogTool.i("---" + from + "---:" + JSON.toJSONString(response.body()));
                if (activity != null) {
                    RxToast.error(activity.getString(R.string.error_network));
                    iHttp.onFailure(response, from);
                }
            }
        });
    }

}
