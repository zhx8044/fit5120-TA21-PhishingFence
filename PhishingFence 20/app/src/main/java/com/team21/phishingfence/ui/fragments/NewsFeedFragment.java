package com.team21.phishingfence.ui.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.team21.phishingfence.R;
import com.team21.phishingfence.models.NewsInfo;
import com.team21.phishingfence.ui.activities.NewsDetailsActivity;
import com.team21.phishingfence.ui.adapters.NewsListAdapter;
import com.team21.phishingfence.viewmodels.NewsFeedViewModel;

import java.io.Serializable;
import java.util.List;

public class NewsFeedFragment extends Fragment {
    private ImageButton imageButton;
    private RecyclerView newsRecyclerView;
    private NewsListAdapter newsListAdapter;
    private NewsFeedViewModel newsFeedViewModel;
    private ImageView imageViewHistory;


    public NewsFeedFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_news_feed, container, false);

        //初始化控件
        this.imageButton = rootView.findViewById(R.id.imageButton);
        this.newsRecyclerView = rootView.findViewById(R.id.newsRecyclerView);
        this.newsListAdapter = new NewsListAdapter();
        this.newsFeedViewModel = new ViewModelProvider(this).get(NewsFeedViewModel.class);
        this.imageViewHistory = rootView.findViewById(R.id.imageViewHistory);

        this.newsRecyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
        this.newsRecyclerView.setAdapter(this.newsListAdapter);

        setButtonOnClickListener();
        loadNewsData();

        return rootView;
    }

    private void setButtonOnClickListener() {
        this.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController controller = Navigation.findNavController(v);
                controller.navigate(R.id.action_newsFeedFragment_to_scamScenairoFragment);
            }
        });

        setupRecyclerViewClickListeners();

        this.imageViewHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController controller = Navigation.findNavController(v);
                controller.navigate(R.id.action_newsFeedFragment_to_historyFragment);
            }
        });
    }

    private void loadNewsData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                // 在这个后台线程中加载数据
                final List<NewsInfo> newsData = NewsFeedFragment.this.newsFeedViewModel.getNewsData();

                // 切换到主线程更新UI
                if(isAdded()) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            NewsFeedFragment.this.newsListAdapter.setListData(newsData);
                            NewsFeedFragment.this.newsListAdapter.notifyDataSetChanged();
                        }
                    });
                }
            }
        }).start();
    }

    private void setupRecyclerViewClickListeners()
    {
        //recyclerView点击事件应该放在这里
        this.newsListAdapter.setOnItemClickListener(new NewsListAdapter.OnItemClickListener()
        {
            @Override
            public void onItemClick(NewsInfo newsInfo)
            {
                // 处理点击事件，启动新的新闻详情Activity
                Intent intent = new Intent(getActivity(), NewsDetailsActivity.class);
                //intent传地对象的时候，实体类实现Serializable
                intent.putExtra("newsInfo", newsInfo);
                startActivity(intent);
            }
        });
    }
}