package com.alltrustnetworks.smartcheck.models;

import com.squareup.moshi.Json;

import org.parceler.Parcel;

@Parcel
public class Fee {
    public @Json(name = "payout") String payout;
    public @Json(name = "total_fees") String total_fees;
    public @Json(name = "fee_amount") String fee_amount;
    public @Json(name = "surcharges_applied") SurchargesApplied[] surcharges_applied;
}
