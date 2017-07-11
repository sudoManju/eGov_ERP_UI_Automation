package entities.responses.propertyTax.services.create;

public class Boundary {
    private String westBoundedBy;
    private LocationBoundary locationBoundary;
    private AuditDetails auditDetails;
    private String southBoundedBy;
    private Object id;
    private String northBoundedBy;
    private RevenueBoundary revenueBoundary;
    private String eastBoundedBy;
    private AdminBoundary adminBoundary;

    public String getWestBoundedBy() {
        return this.westBoundedBy;
    }

    public void setWestBoundedBy(String westBoundedBy) {
        this.westBoundedBy = westBoundedBy;
    }

    public LocationBoundary getLocationBoundary() {
        return this.locationBoundary;
    }

    public void setLocationBoundary(LocationBoundary locationBoundary) {
        this.locationBoundary = locationBoundary;
    }

    public AuditDetails getAuditDetails() {
        return this.auditDetails;
    }

    public void setAuditDetails(AuditDetails auditDetails) {
        this.auditDetails = auditDetails;
    }

    public String getSouthBoundedBy() {
        return this.southBoundedBy;
    }

    public void setSouthBoundedBy(String southBoundedBy) {
        this.southBoundedBy = southBoundedBy;
    }

    public Object getId() {
        return this.id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public String getNorthBoundedBy() {
        return this.northBoundedBy;
    }

    public void setNorthBoundedBy(String northBoundedBy) {
        this.northBoundedBy = northBoundedBy;
    }

    public RevenueBoundary getRevenueBoundary() {
        return this.revenueBoundary;
    }

    public void setRevenueBoundary(RevenueBoundary revenueBoundary) {
        this.revenueBoundary = revenueBoundary;
    }

    public String getEastBoundedBy() {
        return this.eastBoundedBy;
    }

    public void setEastBoundedBy(String eastBoundedBy) {
        this.eastBoundedBy = eastBoundedBy;
    }

    public AdminBoundary getAdminBoundary() {
        return this.adminBoundary;
    }

    public void setAdminBoundary(AdminBoundary adminBoundary) {
        this.adminBoundary = adminBoundary;
    }
}
