package com.kang.excelchart.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.kang.excelchart.R;
import com.kang.excelchart.bean.TextColorBean;
import com.kang.excelchart.utils.TextPaintUtils;

import java.util.List;

/**
 * 类描述：
 */
public class ColorAdapter extends RecyclerView.Adapter {
    private List<TextColorBean> colorList;
    private Context context;
    private ISelectColor iSelectColor;

    public ColorAdapter(Context context, List<TextColorBean> colorList,ISelectColor iSelectColor) {
        this.context = context;
        this.colorList = colorList;
        this.iSelectColor = iSelectColor;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_txt_color, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ViewHolder vh = (ViewHolder) holder;
        final TextColorBean textColorBean = colorList.get(position);
        vh.tvColor.setBackgroundColor(TextPaintUtils.hexToColor(textColorBean.getColorStr()));
        if (textColorBean.isClick) {
            vh.itemLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.select_color));
        } else {
            vh.itemLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.white));
        }

        vh.itemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < colorList.size(); i++) {
                    if (i == position) {
                        colorList.get(i).setClick(true);
                    } else colorList.get(i).setClick(false);
                }
                iSelectColor.select(textColorBean);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return colorList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout itemLayout;
        private TextView tvColor;

        public ViewHolder(@NonNull View rootView) {
            super(rootView);

            itemLayout = (LinearLayout) rootView.findViewById(R.id.ll_item);
            tvColor = (TextView) rootView.findViewById(R.id.tv_color);
        }
    }

    public interface ISelectColor{
        void select(TextColorBean textColorBean);
    }
}
