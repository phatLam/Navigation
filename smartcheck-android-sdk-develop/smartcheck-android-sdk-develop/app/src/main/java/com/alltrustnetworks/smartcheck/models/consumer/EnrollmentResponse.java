package com.alltrustnetworks.smartcheck.models.consumer;

import com.squareup.moshi.Json;

import org.parceler.Parcel;

@Parcel
public class EnrollmentResponse {
    @Json(name = "consumer_form") public ConsumerFormEnrollment consumer_form;
    @Json(name = "msg") public String msg;
    @Json(name = "success") public Boolean success = false;
    @Json(name = "new_device") public Boolean new_device = false;
}