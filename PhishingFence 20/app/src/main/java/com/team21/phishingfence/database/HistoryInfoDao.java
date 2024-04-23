package com.team21.phishingfence.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.team21.phishingfence.models.HistoryInfo;

import java.util.List;

@Dao
public interface HistoryInfoDao {
    @Insert
    void insertHistories(HistoryInfo... histories);

    @Update
    void updateHistories(HistoryInfo... histories);

    @Delete
    void deleteHistories(HistoryInfo... histories);

    @Query("DELETE FROM HistoryInfo")
    void deleteAllHistories();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    List<Long> insertOrUpdateHistories(HistoryInfo... historyInfos);

    @Query("SELECT * FROM HistoryInfo ORDER BY viewedTime DESC")
    LiveData<List<HistoryInfo>> getAllHistoryInfosLive();
}
