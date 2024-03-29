package com.kang.excelchart.custom.dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kang.excelchart.BuildConfig;
import com.kang.excelchart.R;
import com.kang.excelchart.activity.login.LoginActivity;
import com.kang.excelchart.bean.Tables;
import com.kang.excelchart.bean.UpdateEvent;
import com.kang.excelchart.config.BaseConfig;
import com.kang.excelchart.config.UserConfig;
import com.vondear.rxtool.RxActivityTool;
import com.vondear.rxtool.RxLogTool;
import com.vondear.rxtool.view.RxToast;

import org.greenrobot.eventbus.EventBus;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;
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
    private Tables tables;

    public SettingDialog(Context context, Tables tables) {
        super(context);
        this.tables = tables;

        tvContent = (TextView) findViewById(R.id.tv_content);
        tvDelete = (TextView) findViewById(R.id.tv_delete);
        tvCopy = (TextView) findViewById(R.id.tv_copy);
        tvMove = (TextView) findViewById(R.id.tv_move);
        tvRename = (TextView) findViewById(R.id.tv_rename);
        tvCollect = (TextView) findViewById(R.id.tv_collect);
        tvSend = (TextView) findViewById(R.id.tv_send);
        tvCancel = (TextView) findViewById(R.id.tv_cancel);

        tvContent.setText(String.format(context.getString(R.string.operation_content), tables.getName()));

        //删除
        tvDelete.setOnClickListener((view) -> {
            dismiss();
            BaseDialog logoutDialog = new BaseDialog(context,
                    null,
                    context.getString(R.string.hint_delete_file),
                    context.getString(R.string.confirm),
                    context.getString(R.string.cancel),
                    0,
                    0,
                    false,
                    new BaseDialog.IBaseDialog() {
                        @Override
                        public void clickLeft(View v) {
                            Tables p2 = BaseConfig.getTableClass(context, null);
                            p2.setObjectId(tables.getObjectId());
                            p2.delete(new UpdateListener() {
                                @Override
                                public void done(BmobException e) {
                                    if (e == null) {
                                        RxToast.success(context.getString(R.string.success_delete));
                                        EventBus.getDefault().post(new UpdateEvent());
                                    } else {
                                        RxToast.success(context.getString(R.string.failure_delete)+"!"+e.getMessage());
                                    }
                                }
                            });
                        }

                        @Override
                        public void clickRight(View v) {

                        }
                    });
            logoutDialog.show();

        });

        //收藏
        tvCollect.setOnClickListener((view) -> {
            dismiss();
            Tables p2 = BaseConfig.getTableClass(context, null);
            p2.setColl(true);
            p2.update(tables.getObjectId(), new UpdateListener() {
                @Override
                public void done(BmobException e) {
                    if (e == null) {
                        RxToast.success(context.getString(R.string.collect_success));
                        EventBus.getDefault().post(new UpdateEvent());
                    } else {
                        RxLogTool.d("更新失败：" + e.getMessage());
                        RxToast.error(context.getString(R.string.collect_failure));
                    }
                }
            });
        });

        //重命名
        tvRename.setOnClickListener((view) -> {
            dismiss();
            VerifyDialog verifyDialog = new VerifyDialog(context,
                    context.getString(R.string.rename),
                    context.getString(R.string.designation),
                    null,
                    (str, str2) -> {
                        Tables p2 = BaseConfig.getTableClass(context, null);
                        p2.setName(str);
                        p2.update(tables.getObjectId(), new UpdateListener() {
                            @Override
                            public void done(BmobException e) {
                                if (e == null) {
                                    RxToast.success(context.getString(R.string.rename_success));
                                    EventBus.getDefault().post(new UpdateEvent());
                                } else {
                                    RxLogTool.d("更新失败：" + e.getMessage());
                                    RxToast.error(context.getString(R.string.rename_failure));
                                }
                            }
                        });
                    });
            verifyDialog.show();
        });

        //取消
        tvCancel.setOnClickListener((view -> {
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
