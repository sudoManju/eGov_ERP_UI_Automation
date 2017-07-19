package entities.responses.propertyTax.billingServices.businessService;

public class BusinessServiceDetails {
    private String businessService;
    private String callBackApportionURL;
    private String[] collectionModesNotAllowed;
    private String tenantId;
    private boolean partPaymentAllowed;
    private String id;
    private boolean callBackForApportioning;
    private AuditDetail auditDetail;

    public String getBusinessService() {
        return this.businessService;
    }

    public void setBusinessService(String businessService) {
        this.businessService = businessService;
    }

    public String getCallBackApportionURL() {
        return this.callBackApportionURL;
    }

    public void setCallBackApportionURL(String callBackApportionURL) {
        this.callBackApportionURL = callBackApportionURL;
    }

    public String[] getCollectionModesNotAllowed() {
        return this.collectionModesNotAllowed;
    }

    public void setCollectionModesNotAllowed(String[] collectionModesNotAllowed) {
        this.collectionModesNotAllowed = collectionModesNotAllowed;
    }

    public String getTenantId() {
        return this.tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public boolean getPartPaymentAllowed() {
        return this.partPaymentAllowed;
    }

    public void setPartPaymentAllowed(boolean partPaymentAllowed) {
        this.partPaymentAllowed = partPaymentAllowed;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean getCallBackForApportioning() {
        return this.callBackForApportioning;
    }

    public void setCallBackForApportioning(boolean callBackForApportioning) {
        this.callBackForApportioning = callBackForApportioning;
    }

    public AuditDetail getAuditDetail() {
        return this.auditDetail;
    }

    public void setAuditDetail(AuditDetail auditDetail) {
        this.auditDetail = auditDetail;
    }
}
