package com.alltrustnetworks.smartcheck.models;

import com.squareup.moshi.Json;

import org.parceler.Parcel;

@Parcel
public class SurchargesApplied {
    public @Json(name = "id") String id;
    public @Json(name = "business_id") String business_id;
    public @Json(name = "name") String name;
    public @Json(name = "amount") String amount;
}
