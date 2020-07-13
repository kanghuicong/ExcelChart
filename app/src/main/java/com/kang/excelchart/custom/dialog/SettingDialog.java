package com.kang.excelchart.custom.dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kang.excelchart.R;

import razerdp.basepopup.BasePopupWindow;

public class SettingDialog extends BasePopupWindow {

    private TextView tvContent;
    private TextView tvDelete;
    private TextView tvCopy;
    private TextView tvMove;
    private TextView tvRename;
    private TextView tvCollect;
    private TextView tvSend;
    private TextView tvCancel;

    public SettingDialog(Context context) {
        super(context);


        tvContent = (TextView) findViewById(R.id.tv_content);
        tvDelete = (TextView) findViewById(R.id.tv_delete);
        tvCopy = (TextView) findViewById(R.id.tv_copy);
        tvMove = (TextView) findViewById(R.id.tv_move);
        tvRename = (TextView) findViewById(R.id.tv_rename);
        tvCollect = (TextView) findViewById(R.id.tv_collect);
        tvSend = (TextView) findViewById(R.id.tv_send);
        tvCancel = (TextView) findViewById(R.id.tv_cancel);

        //取消
        tvCancel.setOnClickListener((view->{
            dismiss();
        }));

    }

    public void showDialog() {
        this.setPopupGravity(Gravity.BOTTOM)
                .showPopupWindow();
    }

    @Override
    public View onCreateContentView() {
        return createPopupById(R.layout.dialog_setting);
    }
}
