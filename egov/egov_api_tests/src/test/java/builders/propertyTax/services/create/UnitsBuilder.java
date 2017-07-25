package builders.propertyTax.services.create;

import builders.propertyTax.AuditDetailsBuilder;
import entities.requests.propertyTax.AuditDetails;
import entities.requests.propertyTax.services.create.Units;

public class UnitsBuilder {

    Units units = new Units();

    AuditDetails auditDetails = new AuditDetailsBuilder().build();

    public UnitsBuilder(){
        units.setUnitNo("2");
        units.setUnitType("FLAT");
        units.setLength(114);
        units.setWidth(175);
        units.setBuiltupArea(120);
        units.setAssessableArea(105);
        units.setBpaBuiltupArea(195);
        units.setBpaNo("bpn2");
        units.setBpaDate("15/02/2017");
        units.setUsage("tset");
        units.setOccupancyType("asf");
        units.setOccupierName("kumar");
        units.setFirmName("wtc technologies");
        units.setRentCollected(17);
        units.setStructure("rec14");
        units.setAge("28");
        units.setExemptionReason("testCase");
        units.setStructured(false);
        units.setOccupancyDate("15/02/2017");
        units.setConstCompletionDate("15/02/2017");
        units.setElectricMeterNo("emn2");
        units.setWaterMeterNo("wmn2");
        units.setAuditDetails(auditDetails);
    }

    public UnitsBuilder withUnits(Units[] unitses){
        units.setUnitses(unitses);
        return this;
    }

    public Units build(){
        return units;
    }
}
