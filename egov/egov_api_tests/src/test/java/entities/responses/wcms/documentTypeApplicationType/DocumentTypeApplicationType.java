package entities.responses.wcms.documentTypeApplicationType;

public class DocumentTypeApplicationType {
    private String applicationType;
    private Object documentType;
    private Object auditDetails;
    private int documentTypeId;
    private String tenantId;
    private boolean active;
    private Object id;
    private boolean mandatory;

    public String getApplicationType() {
        return this.applicationType;
    }

    public void setApplicationType(String applicationType) {
        this.applicationType = applicationType;
    }

    public Object getDocumentType() {
        return this.documentType;
    }

    public void setDocumentType(Object documentType) {
        this.documentType = documentType;
    }

    public Object getAuditDetails() {
        return this.auditDetails;
    }

    public void setAuditDetails(Object auditDetails) {
        this.auditDetails = auditDetails;
    }

    public int getDocumentTypeId() {
        return this.documentTypeId;
    }

    public void setDocumentTypeId(int documentTypeId) {
        this.documentTypeId = documentTypeId;
    }

    public String getTenantId() {
        return this.tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public boolean getActive() {
        return this.active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Object getId() {
        return this.id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public boolean getMandatory() {
        return this.mandatory;
    }

    public void setMandatory(boolean mandatory) {
        this.mandatory = mandatory;
    }
}
