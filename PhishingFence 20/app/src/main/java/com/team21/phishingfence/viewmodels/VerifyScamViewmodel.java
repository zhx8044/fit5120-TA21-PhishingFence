package com.team21.phishingfence.viewmodels;

import android.widget.TextView;

import androidx.lifecycle.ViewModel;

import com.team21.phishingfence.repositories.VerifyScamRepository;

public class VerifyScamViewmodel extends ViewModel {
    private String message;
    private String result;
    private final VerifyScamRepository verifyScamRepository;

    public VerifyScamViewmodel() {
        this.verifyScamRepository = new VerifyScamRepository();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void verify(TextView textView) {
        this.verifyScamRepository.verify(this.message,textView);
        this.result = textView.getText().toString();
    }
}
