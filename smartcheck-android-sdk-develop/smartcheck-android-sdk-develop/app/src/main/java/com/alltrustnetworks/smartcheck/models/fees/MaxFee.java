package com.alltrustnetworks.smartcheck.models.fees;

import org.parceler.Parcel;

@Parcel
public class MaxFee {
    public int value;

    public MaxFee() {
    }

    public MaxFee(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "MaxFee{" +
                "value=" + value +
                '}';
    }
}
