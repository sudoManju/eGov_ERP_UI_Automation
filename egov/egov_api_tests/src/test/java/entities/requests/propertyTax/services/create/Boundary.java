package entities.requests.propertyTax.services.create;

public class Boundary
{
    private String westBoundedBy;

    private LocationBoundary locationBoundary;

    private AdminBoundary adminBoundary;

    private String southBoundedBy;

    private String eastBoundedBy;

    private RevenueBoundary revenueBoundary;

    private AuditDetails auditDetails;

    private String northBoundedBy;

    public String getWestBoundedBy() {
        return westBoundedBy;
    }

    public void setWestBoundedBy(String westBoundedBy) {
        this.westBoundedBy = westBoundedBy;
    }

    public LocationBoundary getLocationBoundary() {
        return locationBoundary;
    }

    public void setLocationBoundary(LocationBoundary locationBoundary) {
        this.locationBoundary = locationBoundary;
    }

    public AdminBoundary getAdminBoundary() {
        return adminBoundary;
    }

    public void setAdminBoundary(AdminBoundary adminBoundary) {
        this.adminBoundary = adminBoundary;
    }

    public String getSouthBoundedBy() {
        return southBoundedBy;
    }

    public void setSouthBoundedBy(String southBoundedBy) {
        this.southBoundedBy = southBoundedBy;
    }

    public String getEastBoundedBy() {
        return eastBoundedBy;
    }

    public void setEastBoundedBy(String eastBoundedBy) {
        this.eastBoundedBy = eastBoundedBy;
    }

    public RevenueBoundary getRevenueBoundary() {
        return revenueBoundary;
    }

    public void setRevenueBoundary(RevenueBoundary revenueBoundary) {
        this.revenueBoundary = revenueBoundary;
    }

    public AuditDetails getAuditDetails() {
        return auditDetails;
    }

    public void setAuditDetails(AuditDetails auditDetails) {
        this.auditDetails = auditDetails;
    }

    public String getNorthBoundedBy() {
        return northBoundedBy;
    }

    public void setNorthBoundedBy(String northBoundedBy) {
        this.northBoundedBy = northBoundedBy;
    }
}
