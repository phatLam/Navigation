package com.alltrustnetworks.smartcheck.models;

import com.squareup.moshi.Json;

import org.parceler.Parcel;

@Parcel
public class IdVerificationResponse {
    public IdVerificationResponse() {
    }

    public @Json(name = "status") String status = "";
    public @Json(name = "challenge_required") boolean challenge_required = false;
    public @Json(name = "challenge") IdVerificationChallenge challenge;

    public IdVerificationResponse(String status, boolean challengeRequired, IdVerificationChallenge challenge) {
        this.status = status;
        this.challenge_required = challengeRequired;
        this.challenge = challenge;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean getChallenge_required() {
        return challenge_required;
    }

    public void setChallenge_required(boolean challenge_required) {
        this.challenge_required = challenge_required;
    }

    public IdVerificationChallenge getChallenge() {
        return challenge;
    }

    public void setChallenge(IdVerificationChallenge challenge) {
        this.challenge = challenge;
    }

    @Override
    public String toString() {
        return "IdVerificationResponse{" +
                "status='" + status + '\'' +
                ", challenge_required='" + challenge_required + '\'' +
                ", challenge='" + challenge + '\'' +
                '}';
    }
}
