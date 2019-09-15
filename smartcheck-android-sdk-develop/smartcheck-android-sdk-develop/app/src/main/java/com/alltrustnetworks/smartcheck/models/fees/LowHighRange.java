package com.alltrustnetworks.smartcheck.models.fees;

import org.parceler.Parcel;

@Parcel
public class LowHighRange {
    public boolean active;
    public int amount;
    public int percentage;
    public int flat;
    public String relationship;

    public LowHighRange() {
    }

    public LowHighRange(boolean active, int amount, int percentage, int flat, String relationship) {
        this.active = active;
        this.amount = amount;
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
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
        return "LowHighRange{" +
                "active=" + active +
                ", amount=" + amount +
                ", percentage=" + percentage +
                ", flat=" + flat +
                ", relationship='" + relationship + '\'' +
                '}';
    }
}
