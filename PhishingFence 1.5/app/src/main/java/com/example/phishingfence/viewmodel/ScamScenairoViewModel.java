package com.example.phishingfence.viewmodel;

import androidx.lifecycle.ViewModel;

import com.example.phishingfence.R;
import com.example.phishingfence.model.NewsInfo;

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
        mBannerDataInfos.add(new NewsInfo(1011,
                "Australia’s Education Mirage: The story of student immigration’s dual toll",
                "Lavanya Gautam & Anurag Mittal",
                "February 23, 2024",
                "https://www.fabians.org.au/australias_education_mirage",
                R.drawable.news_img11));
        mBannerDataInfos.add(new NewsInfo(1012,
                "University students targeted in sinister recruitment scam",
                "Yashee Sharma",
                "February 20, 2024",
                "https://www.9news.com.au/national/university-students-targeted-in-recruitment-scam/0aefdbd3-eb85-40e6-bfdf-b5699b13d22b",
                R.drawable.news_img12));
        mBannerDataInfos.add(new NewsInfo(1013,
                "'Ghosted' by a fake rental agent these students have lost thousands to a property scam",
                "Yimin Qiang",
                "October 31, 2023",
                "https://www.sbs.com.au/language/chinese/en/article/fake-rental-properties-students-lose-thousands-of-dollars-to-fraudster/blzeap1w8",
                R.drawable.news_img13));
        mBannerDataInfos.add(new NewsInfo(1014,
                "Alarm raised over ‘virtual kidnapping’ scams targeting international students",
                "Jack Evans",
                "October 18, 2023",
                "https://www.news.com.au/national/crime/alarm-raised-over-virtual-kidnapping-scams-targeting-international-students/news-story/da63fdf52c34b58ccd54e010cbc6d2c4",
                R.drawable.news_img14));
    }

    public List<NewsInfo> getBannerDataInfos()
    {
        return mBannerDataInfos;
    }
}
