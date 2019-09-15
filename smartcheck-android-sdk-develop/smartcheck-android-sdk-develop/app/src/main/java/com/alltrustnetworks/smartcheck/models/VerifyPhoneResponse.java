package com.alltrustnetworks.smartcheck.models;

import com.squareup.moshi.Json;

import org.parceler.Parcel;

@Parcel
public class VerifyPhoneResponse {
    public @Json(name = "verified") boolean verified;
    public @Json(name = "normalized_phone_number")String normalized_phone_number;

    public VerifyPhoneResponse() {
    }

    public VerifyPhoneResponse(boolean verified, String normalized_phone_number) {
        this.verified = verified;
        this.normalized_phone_number = normalized_phone_number;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public String getNormalized_phone_number() {
        return normalized_phone_number;
    }

    public void setNormalized_phone_number(String normalized_phone_number) {
        this.normalized_phone_number = normalized_phone_number;
    }
}
