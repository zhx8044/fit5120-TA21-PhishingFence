package com.team21.phishingfence.repositories;

import android.util.Log;

import com.deepl.api.*;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TranslateRepository {
    private final static String AUTHKEY = "726ef87b-d895-467e-8909-7b51dd349ab0:fx";
    private final Translator translator = new Translator(TranslateRepository.AUTHKEY);

    public String translate(String textToTranslate, String sourceLang, String targetLang) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        TranslationTask task = new TranslationTask(textToTranslate, sourceLang, targetLang, this.translator);//开启线程执行翻译功能
        Future<String> future = executor.submit(task);
        try {
            return future.get();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            Log.e("TranslateRepository", "Thread was interrupted", e);
            return "Translation interrupted.";
        } catch (ExecutionException e) {
            Log.e("TranslateRepository", "Error during translation", e.getCause());
            return "Error during translation.";
        } finally {
            executor.shutdown();
        }
    }

    public static class TranslationTask implements Callable<String> {
        private final String textToTranslate;
        private final String sourceLang;
        private final String targetLang;
        private final Translator translator;

        public TranslationTask(String textToTranslate, String sourceLang, String targetLang, Translator translator) {
            this.textToTranslate = textToTranslate;
            this.sourceLang = sourceLang;
            this.targetLang = targetLang;
            this.translator = translator;
        }

        @Override
        public String call() throws Exception {
            TextResult result = this.translator.translateText(textToTranslate, sourceLang, targetLang);
            return result.getText();
        }
    }
}
