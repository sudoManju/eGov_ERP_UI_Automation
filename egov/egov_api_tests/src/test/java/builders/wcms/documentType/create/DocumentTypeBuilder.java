package builders.wcms.documentType.create;

import entities.requests.wcms.documentType.create.DocumentType;
import tests.BaseAPITest;

public class DocumentTypeBuilder {
    DocumentType documentType = new DocumentType();

    public DocumentTypeBuilder() {
        documentType.setActive(true);
        documentType.setId(0);
        documentType.setCode("");
        documentType.setName("DocumentType " + new BaseAPITest().get3DigitRandomInt());
        documentType.setDescription("Document Description");
        documentType.setTenantId("default");
    }

    public DocumentTypeBuilder withCode(String code) {
        documentType.setCode(code);
        return this;
    }

    public DocumentTypeBuilder withName(String name) {
        documentType.setName(name);
        return this;
    }

    public DocumentTypeBuilder withTenantId(String tenantId) {
        documentType.setTenantId(tenantId);
        return this;
    }

    public DocumentTypeBuilder withDescription(String description) {
        documentType.setDescription(description);
        return this;
    }

    public DocumentTypeBuilder withActive(boolean active) {
        documentType.setActive(active);
        return this;
    }

    public DocumentTypeBuilder withId(int id) {
        documentType.setId(id);
        return this;
    }

    public DocumentType build() {
        return documentType;
    }
}
