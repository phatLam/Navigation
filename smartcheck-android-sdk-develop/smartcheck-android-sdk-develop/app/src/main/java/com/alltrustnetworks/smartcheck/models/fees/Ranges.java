package com.alltrustnetworks.smartcheck.models.fees;

import org.parceler.Parcel;

import java.util.Arrays;

@Parcel
public class Ranges {

    public LowHighRange least;
    public BetweenRange[] between;
    public LowHighRange greatest;

    public Ranges() {
    }

    public Ranges(LowHighRange least, BetweenRange[] between, LowHighRange greatest) {
        this.least = least;
        this.between = between;
        this.greatest = greatest;
    }

    public LowHighRange getLeast() {
        return least;
    }

    public void setLeast(LowHighRange least) {
        this.least = least;
    }

    public LowHighRange getGreatest() {
        return greatest;
    }

    public void setGreatest(LowHighRange greatest) {
        this.greatest = greatest;
    }

    public BetweenRange[] getBetween() {
        return between;
    }

    public void setBetween(BetweenRange[] between) {
        this.between = between;
    }

    @Override
    public String toString() {
        return "Ranges{" +
                "least=" + least +
                ", between=" + Arrays.toString(between) +
                ", greatest=" + greatest +
                '}';
    }
}
