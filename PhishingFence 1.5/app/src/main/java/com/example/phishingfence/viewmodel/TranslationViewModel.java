package com.example.phishingfence.viewmodel;

import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.deepl.api.*;

public class TranslationViewModel extends ViewModel
{
    // 定义一个回调接口
    public interface TranslationCallback
    {
        void onTranslationResult(String translatedText);
        void onTranslationError(Exception e);
    }

    //点击翻译后跳转到翻译结果页面
    // LiveData来存储翻译结果
    private MutableLiveData<String> liveTranslationResult = new MutableLiveData<>();

    // 获取LiveData的公共方法
    public LiveData<String> getTranslationResult() {
        return liveTranslationResult;
    }

    public void translateText(String textToTranslate, String sourceLanguage, String targetLanguage, TranslationCallback callback)
    {
        // 创建指向主线程的Handler
        Handler mainThreadHandler = new Handler(Looper.getMainLooper());

        Runnable translationRunnable = new Runnable() {
            @Override
            public void run() {
                try {
                    String authKey = "726ef87b-d895-467e-8909-7b51dd349ab0:fx";
                    Translator translator = new Translator(authKey);
                    TextResult result = translator.translateText(textToTranslate, sourceLanguage, targetLanguage);
                    String translated = result.getText();
                    if (callback != null) {
                        // 确保回调在主线程上执行
                        mainThreadHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                callback.onTranslationResult(translated); // 在主线程上更新UI
                            }
                        });
                    }
                } catch (Exception e) {
                    if (callback != null) {
                        // 确保回调在主线程上执行
                        mainThreadHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                callback.onTranslationError(e); // 在主线程上处理错误
                            }
                        });
                    }
                }
            }
        };

        new Thread(translationRunnable).start();
    }
}
