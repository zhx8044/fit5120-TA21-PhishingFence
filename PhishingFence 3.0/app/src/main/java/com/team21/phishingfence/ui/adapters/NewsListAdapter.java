package com.team21.phishingfence.ui.adapters;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.team21.phishingfence.R;
import com.team21.phishingfence.models.NewsInfo;

import java.util.ArrayList;
import java.util.List;

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.MyHolder>
{
    private List<NewsInfo> mNewsInfo = new ArrayList<>();
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener
    {
        void onItemClick(NewsInfo newsInfo);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener)
    {
        this.mOnItemClickListener = onItemClickListener;
    }

    public void setListData(List<NewsInfo> list)
    {
        this.mNewsInfo = list;
        //一定要刷新
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        //加载布局
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item, null);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        //绑定数据
        NewsInfo newsInfo = mNewsInfo.get(position);

        //holder.news_img.setImageResource(newsInfo.getNewsImage());
        // 使用Glide加载图片
        Glide.with(holder.itemView.getContext())
                .load(newsInfo.getNewsImage()) // 假设getNewsImageUrl()是返回图片URL的方法
                //.placeholder(R.drawable.loading_spinner) // 加载中的默认图片
                //.error(R.drawable.image_not_found) // 错误时的默认图片
                .into(holder.news_img); // 将加载的图片设置到这个ImageView
        holder.news_title.setText(newsInfo.getTitle());
        holder.news_source.setText(newsInfo.getSource());
        holder.news_publishDate.setText(newsInfo.getPublishDate());

        //点击事件
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if(null!=mOnItemClickListener)
                {
                    mOnItemClickListener.onItemClick(newsInfo);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mNewsInfo.size();
    }

    static class MyHolder extends RecyclerView.ViewHolder
    {
        ImageView news_img;
        TextView news_title;
        TextView news_source;
        TextView news_publishDate;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            //初始化控件
            news_img = itemView.findViewById(R.id.news_img);
            news_title = itemView.findViewById(R.id.news_title);
            news_source = itemView.findViewById(R.id.news_source);
            news_publishDate = itemView.findViewById(R.id.news_publishDate);
        }
    }
}