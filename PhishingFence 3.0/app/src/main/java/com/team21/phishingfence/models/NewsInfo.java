package com.team21.phishingfence.models;

import java.io.Serializable;

public class NewsInfo implements Serializable {
    private int newsId;
    private String title;
    private String source;
    private String publishDate;
    private String detailUrl;
    private String newsImage;

    public NewsInfo(int newsId, String title, String source, String publishDate, String detailUrl, String newsImage) {
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

    public String getNewsImage() {
        return newsImage;
    }

    public void setNewsImage(String newsImage) {
        this.newsImage = newsImage;
    }
}
