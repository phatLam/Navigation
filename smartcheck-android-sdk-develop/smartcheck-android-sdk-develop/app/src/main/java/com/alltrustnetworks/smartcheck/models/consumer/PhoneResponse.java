package com.alltrustnetworks.smartcheck.models.consumer;

import com.squareup.moshi.Json;

import org.parceler.Parcel;

@Parcel
public class PhoneResponse {
    @Json(name = "consumer_form") public ConsumerFormEnrollment consumer_form;
    @Json(name = "msg")         public String msg;
    @Json(name = "success")     public boolean success;
    @Json(name = "new_user")    public boolean new_user;

    public PhoneResponse(){}

    public ConsumerFormEnrollment getConsumer_form() {
        return consumer_form;
    }

    public void setConsumer_form(ConsumerFormEnrollment consumer_form) {
        this.consumer_form = consumer_form;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public boolean isNew_user() {
        return new_user;
    }

    public void setNew_user(boolean new_user) {
        this.new_user = new_user;
    }

    @Override
    public String toString() {
        return "PhoneResponse{" +
                "consumer_form=" + consumer_form +
                ", msg='" + msg + '\'' +
                ", success=" + success +
                ", new_user=" + new_user +
                '}';
    }
}
