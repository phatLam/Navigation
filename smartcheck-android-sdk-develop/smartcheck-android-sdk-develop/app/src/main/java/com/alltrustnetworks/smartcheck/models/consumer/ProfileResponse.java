package com.alltrustnetworks.smartcheck.models.consumer;

import com.squareup.moshi.Json;

import org.parceler.Parcel;

@Parcel
public class ProfileResponse {
        @Json(name = "consumer_form") public FormConsumer consumer_form;
        @Json(name = "msg") public String msg;
        @Json(name = "success") public boolean mssuccessg;

    public ProfileResponse() {
    }

    public ProfileResponse(FormConsumer consumer_form, String msg, boolean mssuccessg) {
        this.consumer_form = consumer_form;
        this.msg = msg;
        this.mssuccessg = mssuccessg;
    }

    public FormConsumer getConsumer_form() {
        return consumer_form;
    }

    public void setConsumer_form(FormConsumer consumer_form) {
        this.consumer_form = consumer_form;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isMssuccessg() {
        return mssuccessg;
    }

    public void setMssuccessg(boolean mssuccessg) {
        this.mssuccessg = mssuccessg;
    }
}
