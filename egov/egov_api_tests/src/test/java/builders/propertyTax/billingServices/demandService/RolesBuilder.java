package builders.propertyTax.billingServices.demandService;

import entities.requests.propertyTax.billingServices.demandService.Roles;

public class RolesBuilder {

    Roles roles = new Roles();

    public RolesBuilder(){
        roles.setName("citizen");
        roles.setDescription("CITIZEN");
    }

    public Roles build(){
        return roles;
    }
}
