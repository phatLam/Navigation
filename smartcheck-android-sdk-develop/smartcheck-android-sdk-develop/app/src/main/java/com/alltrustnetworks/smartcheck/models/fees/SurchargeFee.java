package com.alltrustnetworks.smartcheck.models.fees;

import org.parceler.Parcel;

@Parcel
public class SurchargeFee {
    public int percentage;
    public int flat;
    public String relationship;

    public SurchargeFee() {
    }

    public SurchargeFee(int percentage, int flat, String relationship) {
        this.percentage = percentage;
        this.flat = flat;
        this.relationship = relationship;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    public int getFlat() {
        return flat;
    }

    public void setFlat(int flat) {
        this.flat = flat;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    @Override
    public String toString() {
        return "SurchargeFee{" +
                "percentage=" + percentage +
                ", flat=" + flat +
                ", relationship='" + relationship + '\'' +
                '}';
    }
}
