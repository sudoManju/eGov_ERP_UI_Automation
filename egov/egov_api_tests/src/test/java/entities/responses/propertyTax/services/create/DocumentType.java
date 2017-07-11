package entities.responses.propertyTax.services.create;

public class DocumentType {
    private Object application;
    private AuditDetails auditDetails;
    private String name;
    private Object id;

    public Object getApplication() {
        return this.application;
    }

    public void setApplication(Object application) {
        this.application = application;
    }

    public AuditDetails getAuditDetails() {
        return this.auditDetails;
    }

    public void setAuditDetails(AuditDetails auditDetails) {
        this.auditDetails = auditDetails;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getId() {
        return this.id;
    }

    public void setId(Object id) {
        this.id = id;
    }
}
