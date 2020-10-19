package com.kang.excelchart.bean;

import com.tencent.mm.opensdk.modelbase.BaseResp;

public class WeChatEvent {
    BaseResp baseResp;
    int code;

    public WeChatEvent(int code, BaseResp baseResp) {
        this.code = code;
        this.baseResp = baseResp;
    }

    public BaseResp getBaseResp() {
        return baseResp;
    }

    public void setBaseResp(BaseResp baseResp) {
        this.baseResp = baseResp;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
