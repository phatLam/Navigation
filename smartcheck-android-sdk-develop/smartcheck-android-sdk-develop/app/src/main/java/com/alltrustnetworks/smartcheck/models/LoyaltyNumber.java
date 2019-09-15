package com.alltrustnetworks.smartcheck.models;

import com.alltrustnetworks.smartcheck.BuildConfig;
import com.alltrustnetworks.smartcheck.SmartCheckApi;
import com.squareup.moshi.Json;

import org.parceler.Parcel;

@Parcel
public class LoyaltyNumber {
    public @Json(name = "business_id") String businessId;
    public @Json(name = "loyalty_number") String loyaltyNumber;

    public LoyaltyNumber() {
    }

    public LoyaltyNumber(String loyaltyNumber) {
        this.businessId = BuildConfig.BUSINESS_ID;
        this.loyaltyNumber = loyaltyNumber;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public String getLoyaltyNumber() {
        return loyaltyNumber;
    }

    public void setLoyaltyNumber(String loyaltyNumber) {
        this.loyaltyNumber = loyaltyNumber;
    }

    @Override
    public String toString() {
        return "LoyaltyNumber{" +
                "businessId='" + businessId + '\'' +
                ", loyaltyNumber='" + loyaltyNumber + '\'' +
                '}';
    }
}
