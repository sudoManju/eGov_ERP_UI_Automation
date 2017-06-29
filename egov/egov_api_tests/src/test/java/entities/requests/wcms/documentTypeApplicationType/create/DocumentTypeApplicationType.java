package entities.requests.wcms.documentTypeApplicationType.create;

public class DocumentTypeApplicationType {
    private String applicationType;
    private int documentTypeId;
    private String tenantId;
    private String active;
    private String mandatory;

    public String getApplicationType() {
        return this.applicationType;
    }

    public void setApplicationType(String applicationType) {
        this.applicationType = applicationType;
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

    public String getActive() {
        return this.active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getMandatory() {
        return this.mandatory;
    }

    public void setMandatory(String mandatory) {
        this.mandatory = mandatory;
    }
}
