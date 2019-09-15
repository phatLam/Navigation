package com.alltrustnetworks.smartcheck.models;

import com.squareup.moshi.Json;

import org.parceler.Parcel;

import java.util.Arrays;
@Parcel
public class IdVerificationChallenge{
    public  @Json(name = "questions") IdVerificationQuestions[] questions;

    public IdVerificationChallenge() {
    }

    public IdVerificationChallenge(IdVerificationQuestions[] questions) {
        this.questions = questions;
    }

    public IdVerificationQuestions[] getQuestions() {
        return questions;
    }

    @Override
    public String toString() {
        return "IdVerificationChallenge{" +
                "questions=" + Arrays.toString(questions) +
                '}';
    }
}
