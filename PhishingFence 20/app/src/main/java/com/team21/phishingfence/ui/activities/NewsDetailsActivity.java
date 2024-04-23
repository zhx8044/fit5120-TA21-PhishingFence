package com.team21.phishingfence.ui.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.team21.phishingfence.R;
import com.team21.phishingfence.models.HistoryInfo;
import com.team21.phishingfence.models.NewsInfo;
import com.team21.phishingfence.viewmodels.HistoryInfoViewmodel;

public class NewsDetailsActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private WebView mWebView;
    private NewsInfo newsInfo;
    private HistoryInfoViewmodel historyInfoViewmodel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);

        //获取传递数据
        newsInfo = (NewsInfo) getIntent().getSerializableExtra("newsInfo");

        //初始化控件
        mToolbar = findViewById(R.id.toolbar);
        mWebView = findViewById(R.id.webView);
        this.historyInfoViewmodel = new ViewModelProvider(this).get(HistoryInfoViewmodel.class);

        updateHistory(newsInfo);
        setupWebView();
        setupToolbar();
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void setupWebView() {
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true); // 启用DOM存储API

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                //Toast.makeText(NewsDetailsActivity.this, "页面加载出错", Toast.LENGTH_LONG).show();
            }
        });

        if (newsInfo != null) {
            String url = newsInfo.getDetailUrl();
            if (url != null && !url.isEmpty()) {
                mWebView.loadUrl(url);
            } else {
                Toast.makeText(this, "无效的网址", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "新闻详情未提供", Toast.LENGTH_LONG).show();
        }
    }

    private void setupToolbar() {
        mToolbar.setNavigationOnClickListener(v -> finish());
    }

    private void updateHistory(NewsInfo newsInfo) {
        String historyInfoTitle = newsInfo.getTitle();
        String historyInfoDetailUrl = newsInfo.getDetailUrl();
        String historyInfoImage = newsInfo.getNewsImage();
        HistoryInfo historyInfo = new HistoryInfo(historyInfoTitle,historyInfoDetailUrl,historyInfoImage);
        this.historyInfoViewmodel.insertOrUpdateHistoryInfos(historyInfo);
    }
}