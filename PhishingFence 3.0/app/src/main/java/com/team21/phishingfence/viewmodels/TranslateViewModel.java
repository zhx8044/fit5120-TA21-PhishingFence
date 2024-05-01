package com.team21.phishingfence.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.team21.phishingfence.repositories.TranslateRepository;

public class TranslateViewModel extends ViewModel {
    private final TranslateRepository translateRepository;
    private String textToTranslate;
    private String translatedText;

    public TranslateViewModel() {
        this.translateRepository = new TranslateRepository();
    }

    public void setTextToTranslate(String textToTranslate) {
        this.textToTranslate = textToTranslate;
    }

    public String getTextToTranslate() {
        return textToTranslate;
    }

    public String getTranslatedText() {
        return translatedText;
    }

    public void translate(String sourceLang, String targetLang) {
        this.translatedText = this.translateRepository.translate(this.textToTranslate, sourceLang, targetLang);
    }
}
