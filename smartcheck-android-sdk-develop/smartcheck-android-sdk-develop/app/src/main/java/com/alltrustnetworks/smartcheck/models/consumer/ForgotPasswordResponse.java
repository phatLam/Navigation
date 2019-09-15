package com.alltrustnetworks.smartcheck.models.consumer;

import com.squareup.moshi.Json;

import org.parceler.Parcel;

@Parcel
public class ForgotPasswordResponse {

    @Json(name = "msg") public String message;
    @Json(name = "success") public boolean success;
    @Json(name = "consumer_id") public int consumerId;

    public ForgotPasswordResponse() {
    }

    public ForgotPasswordResponse(String message, boolean success, int consumerId) {
        this.message = message;
        this.success = success;
        this.consumerId = consumerId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getConsumerId() {
        return consumerId;
    }

    public void setConsumerId(int consumerId) {
        this.consumerId = consumerId;
    }
}
