package com.alltrustnetworks.smartcheck.models;

import com.squareup.moshi.Json;

import org.parceler.Parcel;

@Parcel
public class Micr {
    public @Json(name = "raw_micr") String rawMicr;
    public @Json(name = "parse_error") boolean parseError;
    public @Json(name = "status_message") String statusMessage;
    public @Json(name = "contains_unrecognized_symbols") boolean containsUnrecognizedSymbols;
    public @Json(name = "check_type") String checkType;
    public @Json(name = "aux_onus") String auxOnus;
    public String onus;
    public @Json(name = "transit_number") String transitNumber;
    public @Json(name = "account_number") String accountNumber;
    public @Json(name = "check_number") String checkNumber;
    public String amount;
    public boolean special;

    public Micr() {
    }

    public Micr(String rawMicr, boolean parseError, String statusMessage, boolean containsUnrecognizedSymbols, String checkType, String auxOnus, String transitNumber, String onus, String accountNumber, String checkNumber, String amount, boolean special) {
        this.rawMicr = rawMicr;
        this.parseError = parseError;
        this.statusMessage = statusMessage;
        this.containsUnrecognizedSymbols = containsUnrecognizedSymbols;
        this.checkType = checkType;
        this.auxOnus = auxOnus;
        this.transitNumber = transitNumber;
        this.onus = onus;
        this.accountNumber = accountNumber;
        this.checkNumber = checkNumber;
        this.amount = amount;
        this.special = special;
    }

    public String getRawMicr() {
        return rawMicr;
    }

    public void setRawMicr(String rawMicr) {
        this.rawMicr = rawMicr;
    }

    public boolean isParseError() {
        return parseError;
    }

    public void setParseError(boolean parseError) {
        this.parseError = parseError;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public boolean isContainsUnrecognizedSymbols() {
        return containsUnrecognizedSymbols;
    }

    public void setContainsUnrecognizedSymbols(boolean containsUnrecognizedSymbols) {
        this.containsUnrecognizedSymbols = containsUnrecognizedSymbols;
    }

    public String getCheckType() {
        return checkType;
    }

    public void setCheckType(String checkType) {
        this.checkType = checkType;
    }

    public String getAuxOnus() {
        return auxOnus;
    }

    public void setAuxOnus(String auxOnus) {
        this.auxOnus = auxOnus;
    }

    public String getTransitNumber() {
        return transitNumber;
    }

    public void setTransitNumber(String transitNumber) {
        this.transitNumber = transitNumber;
    }

    public String getOnus() {
        return onus;
    }

    public void setOnus(String onus) {
        this.onus = onus;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getCheckNumber() {
        return checkNumber;
    }

    public void setCheckNumber(String checkNumber) {
        this.checkNumber = checkNumber;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public boolean isSpecial() {
        return special;
    }

    public void setSpecial(boolean special) {
        this.special = special;
    }

    @Override
    public String toString() {
        return "Micr{" +
                "rawMicr=" + rawMicr +
                ", parseError=" + parseError +
                ", statusMessage=" + statusMessage +
                ", containsUnrecognizedSymbols=" + containsUnrecognizedSymbols +
                ", checkType=" + checkType +
                ", auxOnus=" + auxOnus  +
                ", transitNumber=" + transitNumber+
                ", onus=" + onus +
                ", accountNumber=" + accountNumber  +
                ", checkNumber=" + checkNumber +
                ", amount=" + amount +
                ", special=" + special +
                '}';
    }
}
