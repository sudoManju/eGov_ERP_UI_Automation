package entities.responses.propertyTax.services.create;

public class Floors {
    private AuditDetails auditDetails;
    private String floorNo;
    private Object id;
    private Units[] units;

    public AuditDetails getAuditDetails() {
        return this.auditDetails;
    }

    public void setAuditDetails(AuditDetails auditDetails) {
        this.auditDetails = auditDetails;
    }

    public String getFloorNo() {
        return this.floorNo;
    }

    public void setFloorNo(String floorNo) {
        this.floorNo = floorNo;
    }

    public Object getId() {
        return this.id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public Units[] getUnits() {
        return this.units;
    }

    public void setUnits(Units[] units) {
        this.units = units;
    }
}
