package com.kang.excelchart.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kang.excelchart.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ChartAdapter extends RecyclerView.Adapter {

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chart, parent, false);
        return new ColorAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder vh = (ViewHolder) holder;
    }

    @Override
    public int getItemCount() {
        return 0;
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
            ivWarn = (ImageView) rootView.findViewById(R.id.iv_warn);
            ivSetting = (ImageView) rootView.findViewById(R.id.iv_setting);
        }
    }
}
