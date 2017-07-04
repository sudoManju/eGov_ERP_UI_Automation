package utils;

import java.io.Serializable;

public class ScenarioContext implements Serializable {

    private String applicationNumber;

    private String assessmentNumber;

    private String dataScreenAssessmentNumber;

    private String actualMessage;

    private int isRemittance;

    private String user;

    private String referenceNumber;

    private String assetCategory;

    private String licenseCategory;
    
    private String UOM;
    
    private String licenseSubCategory;

    private String feeRateType;

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public String getAssetCategory() {
        return assetCategory;
    }

    public void setAssetCategory(String assetCategory) {
        this.assetCategory = assetCategory;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getIsRemittance() {
        return isRemittance;
    }

    public void setIsRemittance(int isRemittance) {
        this.isRemittance = isRemittance;
    }

    public String getActualMessage() {
        return actualMessage;
    }

    public void setActualMessage(String actualMessage) {
        this.actualMessage = actualMessage;
    }

    public String getAssessmentNumber() {
        return assessmentNumber;
    }

    public void setAssessmentNumber(String assessmentNumber) {
        this.assessmentNumber = assessmentNumber;
    }

    public String getApplicationNumber() {
        return applicationNumber;
    }

    public void setApplicationNumber(String applicationNumber) {
        this.applicationNumber = applicationNumber;
    }

    public String getDataScreenAssessmentNumber() {
        return dataScreenAssessmentNumber;
    }

    public void setDataScreenAssessmentNumber(String dataScreenAssessmentNumber) {
        this.dataScreenAssessmentNumber = dataScreenAssessmentNumber;
    }

    public void setLicenseCategory(String licenseCategory) {
        this.licenseCategory = licenseCategory;
    }

    public String getLicenseCategory() {
        return licenseCategory;
    }

    public void setUOM(String UOM) {
        this.UOM = UOM;
    }

    public String getUOM() {
        return UOM;
    }

    public void setLicenseSubCategory(String licenseSubCategory) {
        this.licenseSubCategory = licenseSubCategory;
    }

    public String getLicenseSubCategory() {
        return licenseSubCategory;
    }

    public void setFeeRateType(String feeRateType) {
        this.feeRateType = feeRateType;
    }
}

