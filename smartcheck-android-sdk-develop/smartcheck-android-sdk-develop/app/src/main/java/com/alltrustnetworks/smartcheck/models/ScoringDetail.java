package com.alltrustnetworks.smartcheck.models;

import com.squareup.moshi.Json;

import org.parceler.Parcel;

@Parcel
public class ScoringDetail {
    public @Json(name = "positive_contribution") long positiveContribution;
    public @Json(name = "negative_contribution") long negativeContribution;
    public long potential;
    public String description;

    public ScoringDetail() {
    }

    public ScoringDetail(long positiveContribution, long negativeContribution, long potential, String description) {
        this.positiveContribution = positiveContribution;
        this.negativeContribution = negativeContribution;
        this.potential = potential;
        this.description = description;
    }

    public long getPositiveContribution() {
        return positiveContribution;
    }

    public void setPositiveContribution(long positiveContribution) {
        this.positiveContribution = positiveContribution;
    }

    public long getNegativeContribution() {
        return negativeContribution;
    }

    public void setNegativeContribution(long negativeContribution) {
        this.negativeContribution = negativeContribution;
    }

    public long getPotential() {
        return potential;
    }

    public void setPotential(long potential) {
        this.potential = potential;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ScoringDetail{" +
                "positiveContribution=" + positiveContribution +
                ", negativeContribution=" + negativeContribution +
                ", potential=" + potential +
                ", description='" + description + '\'' +
                '}';
    }
}
