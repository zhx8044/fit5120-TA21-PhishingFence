package com.example.phishingfence.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.phishingfence.localdb.HistoryDbHelper;
import com.example.phishingfence.localdb.NewsService;
import com.example.phishingfence.model.HistoryInfo;
import com.example.phishingfence.model.NewsInfo;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class HistoryViewModel extends AndroidViewModel {
    private List<HistoryInfo> historyInfoList;
    private HistoryDbHelper mHistoryDbHelper;

    public HistoryViewModel(Application application) {
        super(application);
        mHistoryDbHelper = HistoryDbHelper.getInstance(application);
    }

    // 调用这个方法来加载数据
    public void loadHistoryForUser(String username) {
        historyInfoList = mHistoryDbHelper.findAllHistoryByUserAndMaintain(username);
    }

    // UI层通过这个方法获取数据
    public List<HistoryInfo> getHistoryInfoList() {
        return historyInfoList;
    }
}




