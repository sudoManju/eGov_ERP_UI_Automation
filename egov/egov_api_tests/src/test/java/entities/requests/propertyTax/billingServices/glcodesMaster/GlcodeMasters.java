package entities.requests.propertyTax.billingServices.glcodesMaster;

import entities.requests.propertyTax.AuditDetails;

public class GlcodeMasters {

    private long fromDate;
    private String service;
    private String glCode;
    private long toDate;
    private AuditDetails auditDetails;
    private int id;
    private String taxHead;
    private String tenantId;

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

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

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTaxHead() {
        return this.taxHead;
    }

    public void setTaxHead(String taxHead) {
        this.taxHead = taxHead;
    }
}
