package com.team21.phishingfence.viewmodels;

import androidx.lifecycle.ViewModel;

import com.team21.phishingfence.repositories.NewsRepository;
import com.team21.phishingfence.models.NewsInfo;

import java.util.List;

public class NewsFeedViewModel extends ViewModel {

    public List<NewsInfo> getNewsData() {
        // 这里模拟从NewsService获取数据
        // 注意: 这是同步操作，实际上可能需要处理线程
        return NewsRepository.getNewsData();
    }
}