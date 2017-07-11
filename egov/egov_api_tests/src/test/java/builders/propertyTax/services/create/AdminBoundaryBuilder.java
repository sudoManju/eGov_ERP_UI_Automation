package builders.propertyTax.services.create;

import entities.requests.propertyTax.services.create.AdminBoundary;

public class AdminBoundaryBuilder {

    AdminBoundary adminBoundary = new AdminBoundary();

    public AdminBoundaryBuilder(){
        adminBoundary.setId(173);
        adminBoundary.setName("Election Ward No 1");
    }

    public AdminBoundary build(){
        return adminBoundary;
    }
 }
