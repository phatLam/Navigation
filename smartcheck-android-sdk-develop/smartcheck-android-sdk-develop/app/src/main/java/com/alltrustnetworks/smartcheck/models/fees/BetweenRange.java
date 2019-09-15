package com.alltrustnetworks.smartcheck.models.fees;

import org.parceler.Parcel;

@Parcel
public class BetweenRange {
    public int id;
    public boolean active;
    public int low;
    public int high;
    public int percentage;
    public int flat;
    public String relationship;

    public BetweenRange() {
    }

    public BetweenRange(int id, boolean active, int low, int high, int percentage, int flat, String relationship) {
        this.id = id;
        this.active = active;
        this.low = low;
        this.high = high;
        this.percentage = percentage;
        this.flat = flat;
        this.relationship = relationship;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getLow() {
        return low;
    }

    public void setLow(int low) {
        this.low = low;
    }

    public int getHigh() {
        return high;
    }

    public void setHigh(int high) {
        this.high = high;
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
        return "BetweenRange{" +
                "id=" + id +
                ", active=" + active +
                ", low=" + low +
                ", high=" + high +
                ", percentage=" + percentage +
                ", flat=" + flat +
                ", relationship='" + relationship + '\'' +
                '}';
    }
}
