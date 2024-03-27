package com.example.phishingfence.ui.activities;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
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

import com.example.phishingfence.R;
import com.example.phishingfence.localdb.HistoryDbHelper;
import com.example.phishingfence.model.NewsInfo;
import com.example.phishingfence.viewmodel.HistoryViewModel;

public class NewsDetailesActivity extends AppCompatActivity
{
    private Toolbar mToolbar;
    private WebView mWebView;
    private NewsInfo newsInfo;
    private HistoryDbHelper mHistoryDbHelper;

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
        addHistoryRecord();
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

    //添加历史记录
    private void addHistoryRecord() {
        if (newsInfo != null) {
            // 在新的线程中执行数据库操作
            new Thread(new Runnable() {
                @Override
                public void run() {
                    // 获取HistoryDbHelper的实例并执行数据库操作
                    mHistoryDbHelper = HistoryDbHelper.getInstance(NewsDetailesActivity.this);
                    mHistoryDbHelper.updateOrAddHistory("zsan", newsInfo.getTitle(), newsInfo.getDetailUrl(), newsInfo.getNewsImage());

                    // 如果需要在操作完成后更新UI（如显示一个提示），确保该操作在主线程中执行
                    //runOnUiThread(() -> {
                    //   Toast.makeText(NewsDetailesActivity.this, "History added!", Toast.LENGTH_SHORT).show();
                    //});
                }
            }).start();
        }
    }
}