package builders.propertyTax.masters;

import entities.requests.propertyTax.masters.AuditDetails;
import entities.requests.propertyTax.masters.UsageMasters;

public class UsageMastersBuilder {

    UsageMasters usageMasters = new UsageMasters();

    AuditDetails auditDetails = new AuditDetailsBuilder().build();

    public UsageMastersBuilder(){
        usageMasters.setId(0);
        usageMasters.setName("Residential");
        usageMasters.setCode("123");
        usageMasters.setNameLocal("Residential");
        usageMasters.setDescription("Testing the Master Api of usage");
        usageMasters.setActive(true);
        usageMasters.setIsResidential(true);
        usageMasters.setOrderNumber(0);
        usageMasters.setTenantId("ap.kurnool");
        usageMasters.setAuditDetails(auditDetails);
    }

    public UsageMasters build(){
        return usageMasters;
    }
}
