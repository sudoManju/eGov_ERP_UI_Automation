package builders.wcms.documentTypeApplicationType.create;

import entities.requests.wcms.documentTypeApplicationType.ApplicationTypesData;
import entities.requests.wcms.documentTypeApplicationType.create.DocumentTypeApplicationType;

import static data.SearchParameterData.TENANT_DEFAULT;

public class DocumentTypeApplicationTypeBuilder {
    DocumentTypeApplicationType documentTypeApplicationType = new DocumentTypeApplicationType();

    public DocumentTypeApplicationTypeBuilder() {
        documentTypeApplicationType.setDocumentType("");
        documentTypeApplicationType.setApplicationType(new ApplicationTypesData().randomApplicationType());
        documentTypeApplicationType.setMandatory("true");
        documentTypeApplicationType.setTenantId(TENANT_DEFAULT);
        documentTypeApplicationType.setActive("true");
    }

    public DocumentTypeApplicationTypeBuilder withApplicationType(String applicationType) {
        documentTypeApplicationType.setApplicationType(applicationType);
        return this;
    }

    public DocumentTypeApplicationTypeBuilder withDocumentType(String documentTypeId) {
        documentTypeApplicationType.setDocumentType(documentTypeId);
        return this;
    }

    public DocumentTypeApplicationTypeBuilder withTenantId(String tenantId) {
        documentTypeApplicationType.setTenantId(tenantId);
        return this;
    }

    public DocumentTypeApplicationTypeBuilder withActive(String active) {
        documentTypeApplicationType.setActive(active);
        return this;
    }

    public DocumentTypeApplicationTypeBuilder withMandatory(String mandatory) {
        documentTypeApplicationType.setMandatory(mandatory);
        return this;
    }

    public DocumentTypeApplicationType build() {
        return documentTypeApplicationType;
    }
}
