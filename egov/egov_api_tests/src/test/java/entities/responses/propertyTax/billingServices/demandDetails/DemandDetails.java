package entities.responses.propertyTax.billingServices.demandDetails;

public class DemandDetails {
    private String demandId;
    private Object taxHeadMasterCode;
    private int collectionAmount;
    private String tenantId;
    private String id;
    private int taxAmount;
    private AuditDetail auditDetail;

    public String getDemandId() {
        return this.demandId;
    }

    public void setDemandId(String demandId) {
        this.demandId = demandId;
    }

    public Object getTaxHeadMasterCode() {
        return this.taxHeadMasterCode;
    }

    public void setTaxHeadMasterCode(Object taxHeadMasterCode) {
        this.taxHeadMasterCode = taxHeadMasterCode;
    }

    public int getCollectionAmount() {
        return this.collectionAmount;
    }

    public void setCollectionAmount(int collectionAmount) {
        this.collectionAmount = collectionAmount;
    }

    public String getTenantId() {
        return this.tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getTaxAmount() {
        return this.taxAmount;
    }

    public void setTaxAmount(int taxAmount) {
        this.taxAmount = taxAmount;
    }

    public AuditDetail getAuditDetail() {
        return this.auditDetail;
    }

    public void setAuditDetail(AuditDetail auditDetail) {
        this.auditDetail = auditDetail;
    }
}
