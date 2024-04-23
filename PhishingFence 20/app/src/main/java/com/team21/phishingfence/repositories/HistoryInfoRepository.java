package com.team21.phishingfence.repositories;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.team21.phishingfence.database.HistoryInfoDao;
import com.team21.phishingfence.database.HistoryInfoDb;
import com.team21.phishingfence.models.HistoryInfo;

import java.util.List;

public class HistoryInfoRepository {
    private final LiveData<List<HistoryInfo>> allHistoryInfosLive;
    private final HistoryInfoDao historyInfoDao;

    public HistoryInfoRepository(Context context) {
        HistoryInfoDb historyInfoDb = HistoryInfoDb.getDatabase(context.getApplicationContext());
        this.historyInfoDao = historyInfoDb.getHistoryInfoDao();
        this.allHistoryInfosLive = this.historyInfoDao.getAllHistoryInfosLive();
    }

    public LiveData<List<HistoryInfo>> getAllHistoryInfosLive() {
        return allHistoryInfosLive;
    }

    public void insertHistoryInfos(HistoryInfo... historyInfos) {
        new InsertAsynctask(this.historyInfoDao).execute(historyInfos);
    }

    public void updateHistoryInfos(HistoryInfo... historyInfos) {
        new UpdateAsynctask(this.historyInfoDao).execute(historyInfos);
    }

    public void deleteHistoryInfos(HistoryInfo... historyInfos) {
        new DeleteAsynctask(this.historyInfoDao).execute(historyInfos);
    }

    public void deleteAllHistoryInfos(HistoryInfo... voids) {
        new DeleteAllAsynctask(this.historyInfoDao).execute();
    }

    public void insertOrUpdateHistoryInfos(HistoryInfo... historyInfos) {
        new InsertOrUpdateAsynctask(this.historyInfoDao).execute(historyInfos);
    }

    private static class InsertAsynctask extends AsyncTask<HistoryInfo, Void, Void> {
        private final HistoryInfoDao historyInfoDao;

        public InsertAsynctask(HistoryInfoDao historyInfoDao) {
            this.historyInfoDao = historyInfoDao;
        }

        @Override
        protected Void doInBackground(HistoryInfo... historyInfos) {
            this.historyInfoDao.insertHistories(historyInfos);
            return null;
        }
    }

    private static class UpdateAsynctask extends AsyncTask<HistoryInfo, Void, Void> {
        private final HistoryInfoDao historyInfoDao;

        public UpdateAsynctask(HistoryInfoDao historyInfoDao) {
            this.historyInfoDao = historyInfoDao;
        }

        @Override
        protected Void doInBackground(HistoryInfo... historyInfos) {
            this.historyInfoDao.updateHistories(historyInfos);
            return null;
        }
    }

    private static class DeleteAsynctask extends AsyncTask<HistoryInfo, Void, Void> {
        private final HistoryInfoDao historyInfoDao;

        public DeleteAsynctask(HistoryInfoDao historyInfoDao) {
            this.historyInfoDao = historyInfoDao;
        }

        @Override
        protected Void doInBackground(HistoryInfo... historyInfos) {
            this.historyInfoDao.deleteHistories(historyInfos);
            return null;
        }
    }

    private static class DeleteAllAsynctask extends AsyncTask<Void, Void, Void> {
        private final HistoryInfoDao historyInfoDao;

        public DeleteAllAsynctask(HistoryInfoDao historyInfoDao) {
            this.historyInfoDao = historyInfoDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            this.historyInfoDao.deleteAllHistories();
            return null;
        }
    }

    private static class InsertOrUpdateAsynctask extends AsyncTask<HistoryInfo, Void, Void> {
        private final HistoryInfoDao historyInfoDao;

        public InsertOrUpdateAsynctask(HistoryInfoDao historyInfoDao) {
            this.historyInfoDao = historyInfoDao;
        }

        @Override
        protected Void doInBackground(HistoryInfo... historyInfos) {
            this.historyInfoDao.insertOrUpdateHistories(historyInfos);
            return null;
        }
    }
}
