package com.alltrustnetworks.smartcheck.models;

import android.util.Log;

import com.alltrustnetworks.smartcheck.util.Util;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.squareup.moshi.Json;

import org.parceler.Parcel;

@Parcel
public class Transaction {

    public String id;
    public @Json(name = "printable_id") String printableId;
    public @Json(name = "check") FullCheck check;
    public @Json(name = "customer_id") String customerId;
    public @Json(name = "customer_name") String customerName;
    public @Json(name = "location_id") String locationId;
    public @Json(name = "location_name") String locationName;
    public String recommendation;
    public String result;
    public @Json(name = "current_status") String currentStatus;
    public @Json(name = "fee_amount") int feeAmount;
    public @Json(name = "merchant_user_name") String merchantUserName;
    public @Json(name = "created") String created;
    public @Json(name = "review_information") ReviewInformation reviewInformation;
    public @Json(name = "check_research_recommendation") String checkResearchRecommendation;
    public @Json(name = "decisioning_result") DecisioningResult decisioningResult;
    public boolean deposited;
    public @Json(name = "deposit_event_id") String depositEventId;
    public @Json(name = "allow_duplicates") boolean allowDuplicates;
    public @Json(name = "client_data") String clientData;
    public @Json(name = "check_date") String checkDate;
    private static final String HOLD = "preapproved";
    private static final String DECLINED = "declined";
    private static final String APPROVED = "approved";
    private static final String REVIEW = "review";


    public static final Map<String, String> RESULTS;

    static {
        RESULTS = new HashMap<String, String>();
        RESULTS.put("accept", HOLD);
        RESULTS.put("decline", DECLINED);
        RESULTS.put("review", REVIEW);
        RESULTS.put("approved", HOLD);
        RESULTS.put("rejected", DECLINED);
    }

    public Transaction() {
    }


    public Transaction(Check check, Customer customer, DecisioningResult decisioningResult, String locationId, String businessId, String feeAmount) {
        this.setId("");

        String recommendation = "decline";

        if (decisioningResult.getRecommendation() != null) {
            recommendation = decisioningResult.getRecommendation().toLowerCase();
        }

        Log.i("SmartCheck", "recommendation" + recommendation);
        String result = RESULTS.get(recommendation);
        Log.i("SmartCheck", "result" + result);

        if (result == null) {
            result = DECLINED;
        }

        this.setLocationId(locationId);
        this.setCustomerId(customer.getId());
        this.setRecommendation(recommendation);
        this.setCreated(Util.getDateStr(new Date()));
        this.setResult(result);
        FullCheck fullCheck = new FullCheck(check);
        this.setCheck(fullCheck);
        this.setAllowDuplicates(false);
        this.setDecisioningResult(decisioningResult);
        this.setFeeAmount(Integer.parseInt(feeAmount));
        ReviewInformation reviewInformation = new ReviewInformation();
        reviewInformation.setReviewCenterBusinessId(businessId);
        this.setReviewInformation(reviewInformation);
    }

    public Transaction(String id, String printableId, FullCheck check, String customerId, String customerName, String locationId, String locationName, String recommendation, String result, String currentStatus, String feeAmount, String merchantUserName, String created, ReviewInformation reviewInformation, String checkResearchRecommendation, DecisioningResult decisioningResult, boolean deposited, String depositEventId, boolean allowDuplicates, String clientData) {
        this.id = id;
        this.printableId = printableId;
        this.check = check;
        this.customerId = customerId;
        this.customerName = customerName;
        this.locationId = locationId;
        this.locationName = locationName;
        this.recommendation = recommendation;
        this.result = result;
        this.currentStatus = currentStatus;
        this.feeAmount = Integer.parseInt(feeAmount);
        this.merchantUserName = merchantUserName;
        this.created = created;
        this.reviewInformation = reviewInformation;
        this.checkResearchRecommendation = checkResearchRecommendation;
        this.decisioningResult = decisioningResult;
        this.deposited = deposited;
        this.depositEventId = depositEventId;
        this.allowDuplicates = allowDuplicates;
        this.clientData = clientData;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrintableId() {
        return printableId;
    }

    public void setPrintableId(String printableId) {
        this.printableId = printableId;
    }

    public FullCheck getCheck() {
        return check;
    }

    public void setCheck(FullCheck check) {
        this.check = check;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(String currentStatus) {
        this.currentStatus = currentStatus;
    }

    public int getFeeAmount() {
        return feeAmount;
    }

    public void setFeeAmount(int feeAmount) {
        this.feeAmount = feeAmount;
    }

    public String getMerchantUserName() {
        return merchantUserName;
    }

    public void setMerchantUserName(String merchantUserName) {
        this.merchantUserName = merchantUserName;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public ReviewInformation getReviewInformation() {
        return reviewInformation;
    }

    public void setReviewInformation(ReviewInformation reviewInformation) {
        this.reviewInformation = reviewInformation;
    }

    public String getCheckResearchRecommendation() {
        return checkResearchRecommendation;
    }

    public void setCheckResearchRecommendation(String checkResearchRecommendation) {
        this.checkResearchRecommendation = checkResearchRecommendation;
    }

    public DecisioningResult getDecisioningResult() {
        return decisioningResult;
    }

    public void setDecisioningResult(DecisioningResult decisioningResult) {
        this.decisioningResult = decisioningResult;
    }

    public boolean isDeposited() {
        return deposited;
    }

    public void setDeposited(boolean deposited) {
        this.deposited = deposited;
    }

    public String getDepositEventId() {
        return depositEventId;
    }

    public void setDepositEventId(String depositEventId) {
        this.depositEventId = depositEventId;
    }

    public boolean isAllowDuplicates() {
        return allowDuplicates;
    }

    public void setAllowDuplicates(boolean allowDuplicates) {
        this.allowDuplicates = allowDuplicates;
    }

    public String getClientData() {
        return clientData;
    }

    public void setClientData(String clientData) {
        this.clientData = clientData;
    }

    public boolean isApproved() {
        return HOLD.equals(result);
    }

    public boolean isDeclined() {
        return DECLINED.equals(result);
    }

    public boolean isAccepted() {
        return HOLD.equals(result);
    }

    public boolean isPendingReview() {
        return REVIEW.equals(result);
    }


    @Override
    public String toString() {
        String transaction = "Transaction{" +
            "id='" + id + '\'' +
            ", printableId='" + printableId + '\'';

        if (check != null) {
            transaction += ", check=" + check.toString();
        }

        transaction +=
            ", customerId='" + customerId + '\'' +
            ", customerName='" + customerName + '\'' +
            ", locationId='" + locationId + '\'' +
            ", locationName='" + locationName + '\'' +
            ", recommendation='" + recommendation + '\'' +
            ", result='" + result + '\'' +
            ", currentStatus='" + currentStatus + '\'' +
            ", feeAmount='" + feeAmount + '\'' +
            ", merchantUserName='" + merchantUserName + '\'' +
            ", created='" + created + '\'' +
            ", reviewInformation=" + reviewInformation +
            ", checkResearchRecommendation='" + checkResearchRecommendation + '\'' +
            ", decisioningResult=" + decisioningResult +
            ", deposited=" + deposited +
            ", depositEventId='" + depositEventId + '\'' +
            ", allowDuplicates=" + allowDuplicates +
            ", clientData='" + clientData + '\'' +
            '}';

        return transaction;
    }
}
