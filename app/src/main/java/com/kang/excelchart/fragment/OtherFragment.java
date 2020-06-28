package com.kang.excelchart.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.kang.excelchart.R;
import com.kang.excelchart.base.BaseFragment;
import com.kang.excelchart.bean.InputTextBean;
import com.kang.excelchart.custom.FindReplaceDialog;
import com.kang.excelchart.custom.SuperItemView;
import com.vondear.rxtool.RxDataTool;
import com.vondear.rxtool.RxTimeTool;
import com.vondear.rxtool.view.RxToast;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * 类描述：
 */
public class OtherFragment extends BaseFragment implements View.OnClickListener {
    private SuperItemView bulkInsert;
    private SuperItemView findAndReplace;
    private SuperItemView unifiedWideHigh;
    private SuperItemView superSet;
    private SuperItemView insertDate;
    private SuperItemView insertTime;
    private SuperItemView howRealizeCalculation;
    private SuperItemView howCreateTable;
    private SuperItemView howCellSize;

    @Override
    protected int initLayout(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_other;
    }

    @Override
    protected void initView(View view) {
        bulkInsert = (SuperItemView) view.findViewById(R.id.bulk_insert);
        findAndReplace = (SuperItemView) view.findViewById(R.id.find_and_replace);
        unifiedWideHigh = (SuperItemView) view.findViewById(R.id.unified_wide_high);
        superSet = (SuperItemView) view.findViewById(R.id.super_set);
        insertDate = (SuperItemView) view.findViewById(R.id.insert_date);
        insertTime = (SuperItemView) view.findViewById(R.id.insert_time);
        howRealizeCalculation = (SuperItemView) view.findViewById(R.id.how_realize_calculation);
        howCreateTable = (SuperItemView) view.findViewById(R.id.how_create_table);
        howCellSize = (SuperItemView) view.findViewById(R.id.how_cell_size);

        bulkInsert.setOnClickListener(this);
        findAndReplace.setOnClickListener(this);
        unifiedWideHigh.setOnClickListener(this);
        superSet.setOnClickListener(this);
        insertDate.setOnClickListener(this);
        insertTime.setOnClickListener(this);
        howRealizeCalculation.setOnClickListener(this);
        howCreateTable.setOnClickListener(this);
        howCellSize.setOnClickListener(this);
    }

    @Override
    protected void init(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        unifiedWideHigh.setRightText(getString(R.string.unified_wide_high_hint))
                .setRightTextColor(R.color.black66, false);
        unifiedWideHigh.setOnClickRightText(null);

        insertDate.setRightText(getString(R.string.insert_date_hint))
                .setRightTextColor(R.color.black66, false);

        insertTime.setRightText(getString(R.string.insert_time_hint))
                .setRightTextColor(R.color.black66, false);
    }

    @Override
    protected void getInputTextBean(InputTextBean event) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bulk_insert:

                break;
            case R.id.find_and_replace:
                FindReplaceDialog findReplaceDialog = new FindReplaceDialog(activity, new FindReplaceDialog.IReplace() {
                    @Override
                    public void replace(String content, String replaceStr) {
                        chartView.setReplace(content, replaceStr);
                    }
                });
                findReplaceDialog.show();
                break;
            case R.id.unified_wide_high:
                if (!chartView.isUnifiedWidthHeight) {
                    chartView.unifiedWidthHeight();
                    unifiedWideHigh.setRightText(getString(R.string.undo))
                            .setRightTextColor(R.color.redE24C54, false);
                    unifiedWideHigh.setOnClickRightText(new SuperItemView.IClick() {
                        @Override
                        public void onClick(View view) {
                            chartView.undoWidthHeight();
                            unifiedWideHigh.setRightText(getString(R.string.unified_wide_high_hint))
                                    .setRightTextColor(R.color.black66, false);
                            unifiedWideHigh.setOnClickRightText(null);
                        }
                    });
                } else {
                    RxToast.normal(getString(R.string.unified));
                }
                break;
            case R.id.super_set:

                break;
            case R.id.insert_date:
                String data = RxTimeTool.getCurTimeString(new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()));
                chartView.setTextContent(data);
                etContent.setText(data);
                break;
            case R.id.insert_time:
                String time = RxTimeTool.getCurTimeString(new SimpleDateFormat("HH:mm:ss", Locale.getDefault()));
                chartView.setTextContent(time);
                etContent.setText(time);
                break;
            case R.id.how_realize_calculation:

                break;
            case R.id.how_create_table:

                break;
            case R.id.how_cell_size:

                break;
            default:
                break;
        }
    }
}
