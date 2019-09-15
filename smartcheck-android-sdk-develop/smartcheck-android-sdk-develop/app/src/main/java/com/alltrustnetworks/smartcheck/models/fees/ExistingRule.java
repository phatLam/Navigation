package com.alltrustnetworks.smartcheck.models.fees;

import com.squareup.moshi.Json;

import org.parceler.Parcel;

@Parcel
public class ExistingRule {
    public boolean active;
    public @Json(name = "bad_checks") int badChecks;
    public int percentage;
    public int flat;
    public String relationship;

    public ExistingRule() {
    }

    public ExistingRule(boolean active, int badChecks, int percentage, int flat, String relationship) {
        this.active = active;
        this.badChecks = badChecks;
        this.percentage = percentage;
        this.flat = flat;
        this.relationship = relationship;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getBadChecks() {
        return badChecks;
    }

    public void setBadChecks(int badChecks) {
        this.badChecks = badChecks;
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
        return "ExistingRule{" +
                "active=" + active +
                ", badChecks=" + badChecks +
                ", percentage=" + percentage +
                ", flat=" + flat +
                ", relationship='" + relationship + '\'' +
                '}';
    }
}
