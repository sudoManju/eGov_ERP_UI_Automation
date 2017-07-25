package entities.requests.propertyTax.services.create;

import entities.requests.propertyTax.AuditDetails;

public class PropertyDetail
{
    private String floorType;

    private String reason;

    private String department;

    private String regdDocDate;

    private String apartment;

    private String exemptionReason;

    private String landOwner;

    private AuditDetails auditDetails;

    private WorkFlowDetails workFlowDetails;

    private Floors[] floors;

    private Boolean isExempted;

    private Boolean isVerified;

    private int totalBuiltupArea;

    private Documents[] documents;

    private String status;

    private String woodType;

    private String roofType;

    private int siteLength;

    private Boolean isSuperStructure;

    private int siteBreadth;

    private int undividedShare;

    private String category;

    private String propertyType;

    private String source;

    private String verificationDate;

    private String stateId;

    private String usage;

    private String regdDocNo;

    private int noOfFloors;

    private int sitalArea;

    private String wallType;

    public String getFloorType() {
        return floorType;
    }

    public void setFloorType(String floorType) {
        this.floorType = floorType;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getRegdDocDate() {
        return regdDocDate;
    }

    public void setRegdDocDate(String regdDocDate) {
        this.regdDocDate = regdDocDate;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    public String getExemptionReason() {
        return exemptionReason;
    }

    public void setExemptionReason(String exemptionReason) {
        this.exemptionReason = exemptionReason;
    }

    public String getLandOwner() {
        return landOwner;
    }

    public void setLandOwner(String landOwner) {
        this.landOwner = landOwner;
    }

    public AuditDetails getAuditDetails() {
        return auditDetails;
    }

    public void setAuditDetails(AuditDetails auditDetails) {
        this.auditDetails = auditDetails;
    }

    public WorkFlowDetails getWorkFlowDetails() {
        return workFlowDetails;
    }

    public void setWorkFlowDetails(WorkFlowDetails workFlowDetails) {
        this.workFlowDetails = workFlowDetails;
    }

    public Floors[] getFloors() {
        return floors;
    }

    public void setFloors(Floors[] floors) {
        this.floors = floors;
    }

    public Boolean getIsExempted() {
        return isExempted;
    }

    public void setIsExempted(Boolean exempted) {
        isExempted = exempted;
    }

    public Boolean getIsVerified() {
        return isVerified;
    }

    public void setIsVerified(Boolean verified) {
        isVerified = verified;
    }

    public int getTotalBuiltupArea() {
        return totalBuiltupArea;
    }

    public void setTotalBuiltupArea(int totalBuiltupArea) {
        this.totalBuiltupArea = totalBuiltupArea;
    }

    public Documents[] getDocuments() {
        return documents;
    }

    public void setDocuments(Documents[] documents) {
        this.documents = documents;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getWoodType() {
        return woodType;
    }

    public void setWoodType(String woodType) {
        this.woodType = woodType;
    }

    public String getRoofType() {
        return roofType;
    }

    public void setRoofType(String roofType) {
        this.roofType = roofType;
    }

    public int getSiteLength() {
        return siteLength;
    }

    public void setSiteLength(int siteLength) {
        this.siteLength = siteLength;
    }

    public Boolean getIsSuperStructure() {
        return isSuperStructure;
    }

    public void setIsSuperStructure(Boolean superStructure) {
        isSuperStructure = superStructure;
    }

    public int getSiteBreadth() {
        return siteBreadth;
    }

    public void setSiteBreadth(int siteBreadth) {
        this.siteBreadth = siteBreadth;
    }

    public int getUndividedShare() {
        return undividedShare;
    }

    public void setUndividedShare(int undividedShare) {
        this.undividedShare = undividedShare;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getVerificationDate() {
        return verificationDate;
    }

    public void setVerificationDate(String verificationDate) {
        this.verificationDate = verificationDate;
    }

    public String getStateId() {
        return stateId;
    }

    public void setStateId(String stateId) {
        this.stateId = stateId;
    }

    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    public String getRegdDocNo() {
        return regdDocNo;
    }

    public void setRegdDocNo(String regdDocNo) {
        this.regdDocNo = regdDocNo;
    }

    public int getNoOfFloors() {
        return noOfFloors;
    }

    public void setNoOfFloors(int noOfFloors) {
        this.noOfFloors = noOfFloors;
    }

    public int getSitalArea() {
        return sitalArea;
    }

    public void setSitalArea(int sitalArea) {
        this.sitalArea = sitalArea;
    }

    public String getWallType() {
        return wallType;
    }

    public void setWallType(String wallType) {
        this.wallType = wallType;
    }
}
