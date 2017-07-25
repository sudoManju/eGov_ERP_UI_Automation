package entities.requests.propertyTax.services.create;

import entities.requests.propertyTax.AuditDetails;

public class DocumentType
{
    private String application;

    private String name;

    private AuditDetails auditDetails;

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AuditDetails getAuditDetails() {
        return auditDetails;
    }

    public void setAuditDetails(AuditDetails auditDetails) {
        this.auditDetails = auditDetails;
    }
}
