package com.example.phishingfence.model;

import java.io.Serializable;

public class HistoryInfo
{
    private int history_id;
    private String username;
    private String title;
    private String detailUrl;
    private int newsImage;
    private String viewed_date;

    public HistoryInfo(int history_id, String username, String title, String detailUrl, int newsImage, String viewed_date) {
        this.history_id = history_id;
        this.username = username;
        this.title = title;
        this.detailUrl = detailUrl;
        this.newsImage = newsImage;
        this.viewed_date = viewed_date;
    }

    public int getHistory_id() {
        return history_id;
    }

    public void setHistory_id(int history_id) {
        this.history_id = history_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getViewed_date() {
        return viewed_date;
    }

    public void setViewed_date(String viewed_date) {
        this.viewed_date = viewed_date;
    }
}
