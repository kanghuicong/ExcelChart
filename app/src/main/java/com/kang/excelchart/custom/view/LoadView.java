package com.kang.excelchart.custom.view;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.kang.excelchart.R;


/**
 * 类描述：加载动画
 */
public class LoadView extends LinearLayout {
    private ImageView ivTip;
    private boolean isStart = false;
    private ObjectAnimator rotate;
    public LoadView(Context context) {
        super(context);
        initView(context);
    }

    public LoadView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public LoadView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.view_tip, this, true);
        ivTip = findViewById(R.id.iv_tip);

        rotate = ObjectAnimator.ofFloat(ivTip, "rotation", 0f, 360f);
        rotate.setDuration(1000);
        rotate.setRepeatCount(ValueAnimator.INFINITE);
        rotate.setRepeatMode(ObjectAnimator.RESTART);//匀速
        rotate.setInterpolator(new LinearInterpolator());

    }

    public boolean isStart() {
        return isStart;
    }

    public void start() {
        this.setVisibility(VISIBLE);
        isStart = true;
        rotate.start();
    }

    public void stop() {
        this.setVisibility(GONE);
        isStart = false;
        rotate.cancel();
    }

}
