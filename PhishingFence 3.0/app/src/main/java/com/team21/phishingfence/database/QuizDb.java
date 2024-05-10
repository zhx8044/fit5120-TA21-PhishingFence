package com.team21.phishingfence.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.team21.phishingfence.models.Quiz;

@Database(entities = {Quiz.class},version = 1,exportSchema = false)
public abstract class QuizDb extends RoomDatabase {
    private static QuizDb INSTANCE;

    public synchronized static QuizDb getDatabase(Context context) {
        if(QuizDb.INSTANCE == null) {
            QuizDb.INSTANCE = Room.databaseBuilder(context.getApplicationContext(), QuizDb.class,"quiz_database")
                    .build();
        }
        return QuizDb.INSTANCE;
    }

    public abstract QuizDao getQuizDao();
}
