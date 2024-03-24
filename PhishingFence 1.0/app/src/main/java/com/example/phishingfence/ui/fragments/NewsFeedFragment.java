package com.example.phishingfence.ui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.phishingfence.R;
import com.example.phishingfence.localdb.NewsService;
import com.example.phishingfence.ui.adapters.NewsListAdapter;

public class NewsFeedFragment extends Fragment
{
    private ImageView btnBack;
    private RecyclerView newsRecyclerView;
    private OnNewsFeedFragmentInteractionListener mOnNewsFeedFragmentInteractionListener;
    private NewsListAdapter mNewsListAdapter;

    public NewsFeedFragment()
    {
        // Required empty public constructor
    }

    public interface OnNewsFeedFragmentInteractionListener
    {
        void onBackClick();
    }

    public void setOnNewsFeedFragmentInteractionListener(OnNewsFeedFragmentInteractionListener onNewsFeedFragmentInteractionListener) {
        this.mOnNewsFeedFragmentInteractionListener = onNewsFeedFragmentInteractionListener;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View rootview =  inflater.inflate(R.layout.fragment_news_feed, container, false);

        //初始化控件
        this.btnBack = rootview.findViewById(R.id.img_back);
        this.newsRecyclerView = rootview.findViewById(R.id.newsRecyclerView);

        this.mNewsListAdapter = new NewsListAdapter();
        newsRecyclerView.setAdapter(mNewsListAdapter);
        //获取新闻列表对应数据
        mNewsListAdapter.setListData(NewsService.getNewsData());

        setupClickListeners();

        return rootview;
    }

    private void setupClickListeners()
    {
        setupBackButtonListener();
    }

    private void setupBackButtonListener()
    {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mOnNewsFeedFragmentInteractionListener!=null)
                {
                    mOnNewsFeedFragmentInteractionListener.onBackClick();
                }
            }
        });
    }
}