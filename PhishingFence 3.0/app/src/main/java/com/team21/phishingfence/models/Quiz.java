package com.team21.phishingfence.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Quiz {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private int id;
    @ColumnInfo(name = "type")
    private int type;
    @ColumnInfo(name = "question")
    private int question;
    @ColumnInfo(name = "phoneMessage")
    private int phoneMessage;
    @ColumnInfo(name = "answer")
    private Boolean answer;
    @ColumnInfo(name = "warning")
    private int warning;

    @ColumnInfo(name = "tips")
    private int tips;

    public Quiz(int id, int type, int question, int phoneMessage, Boolean answer, int warning, int tips) {
        this.id = id;
        this.type = type;
        this.question = question;
        this.phoneMessage = phoneMessage;
        this.answer = answer;
        this.warning = warning;
        this.tips = tips;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getQuestion() {
        return question;
    }

    public void setQuestion(int question) {
        this.question = question;
    }

    public int getPhoneMessage() {
        return phoneMessage;
    }

    public void setPhoneMessage(int phoneMessage) {
        this.phoneMessage = phoneMessage;
    }

    public Boolean getAnswer() {
        return answer;
    }

    public void setAnswer(Boolean answer) {
        this.answer = answer;
    }

    public int getWarning() {
        return warning;
    }

    public void setWarning(int warning) {
        this.warning = warning;
    }

    public int getTips() {
        return tips;
    }

    public void setTips(int tips) {
        this.tips = tips;
    }
}
