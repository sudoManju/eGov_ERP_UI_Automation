package builders.propertyTax.services.create;

import entities.requests.propertyTax.services.create.AuditDetails;
import entities.requests.propertyTax.services.create.Floors;
import entities.requests.propertyTax.services.create.Units;

public class FloorsBuilder {

    Floors floors = new Floors();

    AuditDetails auditDetails = new AuditDetailsBuilder().build();

    Units[] units1 = new Units[1];

    Units[] units2 = new Units[1];

    Units units = new UnitsBuilder().build();

    Units unitses = new UnitsBuilder().withUnits(units1).build();

    public FloorsBuilder(){
        floors.setFloorNo("fn3");
        floors.setAuditDetails(auditDetails);
        units1[0] = units;
        units2[0] = unitses;
        floors.setUnits(units2);
    }

    public Floors build(){
        return floors;
    }
}
