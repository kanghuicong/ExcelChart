package com.kang.excelchart.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kang.excelchart.R;
import com.kang.excelchart.bean.Tables;
import com.kang.excelchart.custom.dialog.SettingDialog;
import com.vondear.rxtool.RxConstants;
import com.vondear.rxtool.RxTimeTool;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

public class ChartAdapter extends RecyclerView.Adapter {

    public List<Tables> list = new ArrayList<>();
    public Context context;
    public int from;

    public ChartAdapter(Context context,int from,List<Tables> list) {
        this.context = context;
        this.list = list;
        this.from = from;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chart, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder vh = (ViewHolder) holder;
        Tables table = list.get(position);
        vh.tvTitle.setText(table.getName());

        String time ;
        switch (from) {
            case 0:
            case 2:
                time = table.getCreatedAt()+" "+context.getString(R.string.create);
                break;
            case 1:
                time = table.getUpdatedAt()+" "+context.getString(R.string.update);
                break;
            default:
                time = table.getUpdatedAt();
                break;
        }
        vh.tvTime.setText(time);

        vh.ivWarn.setVisibility(View.VISIBLE);

        switch (table.getType()) {
            case 0:
                vh.ivIcon.setBackground(ContextCompat.getDrawable(context, R.mipmap.table_type_0));
                break;
            case 1:
                vh.ivIcon.setBackground(ContextCompat.getDrawable(context, R.mipmap.table_type_1));
                break;
            case 2:
                vh.ivIcon.setBackground(ContextCompat.getDrawable(context, R.mipmap.table_type_2));
                break;
            case 3:
                vh.ivIcon.setBackground(ContextCompat.getDrawable(context, R.mipmap.table_type_3));
                break;
            case 4:
                vh.ivIcon.setBackground(ContextCompat.getDrawable(context, R.mipmap.table_type_4));
                break;
            default:
                vh.ivIcon.setBackground(ContextCompat.getDrawable(context, R.mipmap.ic_launcher));
                break;
        }

        vh.ivSetting.setOnClickListener((view->{
            SettingDialog dialog = new SettingDialog(context);
        }));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivIcon;
        private TextView tvTitle;
        private TextView tvTime;
        private ImageView ivWarn;
        private ImageView ivSetting;

        public ViewHolder(@NonNull View rootView) {
            super(rootView);

            ivIcon = (ImageView) rootView.findViewById(R.id.iv_icon);
            tvTitle = (TextView) rootView.findViewById(R.id.tv_title);
            tvTime = (TextView) rootView.findViewById(R.id.tv_time);
            ivWarn = (ImageView) rootView.findViewById(R.id.iv_warn);
            ivSetting = (ImageView) rootView.findViewById(R.id.iv_setting);
        }
    }
}
