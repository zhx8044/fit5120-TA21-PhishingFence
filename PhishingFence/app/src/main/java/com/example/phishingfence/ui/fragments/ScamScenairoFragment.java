package com.example.phishingfence.ui.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.phishingfence.R;
import com.example.phishingfence.ui.activities.NewsFeedActivity;
import com.example.phishingfence.ui.activities.StatisticalTrendActivity;


public class ScamScenairoFragment extends Fragment
{
    private Button btnNewsFeed;
    private Button btnViewStatistics;

    public ScamScenairoFragment()
    {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        // 这里可以保留对Fragment的全局初始化（如果有的话）
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_scam_scenairo, container, false);

        //初始化控件
        this.btnNewsFeed = rootview.findViewById(R.id.btn_news_feed);
        this.btnViewStatistics = rootview.findViewById(R.id.btn_view_statistics);

        setupClickListeners();

        return rootview;
    }

    private void setupClickListeners()
    {
        setupNewsFeedButtonListener();
        setupViewStatisticsListener();
    }

    private void setupNewsFeedButtonListener()
    {
        btnNewsFeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NewsFeedActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setupViewStatisticsListener()
    {
        btnViewStatistics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), StatisticalTrendActivity.class);
                startActivity(intent);
            }
        });
    }
}