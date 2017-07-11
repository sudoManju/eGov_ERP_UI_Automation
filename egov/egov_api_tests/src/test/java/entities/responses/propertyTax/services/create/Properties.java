package entities.responses.propertyTax.services.create;

public class Properties {
    private Boundary boundary;
    private Address address;
    private String assessmentDate;
    private String channel;
    private String oldUpicNumber;
    private boolean isAuthorised;
    private boolean active;
    private Owners[] owners;
    private String creationReason;
    private VacantLand vacantLand;
    private String vltUpicNumber;
    private PropertyDetail propertyDetail;
    private String gisRefNo;
    private String occupancyDate;
    private AuditDetails auditDetails;
    private String tenantId;
    private boolean isUnderWorkflow;
    private Object id;
    private Object upicNumber;
    private Object demands;

    public Boundary getBoundary() {
        return this.boundary;
    }

    public void setBoundary(Boundary boundary) {
        this.boundary = boundary;
    }

    public Address getAddress() {
        return this.address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getAssessmentDate() {
        return this.assessmentDate;
    }

    public void setAssessmentDate(String assessmentDate) {
        this.assessmentDate = assessmentDate;
    }

    public String getChannel() {
        return this.channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getOldUpicNumber() {
        return this.oldUpicNumber;
    }

    public void setOldUpicNumber(String oldUpicNumber) {
        this.oldUpicNumber = oldUpicNumber;
    }

    public boolean getIsAuthorised() {
        return this.isAuthorised;
    }

    public void setIsAuthorised(boolean isAuthorised) {
        this.isAuthorised = isAuthorised;
    }

    public boolean getActive() {
        return this.active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Owners[] getOwners() {
        return this.owners;
    }

    public void setOwners(Owners[] owners) {
        this.owners = owners;
    }

    public String getCreationReason() {
        return this.creationReason;
    }

    public void setCreationReason(String creationReason) {
        this.creationReason = creationReason;
    }

    public VacantLand getVacantLand() {
        return this.vacantLand;
    }

    public void setVacantLand(VacantLand vacantLand) {
        this.vacantLand = vacantLand;
    }

    public String getVltUpicNumber() {
        return this.vltUpicNumber;
    }

    public void setVltUpicNumber(String vltUpicNumber) {
        this.vltUpicNumber = vltUpicNumber;
    }

    public PropertyDetail getPropertyDetail() {
        return this.propertyDetail;
    }

    public void setPropertyDetail(PropertyDetail propertyDetail) {
        this.propertyDetail = propertyDetail;
    }

    public String getGisRefNo() {
        return this.gisRefNo;
    }

    public void setGisRefNo(String gisRefNo) {
        this.gisRefNo = gisRefNo;
    }

    public String getOccupancyDate() {
        return this.occupancyDate;
    }

    public void setOccupancyDate(String occupancyDate) {
        this.occupancyDate = occupancyDate;
    }

    public AuditDetails getAuditDetails() {
        return this.auditDetails;
    }

    public void setAuditDetails(AuditDetails auditDetails) {
        this.auditDetails = auditDetails;
    }

    public String getTenantId() {
        return this.tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public boolean getIsUnderWorkflow() {
        return this.isUnderWorkflow;
    }

    public void setIsUnderWorkflow(boolean isUnderWorkflow) {
        this.isUnderWorkflow = isUnderWorkflow;
    }

    public Object getId() {
        return this.id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public Object getUpicNumber() {
        return this.upicNumber;
    }

    public void setUpicNumber(Object upicNumber) {
        this.upicNumber = upicNumber;
    }

    public Object getDemands() {
        return this.demands;
    }

    public void setDemands(Object demands) {
        this.demands = demands;
    }
}
