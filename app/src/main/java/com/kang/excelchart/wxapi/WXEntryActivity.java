package com.kang.excelchart.wxapi;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.vondear.rxtool.RxLogTool;

/**
 * 类描述：
 */
public class WXEntryActivity extends AppCompatActivity implements IWXAPIEventHandler {

    public static IWXAPI api;

    public static final String WX_APPID = "wx778f4a52a82c568c";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        api = WXAPIFactory.createWXAPI(this, WX_APPID, false);
        //将你收到的intent和实现IWXAPIEventHandler接口的对象传递给handleIntent方法
        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
        finish();
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp baseResp) {
        String result = "";
        switch (baseResp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                String code = ((SendAuth.Resp) baseResp).code;
//                SharedPreferences WxSp = getApplicationContext().getSharedPreferences(PrefParams.spName, Context.MODE_PRIVATE);
//                SharedPreferences.Editor WxSpEditor = WxSp.edit();
//                WxSpEditor.putString(PrefParams.CODE,code);
//                WxSpEditor.apply();
                //071alXdf0D7oix1Zu5df07uXdf0alXdJ
                RxLogTool.i("code:"+code);
                Intent intent = new Intent();
                intent.setAction("authlogin");
                WXEntryActivity.this.sendBroadcast(intent);

//                httpUtils = new HttpUtils(this,null,null);
//                httpUtils.doRequest(HttpConfig.postWechatBind(code), HttpConfig.WECHAT_BIND, new IHttp() {
//                    @Override
//                    public void onSuccess(BaseBean response, String from) {
//                        EventBus.getDefault().post(new BindWeChatEvent());
//                        finish();
//                    }
//
//                    @Override
//                    public void onFailure(Response<BaseBean> response, String from) {
//                        finish();
//                    }
//                });

                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                result = "发送取消";
                Toast.makeText(this, result, Toast.LENGTH_LONG).show();
                finish();
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                result = "发送被拒绝";
                Toast.makeText(this, result, Toast.LENGTH_LONG).show();
                finish();
                break;
            default:
                result = "发送返回";
                Toast.makeText(this, result, Toast.LENGTH_LONG).show();

                finish();
                break;
        }
    }

    // IWXAPI 是第三方app和微信通信的openApi接口
    public static void regToWx(Context context) {
        // 通过WXAPIFactory工厂，获取IWXAPI的实例
        if (api == null) {
            api = WXAPIFactory.createWXAPI(context, WXEntryActivity.WX_APPID, true);
            // 将应用的appId注册到微信
            api.registerApp(WXEntryActivity.WX_APPID);
        }

        SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "wx_login_duzun";
        api.sendReq(req);
    }
}