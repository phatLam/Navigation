package com.alltrustnetworks.smartcheck.models;

import org.parceler.Parcel;

@Parcel
public class ReviewStatus {
    public String status="";

    private static final String PENDING = "pending";
    private static final String INPROGRESS = "inprogress";
    private static final String REVIEW = "review";
    private static final String APPROVED = "approved";
    private static final String DECLINED = "declined";
    private static final String HOLD = "hold";

    public ReviewStatus() {
    }

    public ReviewStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isApproved() {
        if (status.isEmpty()) return false;
        return HOLD.equals(status) || APPROVED.equals(status);
    }

    public boolean isDeclined() {
        if (status.isEmpty()) return false;
        return DECLINED.equals(status);
    }

    public boolean isPendingReview() {
        if (status.isEmpty()) return false;
        return PENDING.equals(status) || INPROGRESS.equals(status) || REVIEW.equals(status);
    }

    @Override
    public String toString() {
        return "ReviewStatus{" +
                "status='" + status + '\'' +
                '}';
    }
}
