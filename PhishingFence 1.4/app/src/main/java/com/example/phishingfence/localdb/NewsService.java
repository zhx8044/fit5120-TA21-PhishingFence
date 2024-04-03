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
//        newsList.add(new NewsInfo(1001,
//                "UWPD warns students of increased scams targeting international students",
//                "Bryna Goeking",
//                "March 4, 2024 | 2:00am CST",
//                "https://www.dailycardinal.com/article/2024/03/uwpd-warns-students-of-increased-scams-targeting-international-students",
//                R.drawable.news_img1));
//        newsList.add(new NewsInfo(1002,
//                "Gaming the Student Visa System",
//                " Liam Knox",
//                "January 12, 2024",
//                "https://www.insidehighered.com/news/global/international-students-us/2024/01/12/international-admission-offices-plagued-fraud-and",
//                R.drawable.news_img2));
//        newsList.add(new NewsInfo(1003,
//                "‘Dodgy’ providers and agents ‘exploiting’ students: Top scam alerts for international students",
//                "NATHAN HEW",
//                "May 25, 2023",
//                "https://studyinternational.com/news/scam-alerts-international-students/",
//                R.drawable.news_img3));
//        newsList.add(new NewsInfo(1004,
//                "International students scammed – were sold tickets to fake parties",
//                "Idun Syvertsen",
//                "November 20, 2023 11:52pm",
//                "https://www.underdusken.no/english-international-students-scam/international-students-scammed-were-sold-tickets-to-fake-parties/318820",
//                R.drawable.news_img4));
//        newsList.add(new NewsInfo(1005,
//                "Scams targeting international students",
//                "",
//                "January 15, 2023",
//                "https://www.bournemouth.ac.uk/news/2024-01-15/scams-targeting-international-students",
//                R.drawable.news_img5));
//        newsList.add(new NewsInfo(1006,
//                "Admission Scams Push Indian International Students in Canada to the Brink of Deportation",
//                "Asia Pacific Foundation of Canada",
//                "September 7, 2023",
//                "https://www.asiapacific.ca/publication/admission-scams-push-indian-international-students-canada",
//                R.drawable.news_img6));
//        newsList.add(new NewsInfo(1007,
//                "Charges laid in scam that impacted 700 Indian international students",
//                "Edana Robitaille",
//                "July 22, 2023",
//                "https://www.cicnews.com/2023/07/charges-laid-in-scam-that-impacted-700-indian-international-students-0736272.html#gs.6azzzz",
//                R.drawable.news_img7));
//        newsList.add(new NewsInfo(1008,
//                "When an international student isn’t an international student",
//                "Niamh Ollerton",
//                "September 21, 2023",
//                "https://qs-gen.com/when-an-international-student-isnt-an-international-student/",
//                R.drawable.news_img8));
//        newsList.add(new NewsInfo(1009,
//                "Federal government trying to protect international students from admission scams",
//                "",
//                "October 30, 2023",
//                "https://globalnews.ca/video/10059433/federal-government-trying-to-protect-international-students-from-admission-scams",
//                R.drawable.news_img10));
//        newsList.add(new NewsInfo(1010,
//                "International students warned about scams",
//                "Niamh Ollerton",
//                "September 28, 2023",
//                "https://studytravel.network/magazine/news/0/30126/",
//                R.drawable.news_img9));


        // 添加从文件中获得的新闻条目
        newsList.add(new NewsInfo(1011,
                "Australia’s Education Mirage: The story of student immigration’s dual toll",
                "Lavanya Gautam & Anurag Mittal",
                "February 23, 2024",
                "https://www.fabians.org.au/australias_education_mirage",
                R.drawable.news_img11));
        newsList.add(new NewsInfo(1012,
                "University students targeted in sinister recruitment scam",
                "Yashee Sharma",
                "February 20, 2024",
                "https://www.9news.com.au/national/university-students-targeted-in-recruitment-scam/0aefdbd3-eb85-40e6-bfdf-b5699b13d22b",
                R.drawable.news_img12));
        newsList.add(new NewsInfo(1013,
                "'Ghosted' by a fake rental agent these students have lost thousands to a property scam",
                "Yimin Qiang",
                "October 31, 2023",
                "https://www.sbs.com.au/language/chinese/en/article/fake-rental-properties-students-lose-thousands-of-dollars-to-fraudster/blzeap1w8",
                R.drawable.news_img13));
        newsList.add(new NewsInfo(1014,
                "Alarm raised over ‘virtual kidnapping’ scams targeting international students",
                "Jack Evans",
                "October 18, 2023",
                "https://www.news.com.au/national/crime/alarm-raised-over-virtual-kidnapping-scams-targeting-international-students/news-story/da63fdf52c34b58ccd54e010cbc6d2c4",
                R.drawable.news_img14));
        newsList.add(new NewsInfo(1015,
                "Family loses $288k to kidnapping scam targeting international students in NSW",
                "Mikala Theocharous",
                "October 18, 2023",
                "https://www.9news.com.au/national/kidnapping-scam-family-loses-288k-to-kidnapping-scam-targetting-international-students-in-nsw/a6c035ee-7d09-4f50-bbc1-c9010754997b",
                R.drawable.news_img15));
        newsList.add(new NewsInfo(1016,
                "International students are bombarded by scams asking for money and personal information. Authorities say the scam uses increasingly sophisticated techniques to impersonate authorities",
                "Gareth Boreham",
                "October 1, 2023",
                "https://www.sbs.com.au/language/indonesian/en/podcast-episode/peringatan-atas-taktik-penipuan-yang-canggih-terhadap-pelajar-dari-tiongkok-di-australia/l5zrv2eks",
                R.drawable.news_img16));
        newsList.add(new NewsInfo(1017,
                "Sophisticated scams targeting Chinese international students ramping up ACCC warns",
                "Gareth Boreham & Ruth McHugh-Dillon",
                "September 22, 2023",
                "https://www.sbs.com.au/news/article/sophisticated-scams-targeting-chinese-international-students-ramping-up-accc-warns/odx22265d",
                R.drawable.news_img17));
        newsList.add(new NewsInfo(1018,
                "Scammers are increasingly targeting Chinese international students with the scams using increasingly sophisticated techniques to imitate authorities",
                "Gareth Boreham",
                "September 21, 2023",
                "https://www.sbs.com.au/news/podcast-episode/warning-over-elaborate-ploy-to-scam-international-students-in-australia/w8he767t9",
                R.drawable.news_img18));
        newsList.add(new NewsInfo(1019,
                "Scammers circle international students amid university accommodation crisis",
                "Andrea Mayes",
                "February 6, 2023",
                "https://www.abc.net.au/news/2023-02-04/university-accommodation-crisis-as-international-students-return/101912694",
                R.drawable.news_img19));
        newsList.add(new NewsInfo(1020,
                "Chinese students lose millions to complex scam",
                "Millie Costigan",
                "September 26, 2023",
                "https://www.centralwesterndaily.com.au/story/8360308/scammers-posing-as-chinese-police-are-extorting-international-students-in-australia/",
                R.drawable.news_img20));



        return newsList;
    }
}