package com.alltrustnetworks.smartcheck.models;

import com.squareup.moshi.Json;

import org.parceler.Parcel;

@Parcel
public class CustomerError {
    public @Json(name = "duplicate_reason") String duplicateReason;
    public @Json(name = "duplicate_customer_id") String duplicateCustomerId;

    public CustomerError() {
    }

    public CustomerError(String duplicateReason, String duplicateCustomerId) {
        this.duplicateReason = duplicateReason;
        this.duplicateCustomerId = duplicateCustomerId;
    }

    public String getDuplicateReason() {
        return duplicateReason;
    }

    public void setDuplicateReason(String duplicateReason) {
        this.duplicateReason = duplicateReason;
    }

    public String getDuplicateCustomerId() {
        return duplicateCustomerId;
    }

    public void setDuplicateCustomerId(String duplicateCustomerId) {
        this.duplicateCustomerId = duplicateCustomerId;
    }

    @Override
    public String toString() {
        return "CustomerError{" +
                "duplicateReason='" + duplicateReason + '\'' +
                ", duplicateCustomerId='" + duplicateCustomerId + '\'' +
                '}';
    }
}
