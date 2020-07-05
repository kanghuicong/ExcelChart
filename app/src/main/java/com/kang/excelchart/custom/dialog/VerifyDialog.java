package com.kang.excelchart.custom.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kang.excelchart.R;

import androidx.annotation.NonNull;

public class VerifyDialog extends Dialog {

    private TextView tvTitle;
    private TextView tvHint1;
    private EditText et1;
    private TextView tvHint2;
    private EditText et2;
    private TextView tvCancel;
    private TextView tvConfirm;
    private LinearLayout ll2;
    private View line2;
    private ImageButton iv1;
    private ImageButton iv2;

    private Context context;
    private String title;
    private String hint1;
    private String hint2;
    private IConfirm iConfirm;

    public VerifyDialog(@NonNull Context context, String title, String hint1, String hint2, IConfirm iConfirm) {
        super(context, R.style.BaseDialogStyle);
        this.context = context;
        this.title = title;
        this.hint1 = hint1;
        this.hint2 = hint2;
        this.iConfirm = iConfirm;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.dialog_verify);

        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvHint1 = (TextView) findViewById(R.id.hint_1);
        et1 = (EditText) findViewById(R.id.et_1);
        tvHint2 = (TextView) findViewById(R.id.hint_2);
        ll2 = (LinearLayout) findViewById(R.id.ll_2);
        et2 = (EditText) findViewById(R.id.et_2);
        line2 = (View) findViewById(R.id.line2);
        tvCancel = (TextView) findViewById(R.id.tv_cancel);
        tvConfirm = (TextView) findViewById(R.id.tv_confirm);
        iv1 = (ImageButton) findViewById(R.id.iv_1);
        iv2 = (ImageButton) findViewById(R.id.iv_2);


        if (hint2 == null) {
            tvHint2.setVisibility(View.GONE);
            ll2.setVisibility(View.GONE);
            line2.setVisibility(View.GONE);
        }

        if (title.equals(context.getString(R.string.change_password))) {
            et1.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            et2.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        }

        tvCancel.setOnClickListener((view -> {
            dismiss();
        }));

        tvConfirm.setOnClickListener((view -> {
            if (iConfirm != null) {
                iConfirm.confirm(et1.getText().toString(), et2.getText().toString());
            }
            dismiss();
        }));

        iv1.setOnClickListener((view -> {
            et1.setText("");
        }));

        iv2.setOnClickListener((view -> {
            et2.setText("");
        }));

        et1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() == 0) {
                    iv1.setVisibility(View.GONE);
                } else {
                    iv1.setVisibility(View.VISIBLE);
                }
            }
        });

        et2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() == 0) {
                    iv2.setVisibility(View.GONE);
                } else {
                    iv2.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    public interface IConfirm {
        void confirm(String str1, String str2);
    }
}
