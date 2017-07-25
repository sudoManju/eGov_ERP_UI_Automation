package builders.propertyTax.masters.occupancy;

import builders.propertyTax.AuditDetailsBuilder;
import entities.requests.propertyTax.AuditDetails;
import entities.requests.propertyTax.masters.occupancy.OccuapancyMasters;

import static data.ConstantData.tenantId;

public class OccupancyMastersBuilder {

    OccuapancyMasters occuapancyMasters = new OccuapancyMasters();

    AuditDetails auditDetails = new AuditDetailsBuilder().build();

    public OccupancyMastersBuilder() {
        occuapancyMasters.setId(0);
        occuapancyMasters.setDescription("Testing the Occupancy Master");
        occuapancyMasters.setTenantId(tenantId);
        occuapancyMasters.setActive(true);
        occuapancyMasters.setAuditDetails(auditDetails);
    }

    public OccupancyMastersBuilder withName(String name) {
        occuapancyMasters.setName(name);
        return this;
    }

    public OccupancyMastersBuilder withCode(String code) {
        occuapancyMasters.setCode(code);
        return this;
    }

    public OccupancyMastersBuilder withNameLocal(String nameLocal) {
        occuapancyMasters.setNameLocal(nameLocal);
        return this;
    }

    public OccupancyMastersBuilder withId(int id) {
        occuapancyMasters.setId(id);
        return this;
    }

    public OccuapancyMasters build() {
        return occuapancyMasters;
    }
}
