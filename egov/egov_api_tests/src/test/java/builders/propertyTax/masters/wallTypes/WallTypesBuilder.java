package builders.propertyTax.masters.wallTypes;

import builders.propertyTax.masters.AuditDetailsBuilder;
import entities.requests.propertyTax.masters.AuditDetails;
import entities.requests.propertyTax.masters.wallType.WallTypes;

public class WallTypesBuilder {

    WallTypes wallTypes = new WallTypes();

    AuditDetails auditDetails = new AuditDetailsBuilder().build();

    public WallTypesBuilder(){
        wallTypes.setId(0);
        wallTypes.setTenantId("ap.kurnool");
        wallTypes.setDescription("Testing the wall type");
        wallTypes.setAuditDetails(auditDetails);
    }

    public WallTypesBuilder withName(String name){
        wallTypes.setName(name);
        return this;
    }

    public WallTypesBuilder withCode(String code){
        wallTypes.setCode(code);
        return this;
    }

    public WallTypesBuilder withNameLocal(String nameLocal){
        wallTypes.setNameLocal(nameLocal);
        return this;
    }

    public WallTypes build(){
        return wallTypes;
    }
}
