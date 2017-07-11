package builders.propertyTax.services.create;

import entities.requests.propertyTax.services.create.AuditDetails;
import entities.requests.propertyTax.services.create.DocumentType;
import entities.requests.propertyTax.services.create.Documents;

public class DocumentBuilder {

    Documents documents = new Documents();

    DocumentType documentType = new DocumentTypeBuilder().build();

    AuditDetails auditDetails = new AuditDetailsBuilder().build();

    public DocumentBuilder(){
        documents.setDocumentType(documentType);
        documents.setFileStore("Testing");
        documents.setAuditDetails(auditDetails);
    }

    public Documents build() {
        return documents;
    }
}
