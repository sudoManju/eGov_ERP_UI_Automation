package entities.requests.propertyTax.services.create;

import entities.requests.propertyTax.AuditDetails;

public class Properties
{
    private String tenantId;

    private VacantLand vacantLand;

    private Boolean isAuthorised;

    private String oldUpicNumber;

    private String occupancyDate;

    private AuditDetails auditDetails;

    private Boundary boundary;

    private Boolean isUnderWorkflow;

    private String vltUpicNumber;

    private String creationReason;

    private Address address;

    private String assessmentDate;

    private PropertyDetail propertyDetail;

    private Boolean active;

    private String channel;

    private Owners[] owners;

    private String gisRefNo;

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public VacantLand getVacantLand() {
        return vacantLand;
    }

    public void setVacantLand(VacantLand vacantLand) {
        this.vacantLand = vacantLand;
    }

    public Boolean getIsAuthorised() {
        return isAuthorised;
    }

    public void setIsAuthorised(Boolean isAuthorised) {
        this.isAuthorised = isAuthorised;
    }

    public String getOldUpicNumber() {
        return oldUpicNumber;
    }

    public void setOldUpicNumber(String oldUpicNumber) {
        this.oldUpicNumber = oldUpicNumber;
    }

    public String getOccupancyDate() {
        return occupancyDate;
    }

    public void setOccupancyDate(String occupancyDate) {
        this.occupancyDate = occupancyDate;
    }

    public AuditDetails getAuditDetails() {
        return auditDetails;
    }

    public void setAuditDetails(AuditDetails auditDetails) {
        this.auditDetails = auditDetails;
    }

    public Boundary getBoundary() {
        return boundary;
    }

    public void setBoundary(Boundary boundary) {
        this.boundary = boundary;
    }

    public Boolean getIsUnderWorkflow() {
        return isUnderWorkflow;
    }

    public void setIsUnderWorkflow(Boolean isUnderWorkflow) {
        this.isUnderWorkflow = isUnderWorkflow;
    }

    public String getVltUpicNumber() {
        return vltUpicNumber;
    }

    public void setVltUpicNumber(String vltUpicNumber) {
        this.vltUpicNumber = vltUpicNumber;
    }

    public String getCreationReason() {
        return creationReason;
    }

    public void setCreationReason(String creationReason) {
        this.creationReason = creationReason;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getAssessmentDate() {
        return assessmentDate;
    }

    public void setAssessmentDate(String assessmentDate) {
        this.assessmentDate = assessmentDate;
    }

    public PropertyDetail getPropertyDetail() {
        return propertyDetail;
    }

    public void setPropertyDetail(PropertyDetail propertyDetail) {
        this.propertyDetail = propertyDetail;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public Owners[] getOwners() {
        return owners;
    }

    public void setOwners(Owners[] owners) {
        this.owners = owners;
    }

    public String getGisRefNo() {
        return gisRefNo;
    }

    public void setGisRefNo(String gisRefNo) {
        this.gisRefNo = gisRefNo;
    }
}
