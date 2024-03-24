package com.example.phishingfence.ui.activities;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.phishingfence.R;
import com.example.phishingfence.model.NewsInfo;

public class NewsDetailesActivity extends AppCompatActivity
{
    private Toolbar mToolbar;
    private WebView mWebView;
    private NewsInfo newsInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detailes);

        //获取传递数据
        newsInfo = (NewsInfo) getIntent().getSerializableExtra("newsInfo");

        //初始化控件
        mToolbar = findViewById(R.id.toolbar);
        mWebView = findViewById(R.id.webView);

        displayWeb();
        setupClickListeners();
    }

    private void setupClickListeners()
    {
        setupBackButtonListener();
    }

    private void setupBackButtonListener()
    {
        //返回
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void displayWeb()
    {
        // 开启JavaScript
        mWebView.getSettings().setJavaScriptEnabled(true);

        // 设置WebViewClient
        mWebView.setWebViewClient(new WebViewClient());

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                // 在这里处理错误，显示一个错误视图或消息
            }
        });


        if(newsInfo!=null)
        {
            mWebView.loadUrl(newsInfo.getDetailUrl());
        }
    }

    //在Activity销毁时，如果WebView还在加载数据，建议停止加载，避免内存泄露
    @Override
    protected void onDestroy() {
        if (mWebView != null) {
            mWebView.stopLoading();
            mWebView.setWebViewClient(null);
            mWebView.destroy();
        }
        super.onDestroy();
    }
}