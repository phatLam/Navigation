package com.alltrustnetworks.smartcheck.models.misnap;

import com.squareup.moshi.Json;

import org.parceler.Parcel;

@Parcel
public class TransactionData {
    public @Json(name = "TransactionId")  int transactionId;
    public @Json(name = "UserName") String username;
    public @Json(name = "UserAccountNumber") String userAccountNumber;
    public @Json(name = "Status") String status;
    public @Json(name = "NeedsReview") String needsReview;
    public @Json(name = "SubmissionDate") String submissionDate;
    public @Json(name = "CheckNumber") String checkNumber;
    public @Json(name = "FirstName") String firstName;
    public @Json(name = "LastName") String lastName;
    public @Json(name = "AccountNickName") String accountNickName;
    public @Json(name = "UserRoutingNumber") String userRoutingNumber;
    public @Json(name = "OperatorActionDate") String operatorActionDate;
    public @Json(name = "ApprovedAmount") double approvedAmount;
    public @Json(name = "EnteredAmount") double enteredAmount;
    public @Json(name = "TransactionFee") double transactionFee;
    public @Json(name = "CheckAccountNumber") String checkAccountNumber;
    public @Json(name = "CheckRoutingNumber") String checkRoutingNumber;
    public @Json(name = "OperatorUserName") String pperatorUserName;
    public @Json(name = "OperatorMessage") String operatorMessage;
    public @Json(name = "BizRuleEngineMessage") String bizRuleEngineMessage;
    public @Json(name = "Micr") String micr;
    public @Json(name = "RecognizedAmount") double recognizedAmount;
    public @Json(name = "Latitude") double latitude;
    public @Json(name = "Longitude") double longitude;
    public @Json(name = "CheckClassificationType") String checkClassificationType;
    public @Json(name = "PartnerTransactionId") String partnerTransactionId;
    public @Json(name = "FundingType") String fundingType;
    public @Json(name = "DeferredTransactionFee") double deferredTransactionFee;
    public int amount;

    public TransactionData() {
    }

