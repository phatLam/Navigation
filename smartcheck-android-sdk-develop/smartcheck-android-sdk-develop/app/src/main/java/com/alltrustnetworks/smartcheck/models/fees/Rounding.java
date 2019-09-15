package com.alltrustnetworks.smartcheck.models.fees;


import org.parceler.Parcel;

@Parcel
public class Rounding {
    public boolean active;
    public String direction;

    public Rounding() {
    }

    public Rounding(boolean active, String direction) {
        this.active = active;
        this.direction = direction;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    @Override
    public String toString() {
        return "Rounding{" +
                "active=" + active +
                ", direction='" + direction + '\'' +
                '}';
    }
}
