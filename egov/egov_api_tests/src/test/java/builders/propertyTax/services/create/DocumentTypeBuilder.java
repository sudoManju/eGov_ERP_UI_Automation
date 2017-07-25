package builders.propertyTax.services.create;

import builders.propertyTax.AuditDetailsBuilder;
import entities.requests.propertyTax.AuditDetails;
import entities.requests.propertyTax.services.create.DocumentType;

public class DocumentTypeBuilder {

    DocumentType documentType = new DocumentType();

    AuditDetails auditDetails = new AuditDetailsBuilder().build();

    public DocumentTypeBuilder(){
        documentType.setName("Testing the application");
        documentType.setApplication("Create");
        documentType.setAuditDetails(auditDetails);
    }

    public DocumentType build(){
        return documentType;
    }
}
