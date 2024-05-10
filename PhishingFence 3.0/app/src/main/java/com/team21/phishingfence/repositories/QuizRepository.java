package com.team21.phishingfence.repositories;

import android.content.Context;
import android.os.AsyncTask;

import com.team21.phishingfence.database.HistoryInfoDao;
import com.team21.phishingfence.database.QuizDao;
import com.team21.phishingfence.database.QuizDb;
import com.team21.phishingfence.models.HistoryInfo;
import com.team21.phishingfence.models.Quiz;

public class QuizRepository {
    private final QuizDao quizDao;

    public QuizRepository(Context context) {
        QuizDb quizDb = QuizDb.getDatabase(context.getApplicationContext());
        this.quizDao = quizDb.getQuizDao();
    }

    public void insertQuizzes(Quiz... quizzes) {
        new QuizRepository.InsertAsynctask(this.quizDao).execute(quizzes);
    }

    private static class InsertAsynctask extends AsyncTask<Quiz, Void, Void> {
        private final QuizDao quizDao;

        public InsertAsynctask(QuizDao quizDao) {
            this.quizDao = quizDao;
        }

        @Override
        protected Void doInBackground(Quiz... quizzes) {
            this.quizDao.insertQuizzes(quizzes);
            return null;
        }
    }

    // 删除方法，通过异步任务执行
    public void deleteQuizzes(Quiz... quizzes) {
        new DeleteAsyncTask(quizDao).execute(quizzes);
    }

    // 异步任务类用于删除数据
    private static class DeleteAsyncTask extends AsyncTask<Quiz, Void, Void> {
        private final QuizDao quizDao;

        public DeleteAsyncTask(QuizDao quizDao) {
            this.quizDao = quizDao;
        }

        @Override
        protected Void doInBackground(Quiz... quizzes) {
            quizDao.deleteQuizzes(quizzes);
            return null;
        }
    }

    public Quiz getQuizById(int id) {
        return quizDao.getQuizById(id);
    }
}
