package com.team21.phishingfence.repositories;

import com.team21.phishingfence.models.NewsInfo;

import java.util.ArrayList;
import java.util.List;

public class NewsRepository {

    //提供新闻数据
    public static List<NewsInfo> getNewsData() {
        List<NewsInfo> newsList = new ArrayList<>();

        // 添加从文件中获得的新闻条目
        newsList.add(new NewsInfo(1011,
                "Australia’s Education Mirage: The story of student immigration’s dual toll",
                "Lavanya Gautam & Anurag Mittal",
                "February 23, 2024",
                "https://www.fabians.org.au/australias_education_mirage",
                "https://assets.nationbuilder.com/australianfabians/pages/1591/attachments/original/1708665514/pexels-pixabay-247899.jpg?1708665514"));
        newsList.add(new NewsInfo(1012,
                "University students targeted in sinister recruitment scam",
                "Yashee Sharma",
                "February 20, 2024",
                "https://www.9news.com.au/national/university-students-targeted-in-recruitment-scam/0aefdbd3-eb85-40e6-bfdf-b5699b13d22b",
                "https://imageresizer.static9.net.au/asyRzqOCD-60lTijKo5MUmbZ9jY=/1600x0/https%3A%2F%2Fprod.static9.net.au%2Ffs%2F017599d5-bbe1-4674-82b7-ae4343a5f170"));
        newsList.add(new NewsInfo(1013,
                "Family loses $288k to kidnapping scam targeting international students in NSW",
                "Mikala Theocharous",
                "October 18, 2023",
                "https://www.9news.com.au/national/kidnapping-scam-family-loses-288k-to-kidnapping-scam-targetting-international-students-in-nsw/a6c035ee-7d09-4f50-bbc1-c9010754997b",
                "https://imageresizer.static9.net.au/QnhWvwL7WE7p5x4pvDeWMWNcAxs=/0x2:2121x1195/1600x0/https%3A%2F%2Fprod.static9.net.au%2Ffs%2F59f83261-6c62-4af1-b32a-25f1c706fb80"));
        newsList.add(new NewsInfo(1014,
                "Sophisticated scams targeting Chinese international students ramping up ACCC warns",
                "Gareth Boreham & Ruth McHugh-Dillon",
                "September 22, 2023",
                "https://www.sbs.com.au/news/article/sophisticated-scams-targeting-chinese-international-students-ramping-up-accc-warns/odx22265d",
                "https://images.sbs.com.au/7f/de/85889033414dacfe155cd9693215/gettyimages-1414174437.jpg?imwidth=1280"));
        newsList.add(new NewsInfo(1015,
                "'Ghosted' by a fake rental agent these students have lost thousands to a property scam",
                "Yimin Qiang",
                "October 31, 2023",
                "https://www.sbs.com.au/language/chinese/en/article/fake-rental-properties-students-lose-thousands-of-dollars-to-fraudster/blzeap1w8",
                "https://images.sbs.com.au/34/5e/4134a74349f780d78ab7b68a56db/contract.jpg?imwidth=1280"));
        newsList.add(new NewsInfo(1016,
                "International students are bombarded by scams asking for money and personal information. Authorities say the scam uses increasingly sophisticated techniques to impersonate authorities",
                "Gareth Boreham",
                "October 1, 2023",
                "https://www.sbs.com.au/language/indonesian/en/podcast-episode/peringatan-atas-taktik-penipuan-yang-canggih-terhadap-pelajar-dari-tiongkok-di-australia/l5zrv2eks",
                "https://images.sbs.com.au/dims4/default/08465a7/2147483647/strip/true/resize/1280x720!/quality/90/?url=http%3A%2F%2Fsbs-au-brightspot.s3.amazonaws.com%2Fdrupal%2Fyourlanguage%2Fpublic%2Fchinese_students.jpg&imwidth=1280"));
        newsList.add(new NewsInfo(1017,
                "Alarm raised over ‘virtual kidnapping’ scams targeting international students",
                "Jack Evans",
                "October 18, 2023",
                "https://www.news.com.au/national/crime/alarm-raised-over-virtual-kidnapping-scams-targeting-international-students/news-story/da63fdf52c34b58ccd54e010cbc6d2c4",
                "https://content.api.news/v3/images/bin/3cc9cfe01c346e69e27a0f34460d9c8c?width=1024"));
       newsList.add(new NewsInfo(1018,
                "Scammers are increasingly targeting Chinese international students with the scams using increasingly sophisticated techniques to imitate authorities",
                "Gareth Boreham",
                "September 21, 2023",
                "https://www.sbs.com.au/news/podcast-episode/warning-over-elaborate-ploy-to-scam-international-students-in-australia/w8he767t9",
                "https://images.sbs.com.au/dims4/default/021dd61/2147483647/strip/true/crop/1800x1013+0+96/resize/1280x720!/quality/90/?url=http%3A%2F%2Fsbs-au-brightspot.s3.amazonaws.com%2Ffb%2F93%2Fb331d3c84bbabddfdcebdf9acaa2%2Ffemale-university-students-with-graduating-cloths-at-a-university-in-xiangyang-hubei-province-china-getty.jpg&imwidth=1280"));
        newsList.add(new NewsInfo(1019,
                "Scammers circle international students amid university accommodation crisis",
                "Andrea Mayes",
                "February 6, 2023",
                "https://www.abc.net.au/news/2023-02-04/university-accommodation-crisis-as-international-students-return/101912694",
                "https://live-production.wcms.abc-cdn.net.au/113623f8e384dbc8ddf74f4cba4adb82?impolicy=wcms_crop_resize&cropH=670&cropW=1191&xPos=0&yPos=0&width=862&height=485"));
        newsList.add(new NewsInfo(1020,
                "Chinese students lose millions to complex scam",
                "Millie Costigan",
                "September 26, 2023",
                "https://www.centralwesterndaily.com.au/story/8360308/scammers-posing-as-chinese-police-are-extorting-international-students-in-australia/",
                "https://www.centralwesterndaily.com.au/images/transform/v1/crop/frm/190143465/34d4cd69-b0f8-4155-9290-68586056e45e.jpg/r0_107_3711_2193_w1200_h678_fmax.webp"));
        return newsList;
    }
}