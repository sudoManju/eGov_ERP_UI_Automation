package entities.requests.propertyTax.services.create;

import entities.requests.propertyTax.AuditDetails;

public class Documents
{
    private DocumentType documentType;

    private String fileStore;

    private AuditDetails auditDetails;

    public DocumentType getDocumentType() {
        return documentType;
    }

    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
    }

    public String getFileStore() {
        return fileStore;
    }

    public void setFileStore(String fileStore) {
        this.fileStore = fileStore;
    }

    public AuditDetails getAuditDetails() {
        return auditDetails;
    }

    public void setAuditDetails(AuditDetails auditDetails) {
        this.auditDetails = auditDetails;
    }
}
