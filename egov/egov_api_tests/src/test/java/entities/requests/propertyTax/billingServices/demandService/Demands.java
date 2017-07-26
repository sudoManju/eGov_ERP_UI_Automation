package entities.requests.propertyTax.billingServices.demandService;

import entities.requests.propertyTax.AuditDetails;

public class Demands {
    private Owner owner;
    private String taxPeriodFrom;
    private DemandDetails[] demandDetails;
    private String consumerType;
    private String businessService;
    private int minimumAmountPayable;
    private String taxPeriodTo;
    private AuditDetails auditDetails;
    private String tenantId;
    private String consumerCode;
    private int id;

    public Owner getOwner() {
        return this.owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public String getTaxPeriodFrom() {
        return this.taxPeriodFrom;
    }

    public void setTaxPeriodFrom(String taxPeriodFrom) {
        this.taxPeriodFrom = taxPeriodFrom;
    }

    public DemandDetails[] getDemandDetails() {
        return this.demandDetails;
    }

    public void setDemandDetails(DemandDetails[] demandDetails) {
        this.demandDetails = demandDetails;
    }

    public String getConsumerType() {
        return this.consumerType;
    }

    public void setConsumerType(String consumerType) {
        this.consumerType = consumerType;
    }

    public String getBusinessService() {
        return this.businessService;
    }

    public void setBusinessService(String businessService) {
        this.businessService = businessService;
    }

    public int getMinimumAmountPayable() {
        return this.minimumAmountPayable;
    }

    public void setMinimumAmountPayable(int minimumAmountPayable) {
        this.minimumAmountPayable = minimumAmountPayable;
    }

    public String getTaxPeriodTo() {
        return this.taxPeriodTo;
    }

    public void setTaxPeriodTo(String taxPeriodTo) {
        this.taxPeriodTo = taxPeriodTo;
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

    public String getConsumerCode() {
        return this.consumerCode;
    }

    public void setConsumerCode(String consumerCode) {
        this.consumerCode = consumerCode;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
