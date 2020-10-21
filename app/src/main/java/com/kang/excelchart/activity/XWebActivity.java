package com.kang.excelchart.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import com.kang.excelchart.R;
import com.kang.excelchart.base.BaseActivity;
import com.kang.excelchart.custom.view.TitleView;
import com.vondear.rxtool.RxActivityTool;

import java.util.List;

public class XWebActivity extends BaseActivity {

    private WebView webview;
    private TitleView header;

    public static void doIntent(Context context,String title,String url){
        Bundle bundle = new Bundle();
        bundle.putString("url",url);
        bundle.putString("title",title);
        RxActivityTool.skipActivity(context, XWebActivity.class,bundle);
    }
    @Override
    public int initLayout() {
        return R.layout.activity_xwebview;
    }

    @Override
    public void initView() {
        header = findViewById(R.id.header);
        webview = findViewById(R.id.webview);
    }

    @Override
    public void init(Bundle savedInstanceState) {
        header.setTitle(getIntent().getExtras().getString("title"));
        webview.loadUrl(getIntent().getExtras().getString("url"));
    }

    @Override
    protected void onDestroy() {
        if (webview != null) {
            webview.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            webview.clearHistory();
            ((ViewGroup) webview.getParent()).removeView(webview);
            webview.destroy();
            webview = null;
        }
        super.onDestroy();
    }

    @Override
    public List<View> needStopView() {
        return null;
    }
}