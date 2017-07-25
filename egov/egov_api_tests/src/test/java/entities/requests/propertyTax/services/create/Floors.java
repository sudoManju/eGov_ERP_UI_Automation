package entities.requests.propertyTax.services.create;

import entities.requests.propertyTax.AuditDetails;

public class Floors
{
    private String floorNo;

    private AuditDetails auditDetails;

    private Units[] units;

    public String getFloorNo() {
        return floorNo;
    }

    public void setFloorNo(String floorNo) {
        this.floorNo = floorNo;
    }

    public AuditDetails getAuditDetails() {
        return auditDetails;
    }

    public void setAuditDetails(AuditDetails auditDetails) {
        this.auditDetails = auditDetails;
    }

    public Units[] getUnits() {
        return units;
    }

    public void setUnits(Units[] units) {
        this.units = units;
    }
}
