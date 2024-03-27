package com.example.phishingfence.localdb;

import com.example.phishingfence.R;
import com.example.phishingfence.model.NewsInfo;
import java.util.ArrayList;
import java.util.List;

public class NewsService {

    //提供新闻数据
    public static List<NewsInfo> getNewsData() {
        List<NewsInfo> newsList = new ArrayList<>();

        // 添加一些硬编码的新闻数据
        newsList.add(new NewsInfo(1001,
                "UWPD warns students of increased scams targeting international students",
                "Bryna Goeking",
                "March 4, 2024 | 2:00am CST",
                "https://www.dailycardinal.com/article/2024/03/uwpd-warns-students-of-increased-scams-targeting-international-students",
                R.drawable.news_img1));
        newsList.add(new NewsInfo(1002,
                "Gaming the Student Visa System",
                " Liam Knox",
                "January 12, 2024",
                "https://www.insidehighered.com/news/global/international-students-us/2024/01/12/international-admission-offices-plagued-fraud-and",
                R.drawable.news_img2));
        newsList.add(new NewsInfo(1003,
                "‘Dodgy’ providers and agents ‘exploiting’ students: Top scam alerts for international students",
                "NATHAN HEW",
                "May 25, 2023",
                "https://studyinternational.com/news/scam-alerts-international-students/",
                R.drawable.news_img3));
        newsList.add(new NewsInfo(1004,
                "International students scammed – were sold tickets to fake parties",
                "Idun Syvertsen",
                "November 20, 2023 11:52pm",
                "https://www.underdusken.no/english-international-students-scam/international-students-scammed-were-sold-tickets-to-fake-parties/318820",
                R.drawable.news_img4));
        newsList.add(new NewsInfo(1005,
                "Scams targeting international students",
                "",
                "January 15, 2023",
                "https://www.bournemouth.ac.uk/news/2024-01-15/scams-targeting-international-students",
                R.drawable.news_img5));
        newsList.add(new NewsInfo(1006,
                "Admission Scams Push Indian International Students in Canada to the Brink of Deportation",
                "Asia Pacific Foundation of Canada",
                "September 7, 2023",
                "https://www.asiapacific.ca/publication/admission-scams-push-indian-international-students-canada",
                R.drawable.news_img6));
        newsList.add(new NewsInfo(1007,
                "Charges laid in scam that impacted 700 Indian international students",
                "Edana Robitaille",
                "July 22, 2023",
                "https://www.cicnews.com/2023/07/charges-laid-in-scam-that-impacted-700-indian-international-students-0736272.html#gs.6azzzz",
                R.drawable.news_img7));
        newsList.add(new NewsInfo(1008,
                "When an international student isn’t an international student",
                "Niamh Ollerton",
                "September 21, 2023",
                "https://qs-gen.com/when-an-international-student-isnt-an-international-student/",
                R.drawable.news_img8));
        newsList.add(new NewsInfo(1009,
                "Federal government trying to protect international students from admission scams",
                "",
                "October 30, 2023",
                "https://globalnews.ca/video/10059433/federal-government-trying-to-protect-international-students-from-admission-scams",
                R.drawable.news_img10));
        newsList.add(new NewsInfo(1010,
                "International students warned about scams",
                "Niamh Ollerton",
                "September 28, 2023",
                "https://studytravel.network/magazine/news/0/30126/",
                R.drawable.news_img9));

        return newsList;
    }
}