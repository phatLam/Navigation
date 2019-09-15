package com.alltrustnetworks.smartcheck.models;

import com.squareup.moshi.Json;

import org.parceler.Parcel;

@Parcel
public class GeoData {
    public @Json(name = "latitude") double latitude;
    public @Json(name = "longitude") double longitude;
}
