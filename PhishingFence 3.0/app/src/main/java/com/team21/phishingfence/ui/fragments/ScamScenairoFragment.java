package com.team21.phishingfence.ui.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.bumptech.glide.Glide;
import com.team21.phishingfence.R;
import com.team21.phishingfence.models.NewsInfo;
import com.team21.phishingfence.ui.activities.NewsDetailsActivity;
import com.team21.phishingfence.viewmodels.ScamScenairoViewModel;
import com.youth.banner.Banner;
import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.holder.BannerImageHolder;
import com.youth.banner.indicator.CircleIndicator;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

public class ScamScenairoFragment extends Fragment {
    private Button buttonNewsFeed,buttonViewStatistics;
    private Banner banner;
    private List<NewsInfo> mBannerDataInfos = new ArrayList<>();
    private ScamScenairoViewModel viewModel;

    public ScamScenairoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_scam_scenairo, container, false);

        this.buttonNewsFeed = rootView.findViewById(R.id.buttonNewsFeed);
        this.buttonViewStatistics = rootView.findViewById(R.id.buttonViewStatistics);
        this.banner = rootView.findViewById(R.id.banner);
        this.viewModel = new ViewModelProvider(requireActivity()).get(ScamScenairoViewModel.class);
        this.mBannerDataInfos = this.viewModel.getBannerDataInfos();

        setButtonOnClickListener();
        setBannerAdapter();
        setupBannerListener();

        return rootView;
    }

    private void setButtonOnClickListener() {
        this.buttonNewsFeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController controller = Navigation.findNavController(v);
                controller.navigate(R.id.action_scamScenairoFragment_to_newsFeedFragment);
            }
        });

        this.buttonViewStatistics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController controller = Navigation.findNavController(v);
                controller.navigate(R.id.action_scamScenairoFragment_to_statisticalTrendFragment);
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
                        Glide.with(holder.itemView.getContext())
                                .load(newsInfo.getNewsImage()) // 使用URL加载图片
                                //.placeholder(R.drawable.loading_spinner) // 加载中的占位图
                                //.error(R.drawable.image_not_found) // 加载失败的占位图
                                .into(holder.imageView); // 图片加载到哪个ImageView
                    }
                })
                .addBannerLifecycleObserver(this)//添加生命周期观察者
                .setIndicator(new CircleIndicator(requireActivity()));
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
                Intent intent = new Intent(getActivity(), NewsDetailsActivity.class);
                //intent传地对象的时候，实体类实现Serializable
                intent.putExtra("newsInfo",newsInfo);
                startActivity(intent);
            }
        });
    }
}