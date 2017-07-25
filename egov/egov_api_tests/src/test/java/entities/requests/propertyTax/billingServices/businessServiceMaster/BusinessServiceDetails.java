package entities.requests.propertyTax.billingServices.businessServiceMaster;

import entities.requests.propertyTax.AuditDetails;

public class BusinessServiceDetails {

    private int id;
    private String tenantId;
    private String businessService;
    private String[] collectionModesNotAllowed;
    private String callBackApportionURL;
    private boolean callBackForApportioning;
    private boolean partPaymentAllowed;
    private AuditDetails auditDetails;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getBusinessService() {
        return businessService;
    }

    public void setBusinessService(String businessService) {
        this.businessService = businessService;
    }

    public String[] getCollectionModesNotAllowed() {
        return collectionModesNotAllowed;
    }

    public void setCollectionModesNotAllowed(String[] collectionModesNotAllowed) {
        this.collectionModesNotAllowed = collectionModesNotAllowed;
    }

    public String getCallBackApportionURL() {
        return callBackApportionURL;
    }

    public void setCallBackApportionURL(String callBackApportionURL) {
        this.callBackApportionURL = callBackApportionURL;
    }

    public boolean isCallBackForApportioning() {
        return callBackForApportioning;
    }

    public void setCallBackForApportioning(boolean callBackForApportioning) {
        this.callBackForApportioning = callBackForApportioning;
    }

    public boolean isPartPaymentAllowed() {
        return partPaymentAllowed;
    }

    public void setPartPaymentAllowed(boolean partPaymentAllowed) {
        this.partPaymentAllowed = partPaymentAllowed;
    }

    public AuditDetails getAuditDetails() {
        return auditDetails;
    }

    public void setAuditDetails(AuditDetails auditDetails) {
        this.auditDetails = auditDetails;
    }
}
