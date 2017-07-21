package entities.responses.propertyTax.billingServices.taxPeriodsMaster;

public class TaxPeriods {
    private long fromDate;
    private String financialYear;
    private String code;
    private String service;
    private long toDate;
    private String tenantId;
    private String periodCycle;
    private String id;
    private AuditDetail auditDetail;

    public long getFromDate() {
        return this.fromDate;
    }

    public void setFromDate(long fromDate) {
        this.fromDate = fromDate;
    }

    public String getFinancialYear() {
        return this.financialYear;
    }

    public void setFinancialYear(String financialYear) {
        this.financialYear = financialYear;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getService() {
        return this.service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public long getToDate() {
        return this.toDate;
    }

    public void setToDate(long toDate) {
        this.toDate = toDate;
    }

    public String getTenantId() {
        return this.tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getPeriodCycle() {
        return this.periodCycle;
    }

    public void setPeriodCycle(String periodCycle) {
        this.periodCycle = periodCycle;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public AuditDetail getAuditDetail() {
        return this.auditDetail;
    }

    public void setAuditDetail(AuditDetail auditDetail) {
        this.auditDetail = auditDetail;
    }
}
