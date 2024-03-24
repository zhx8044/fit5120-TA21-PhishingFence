package com.example.phishingfence.model;

import java.io.Serializable;

public class NewsInfo implements Serializable
{
    private int newsId;
    private String title;
    private String source;
    private String publishDate;
    private String detailUrl; // 新闻详情页面的链接
    private int newsImage; // 新闻图片，假设使用本地资源ID

    public NewsInfo(int newsId, String title, String source, String publishDate, String detailUrl, int newsImage) {
        this.newsId = newsId;
        this.title = title;
        this.source = source;
        this.publishDate = publishDate;
        this.detailUrl = detailUrl;
        this.newsImage = newsImage;
    }

    public int getNewsId() {
        return newsId;
    }

    public void setNewsId(int newsId) {
        this.newsId = newsId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getDetailUrl() {
        return detailUrl;
    }

    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl;
    }

    public int getNewsImage() {
        return newsImage;
    }

    public void setNewsImage(int newsImage) {
        this.newsImage = newsImage;
    }
}
