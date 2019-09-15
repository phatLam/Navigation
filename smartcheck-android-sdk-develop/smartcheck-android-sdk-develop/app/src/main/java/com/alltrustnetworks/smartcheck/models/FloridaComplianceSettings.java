package com.alltrustnetworks.smartcheck.models;

import com.squareup.moshi.Json;

import org.parceler.Parcel;

@Parcel
public class FloridaComplianceSettings {
    public @Json(name = "license_number") String license_number;
    public @Json(name = "username") String username;
    public @Json(name = "password")String password;
    public @Json(name = "email")String email;
    public @Json(name = "active")boolean active;
}

