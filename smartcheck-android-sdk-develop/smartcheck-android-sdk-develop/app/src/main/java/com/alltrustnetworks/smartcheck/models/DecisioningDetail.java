package com.alltrustnetworks.smartcheck.models;


import com.squareup.moshi.Json;

import org.parceler.Parcel;

@Parcel
public class DecisioningDetail {
    public @Json(name = "rule_id") String ruleId;
    public @Json(name = "rule_name") String ruleName;
    public boolean passed;
    public @Json(name = "rule_setting_description") String ruleSettingDescription;
    public @Json(name = "rule_actual_value") String ruleActualValue;
    public @Json(name = "rule_actual_value_unit_hint") String ruleActualValueUnitHint;
    public @Json(name = "rule_result") String ruleResult;
    public @Json(name = "scoring_detail") ScoringDetail scoringDetail;
    public boolean fatal;
    public @Json(name = "auto_result") String autoResult;

    public DecisioningDetail() {
    }

    public DecisioningDetail(String ruleId, String ruleName, boolean passed, String ruleSettingDescription, String ruleActualValue, String ruleActualValueUnitHint, String ruleResult, ScoringDetail scoringDetail, boolean fatal, String autoResult) {
        this.ruleId = ruleId;
        this.ruleName = ruleName;
        this.passed = passed;
        this.ruleSettingDescription = ruleSettingDescription;
        this.ruleActualValue = ruleActualValue;
        this.ruleActualValueUnitHint = ruleActualValueUnitHint;
        this.ruleResult = ruleResult;
        this.scoringDetail = scoringDetail;
        this.fatal = fatal;
        this.autoResult = autoResult;
    }

    public String getRuleId() {
        return ruleId;
    }

    public void setRuleId(String ruleId) {
        this.ruleId = ruleId;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public boolean isPassed() {
        return passed;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
    }

    public String getRuleSettingDescription() {
        return ruleSettingDescription;
    }

    public void setRuleSettingDescription(String ruleSettingDescription) {
        this.ruleSettingDescription = ruleSettingDescription;
    }

    public String getRuleActualValue() {
        return ruleActualValue;
    }

    public void setRuleActualValue(String ruleActualValue) {
        this.ruleActualValue = ruleActualValue;
    }

    public String getRuleActualValueUnitHint() {
        return ruleActualValueUnitHint;
    }

    public void setRuleActualValueUnitHint(String ruleActualValueUnitHint) {
        this.ruleActualValueUnitHint = ruleActualValueUnitHint;
    }

    public String getRuleResult() {
        return ruleResult;
    }

    public void setRuleResult(String ruleResult) {
        this.ruleResult = ruleResult;
    }

    public ScoringDetail getScoringDetail() {
        return scoringDetail;
    }

    public void setScoringDetail(ScoringDetail scoringDetail) {
        this.scoringDetail = scoringDetail;
    }

    public boolean isFatal() {
        return fatal;
    }

    public void setFatal(boolean fatal) {
        this.fatal = fatal;
    }

    public String getAutoResult() {
        return autoResult;
    }

    public void setAutoResult(String autoResult) {
        this.autoResult = autoResult;
    }

    @Override
    public String toString() {
        return "DecisioningDetail{" +
                "ruleId='" + ruleId + '\'' +
                ", ruleName='" + ruleName + '\'' +
                ", passed=" + passed +
                ", ruleSettingDescription='" + ruleSettingDescription + '\'' +
                ", ruleActualValue='" + ruleActualValue + '\'' +
                ", ruleActualValueUnitHint='" + ruleActualValueUnitHint + '\'' +
                ", ruleResult='" + ruleResult + '\'' +
                ", scoringDetail=" + scoringDetail +
                ", fatal=" + fatal +
                ", autoResult='" + autoResult + '\'' +
                '}';
    }
}