package com.alltrustnetworks.smartcheck.models.consumer;

import com.squareup.moshi.Json;

import org.parceler.Parcel;

@Parcel
public class TemporaryResponse {

    @Json(name = "consumer_form") public FormConsumer consumerForm;
    @Json(name = "msg") public String message;
    @Json(name = "success") public boolean success;
    @Json(name = "new_user") public boolean newUser;

    public TemporaryResponse() {
    }

    public TemporaryResponse(FormConsumer consumerForm, String message, boolean success, boolean newUser) {
        this.consumerForm = consumerForm;
        this.message = message;
        this.success = success;
        this.newUser = newUser;
    }

    public FormConsumer getConsumerForm() {
        return consumerForm;
    }

    public void setConsumerForm(FormConsumer consumerForm) {
        this.consumerForm = consumerForm;
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

    public boolean isNewUser() {
        return newUser;
    }

    public void setNewUser(boolean newUser) {
        this.newUser = newUser;
    }
}