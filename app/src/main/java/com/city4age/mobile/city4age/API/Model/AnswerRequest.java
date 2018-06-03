package com.city4age.mobile.city4age.API.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Srle on 6/3/2018.
 */
public class AnswerRequest {

    @SerializedName("user")
    @Expose
    private String user;

    @SerializedName("question")
    @Expose
    private int question;

    @SerializedName("answer")
    @Expose
    private int answer;

    public AnswerRequest() {}

    public AnswerRequest(String user, int question, int answer) {
        this.user = user;
        this.question = question;
        this.answer = answer;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getQuestion() {
        return question;
    }

    public void setQuestion(int question) {
        this.question = question;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }
}
