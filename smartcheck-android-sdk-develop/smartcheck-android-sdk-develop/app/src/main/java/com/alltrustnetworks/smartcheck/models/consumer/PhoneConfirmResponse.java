package com.alltrustnetworks.smartcheck.models.consumer;

import com.squareup.moshi.Json;

import org.parceler.Parcel;

@Parcel
public class PhoneConfirmResponse {
    @Json(name ="msg")      public String msg;
    @Json(name ="success")  public boolean success;

}
