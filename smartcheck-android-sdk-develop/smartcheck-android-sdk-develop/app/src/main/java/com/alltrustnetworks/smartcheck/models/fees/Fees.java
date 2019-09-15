package com.alltrustnetworks.smartcheck.models.fees;

import com.squareup.moshi.Json;

import org.parceler.Parcel;

@Parcel
public class Fees {
    public String id;
    public @Json(name = "maximum_fee") MaxFee maxFee;
    public Rounding rounding;
    public Rules rules;
    public @Json(name = "handwritten_surcharge") SurchargeFee surchargeFee;
    public Ranges ranges;

    public Fees() {
    }

    public Fees(String id, MaxFee maxFee, Rounding rounding, Rules rules, SurchargeFee surchargeFee, Ranges ranges) {
        this.id = id;
        this.maxFee = maxFee;
        this.rounding = rounding;
        this.rules = rules;
        this.surchargeFee = surchargeFee;
        this.ranges = ranges;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public MaxFee getMaxFee() {
        return maxFee;
    }

    public void setMaxFee(MaxFee maxFee) {
        this.maxFee = maxFee;
    }

    public Rounding getRounding() {
        return rounding;
    }

    public void setRounding(Rounding rounding) {
        this.rounding = rounding;
    }

    public Rules getRules() {
        return rules;
    }

    public void setRules(Rules rules) {
        this.rules = rules;
    }

    public SurchargeFee getSurchargeFee() {
        return surchargeFee;
    }

    public void setSurchargeFee(SurchargeFee surchargeFee) {
        this.surchargeFee = surchargeFee;
    }

    public Ranges getRanges() {
        return ranges;
    }

    public void setRanges(Ranges ranges) {
        this.ranges = ranges;
    }

    @Override
    public String toString() {
        return "Fees{" +
                "id='" + id + '\'' +
                ", maxFee=" + maxFee +
                ", rounding=" + rounding +
                ", rules=" + rules +
                ", surchargeFee=" + surchargeFee +
                '}';
    }
}