    public TransactionData(int transactionId, String username, String userAccountNumber, String status, String needsReview, String submissionDate, String checkNumber, String firstName, String lastName, String accountNickName, String userRoutingNumber, String operatorActionDate, double approvedAmount, double enteredAmount, double transactionFee, String checkAccountNumber, String checkRoutingNumber, String pperatorUserName, String operatorMessage, String bizRuleEngineMessage, String micr, double recognizedAmount, double latitude, double longitude, String checkClassificationType, String partnerTransactionId, String fundingType, double deferredTransactionFee) {
        this.transactionId = transactionId;
        this.username = username;
        this.userAccountNumber = userAccountNumber;
        this.status = status;
        this.needsReview = needsReview;
        this.submissionDate = submissionDate;
        this.checkNumber = checkNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.accountNickName = accountNickName;
        this.userRoutingNumber = userRoutingNumber;
        this.operatorActionDate = operatorActionDate;
        this.approvedAmount = approvedAmount;
        this.enteredAmount = enteredAmount;
        this.transactionFee = transactionFee;
        this.checkAccountNumber = checkAccountNumber;
        this.checkRoutingNumber = checkRoutingNumber;
        this.pperatorUserName = pperatorUserName;
        this.operatorMessage = operatorMessage;
        this.bizRuleEngineMessage = bizRuleEngineMessage;
        this.micr = micr;
        this.recognizedAmount = recognizedAmount;
        this.latitude = latitude;
        this.longitude = longitude;
        this.checkClassificationType = checkClassificationType;
        this.partnerTransactionId = partnerTransactionId;
        this.fundingType = fundingType;
        this.deferredTransactionFee = deferredTransactionFee;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserAccountNumber() {
        return userAccountNumber;
    }

    public void setUserAccountNumber(String userAccountNumber) {
        this.userAccountNumber = userAccountNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNeedsReview() {
        return needsReview;
    }

    public void setNeedsReview(String needsReview) {
        this.needsReview = needsReview;
    }

    public String getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(String submissionDate) {
        this.submissionDate = submissionDate;
    }

    public String getCheckNumber() {
        return checkNumber;
    }

    public void setCheckNumber(String checkNumber) {
        this.checkNumber = checkNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAccountNickName() {
        return accountNickName;
    }

    public void setAccountNickName(String accountNickName) {
        this.accountNickName = accountNickName;
    }

    public String getUserRoutingNumber() {
        return userRoutingNumber;
    }

    public void setUserRoutingNumber(String userRoutingNumber) {
        this.userRoutingNumber = userRoutingNumber;
    }

    public String getOperatorActionDate() {
        return operatorActionDate;
    }

    public void setOperatorActionDate(String operatorActionDate) {
        this.operatorActionDate = operatorActionDate;
    }

    public double getApprovedAmount() {
        return approvedAmount;
    }

    public void setApprovedAmount(double approvedAmount) {
        this.approvedAmount = approvedAmount;
    }

    public double getEnteredAmount() {
        return enteredAmount;
    }

    public void setEnteredAmount(double enteredAmount) {
        this.enteredAmount = enteredAmount;
    }

    public double getTransactionFee() {
        return transactionFee;
    }

    public void setTransactionFee(double transactionFee) {
        this.transactionFee = transactionFee;
    }

    public String getCheckAccountNumber() {
        return checkAccountNumber;
    }

    public void setCheckAccountNumber(String checkAccountNumber) {
        this.checkAccountNumber = checkAccountNumber;
    }

    public String getCheckRoutingNumber() {
        return checkRoutingNumber;
    }

    public void setCheckRoutingNumber(String checkRoutingNumber) {
        this.checkRoutingNumber = checkRoutingNumber;
    }

    public String getPperatorUserName() {
        return pperatorUserName;
    }

    public void setPperatorUserName(String pperatorUserName) {
        this.pperatorUserName = pperatorUserName;
    }

    public String getOperatorMessage() {
        return operatorMessage;
    }

    public void setOperatorMessage(String operatorMessage) {
        this.operatorMessage = operatorMessage;
    }

    public String getBizRuleEngineMessage() {
        return bizRuleEngineMessage;
    }

    public void setBizRuleEngineMessage(String bizRuleEngineMessage) {
        this.bizRuleEngineMessage = bizRuleEngineMessage;
    }

    public String getMicr() {
        return micr;
    }

    public void setMicr(String micr) {
        this.micr = micr;
    }

    public double getRecognizedAmount() {
        return recognizedAmount;
    }

    public void setRecognizedAmount(double recognizedAmount) {
        this.recognizedAmount = recognizedAmount;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getCheckClassificationType() {
        return checkClassificationType;
    }

    public void setCheckClassificationType(String checkClassificationType) {
        this.checkClassificationType = checkClassificationType;
    }

    public String getPartnerTransactionId() {
        return partnerTransactionId;
    }

    public void setPartnerTransactionId(String partnerTransactionId) {
        this.partnerTransactionId = partnerTransactionId;
    }

    public String getFundingType() {
        return fundingType;
    }

    public void setFundingType(String fundingType) {
        this.fundingType = fundingType;
    }

    public double getDeferredTransactionFee() {
        return deferredTransactionFee;
    }

    public void setDeferredTransactionFee(double deferredTransactionFee) {
        this.deferredTransactionFee = deferredTransactionFee;
    }

    public int getAmount() {
        Double amount = new Double(recognizedAmount);
        if (amount != null) {
            this.amount = (int) (amount * 100);
        }

        return this.amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "TransactionData{" +
                "transactionId=" + transactionId +
                ", username='" + username + '\'' +
                ", userAccountNumber='" + userAccountNumber + '\'' +
                ", status='" + status + '\'' +
                ", needsReview='" + needsReview + '\'' +
                ", submissionDate='" + submissionDate + '\'' +
                ", checkNumber='" + checkNumber + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", accountNickName='" + accountNickName + '\'' +
                ", userRoutingNumber='" + userRoutingNumber + '\'' +
                ", operatorActionDate='" + operatorActionDate + '\'' +
                ", approvedAmount=" + approvedAmount +
                ", enteredAmount=" + enteredAmount +
                ", transactionFee=" + transactionFee +
                ", checkAccountNumber='" + checkAccountNumber + '\'' +
                ", checkRoutingNumber='" + checkRoutingNumber + '\'' +
                ", pperatorUserName='" + pperatorUserName + '\'' +
                ", operatorMessage='" + operatorMessage + '\'' +
                ", bizRuleEngineMessage='" + bizRuleEngineMessage + '\'' +
                ", micr='" + micr + '\'' +
                ", recognizedAmount=" + recognizedAmount +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", checkClassificationType='" + checkClassificationType + '\'' +
                ", partnerTransactionId='" + partnerTransactionId + '\'' +
                ", fundingType='" + fundingType + '\'' +
                ", deferredTransactionFee=" + deferredTransactionFee +
                '}';
    }
}

