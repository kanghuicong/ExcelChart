package com.kang.excelchart.custom.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.kang.excelchart.R;

public class BaseDialog extends Dialog {

    private Context context;
    private TextView tvLeft;
    private TextView tvRight;
    private TextView tvTitle;
    private TextView tvContent;
    private View divider;
    private IBaseDialog iBaseDialog;
    private String title;
    private String content;
    private String leftText;
    private String rightText;
    private int rightColor;
    private int leftColor;
    private boolean isDrawable;

    public BaseDialog(@NonNull Context context, String title, String content, @NonNull String leftText, @NonNull String rightText, @NonNull int leftColor, @NonNull int rightColor, @NonNull boolean isDrawable, @NonNull IBaseDialog iBaseDialog) {
        super(context, R.style.transparentStyle);
        this.setCanceledOnTouchOutside(false);
        this.context = context;
        this.iBaseDialog = iBaseDialog;
        this.title = title;
        this.content = content;
        this.leftText = leftText;
        this.rightText = rightText;
        this.rightColor = rightColor;
        this.leftColor = leftColor;
        this.isDrawable = isDrawable;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.dialog_base);

        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvContent = (TextView) findViewById(R.id.tv_content);
        tvLeft = (TextView) findViewById(R.id.tv_left);
        tvRight = (TextView) findViewById(R.id.tv_right);
        divider = (View) findViewById(R.id.divider);
        if (title != null && !title.isEmpty()) {
            tvTitle.setVisibility(View.VISIBLE);
            tvTitle.setText(title);
        }
        if (content != null && !content.isEmpty()) {
            tvContent.setVisibility(View.VISIBLE);
            tvContent.setText(content);
        }
        if (leftText == null || leftText.equals("")) {
            tvLeft.setVisibility(View.GONE);
            divider.setVisibility(View.GONE);
        }

        if (rightText == null || rightText.equals("")) {
            tvRight.setVisibility(View.GONE);
            divider.setVisibility(View.GONE);
        }
        tvLeft.setText(leftText);
        tvRight.setText(rightText);

        if (isDrawable) {
            if (rightColor != 0)
                tvRight.setTextColor(ContextCompat.getColorStateList(context,rightColor));
            if (leftColor != 0)
                tvLeft.setTextColor(ContextCompat.getColorStateList(context,leftColor));
        } else {
            if (rightColor != 0)
                tvRight.setTextColor(ContextCompat.getColor(context,rightColor));
            if (leftColor != 0)
                tvLeft.setTextColor(ContextCompat.getColor(context,leftColor));

        }

        tvLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if (iBaseDialog != null) iBaseDialog.clickLeft(v);
            }
        });

        tvRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if (iBaseDialog != null) iBaseDialog.clickRight(v);
            }
        });
    }

    public BaseDialog setCanceledOutside(boolean cancel) {
        this.setCanceledOnTouchOutside(cancel);
        return this;
    }


    public interface IBaseDialog {
        void clickLeft(View v);

        void clickRight(View v);
    }

}
