package com.example.phishingfence.ui.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.phishingfence.R;
import com.example.phishingfence.localdb.NewsService;
import com.example.phishingfence.model.NewsInfo;
import com.example.phishingfence.ui.activities.NewsDetailesActivity;
import com.example.phishingfence.ui.adapters.NewsListAdapter;
import com.example.phishingfence.viewmodel.NewsFeedViewModel;

import java.util.List;

public class NewsFeedFragment extends Fragment
{
    private ImageView btnBack;
    private RecyclerView newsRecyclerView;
    private NewsListAdapter mNewsListAdapter;
    private OnNewsFeedFragmentInteractionListener mOnNewsFeedFragmentInteractionListener;
    private NewsFeedViewModel newsFeedViewModel;

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
        newsFeedViewModel = new ViewModelProvider(this).get(NewsFeedViewModel.class);
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
        mNewsListAdapter.setListData(newsFeedViewModel.getNewsData());

        setupClickListeners();

        return rootview;
    }

    private void setupClickListeners()
    {
        setupBackButtonListener();
        setupRecyclerViewClickListeners();
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

    private void setupRecyclerViewClickListeners()
    {
        //recyclerView点击事件应该放在这里
        mNewsListAdapter.setOnItemClickListener(new NewsListAdapter.OnItemClickListener()
        {
            @Override
            public void onItemClick(NewsInfo newsInfo)
            {
                // 处理点击事件，启动新的新闻详情Activity
                Intent intent = new Intent(getActivity(), NewsDetailesActivity.class);
                //intent传地对象的时候，实体类实现Serializable
                intent.putExtra("newsInfo",newsInfo);
                startActivity(intent);
            }
        });
    }
}