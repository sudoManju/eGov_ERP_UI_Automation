package builders.propertyTax.services.create;

import builders.propertyTax.AuditDetailsBuilder;
import entities.requests.propertyTax.AuditDetails;
import entities.requests.propertyTax.services.create.*;

public class BoundaryBuilder {

    Boundary boundary = new Boundary();

    AuditDetails auditDetails = new AuditDetailsBuilder().build();

    LocationBoundary locationBoundary = new LocationBoundaryBuilder().build();

    AdminBoundary adminBoundary = new AdminBoundaryBuilder().build();

    RevenueBoundary revenueBoundary = new RevenueBoundaryBuilder().build();

    public BoundaryBuilder(){
        boundary.setEastBoundedBy("EastTest");
        boundary.setWestBoundedBy("WestTest");
        boundary.setNorthBoundedBy("NorthTest");
        boundary.setSouthBoundedBy("SouthTest");
        boundary.setAuditDetails(auditDetails);
        boundary.setLocationBoundary(locationBoundary);
        boundary.setAdminBoundary(adminBoundary);
        boundary.setRevenueBoundary(revenueBoundary);
    }

    public Boundary build(){
        return boundary;
    }
}
