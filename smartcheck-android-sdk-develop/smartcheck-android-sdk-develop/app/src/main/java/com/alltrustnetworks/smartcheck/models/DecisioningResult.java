package com.alltrustnetworks.smartcheck.models;

import com.squareup.moshi.Json;

import org.parceler.Parcel;

import java.util.Arrays;

@Parcel
public class DecisioningResult {

    public long score;
    public boolean fatal;
    public String autoResult;
    public String recommendation;
    public DecisioningDetail[] details;
    public @Json(name = "decision_id") String decisionId;

    public DecisioningResult() {
    }

    public DecisioningResult(long score, boolean fatal, String autoResult, String recommendation, DecisioningDetail[] details, String decisionId) {
        this.score = score;
        this.fatal = fatal;
        this.autoResult = autoResult;
        this.recommendation = recommendation;
        this.details = details;
        this.decisionId = decisionId;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }

    public boolean isFatal() {
        return fatal;
    }

    public void setFatal(boolean fatal) {
        this.fatal = fatal;
    }

    public String getAutoResult() {
        return autoResult;
    }

    public void setAutoResult(String autoResult) {
        this.autoResult = autoResult;
    }

    public String getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }

    public DecisioningDetail[] getDetails() {
        return details;
    }

    public void setDetails(DecisioningDetail[] details) {
        this.details = details;
    }

    public String getDecisionId() {
        return decisionId;
    }

    public void setDecisionId(String decisionId) {
        this.decisionId = decisionId;
    }

    @Override
    public String toString() {
        return "DecisioningResult{" +
                "score=" + score +
                ", fatal=" + fatal +
                ", autoResult='" + autoResult + '\'' +
                ", recommendation='" + recommendation + '\'' +
                ", details=" + Arrays.toString(details) +
                ", decisionId='" + decisionId + '\'' +
                '}';
    }
}
