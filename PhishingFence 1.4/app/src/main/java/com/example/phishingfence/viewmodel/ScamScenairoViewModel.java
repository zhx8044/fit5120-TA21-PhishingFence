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
        //模拟数据
        mBannerDataInfos.add(new NewsInfo(1001,
                "UWPD warns students of increased scams targeting international students",
                "Bryna Goeking",
                "March 4, 2024 | 2:00am CST",
                "https://www.dailycardinal.com/article/2024/03/uwpd-warns-students-of-increased-scams-targeting-international-students",
                R.drawable.news_img1));
        mBannerDataInfos.add(new NewsInfo(1002,
                "Gaming the Student Visa System",
                " Liam Knox",
                "January 12, 2024",
                "https://www.insidehighered.com/news/global/international-students-us/2024/01/12/international-admission-offices-plagued-fraud-and",
                R.drawable.news_img2));
        mBannerDataInfos.add(new NewsInfo(1003,
                "‘Dodgy’ providers and agents ‘exploiting’ students: Top scam alerts for international students",
                "NATHAN HEW",
                "May 25, 2023",
                "https://studyinternational.com/news/scam-alerts-international-students/",
                R.drawable.news_img3));
        mBannerDataInfos.add(new NewsInfo(1004,
                "International students scammed – were sold tickets to fake parties",
                "Idun Syvertsen",
                "November 20, 2023 11:52pm",
                "https://www.underdusken.no/english-international-students-scam/international-students-scammed-were-sold-tickets-to-fake-parties/318820",
                R.drawable.news_img4));
    }

    public List<NewsInfo> getBannerDataInfos()
    {
        return mBannerDataInfos;
    }
}
