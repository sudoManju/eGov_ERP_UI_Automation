package builders.propertyTax;

import entities.requests.propertyTax.AuditDetails;

public class AuditDetailsBuilder {

    AuditDetails auditDetails = new AuditDetails();

    public AuditDetailsBuilder() {
        auditDetails.setCreatedBy("1");
        auditDetails.setLastModifiedBy("1");
    }

    public AuditDetails build() {
        return auditDetails;
    }
}
