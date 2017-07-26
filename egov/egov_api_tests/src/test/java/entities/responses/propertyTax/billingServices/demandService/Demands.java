package entities.responses.propertyTax.billingServices.demandService;

public class Demands {
    private Owner owner;
    private int taxPeriodFrom;
    private DemandDetails[] demandDetails;
    private String consumerType;
    private String businessService;
    private int minimumAmountPayable;
    private int taxPeriodTo;
    private String tenantId;
    private String consumerCode;
    private String id;
    private AuditDetail auditDetail;

    public Owner getOwner() {
        return this.owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public int getTaxPeriodFrom() {
        return this.taxPeriodFrom;
    }

    public void setTaxPeriodFrom(int taxPeriodFrom) {
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

    public int getTaxPeriodTo() {
        return this.taxPeriodTo;
    }

    public void setTaxPeriodTo(int taxPeriodTo) {
        this.taxPeriodTo = taxPeriodTo;
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
