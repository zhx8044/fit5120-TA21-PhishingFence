package com.example.phishingfence.viewmodel;

import androidx.lifecycle.ViewModel;

import com.example.phishingfence.localdb.NewsService;
import com.example.phishingfence.model.NewsInfo;

import java.util.List;

public class NewsFeedViewModel extends ViewModel {

    // 这个方法直接返回数据，没有使用LiveData
    public List<NewsInfo> getNewsData() {
        // 这里模拟从NewsService获取数据
        // 注意: 这是同步操作，实际上可能需要处理线程
        return NewsService.getNewsData();
    }
}