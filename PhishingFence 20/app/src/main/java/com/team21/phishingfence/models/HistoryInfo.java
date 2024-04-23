package com.team21.phishingfence.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class HistoryInfo {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "title")
    private String title;
    @ColumnInfo(name = "detailUrl")
    private String detailUrl;
    @ColumnInfo(name = "newsImage")
    private String newsImage;
    @ColumnInfo(name = "viewedTime")
    private long viewedTime;

    public HistoryInfo(String title, String detailUrl, String newsImage) {
        this.title = title;
        this.detailUrl = detailUrl;
        this.newsImage = newsImage;
        this.viewedTime = System.currentTimeMillis();
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

    public String getNewsImage() {
        return newsImage;
    }

    public void setNewsImage(String newsImage) {
        this.newsImage = newsImage;
    }

    public long getViewedTime() {
        return viewedTime;
    }

    public void setViewedTime(long viewedTime) {
        this.viewedTime = viewedTime;
    }
}
