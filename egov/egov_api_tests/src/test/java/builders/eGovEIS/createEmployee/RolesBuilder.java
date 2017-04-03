package builders.eGovEIS.createEmployee;

import entities.requests.eGovEIS.createEmployee.Roles;

public class RolesBuilder {

    Roles roles = new Roles();

    public RolesBuilder(){}

    public RolesBuilder withName(String name){
        roles.setName(name);
        return this;
    }

    public RolesBuilder withDescription(String description){
        roles.setDescription(description);
        return this;
    }

    public RolesBuilder withCreatedBy(int createdBy){
        roles.setCreatedBy(createdBy);
        return this;
    }

    public RolesBuilder withCreatedDate(String createdDate){
        roles.setCreatedDate(createdDate);
        return this;
    }

    public RolesBuilder withLastModifiedBy(int modifiedBy){
        roles.setLastModifiedBy(modifiedBy);
        return this;
    }

    public RolesBuilder withLastModifiedDate(String lastModifiedDate){
        roles.setLastModifiedDate(lastModifiedDate);
        return this;
    }

    public Roles build(){
        return roles;
    }

}
