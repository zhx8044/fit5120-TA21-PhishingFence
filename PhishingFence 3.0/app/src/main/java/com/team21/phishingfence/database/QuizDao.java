package com.team21.phishingfence.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.team21.phishingfence.models.HistoryInfo;
import com.team21.phishingfence.models.Quiz;

import java.util.List;

@Dao
public interface QuizDao {
    @Insert
    void insertQuizzes(Quiz... quizzes);

    @Delete
    void deleteQuizzes(Quiz... quizzes);

    @Query("SELECT * FROM Quiz WHERE id = :id")
    Quiz getQuizById(int id);
}
