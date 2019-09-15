package com.alltrustnetworks.smartcheck.models;

import com.squareup.moshi.Json;

import org.parceler.Parcel;

@Parcel
public class CheckDuplicateRequest {
    public @Json(name = "aba_number")
    String aba_number;
    public @Json(name = "account_number")
    String account_number;
    public @Json(name = "check_number")
    String check_number;
    public @Json(name = "check_date")
    String check_date;

    public CheckDuplicateRequest() {
    }

    public CheckDuplicateRequest(Check check) {
        this.aba_number = check.micr.transitNumber;
        this.account_number = check.micr.accountNumber;
        this.check_number = check.micr.checkNumber;
        this.check_date = check.checkDate;
    }
}
