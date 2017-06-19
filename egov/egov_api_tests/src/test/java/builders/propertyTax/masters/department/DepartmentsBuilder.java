package builders.propertyTax.masters.department;

import builders.propertyTax.masters.AuditDetailsBuilder;
import entities.requests.propertyTax.masters.AuditDetails;
import entities.requests.propertyTax.masters.department.Departments;

public class DepartmentsBuilder {

    Departments departments = new Departments();

    AuditDetails auditDetails = new AuditDetailsBuilder().build();

    public DepartmentsBuilder(){
        departments.setId(0);
        departments.setTenantId("ap.kurnool");
        departments.setDescription("Testing the Department Master");
        departments.setAuditDetails(auditDetails);
    }

    public DepartmentsBuilder withName(String name){
        departments.setName(name);
        return this;
    }

    public DepartmentsBuilder withCode(String code){
        departments.setCode(code);
        return this;
    }

    public DepartmentsBuilder withNameLocal(String nameLocal){
        departments.setNameLocal(nameLocal);
        return this;
    }

    public Departments build(){
        return departments;
    }

}
