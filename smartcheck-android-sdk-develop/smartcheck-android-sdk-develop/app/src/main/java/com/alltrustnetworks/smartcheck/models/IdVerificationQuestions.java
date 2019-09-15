package com.alltrustnetworks.smartcheck.models;

import com.squareup.moshi.Json;

import org.parceler.Parcel;

import java.util.Arrays;
@Parcel
public class IdVerificationQuestions {
    public @Json(name = "prompt") String prompt = "";
    public @Json(name = "selected_answer") String selectedAnswer = "";
    public @Json(name = "answers") String[] answers;

    public IdVerificationQuestions(String prompt, String selectedAnswer, String[] answers) {
        this.prompt = prompt;
        this.selectedAnswer = selectedAnswer;
        this.answers = answers;
    }

    public IdVerificationQuestions() {
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public String getSelectedAnswer() {
        return selectedAnswer;
    }

    public void setSelectedAnswer(String selectedAnswer) {
        this.selectedAnswer = selectedAnswer;
    }

    public String[] getAnswers() {
        return answers;
    }

    public void setAnswers(String[] answers) {
        this.answers = answers;
    }

    @Override
    public String toString() {
        return "IdVerificationQuestions{" +
                "prompt='" + prompt + '\'' +
                ", selectedAnswer='" + selectedAnswer + '\'' +
                ", answers=" + Arrays.toString(answers) +
                '}';
    }
}

