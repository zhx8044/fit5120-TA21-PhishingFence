package com.team21.phishingfence.ui.adapters;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.team21.phishingfence.R;
import com.team21.phishingfence.models.HistoryInfo;
import com.team21.phishingfence.viewmodels.HistoryInfoViewmodel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class HistoryInfoAdapter extends RecyclerView.Adapter<HistoryInfoAdapter.MyHolder>
{
    private List<HistoryInfo> mHistoryInfo = new ArrayList<>();
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener
    {
        void onItemClick(HistoryInfo historyInfo);
        void delOnCLick(HistoryInfo historyInfo);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public void setListData(List<HistoryInfo> list)
    {
        this.mHistoryInfo = list;
        //一定要刷新
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //加载布局
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item, null);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        //绑定数据
        HistoryInfo historyInfo = mHistoryInfo.get(position);

        //holder.history_img.setImageResource(historyInfo.getNewsImage());
        Glide.with(holder.itemView.getContext())
                .load(historyInfo.getNewsImage()) // 假设getNewsImageUrl()是返回图片URL的方法
                //.placeholder(R.drawable.loading_spinner) // 加载中的默认图片
                //.error(R.drawable.image_not_found) // 错误时的默认图片
                .into(holder.history_img); // 将加载的图片设置到这个ImageView
        holder.history_title.setText(historyInfo.getTitle());
        holder.history_source.setText("");
        //holder.history_browseDate.setText(historyInfo.getViewedTime());
        // Assuming 'viewedTime' is a long type timestamp
        long timestamp = Long.parseLong(String.valueOf(historyInfo.getViewedTime()));
        Date date = new Date(timestamp);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault());
        String formattedDate = simpleDateFormat.format(date);

        holder.history_browseDate.setText(formattedDate);

        //点击事件
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if(null!=mOnItemClickListener)
                {
                    mOnItemClickListener.onItemClick(historyInfo);
                }
            }
        });

        //长按删除
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(mOnItemClickListener!=null)
                {
                    mOnItemClickListener.delOnCLick(historyInfo);
                }
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mHistoryInfo.size();
    }

    static class MyHolder extends RecyclerView.ViewHolder
    {
        ImageView history_img;
        TextView history_title;
        TextView history_source;
        TextView history_browseDate;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            //初始化控件
            history_img = itemView.findViewById(R.id.news_img);
            history_title = itemView.findViewById(R.id.news_title);
            history_source = itemView.findViewById(R.id.news_source);
            history_browseDate = itemView.findViewById(R.id.news_publishDate);
        }
    }
}
