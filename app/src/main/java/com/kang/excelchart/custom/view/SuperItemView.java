package com.kang.excelchart.custom.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.kang.excelchart.R;
import com.vondear.rxtool.RxImageTool;


/**
 * Created by KangHuiCong on 2017/12/19.
 * <p>
 * ———————————————————————————————-top↓
 * ————————————————————————————————————————<TextView1><TextView2>
 * —————————————left→ <ImgView><TextView>                         <TextView><ImgView> ←right
 * ————————————————————————————————————————<TextView3><TextView4>
 * —————————————————————————————bottom↑
 * left的ImgView →设置图片setLeftImageL
 * left的TextView →设置文字内容setLeftText，设置文字大小setLeftTextSize,设置文字颜色setLeftTextColor
 * <p>
 * top和bottom的文字分left和right
 * TextView1→设置文字内容setTopLeftText，设置文字大小setTopLeftTextSize,设置文字颜色setTopLeftTextColor
 * TextView2→设置文字内容setTopRightText，设置文字大小setTopRightTextSize,设置文字颜色setTopRightTextColor
 * TextView3→设置文字内容setBottomLeftText，设置文字大小setBottomLeftTextSize,设置文字颜色setBottomLeftTextColor
 * TextView4→设置文字内容setBottomRightText，设置文字大小setBottomRightTextSize,设置文字颜色setBottomRightTextColor
 * <p>
 * right的TextView →设置文字内容setRightText，设置文字大小setRightTextSize,设置文字颜色setRightTextColor
 * right的ImgView →设置图片setRightImageL
 * <p>
 * xml设置
 * 参照attrs.xml,命名都是有按一定规律命名的
 * 上述动态设置方法也可在xml里设置，在xml里除了可以设置size,color和text外还可以设置margin
 * 特别说明：noLeft,noRight,noTop,noBottom,noLeftImageL,noRightImageL是用来设置是否需要该子View,减少布局复杂度
 * 比如：如果只需要设置左右两边的textView,那么left和right的ImgView，top和bottom皆不需要
 * 则设置noLeftImageL，noRightImageL，noTop，noBottom
 * isPadding默认为false，需要自己设置padding；设置为true时则整个LinearLayout有个默认padding
 * isCheckBox默认false；设置为true时则显示单选CheckBox
 * isSwitch默认false；设置为true时则显示Switch
 * isTopBetween默认false；设置为true时则TextView1权重为1
 * isBottomBetween默认false；设置为true时则TextView3权重为1
 */

public class SuperItemView extends LinearLayout {
    int defaultColor = 0xFF262626;
    int defaultColor2 = 0xFF595959;
    int defaultTextBackground = 0xFFF4F5F6;

    int defaultSize = 14;
    int defaultDrawablePadding = 5;
    int defaultMargins_0 = 0;
    int defaultMargins_10 = 10;
    int defaultMargins_20 = 20;
    //按实际修改
    int defaultRoundHeader = R.mipmap.icon_arrow;
    int defaultRoundImg = R.mipmap.icon_arrow;
    int defaultArrow = R.mipmap.icon_arrow;
    int defaultCheckBox = R.mipmap.icon_arrow;

    private boolean isPadding;
    private boolean needBackground;
    /*left--left图片*/
    private Drawable mLeftImageL;
    private int mLeftImageLWidth;
    private int mLeftImageLHeight;
    private boolean noLeftImgL;
    private int mLeftImageLLeftMargin;
    private int mLeftImageLRightMargin;
    private int mLeftImageLTopMargin;
    private int mLeftImageLBottomMargin;
    /*left文字*/
    private String mLeftText;
    private boolean isLeftTextBold;
    private int mLeftTextColor;
    private int mLeftTextSize;
    private int mLeftDrawablePadding;
    private int mLeftTextLeftMargin;
    private int mLeftTextRightMargin;
    private int mLeftTextTopMargin;
    private int mLeftTextBottomMargin;
    private boolean noLeftText;
    /*left--right图片*/
    private Drawable mLeftImageR;
    private int mLeftImageRWidth;
    private int mLeftImageRHeight;
    /*right--left图片*/
    private Drawable mRightImageL;
    private int mRightImageLWidth;
    private int mRightImageLHeight;
    private int mRightImageLLeftMargin;
    private int mRightImageLRightMargin;
    private int mRightImageLTopMargin;
    private int mRightImageLBottomMargin;
    private boolean noRightImgL;
    private boolean noRightImgR;
    /*right文字*/
    private String mRightText;
    private int mRightTextColor;
    private int mRightTextSize;
    private int mRightDrawablePadding;
    private int mRightTextLeftMargin;
    private int mRightTextRightMargin;
    private int mRightTextTopMargin;
    private int mRightTextBottomMargin;
    private boolean noRightText;
    /*right--right图片*/
    private Drawable mRightImageR;
    private int mRightImageRWidth;
    private int mRightImageRHeight;
    private boolean isArrow;
    /*CheckBox*/
    private boolean isCheckBox;
    private int mCheckBoxWidth;
    private int mCheckBoxHeight;
    private int mCheckBoxLeftMargin;
    private int mCheckBoxRightMargin;
    private int mCheckBoxTopMargin;
    private int mCheckBoxBottomMargin;
    /*switch*/
    private boolean isSwitch;
    /*top*/
    private String mTopGravity;
    private int mTop_leftMargin;
    private int mTop_topMargin;
    private int mTop_bottomMargin;
    private int mTop_rightMargin;
    private boolean noTop;
    private boolean isTopBetween;
    /*top--left文字*/
    private String mTopLeftText;
    private int mTopLeftTextSize;
    private int mTopLeftTextColor;
    private int mTopLeftLeftMargin;
    private int mTopLeftTopMargin;
    private int mTopLeftRightMargin;
    private int mTopLeftBottomMargin;
    /*top--right文字*/
    private String mTopRightText;
    private int mTopRightTextSize;
    private int mTopRightTextColor;
    private int mTopRightLeftMargin;
    private int mTopRightTopMargin;
    private int mTopRightRightMargin;
    private int mTopRightBottomMargin;
    /*bottom*/
    private String mBottomGravity;
    private boolean noBottom;
    private boolean isBottomBetween;
    private int mBottom_leftMargin;
    private int mBottom_topMargin;
    private int mBottom_bottomMargin;
    private int mBottom_rightMargin;
    /*bottom--left文字*/
    private String mBottomLeftText;
    private int mBottomLeftTextSize;
    private int mBottomLeftTextColor;
    private int mBottomLeftLeftMargin;
    private int mBottomLeftTopMargin;
    private int mBottomLeftRightMargin;
    private int mBottomLeftBottomMargin;
    /*bottom--right文字*/
    private String mBottomRightText;
    private int mBottomRightTextSize;
    private int mBottomRightTextColor;
    private int mBottomRightLeftMargin;
    private int mBottomRightTopMargin;
    private int mBottomRightRightMargin;
    private int mBottomRightBottomMargin;

