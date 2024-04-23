package com.team21.phishingfence.viewmodels;

import androidx.lifecycle.ViewModel;

import com.team21.phishingfence.models.NewsInfo;
import com.team21.phishingfence.repositories.NewsRepository;

import java.util.ArrayList;
import java.util.List;

public class ScamScenairoViewModel extends ViewModel
{
    private List<NewsInfo> mBannerDataInfos = new ArrayList<>();;

    public ScamScenairoViewModel()
    {
        loadBannerDataInfos();
    }

    private void loadBannerDataInfos()
    {
        List<NewsInfo> list = NewsRepository.getNewsData();
        this.mBannerDataInfos.add(list.get(0));
        this.mBannerDataInfos.add(list.get(1));
        this.mBannerDataInfos.add(list.get(2));
        this.mBannerDataInfos.add(list.get(3));
    }

    public List<NewsInfo> getBannerDataInfos()
    {
        return mBannerDataInfos;
    }
}
