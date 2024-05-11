package com.team21.phishingfence.viewmodels;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.team21.phishingfence.R;
import com.team21.phishingfence.models.Quiz;
import com.team21.phishingfence.repositories.QuizRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class QuizViewModel extends AndroidViewModel {
    private QuizRepository quizRepository;
    private MutableLiveData<Integer> quizNum;
    private int score = 0;
    private int[] quizList;
    public final static int BASIC_QUIZ = 1;
    public final static int EMAIL_QUIZ = 2;
    public final static int MESSAGE_QUIZ = 3;

    public QuizViewModel(@NonNull Application application) {
        super(application);
        this.quizRepository = new QuizRepository(application);
        this.quizNum = new MutableLiveData<>();
        this.quizNum.setValue(-1);
    }

    public MutableLiveData<Integer> getQuizNum() {
        return quizNum;
    }

    public int getScore() {
        return score;
    }

    public void addScore() {
        this.score++;
    }

    public void resetScore() {
        this.score = 0;
    }

    // 使用 Callable 获取 Quiz 对象
    public Quiz getQuizById(int id) {
        Log.e("TAG", "getQuizById: " + id);
        // 定义 Callable，用于从 Repository 中获取 Quiz 对象
        Callable<Quiz> callable = () -> quizRepository.getQuizById(id);

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        // 提交任务给 ExecutorService 并获取 Future
        Future<Quiz> future = executorService.submit(callable);

        // 尝试获取结果
        try {
            return future.get(); // 这将阻塞当前线程直到结果返回
        } catch (Exception e) {
            // 捕获潜在的异常（如中断、执行错误等）
            e.printStackTrace();
            return null;
        } finally {
            executorService.shutdown();
        }
    }

    public int[] getQuizList() {
        return quizList;
    }

    public void clearQuizList() {
        this.quizList = null;
    }

    public void generateQuizList() {
        Random random = new Random();
        // 创建一个包含 1 到 30 的列表
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= 30; i++) {
            numbers.add(i);
        }

        // 打乱列表以确保随机性
        Collections.shuffle(numbers, random);

        // 初始化 quizList 数组
        this.quizList = new int[7]; // 假设 quizList 的长度是 7
        for (int i = 0; i < this.quizList.length; i++) {
            // 从打乱的列表中取出前 7 个数
            this.quizList[i] = numbers.get(i);
        }

        resetScore();
        this.quizNum.setValue(1);
    }
}
