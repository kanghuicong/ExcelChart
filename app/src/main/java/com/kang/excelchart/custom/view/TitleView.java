package com.kang.excelchart.custom.view;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.kang.excelchart.R;
import com.vondear.rxtool.RxActivityTool;

public class TitleView extends RelativeLayout {
    RelativeLayout rlTitle;
    ImageButton ivBack;
    ImageButton ivRight;
    private Context context;

    public TextView tvRight, tvTitle, line;
    private Activity activity;

    public TitleView(Context context) {
        this(context, null);
    }

    public TitleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        activity = (Activity) context;

        LayoutInflater.from(context).inflate(R.layout.view_title, this, true);


        rlTitle = findViewById(R.id.title_layout);
        tvTitle = findViewById(R.id.title_txt);

        tvRight = findViewById(R.id.title_right);
        ivRight = findViewById(R.id.title_iv_right);
        line = findViewById(R.id.title_line);


        ivBack = findViewById(R.id.title_img);

//        BarUtils.addMarginTopEqualStatusBarHeight(rlTitle);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TitleView);


        /*背景颜色*/
        Drawable backgroundDrawable = typedArray.getDrawable(R.styleable.TitleView_background_drawable);
        if (backgroundDrawable == null) {
            int backgroundColor = typedArray.getColor(R.styleable.TitleView_background_color, 0xFFFFFFFF);
            this.setBackgroundColor(backgroundColor);
        } else this.setBackground(backgroundDrawable);
        /*标题*/
        String title = typedArray.getString(R.styleable.TitleView_title);
        tvTitle.setText(title);
        /*标题颜色*/
        int titleColor = typedArray.getInt(R.styleable.TitleView_title_color, 0xFF4A4A4A);
        tvTitle.setTextColor(titleColor);
        /*标题粗体*/
        boolean isBold = typedArray.getBoolean(R.styleable.TitleView_isbold, true);
        TextPaint tp = tvTitle.getPaint();
        if (isBold) tp.setFakeBoldText(true);
        else tp.setFakeBoldText(false);

        final boolean needLine = typedArray.getBoolean(R.styleable.TitleView_needLine, false);
        line.setVisibility(needLine ? VISIBLE : GONE);

        /*左边文字/图片*/
        //当需要自定义左边img时，isback=false,title_iv_left!=null
        final boolean isBack = typedArray.getBoolean(R.styleable.TitleView_isBack, true);
        if (isBack) {
            ivBack.setImageDrawable(ContextCompat.getDrawable(context, R.mipmap.fanhui));
            ivBack.setVisibility(View.VISIBLE);
        } else {
            Drawable drawable = typedArray.getDrawable(R.styleable.TitleView_title_iv_left);
            if (drawable == null) ivBack.setVisibility(View.GONE);
            else {
                ivBack.setVisibility(View.VISIBLE);
                ivBack.setImageDrawable(drawable);
            }
        }
        ivBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (iBack == null) RxActivityTool.finishActivity(activity);
                else iBack.back(view);
            }
        });

        /*右边文字*/
        String titleRight = typedArray.getString(R.styleable.TitleView_title_right);
        int titleRightColor = typedArray.getColor(R.styleable.TitleView_title_right_color, ContextCompat.getColor(context, R.color.main_color));
        tvRight.setText(titleRight);
        tvRight.setTextColor(titleRightColor);
        tvRight.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (iClickRightTitle != null) iClickRightTitle.click(view);
            }
        });


        Drawable rightImg = typedArray.getDrawable(R.styleable.TitleView_title_iv_right);
        if (rightImg != null) {
            ivRight.setVisibility(VISIBLE);
            ivRight.setImageDrawable(rightImg);
            ivRight.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (iClickRightImg != null) iClickRightImg.click(v);
                }
            });
        } else {
            ivRight.setVisibility(GONE);
        }

        typedArray.recycle();
    }

    /*--------------------------------------------set--------------------------------------------------------------*/
    public TitleView setNoLeft() {
        ivBack.setVisibility(INVISIBLE);
        return this;
    }

    public TitleView setLeftImg(int img) {
        ivBack.setImageDrawable(ContextCompat.getDrawable(context, img));
        return this;
    }

    public TitleView setBackgroundColor(String color) {
        rlTitle.setBackgroundColor(Color.parseColor(color));
        return this;
    }

    public TitleView setTitle(String title) {
        tvTitle.setText(title);
        return this;
    }

    public TitleView setTitleColor(int color) {
        tvTitle.setTextColor(ContextCompat.getColor(context, color));
        return this;
    }


    public TitleView setRightTitle(String title) {
        tvRight.setText(title);
        return this;
    }

    public TitleView setRightImg(int resId) {
        ivRight.setImageDrawable(ContextCompat.getDrawable(context, resId));
        return this;
    }


    public TitleView setRightTitleColor(int color) {
        tvRight.setTextColor(ContextCompat.getColor(context, color));
        return this;
    }

    public TitleView setClickRightTitleLister(IClickRightTitle iClickRightTitle) {
        this.iClickRightTitle = iClickRightTitle;
        return this;
    }

    public TitleView setClickRightImgLister(IClickRightImg iClickRightImg) {
        this.iClickRightImg = iClickRightImg;
        return this;
    }

    public TitleView setClickRightImg2Lister(IClickRightImg iClickRightImg2) {
        this.iClickRightImg2 = iClickRightImg2;
        return this;
    }

    public TitleView setLeftImage(int resource) {
        ivBack.setImageResource(resource);
        return this;
    }


    public TitleView setClickFinishLister(IBack iBack) {
        this.iBack = iBack;
        return this;
    }

    /*--------------------------------------------------get-------------------------------------------------------------*/
    public ImageView getRightImg() {
        return ivRight;
    }


    public TextView getTitleText() {
        return tvTitle;
    }


    public ImageView getLeftImg() {
        return ivBack;
    }


    IClickRightTitle iClickRightTitle;

    public interface IClickRightTitle {
        void click(View view);
    }

    IBack iBack;

    public interface IBack {
        void back(View view);
    }

    IClickRightImg iClickRightImg;
    IClickRightImg iClickRightImg2;

    public interface IClickRightImg {
        void click(View view);
    }
}
