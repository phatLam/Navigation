package com.alltrustnetworks.smartcheck.exceptions;

public class SmartCheckApiException extends Exception {

    public String errorCode;

    public SmartCheckApiException (String message) {
        super (message);
    }

    public SmartCheckApiException (String message, String code) {
        super (message);
        errorCode = code;
    }

    public String getCode() {
        return errorCode;
    }
}
