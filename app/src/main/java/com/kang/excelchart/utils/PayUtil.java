package com.kang.excelchart.utils;


import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;


import com.alipay.sdk.app.PayTask;
import com.kang.excelchart.R;
import com.kang.excelchart.bean.AliPayResultEvent;
import com.kang.excelchart.wxapi.WeChatBean;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.vondear.rxtool.RxLogTool;

import org.greenrobot.eventbus.EventBus;

import java.util.Map;

public class PayUtil {

    public static void payWx(Context context, WeChatBean weChat) {

        try {
            IWXAPI mWxApi = WXAPIFactory.createWXAPI(context, context.getString(R.string.wechat_id), true);
            PayReq request = new PayReq();
            request.appId = context.getString(R.string.wechat_id);
            request.partnerId = weChat.getPartnerId();
            request.prepayId = weChat.getPrepayId();
            request.packageValue = "Sign=WXPay";
            request.nonceStr = weChat.getNonceStr();
            request.timeStamp = weChat.getTimeStamp();
            request.sign = weChat.getSign();
            request.extData = "app data";
            mWxApi.registerApp(context.getString(R.string.wechat_id));
            mWxApi.sendReq(request);
        } catch (Exception e) {

        }

    }

    public static void payALI(final Activity context, final String order, final String id) {
        Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                PayTask alipay = new PayTask(context);
                Map<String, String> result = alipay.payV2(order, true);
                RxLogTool.i("PayResult：", result.toString());
                Message msg = new Message();
                // 额外添加订单id，用于确定来源
                result.put("order_id", id);
                msg.what = 100;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };
        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();

    }

    private static Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 100: {
                    RxLogTool.i("PayResult", ((Map<String, String>) msg.obj).toString());
                    EventBus.getDefault().post(new AliPayResultEvent(((Map<String, String>) msg.obj).get("resultStatus"),((Map<String, String>) msg.obj).get("order_id")));
//                    if (TextUtils.equals(((Map<String, String>) msg.obj).get("resultStatus"), "9000")) { // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
//                        ToastUtil.success("支付成功");
//                    } else if (TextUtils.equals(((Map<String, String>) msg.obj).get("resultStatus"), "6001")) {
//                        ToastUtil.normal("支付取消");
//                    } else { // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
//                        ToastUtil.error("支付失败");
//                    }
                    break;
                }
            }
        }
    };

}


