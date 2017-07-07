package builders.eGovEIS.emp;

import entities.requests.eGovEIS.emp.Roles;

public class RolesBuilder {

    Roles roles = new Roles();

    public RolesBuilder() {
        roles.setName("Employee");
        roles.setCode("EMPLOYEE");
        roles.setDescription("Default roles for all employees");
        roles.setCreatedBy(1);
        roles.setLastModifiedBy(1);
    }

    public Roles build() {
        return roles;
    }
}
