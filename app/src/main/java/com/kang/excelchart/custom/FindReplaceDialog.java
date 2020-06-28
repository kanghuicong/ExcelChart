package com.kang.excelchart.custom;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.kang.excelchart.R;

/**
 * 类描述：
 */
public class FindReplaceDialog extends Dialog {

    private EditText etContent;
    private EditText etReplace;
    private TextView tvCancel;
    private TextView tvConfirm;
    private IReplace iReplace;

    public FindReplaceDialog(@NonNull Context context,IReplace iReplace) {
        super(context, R.style.BaseDialogStyle);
        this.iReplace = iReplace;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.dialog_find_replace);

        etContent = (EditText) findViewById(R.id.et_content);
        etReplace = (EditText) findViewById(R.id.et_replace);
        tvCancel = (TextView) findViewById(R.id.tv_cancel);
        tvConfirm = (TextView) findViewById(R.id.tv_confirm);
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if (iReplace != null) {
                    iReplace.replace(etContent.getText().toString().trim(), etReplace.getText().toString().trim());
                }
            }
        });
    }

    public interface IReplace {
        void replace(String content, String replaceStr);
    }
}
