package com.alltrustnetworks.smartcheck.models;

import com.squareup.moshi.Json;

import org.parceler.Parcel;

@Parcel
public class CheckDuplicateResponse {
    public @Json(name = "is_duplicate") boolean is_duplicate;
    public @Json(name = "duplicate_check_result") String duplicate_check_result;
    public @Json(name = "multiple_seen") boolean multiple_seen;

}
