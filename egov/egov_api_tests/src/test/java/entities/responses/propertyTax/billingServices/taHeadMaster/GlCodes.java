package entities.responses.propertyTax.billingServices.taHeadMaster;

public class GlCodes {
    private long fromDate;
    private String service;
    private String glCode;
    private long toDate;
    private AuditDetails auditDetails;
    private Object tenantId;
    private String id;
    private String taxHead;

    public long getFromDate() {
        return this.fromDate;
    }

    public void setFromDate(long fromDate) {
        this.fromDate = fromDate;
    }

    public String getService() {
        return this.service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getGlCode() {
        return this.glCode;
    }

    public void setGlCode(String glCode) {
        this.glCode = glCode;
    }

    public long getToDate() {
        return this.toDate;
    }

    public void setToDate(long toDate) {
        this.toDate = toDate;
    }

    public AuditDetails getAuditDetails() {
        return this.auditDetails;
    }

    public void setAuditDetails(AuditDetails auditDetails) {
        this.auditDetails = auditDetails;
    }

    public Object getTenantId() {
        return this.tenantId;
    }

    public void setTenantId(Object tenantId) {
        this.tenantId = tenantId;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTaxHead() {
        return this.taxHead;
    }

    public void setTaxHead(String taxHead) {
        this.taxHead = taxHead;
    }
}
