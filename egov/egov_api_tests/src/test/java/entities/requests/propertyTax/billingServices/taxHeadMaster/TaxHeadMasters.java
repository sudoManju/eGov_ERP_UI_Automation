package entities.requests.propertyTax.billingServices.taxHeadMaster;

import entities.requests.propertyTax.AuditDetails;

public class TaxHeadMasters {
    private String validTill;
    private String code;
    private String validFrom;
    private boolean isActualDemand;
    private int oder;
    private Glcodes[] glcodes;
    private String service;
    private AuditDetails auditDetails;
    private String tenantId;
    private String name;
    private int id;
    private boolean isDebit;
    private String category;

    public String getValidTill() {
        return this.validTill;
    }

    public void setValidTill(String validTill) {
        this.validTill = validTill;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValidFrom() {
        return this.validFrom;
    }

    public void setValidFrom(String validFrom) {
        this.validFrom = validFrom;
    }

    public boolean getIsActualDemand() {
        return this.isActualDemand;
    }

    public void setIsActualDemand(boolean isActualDemand) {
        this.isActualDemand = isActualDemand;
    }

    public int getOder() {
        return this.oder;
    }

    public void setOder(int oder) {
        this.oder = oder;
    }

    public Glcodes[] getGlcodes() {
        return this.glcodes;
    }

    public void setGlcodes(Glcodes[] glcodes) {
        this.glcodes = glcodes;
    }

    public String getService() {
        return this.service;
    }

    public void setService(String service) {
        this.service = service;
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

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean getIsDebit() {
        return this.isDebit;
    }

    public void setIsDebit(boolean isDebit) {
        this.isDebit = isDebit;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