    /*---------------------------------------------------------------------------------------------------*/

    private Context context;

    private ImageView leftImageL;
    private TextView leftText;
    private ImageView rightImageL;
    private ImageView rightImageR;
    private TextView rightText;
    private CheckBox checkBox;
    private SwitchCompat switchCompat;

    private LinearLayout centerLayout;

    private LinearLayout topLayout;
    private TextView topLeftText;
    private TextView topRightText;

    private LinearLayout bottomLayout;
    private TextView bottomLeftText;
    private TextView bottomRightText;

    public SuperItemView(Context context) {
        this(context, null);
    }

    public SuperItemView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SuperItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initAttr(attrs);
        init();
    }

    private void initAttr(AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SuperItemView);

        isPadding = typedArray.getBoolean(R.styleable.SuperItemView_isPadding, true);
        needBackground = typedArray.getBoolean(R.styleable.SuperItemView_needBackground, false);
        /*left--left图片*/
        mLeftImageL = typedArray.getDrawable(R.styleable.SuperItemView_left_limg);
        mLeftImageLWidth = typedArray.getInteger(R.styleable.SuperItemView_left_limg_width, LayoutParams.WRAP_CONTENT);
        mLeftImageLHeight = typedArray.getInteger(R.styleable.SuperItemView_left_limg_height, LayoutParams.WRAP_CONTENT);
        mLeftImageLLeftMargin = typedArray.getInteger(R.styleable.SuperItemView_left_limg_leftMargin, defaultMargins_0);
        mLeftImageLRightMargin = typedArray.getInteger(R.styleable.SuperItemView_left_limg_rightMargin, defaultMargins_10);
        mLeftImageLTopMargin = typedArray.getInteger(R.styleable.SuperItemView_left_limg_topMargin, defaultMargins_0);
        mLeftImageLBottomMargin = typedArray.getInteger(R.styleable.SuperItemView_left_limg_bottomMargin, defaultMargins_0);
        noLeftImgL = typedArray.getBoolean(R.styleable.SuperItemView_noLeftImageL, false);
        /*left文字*/
        mLeftText = typedArray.getString(R.styleable.SuperItemView_left_text);
        isLeftTextBold = typedArray.getBoolean(R.styleable.SuperItemView_left_text_bold, false);
        mLeftTextColor = typedArray.getColor(R.styleable.SuperItemView_left_text_color, defaultColor);
        mLeftTextSize = typedArray.getInteger(R.styleable.SuperItemView_left_text_size, defaultSize);
        mLeftDrawablePadding = typedArray.getInteger(R.styleable.SuperItemView_left_drawable_padding, defaultDrawablePadding);
        mLeftTextLeftMargin = typedArray.getInteger(R.styleable.SuperItemView_left_text_leftMargin, defaultMargins_0);
        mLeftTextTopMargin = typedArray.getInteger(R.styleable.SuperItemView_left_text_topMargin, defaultMargins_0);
        mLeftTextBottomMargin = typedArray.getInteger(R.styleable.SuperItemView_left_text_bottomMargin, defaultMargins_0);
        mLeftTextRightMargin = typedArray.getInteger(R.styleable.SuperItemView_left_text_rightMargin, defaultMargins_10);
        noLeftText = typedArray.getBoolean(R.styleable.SuperItemView_noLeftText, false);
        /*left--right图片*/
        mLeftImageR = typedArray.getDrawable(R.styleable.SuperItemView_left_rimg);
        mLeftImageRWidth = typedArray.getInteger(R.styleable.SuperItemView_left_rimg_width, -1);
        mLeftImageRHeight = typedArray.getInteger(R.styleable.SuperItemView_left_rimg_height, -1);
        /*right--left图片*/
        mRightImageL = typedArray.getDrawable(R.styleable.SuperItemView_right_limg);
        mRightImageLWidth = typedArray.getInteger(R.styleable.SuperItemView_right_limg_width, -1);
        mRightImageLHeight = typedArray.getInteger(R.styleable.SuperItemView_right_limg_height, -1);
        mRightImageLLeftMargin = typedArray.getInteger(R.styleable.SuperItemView_right_limg_leftMargin, defaultMargins_10);
        mRightImageLRightMargin = typedArray.getInteger(R.styleable.SuperItemView_right_limg_rightMargin, defaultMargins_0);
        mRightImageLTopMargin = typedArray.getInteger(R.styleable.SuperItemView_right_limg_topMargin, defaultMargins_0);
        mRightImageLBottomMargin = typedArray.getInteger(R.styleable.SuperItemView_right_limg_bottomMargin, defaultMargins_0);
        noRightImgL = typedArray.getBoolean(R.styleable.SuperItemView_noRightImgL, false);
        /*right文字*/
        mRightText = typedArray.getString(R.styleable.SuperItemView_right_text);
        mRightTextColor = typedArray.getColor(R.styleable.SuperItemView_right_text_color, defaultColor2);
        mRightTextSize = typedArray.getInteger(R.styleable.SuperItemView_right_text_size, defaultSize);
        mRightDrawablePadding = typedArray.getInteger(R.styleable.SuperItemView_right_drawable_padding, defaultDrawablePadding);
        mRightTextLeftMargin = typedArray.getInteger(R.styleable.SuperItemView_right_text_leftMargin, defaultMargins_10);
        mRightTextRightMargin = typedArray.getInteger(R.styleable.SuperItemView_right_text_rightMargin, defaultMargins_0);
        mRightTextTopMargin = typedArray.getInteger(R.styleable.SuperItemView_right_text_topMargin, defaultMargins_0);
        mRightTextBottomMargin = typedArray.getInteger(R.styleable.SuperItemView_right_text_bottomMargin, defaultMargins_0);
        noRightText = typedArray.getBoolean(R.styleable.SuperItemView_noRightText, false);
        /*right--right图片*/
        mRightImageR = typedArray.getDrawable(R.styleable.SuperItemView_right_rimg);
        mRightImageRWidth = typedArray.getInteger(R.styleable.SuperItemView_right_rimg_width, -1);
        mRightImageRHeight = typedArray.getInteger(R.styleable.SuperItemView_right_rimg_height, -1);
        isArrow = typedArray.getBoolean(R.styleable.SuperItemView_isArrow, false);

        noRightImgR = typedArray.getBoolean(R.styleable.SuperItemView_noRightImgR, true);
        /*CheckBox*/
        isCheckBox = typedArray.getBoolean(R.styleable.SuperItemView_isCheckBox, false);
        mCheckBoxWidth = typedArray.getInteger(R.styleable.SuperItemView_checkBox_width, defaultMargins_20);
        mCheckBoxHeight = typedArray.getInteger(R.styleable.SuperItemView_checkBox_height, defaultMargins_20);
        mCheckBoxLeftMargin = typedArray.getInteger(R.styleable.SuperItemView_checkBox_leftMargin, defaultMargins_20);
        mCheckBoxTopMargin = typedArray.getInteger(R.styleable.SuperItemView_checkBox_topMargin, defaultMargins_0);
        mCheckBoxRightMargin = typedArray.getInteger(R.styleable.SuperItemView_checkBox_rightMargin, defaultMargins_0);
        mCheckBoxBottomMargin = typedArray.getInteger(R.styleable.SuperItemView_checkBox_bottomMargin, defaultMargins_0);
        /*switch*/
        isSwitch = typedArray.getBoolean(R.styleable.SuperItemView_isSwitch, false);
        /*top*/
        mTopGravity = typedArray.getString(R.styleable.SuperItemView_top_gravity);
        mTop_leftMargin = typedArray.getInteger(R.styleable.SuperItemView_top_leftMargin, defaultMargins_0);
        mTop_topMargin = typedArray.getInteger(R.styleable.SuperItemView_top_topMargin, defaultMargins_0);
        mTop_bottomMargin = typedArray.getInteger(R.styleable.SuperItemView_top_bottomMargin, defaultMargins_0);
        mTop_rightMargin = typedArray.getInteger(R.styleable.SuperItemView_top_rightMargin, defaultMargins_0);
        noTop = typedArray.getBoolean(R.styleable.SuperItemView_noTop, false);
        isTopBetween = typedArray.getBoolean(R.styleable.SuperItemView_isTopBetween, false);
        /*top--left文字*/
        mTopLeftText = typedArray.getString(R.styleable.SuperItemView_top_left_text);
        mTopLeftTextSize = typedArray.getInteger(R.styleable.SuperItemView_top_left_textSize, defaultSize);
        mTopLeftTextColor = typedArray.getColor(R.styleable.SuperItemView_top_left_textColor, defaultColor);
        mTopLeftLeftMargin = typedArray.getInteger(R.styleable.SuperItemView_top_left_leftMargin, defaultMargins_0);
        mTopLeftTopMargin = typedArray.getInteger(R.styleable.SuperItemView_top_left_topMargin, defaultMargins_0);
        mTopLeftRightMargin = typedArray.getInteger(R.styleable.SuperItemView_top_left_rightMargin, defaultMargins_0);
        mTopLeftBottomMargin = typedArray.getInteger(R.styleable.SuperItemView_top_left_bottomMargin, defaultMargins_0);
        /*top--right文字*/
        mTopRightText = typedArray.getString(R.styleable.SuperItemView_top_right_text);
        mTopRightTextSize = typedArray.getInteger(R.styleable.SuperItemView_top_right_textSize, defaultSize);
        mTopRightTextColor = typedArray.getColor(R.styleable.SuperItemView_top_right_textColor, defaultColor);
        mTopRightLeftMargin = typedArray.getInteger(R.styleable.SuperItemView_top_right_leftMargin, defaultMargins_10);
        mTopRightTopMargin = typedArray.getInteger(R.styleable.SuperItemView_top_right_topMargin, defaultMargins_0);
        mTopRightRightMargin = typedArray.getInteger(R.styleable.SuperItemView_top_right_rightMargin, defaultMargins_0);
        mTopRightBottomMargin = typedArray.getInteger(R.styleable.SuperItemView_top_right_bottomMargin, defaultMargins_0);
        /*bottom*/
        mBottomGravity = typedArray.getString(R.styleable.SuperItemView_bottom_gravity);
        mBottom_topMargin = typedArray.getInteger(R.styleable.SuperItemView_bottom_topMargin, defaultMargins_0);
        mBottom_leftMargin = typedArray.getInteger(R.styleable.SuperItemView_bottom_leftMargin, defaultMargins_0);
        mBottom_bottomMargin = typedArray.getInteger(R.styleable.SuperItemView_bottom_bottomMargin, defaultMargins_0);
        mBottom_rightMargin = typedArray.getInteger(R.styleable.SuperItemView_bottom_rightMargin, defaultMargins_0);
        noBottom = typedArray.getBoolean(R.styleable.SuperItemView_noBottom, false);
        isBottomBetween = typedArray.getBoolean(R.styleable.SuperItemView_isBottomBetween, false);
        /*bottom--left文字*/
        mBottomLeftText = typedArray.getString(R.styleable.SuperItemView_bottom_left_text);
        mBottomLeftTextSize = typedArray.getInteger(R.styleable.SuperItemView_bottom_left_textSize, defaultSize);
        mBottomLeftTextColor = typedArray.getColor(R.styleable.SuperItemView_bottom_left_textColor, defaultColor);
        mBottomLeftLeftMargin = typedArray.getInteger(R.styleable.SuperItemView_bottom_left_leftMargin, defaultMargins_0);
        mBottomLeftTopMargin = typedArray.getInteger(R.styleable.SuperItemView_bottom_left_topMargin, defaultMargins_0);
        mBottomLeftRightMargin = typedArray.getInteger(R.styleable.SuperItemView_bottom_left_rightMargin, defaultMargins_0);
        mBottomLeftBottomMargin = typedArray.getInteger(R.styleable.SuperItemView_bottom_left_bottomMargin, defaultMargins_0);
        /*bottom--right文字*/
        mBottomRightText = typedArray.getString(R.styleable.SuperItemView_bottom_right_text);
        mBottomRightTextSize = typedArray.getInteger(R.styleable.SuperItemView_bottom_right_textSize, defaultSize);
        mBottomRightTextColor = typedArray.getColor(R.styleable.SuperItemView_bottom_right_textColor, defaultColor);
        mBottomRightLeftMargin = typedArray.getInteger(R.styleable.SuperItemView_bottom_right_leftMargin, defaultMargins_10);
        mBottomRightTopMargin = typedArray.getInteger(R.styleable.SuperItemView_bottom_right_topMargin, defaultMargins_0);
        mBottomRightRightMargin = typedArray.getInteger(R.styleable.SuperItemView_bottom_right_rightMargin, defaultMargins_0);
        mBottomRightBottomMargin = typedArray.getInteger(R.styleable.SuperItemView_bottom_right_bottomMargin, defaultMargins_0);

        typedArray.recycle();
    }

    private void init() {
        if (isPadding) {

            this.setPadding(fitSize(16), fitSize(10), fitSize(16), fitSize(10));
        } else
            this.setPadding(this.getPaddingLeft(), this.getPaddingTop(), this.getPaddingRight(), this.getPaddingBottom());
        this.setOrientation(HORIZONTAL);
        this.setGravity(Gravity.CENTER);
        if (this.getBackground() == null) {
            this.setBackground(context.getResources().getDrawable(R.drawable.bt_ffffff_press));
        }

        initLeftImage();
        initLeftText();
        initCenter();
        initRightLImage();
        initRightText();
        initRightRImage();
        initCheckBox();
        initSwitch();
    }

    /*-----------------------------------------初始化布局------------------------------------------------*/
    /*left---left图片*/
    private void initLeftImage() {
        if (noLeftImgL) return;
        leftImageL = new ImageView(context);
        initImage(leftImageL, mLeftImageLWidth, mLeftImageLHeight, mLeftImageL, mLeftImageLLeftMargin, mLeftImageLTopMargin, mLeftImageLRightMargin, mLeftImageLBottomMargin);
    }

    /*left文字*/
    private void initLeftText() {
        if (noLeftText) return;
        leftText = new TextView(context);
        //粗体
        TextPaint tp = leftText.getPaint();
        tp.setFakeBoldText(isLeftTextBold);

        initText(leftText, mLeftText, mLeftTextSize, mLeftTextColor, mLeftImageR, mLeftImageRWidth, mLeftImageRHeight, mLeftDrawablePadding, mLeftTextLeftMargin, mLeftTextTopMargin, mLeftTextRightMargin, mLeftTextBottomMargin);
        goneText(mLeftText, leftText);
    }

    /*center*/
    private void initCenter() {
        centerLayout = new LinearLayout(context);
        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 1);
        centerLayout.setLayoutParams(lp);
        centerLayout.setOrientation(VERTICAL);
        centerLayout.setGravity(Gravity.CENTER);
        initTop();
        initBottom();
        addView(centerLayout);
    }

    /*top*/
    private void initTop() {
        if (noTop) return;
        topLayout = new LinearLayout(context);
        initCenterLayout(topLayout, mTopGravity, mTop_leftMargin, mTop_topMargin, mTop_rightMargin, mTop_bottomMargin);

        topLeftText = new TextView(context);
        initCenterText(topLeftText, mTopLeftText, mTopLeftTextSize, mTopLeftTextColor, mTopLeftLeftMargin, mTopLeftTopMargin, mTopLeftRightMargin, mTopLeftBottomMargin, 1, true, isTopBetween);
        topRightText = new TextView(context);
        initCenterText(topRightText, mTopRightText, mTopRightTextSize, mTopRightTextColor, mTopRightLeftMargin, mTopRightTopMargin, mTopRightRightMargin, mTopRightBottomMargin, 1, false, isTopBetween);
        goneText(mTopRightText, topRightText);

        topLayout.addView(topLeftText);
        topLayout.addView(topRightText);
        centerLayout.addView(topLayout);
    }

    /*bottom*/
    private void initBottom() {
        if (noBottom) return;
        bottomLayout = new LinearLayout(context);
        initCenterLayout(bottomLayout, mBottomGravity, mBottom_leftMargin, mBottom_topMargin, mBottom_rightMargin, mBottom_bottomMargin);

        if (mBottomLeftText == null && mBottomRightText == null) bottomLayout.setVisibility(GONE);

        bottomLeftText = new TextView(context);
        initCenterText(bottomLeftText, mBottomLeftText, mBottomLeftTextSize, mBottomLeftTextColor, mBottomLeftLeftMargin, mBottomLeftTopMargin, mBottomLeftRightMargin, mBottomLeftBottomMargin, 1.2f, true, isBottomBetween);
        bottomRightText = new TextView(context);
        initCenterText(bottomRightText, mBottomRightText, mBottomRightTextSize, mBottomRightTextColor, mBottomRightLeftMargin, mBottomRightTopMargin, mBottomRightRightMargin, mBottomRightBottomMargin, 1.2f, false, isBottomBetween);
        goneText(mBottomRightText, bottomRightText);

        bottomLayout.addView(bottomLeftText);
        bottomLayout.addView(bottomRightText);
        centerLayout.addView(bottomLayout);
    }

    /*right---left图片*/
    private void initRightLImage() {
        if (noRightImgL) return;
        rightImageL = new ImageView(context);
        initImage(rightImageL, mRightImageLWidth, mRightImageLHeight, mRightImageL, mRightImageLLeftMargin, mRightImageLTopMargin, mRightImageLRightMargin, mRightImageLBottomMargin);
    }

    /*right文字*/
    private void initRightText() {
        if (noRightText) return;
        rightText = new TextView(context);
        if (!isArrow) {
            initText(rightText, mRightText, mRightTextSize, mRightTextColor, mRightImageR, mRightImageRWidth, mRightImageRHeight, mRightDrawablePadding, mRightTextLeftMargin, mRightTextTopMargin, mRightTextRightMargin, mRightTextBottomMargin);
            goneText(mRightText, rightText);
        } else {
            Bitmap image = BitmapFactory.decodeResource(this.getResources(), defaultArrow);
            initText(rightText, mRightText, mRightTextSize, mRightTextColor, context.getResources().getDrawable(defaultArrow), image.getWidth(), image.getHeight(), mRightDrawablePadding, mRightTextLeftMargin, mRightTextTopMargin, mRightTextRightMargin, mRightTextBottomMargin);
        }
    }

    /*当right文字要单独设置样式，又需要箭头时，isArrow = false,noRightImgR = false*/
    private void initRightRImage() {
        if (noRightImgR) return;
        rightImageR = new ImageView(context);
//        LayoutParams lp = new LayoutParams(new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT));
//        rightImageR.setLayoutParams(lp);
        rightImageR.setBackground(getResources().getDrawable(defaultArrow));
        addView(rightImageR);


    }

    /*CheckBox*/
    private void initCheckBox() {
        if (isCheckBox) {
            checkBox = new CheckBox(context);
            LayoutParams lp = new LayoutParams(fitSize(mCheckBoxWidth), fitSize(mCheckBoxHeight));
            lp.setMargins(mCheckBoxLeftMargin, mCheckBoxTopMargin, mCheckBoxRightMargin, mCheckBoxBottomMargin);
            checkBox.setLayoutParams(lp);
            checkBox.setButtonDrawable(R.color.transparent);
            checkBox.setBackgroundResource(defaultCheckBox);
            addView(checkBox);
        }
    }

    /*switch*/
    private void initSwitch() {
        if (isSwitch) {
//            this.setPadding(fitSize(12), fitSize(10), fitSize(12), fitSize(10));
            switchCompat = new SwitchCompat(context);
            switchCompat.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            switchCompat.setBackgroundResource(R.color.transparent);

            addView(switchCompat);
        }
    }

    /*-------------------------------------------公共方法----------------------------------------------*/

    private void initCenterLayout(LinearLayout layout, String gravity, int leftMargin, int topMargin, int rightMargin, int bottomMargin) {
        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 1);
        lp.setMargins(fitSize(leftMargin), fitSize(topMargin), fitSize(rightMargin), fitSize(bottomMargin));
        layout.setLayoutParams(lp);
        layout.setOrientation(HORIZONTAL);
        layout.setGravity(returnGravity(gravity));
    }

    private void initCenterText(TextView textView, String content, int size, int color, int leftMargin, int topMargin, int rightMargin, int bottomMargin, float line, boolean isLeft, boolean isBetween) {
        LayoutParams lp = null;
        if (isLeft) {
            if (isBetween)
                lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 1);
            else
                lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        } else {
            if (isBetween)
                lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            else
                lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 1);
        }

        lp.setMargins(fitSize(leftMargin), fitSize(topMargin), fitSize(rightMargin), fitSize(bottomMargin));

        textView.setLayoutParams(lp);
        textView.setGravity(Gravity.CENTER | Gravity.LEFT);
        textView.setLineSpacing(2, line);
        textView.setText(content);
        textView.setTextSize(size);
        textView.setTextColor(color);
    }


    private void initText(TextView textView, String content, int textSize, int textColor, Drawable drawable, int drawableWidth, int drawableHight, int drawablePadding, int leftMargin, int topMargin, int rightMargin, int bottomMargin) {


        LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

        if (needBackground) {
            textView.setBackgroundColor(defaultTextBackground);
            textView.setTextColor(getResources().getColor(R.color.transparent));
        } else textView.setTextColor(textColor);

        lp.setMargins(fitSize(leftMargin), fitSize(topMargin), fitSize(rightMargin), fitSize(bottomMargin));
        textView.setLayoutParams(lp);
        textView.setGravity(Gravity.CENTER);
        textView.setText(content);
        textView.setTextSize(textSize);


        if (drawable != null) {
            if (drawableWidth != -1 && drawableHight != -1) {
                drawable.setBounds(0, 0, drawableWidth, drawableHight);
                textView.setCompoundDrawables(null, null, drawable, null);
            } else textView.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        }
        textView.setCompoundDrawablePadding(fitSize(drawablePadding));

        addView(textView);
    }


    private void initImage(ImageView imageView, int imageWidth, int imageHeight, Drawable drawable, int leftMargins, int topMargins, int rightMargins, int bottomMargins) {
        LayoutParams lp = new LayoutParams(fitSize(imageWidth), fitSize(imageHeight));
        lp.setMargins(fitSize(leftMargins), fitSize(topMargins), fitSize(rightMargins), fitSize(bottomMargins));
        imageView.setLayoutParams(lp);
        imageView.setBackgroundDrawable(drawable);
        addView(imageView);
        goneText(drawable, imageView);
    }

    public int fitSize(int size) {
        return RxImageTool.dp2px(size);
//        return size;
    }

    private void goneText(Object content, View view) {
        if (content == null) view.setVisibility(GONE);
    }

    private void visibleText(View view) {
        if (view.getVisibility() == GONE) view.setVisibility(VISIBLE);
    }

    private <T> void glideImage(ImageView view, T url, boolean isRound) {
        if (isRound)
//            Glide.with(context).load(url).apply(new RequestOptions()
//                    .placeholder(defaultRoundHeader)// 正在加载中的图片  
//                    .error(defaultRoundHeader)// 加载失败的图片  
//                    .centerCrop()
//                    .diskCacheStrategy(DiskCacheStrategy.ALL))
//                    .into(view);
            Glide.with(this)
                    .load(url)
                    .apply(RequestOptions.bitmapTransform(new CircleCrop())
                            .placeholder(defaultRoundImg)
                            .error(defaultRoundHeader)
                            .diskCacheStrategy(DiskCacheStrategy.ALL))
                    .into(view);
        else {
            Glide.with(context).load(url).apply(new RequestOptions()
                    .placeholder(defaultRoundImg)// 正在加载中的图片  
                    .error(defaultRoundImg)// 加载失败的图片  
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL))
                    .into(view);
        }

    }


    private int returnGravity(String gravity) {
        if (gravity == null) return Gravity.LEFT;
        switch (gravity) {
            case "center":
                return Gravity.CENTER;
//            case "left":
//                return Gravity.LEFT;
            case "right":
                return Gravity.RIGHT;
            case "top":
                return Gravity.TOP;
            case "bottom":
                return Gravity.BOTTOM;
            default:
                return Gravity.LEFT;
        }
    }

    /*----------------------------------------------动态修改----------------------------------------*/
    /*-------------------------------------------left-----------------------------------------------*/
    /*left--left图片*/
    public <T> SuperItemView setLeftImageL(T url, boolean isRound) {
        visibleText(leftImageL);
        glideImage(leftImageL, url, isRound);
        return this;
    }

    public SuperItemView setLeftImageParams(int imageWidth, int imageHeight, int leftMargins, int topMargins, int rightMargins, int bottomMargins) {
        LayoutParams lp = new LayoutParams(fitSize(imageWidth), fitSize(imageHeight));
        lp.setMargins(fitSize(leftMargins), fitSize(topMargins), fitSize(rightMargins), fitSize(bottomMargins));
        leftImageL.setLayoutParams(lp);
        return this;
    }

    /*left文字content*/
    public SuperItemView setLeftText(String mLeftText) {
        visibleText(leftText);
        leftText.setText(mLeftText);
        return this;
    }

    /*left文字size*/
    public SuperItemView setLeftTextSize(int size) {
        leftText.setTextSize(size);
        return this;
    }

    /*left文字color*/
    public SuperItemView setLeftTextColor(int color, boolean isDrawable) {
        if (isDrawable) leftText.setTextColor(context.getResources().getColorStateList(color));
        else leftText.setTextColor(context.getResources().getColor(color));
        return this;
    }

    /*-------------------------------------------top-----------------------------------------------*/
    public SuperItemView setTopGravity(int gravity) {
        topLayout.setGravity(gravity);
        return this;
    }

    /*top--left文字content*/
    public SuperItemView setTopLeftText(String content) {
        topLeftText.setText(content);
        return this;
    }

    /*top--left文字-左边img*/
    public SuperItemView setTopLeftTextImg(Drawable drawable, int drawableWidth, int drawableHight) {
        drawable.setBounds(0, 0, drawableWidth, drawableHight);
        topLeftText.setCompoundDrawablePadding(5);
        topLeftText.setCompoundDrawables(drawable, null, null, null);

        return this;
    }

    /*top--left文字size*/
    public SuperItemView setTopLeftTextSize(int size) {
        topLeftText.setTextSize(size);
        return this;
    }

    /*top--left文字color*/
    public SuperItemView setTopLeftTextColor(int color, boolean isDrawable) {
        if (isDrawable) topLeftText.setTextColor(context.getResources().getColorStateList(color));
        else topLeftText.setTextColor(context.getResources().getColor(color));
        return this;
    }

    /*top--right文字content*/
    public SuperItemView setTopRightText(String content) {
        visibleText(topRightText);
        topRightText.setText(content);
        return this;
    }

    /*top--right文字size*/
    public SuperItemView setTopRightTextSize(int size) {
        topRightText.setTextSize(size);
        return this;
    }

    /*top--right文字color*/
    public SuperItemView setTopRightTextColor(int color, boolean isDrawable) {
        if (isDrawable) topRightText.setTextColor(context.getResources().getColorStateList(color));
        else topRightText.setTextColor(context.getResources().getColor(color));
        return this;
    }

    /*-------------------------------------------bottom-----------------------------------------------*/
    public SuperItemView setBottomGravity(int gravity) {
        bottomLayout.setGravity(gravity);
        return this;
    }

    /*bottom--left文字content*/
    public SuperItemView setBottomLeftText(String content) {
        visibleText(bottomLayout);
        bottomLeftText.setText(content);
        return this;
    }

    /*top--left文字size*/
    public SuperItemView setBottomLeftTextSize(int size) {
        bottomLeftText.setTextSize(size);
        return this;
    }

    /*bottom--left文字color*/
    public SuperItemView setBottomLeftTextColor(int color, boolean isDrawable) {
        if (isDrawable)
            bottomLeftText.setTextColor(context.getResources().getColorStateList(color));
        else bottomLeftText.setTextColor(context.getResources().getColor(color));
        return this;
    }

    /*bottom--right文字content*/
    public SuperItemView setBottomRightText(String content) {
        visibleText(bottomLayout);
        visibleText(bottomRightText);
        bottomRightText.setText(content);
        return this;
    }

    /*bottom--right文字size*/
    public SuperItemView setBottomRightTextSize(int size) {
        bottomRightText.setTextSize(size);
        return this;
    }

    /*top--right文字color*/
    public SuperItemView setBottomRightTextColor(int color, boolean isDrawable) {
        if (isDrawable)
            bottomRightText.setTextColor(context.getResources().getColorStateList(color));
        else bottomRightText.setTextColor(context.getResources().getColor(color));
        return this;
    }

    /*-------------------------------------------right-----------------------------------------------*/
    /*right---left图片*/
    public SuperItemView setRightImageL(String url, boolean isRound) {
        visibleText(rightImageL);
        glideImage(rightImageL, url, isRound);
        return this;
    }

    public SuperItemView setRightImageL(int resource) {
        visibleText(rightImageL);
        rightImageL.setBackgroundResource(resource);
        return this;
    }

    /*right文字content*/
    public SuperItemView setRightText(String mRightText) {
        visibleText(rightText);
        rightText.setText(mRightText);
        return this;
    }

    public SuperItemView setRightText(StringBuilder mRightText) {
        visibleText(rightText);
        rightText.setText(mRightText);
        return this;
    }

    /*right文字size*/
    public SuperItemView setRightTextSize(int size) {
        rightText.setTextSize(size);
        return this;
    }

    /*right文字size*/

    public SuperItemView setRightTextColor(int color, boolean isDrawable) {
        if (isDrawable) rightText.setTextColor(context.getResources().getColorStateList(color));
        else rightText.setTextColor(context.getResources().getColor(color));
        return this;
    }

    /*-------------------------------------------arrow----------------------------------------------*/
    public SuperItemView setArrow(boolean isArrow) {
        if (!isArrow) {
            rightText.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        }
        return this;
    }

    /*-------------------------------------------switch----------------------------------------------*/
    public SuperItemView setSwitch(boolean isSwitch) {
        switchCompat.setChecked(isSwitch);
        return this;
    }

    public SuperItemView setSwitchClickable(boolean isClickable) {
        switchCompat.setClickable(isClickable);
        return this;
    }

    /*---------------------------------------------checkBox-----------------------------------------------*/
    public SuperItemView setCheckBox(boolean isCheck) {
        checkBox.setChecked(isCheck);
        return this;
    }

    public SuperItemView setIsCheckBox(boolean isSetCheckBox) {
        isCheckBox = isSetCheckBox;
        initCheckBox();
        return this;
    }


    /*-------------------------------------------click----------------------------------------------*/

    public SuperItemView setOnClickItem(final IClick iItemClick) {
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                iItemClick.onClick(view);
            }
        });
        return this;
    }

    /*left---left图片点击事件*/
    public SuperItemView setOnClickLeftImgL(final IClick iItemClick) {
        leftImageL.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                iItemClick.onClick(view);
            }
        });
        return this;
    }

    /*bottom---left文字点击事件*/
    public SuperItemView setOnClickBottomLeftText(final IClick iTextClick) {
        bottomLeftText.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                iTextClick.onClick(view);
            }
        });
        return this;
    }

    /*bottom---right文字点击事件*/
    public SuperItemView setOnClickBottomRightText(final IClick iTextClick) {
        bottomRightText.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                iTextClick.onClick(view);
            }
        });
        return this;
    }

    /*checkBox点击事件*/
    public SuperItemView setOnClickCheckBox(final IChangeClick iChangeClick) {
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                iChangeClick.onClick(buttonView, isChecked);
            }
        });
        return this;
    }

    /*Switch点击事件*/
    public SuperItemView setOnClickSwitch(final IChangeClick iChangeClick) {
        switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                iChangeClick.onClick(buttonView, isChecked);
            }
        });
        return this;
    }

    /*Switch点击事件*/
    public SuperItemView setOnClickRightText(final IClick iClick) {
        rightText.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (iClick != null) iClick.onClick(v);
            }
        });
        return this;
    }

    /*-------------------------------------------------------------------------------------------------*/
    public String getTopLeftText() {
        return topLeftText.getText().toString();
    }

    public String getRightText() {
        return rightText.getText().toString();
    }

    public TextView getRightTextView() {
        return rightText;
    }

    public ImageView getRightImgView() {
        return rightImageL;
    }

    public TextView getLeftTextView() {
        return leftText;
    }

    public TextView getTopLeftTextView() {
        return topLeftText;
    }

    public boolean isCheck() {
        return switchCompat.isChecked();
    }


    /*-------------------------------------------------------------------------------------------------*/

    public ImageView getLeftImageView() {
        return leftImageL;
    }

    public LinearLayout getBottomLayout() {
        return bottomLayout;
    }

    /*---------------------------------------------接口--------------------------------------------*/

    public interface IClick {
        void onClick(View view);
    }

    public interface IChangeClick {
        void onClick(CompoundButton buttonView, boolean isChecked);
    }
}
