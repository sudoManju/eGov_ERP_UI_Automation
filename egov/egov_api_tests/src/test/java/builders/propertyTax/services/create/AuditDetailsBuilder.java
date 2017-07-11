package builders.propertyTax.services.create;

import entities.requests.propertyTax.services.create.AuditDetails;

public class AuditDetailsBuilder {

    AuditDetails auditDetails = new AuditDetails();

    public AuditDetailsBuilder(){
        auditDetails.setCreatedBy("egovernments");
        auditDetails.setCreatedTime(0);
        auditDetails.setLastModifiedBy("egovernments");
        auditDetails.setLastModifiedTime(0);
    }

    public AuditDetails build(){
        return auditDetails;
    }
}
