package com.team21.phishingfence.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.team21.phishingfence.models.HistoryInfo;

@Database(entities = {HistoryInfo.class},version = 1,exportSchema = false)
public abstract class HistoryInfoDb extends RoomDatabase {
    private static HistoryInfoDb INSTANCE;

    public synchronized static HistoryInfoDb getDatabase(Context context) {
        if(HistoryInfoDb.INSTANCE == null) {
            HistoryInfoDb.INSTANCE = Room.databaseBuilder(context.getApplicationContext(), HistoryInfoDb.class,"word_database")
                    .build();
        }
        return HistoryInfoDb.INSTANCE;
    }

    public abstract HistoryInfoDao getHistoryInfoDao();
}
