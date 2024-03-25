package com.example.phishingfence.ui.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.phishingfence.R;
import com.example.phishingfence.model.NewsInfo;
import com.example.phishingfence.ui.activities.NewsDetailesActivity;
import com.example.phishingfence.viewmodel.ScamScenairoViewModel;
import com.youth.banner.Banner;
import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.holder.BannerImageHolder;
import com.youth.banner.indicator.CircleIndicator;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;


public class ScamScenairoFragment extends Fragment
{
    private Button btnNewsFeed;
    private Button btnViewStatistics;
    private OnScamScenairoFragmentInteractionListener mOnScamScenairoFragmentInteractionListener;
    private Banner banner;
    private List<NewsInfo> mBannerDataInfos = new ArrayList<>();
    private ScamScenairoViewModel viewModel;

    public ScamScenairoFragment()
    {
        // Required empty public constructor
    }

    public interface OnScamScenairoFragmentInteractionListener
    {
        void onNewsFeedClick();
        void onViewStatisticsClick();
    }

    public void setOnScamScenairoFragmentInteractionListener(OnScamScenairoFragmentInteractionListener onScamScenairoFragmentInteractionListener) {
        this.mOnScamScenairoFragmentInteractionListener = onScamScenairoFragmentInteractionListener;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        // 这里可以保留对Fragment的全局初始化（如果有的话）
        viewModel = new ViewModelProvider(this).get(ScamScenairoViewModel.class);
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
        this.banner = rootview.findViewById(R.id.banner);

        mBannerDataInfos = viewModel.getBannerDataInfos();
        setBannerAdapter();
        setupClickListeners();

        return rootview;
    }

    private void setupClickListeners()
    {
        setupNewsFeedButtonListener();
        setupViewStatisticsListener();
        setupBannerListener();
    }

    private void setupNewsFeedButtonListener()
    {
        btnNewsFeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mOnScamScenairoFragmentInteractionListener!=null)
                {
                    mOnScamScenairoFragmentInteractionListener.onNewsFeedClick();
                }
            }
        });
    }

    private void setupViewStatisticsListener()
    {
        btnViewStatistics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mOnScamScenairoFragmentInteractionListener!=null)
                {
                    mOnScamScenairoFragmentInteractionListener.onViewStatisticsClick();
                }
            }
        });
    }

    private void setupBannerListener()
    {
        //banner点击事件
        banner.setOnBannerListener(new OnBannerListener<NewsInfo>()
        {

            @Override
            public void OnBannerClick(NewsInfo newsInfo, int position)
            {
                // 处理点击事件，启动新的新闻详情Activity
                Intent intent = new Intent(getActivity(), NewsDetailesActivity.class);
                //intent传地对象的时候，实体类实现Serializable
                intent.putExtra("newsInfo",newsInfo);
                startActivity(intent);
            }
        });
    }

    private void setBannerAdapter()
    {
        //设置adapter
        banner.setAdapter(new BannerImageAdapter<NewsInfo>(mBannerDataInfos) {
                    @Override
                    public void onBindView(BannerImageHolder holder, NewsInfo newsInfo, int position, int size) {
                        //图片加载自己实现
                        holder.imageView.setImageResource(newsInfo.getNewsImage());
                    }
                })
                .addBannerLifecycleObserver(this)//添加生命周期观察者
                .setIndicator(new CircleIndicator(requireActivity()));
    }
}