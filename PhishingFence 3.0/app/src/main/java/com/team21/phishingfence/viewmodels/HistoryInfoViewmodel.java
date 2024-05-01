package com.team21.phishingfence.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.team21.phishingfence.models.HistoryInfo;
import com.team21.phishingfence.repositories.HistoryInfoRepository;

import java.util.List;

public class HistoryInfoViewmodel extends AndroidViewModel {
    private HistoryInfoRepository historyInfoRepository;

    public HistoryInfoViewmodel(@NonNull Application application) {
        super(application);
        this.historyInfoRepository = new HistoryInfoRepository(application);
    }

    public LiveData<List<HistoryInfo>> getAllHistoryInfosLive() {
        return this.historyInfoRepository.getAllHistoryInfosLive();
    }

    public void insertHistoryInfos(HistoryInfo... historyInfos) {
        this.historyInfoRepository.insertHistoryInfos(historyInfos);
    }

    public void updateHistoryInfos(HistoryInfo... historyInfos) {
        this.historyInfoRepository.updateHistoryInfos(historyInfos);
    }

    public void deleteHistoryInfos(HistoryInfo... historyInfos) {
        this.historyInfoRepository.deleteHistoryInfos(historyInfos);
    }

    public void deleteAllHistoryInfos() {
        this.historyInfoRepository.deleteAllHistoryInfos();
    }

    public void insertOrUpdateHistoryInfos(HistoryInfo... historyInfos) {
        this.historyInfoRepository.insertOrUpdateHistoryInfos(historyInfos);
    }
}
