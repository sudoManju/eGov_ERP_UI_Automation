package builders.propertyTax.services.create;

import entities.requests.propertyTax.services.create.Roles;

public class RolesBuilder {

    Roles roles = new Roles();

    public RolesBuilder(){
        roles.setCode("CITIZEN");
        roles.setName("citizen");
    }

    public Roles build(){
        return roles;
    }
}
