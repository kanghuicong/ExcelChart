package com.kang.excelchart.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kang.excelchart.R;
import com.kang.excelchart.activity.XWebActivity;
import com.kang.excelchart.bean.ExampleVideoPlayers;
import com.kang.excelchart.custom.view.SuperItemView;

import java.util.ArrayList;
import java.util.List;

/**
 * 类描述：
 * author:kanghuicong
 */
public class VideoAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<ExampleVideoPlayers> list;


    public VideoAdapter(Context context, List<ExampleVideoPlayers> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_video, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder vh = (ViewHolder) holder;
        vh.SuperItemView.setLeftText(list.get(position).getName());
        vh.SuperItemView.setOnClickListener((view -> {
            XWebActivity.doIntent(context,list.get(position).getName(),list.get(position).getVideoUrl());
        }));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        SuperItemView SuperItemView;

        public ViewHolder(@NonNull View rootView) {
            super(rootView);

            SuperItemView = (SuperItemView) rootView.findViewById(R.id.super_item);
        }
    }
}
