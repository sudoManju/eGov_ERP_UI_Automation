package builders.propertyTax.masters.department;

import builders.propertyTax.masters.AuditDetailsBuilder;
import entities.requests.propertyTax.masters.AuditDetails;
import entities.requests.propertyTax.masters.department.Departments;

import static data.ConstantData.tenantId;

public class DepartmentsBuilder {

    Departments departments = new Departments();

    AuditDetails auditDetails = new AuditDetailsBuilder().build();

    public DepartmentsBuilder() {
        departments.setId(0);
        departments.setTenantId(tenantId);
        departments.setDescription("Testing the Department Master");
        departments.setAuditDetails(auditDetails);
    }

    public DepartmentsBuilder withName(String name) {
        departments.setName(name);
        return this;
    }

    public DepartmentsBuilder withCode(String code) {
        departments.setCode(code);
        return this;
    }

    public DepartmentsBuilder withNameLocal(String nameLocal) {
        departments.setNameLocal(nameLocal);
        return this;
    }

    public DepartmentsBuilder withId(int id) {
        departments.setId(id);
        return this;
    }

    public Departments build() {
        return departments;
    }

}
