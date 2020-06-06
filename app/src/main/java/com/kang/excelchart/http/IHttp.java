package com.kang.excelchart.http;

import com.kang.excelchart.bean.BaseBean;
import com.lzy.okgo.model.Response;


/**
 * 类描述：网络请求状态
 */

public interface IHttp {
    void onSuccess(BaseBean response, String from);

    void onFailure(Response<BaseBean> response, String from);
}
