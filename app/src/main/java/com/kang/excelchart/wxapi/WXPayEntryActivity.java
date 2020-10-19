package com.kang.excelchart.wxapi;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;

import com.kang.excelchart.R;
import com.kang.excelchart.bean.WeChatEvent;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.vondear.rxtool.RxLogTool;

import org.greenrobot.eventbus.EventBus;


public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {
    private IWXAPI api;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        api = WXAPIFactory.createWXAPI(this, getString(R.string.wechat_id));
        api.handleIntent(getIntent(), this);
    }

//    @Override
//    protected void onNewIntent(Intent intent) {
//        super.onNewIntent(intent);
//        setIntent(intent);
//        api.handleIntent(intent, this);
//    }

    @Override
    public void onReq(BaseReq baseReq) {
    }

    @Override
    public void onResp(BaseResp baseResp) {
        RxLogTool.e("yaya", "WXPayEntryActivity回调微信支付的结果errCode=" + baseResp.errCode);
        if (baseResp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            EventBus.getDefault().post(new WeChatEvent(baseResp.errCode, baseResp));
        }
        finish();
        overridePendingTransition(0, 0);
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        finish();
        RxLogTool.d("wx", "onBackPressed");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            finish();
            RxLogTool.d("wx", "onKeyDown");
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxLogTool.d("wx", "onDestroy");
    }

    @Override
    protected void onResume() {
        super.onResume();
        RxLogTool.d("wx", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        RxLogTool.d("wx", "onDestroy");
    }
}