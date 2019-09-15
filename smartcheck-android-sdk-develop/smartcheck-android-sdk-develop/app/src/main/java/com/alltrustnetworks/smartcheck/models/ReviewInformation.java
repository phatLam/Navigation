package com.alltrustnetworks.smartcheck.models;


import com.squareup.moshi.Json;

import org.parceler.Parcel;

@Parcel
public class ReviewInformation {
    public @Json(name = "review_printable_id") int reviewPrintableId;
    public @Json(name = "entered_review") String enteredReview;
    public @Json(name = "review_status") String reviewStatus;
    public String reviewer;
    public @Json(name = "review_center_business_id") String reviewCenterBusinessId;
    public @Json(name = "reject_reason") String rejectReason;

    public ReviewInformation() {
    }

    public ReviewInformation(int reviewPrintableId, String enteredReview, String reviewStatus, String reviewer, String reviewCenterBusinessId, String rejectReason) {
        this.reviewPrintableId = reviewPrintableId;
        this.enteredReview = enteredReview;
        this.reviewStatus = reviewStatus;
        this.reviewer = reviewer;
        this.reviewCenterBusinessId = reviewCenterBusinessId;
        this.rejectReason = rejectReason;
    }

    public int getReviewPrintableId() {
        return reviewPrintableId;
    }

    public void setReviewPrintableId(int reviewPrintableId) {
        this.reviewPrintableId = reviewPrintableId;
    }

    public String getEnteredReview() {
        return enteredReview;
    }

    public void setEnteredReview(String enteredReview) {
        this.enteredReview = enteredReview;
    }

    public String getReviewStatus() {
        return reviewStatus;
    }

    public void setReviewStatus(String reviewStatus) {
        this.reviewStatus = reviewStatus;
    }

    public String getReviewer() {
        return reviewer;
    }

    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }

    public String getReviewCenterBusinessId() {
        return reviewCenterBusinessId;
    }

    public void setReviewCenterBusinessId(String reviewCenterBusinessId) {
        this.reviewCenterBusinessId = reviewCenterBusinessId;
    }

    public String getRejectReason() {
        return rejectReason;
    }

    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason;
    }

    @Override
    public String toString() {
        return "ReviewInformation{" +
                "reviewPrintableId=" + reviewPrintableId +
                ", enteredReview='" + enteredReview + '\'' +
                ", reviewStatus='" + reviewStatus + '\'' +
                ", reviewer='" + reviewer + '\'' +
                ", reviewCenterBusinessId='" + reviewCenterBusinessId + '\'' +
                ", rejectReason='" + rejectReason + '\'' +
                '}';
    }
}
