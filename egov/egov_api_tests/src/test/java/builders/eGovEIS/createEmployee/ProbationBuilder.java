package builders.eGovEIS.createEmployee;

import entities.requests.eGovEIS.createEmployee.Probation;

public final class ProbationBuilder {

    Probation probation = new Probation();

    public ProbationBuilder() {
        probation.setOrderNo("A1");
        probation.setCreatedBy(1);
        probation.setDeclaredOn("18/09/2016");
        probation.setOrderDate("18/09/2016");
        probation.setLastModifiedBy(1);
        probation.setDesignation(1);
        probation.setLastModifiedDate("18/09/2016");
        probation.setRemarks("None");
        probation.setCreatedDate("18/09/2016");
    }

    public ProbationBuilder withOrderNo(String orderNo) {
        probation.setOrderNo(orderNo);
        return this;
    }

    public ProbationBuilder withCreatedBy(int createdBy) {
        probation.setCreatedBy(createdBy);
        return this;
    }

    public ProbationBuilder withDeclaredOn(String declaredOn) {
        probation.setDeclaredOn(declaredOn);
        return this;
    }

    public ProbationBuilder withOrderDate(String orderDate) {
        probation.setOrderDate(orderDate);
        return this;
    }

    public ProbationBuilder withLastModifiedBy(int lastModifiedBy) {
        probation.setLastModifiedBy(lastModifiedBy);
        return this;
    }

    public ProbationBuilder withDesignation(int designation) {
        probation.setDesignation(designation);
        return this;
    }

    public ProbationBuilder withLastModifiedDate(String lastModifiedDate) {
        probation.setLastModifiedDate(lastModifiedDate);
        return this;
    }

    public ProbationBuilder withRemarks(String remarks) {
        probation.setRemarks(remarks);
        return this;
    }

    public ProbationBuilder withCreatedDate(String createdDate) {
        probation.setCreatedDate(createdDate);
        return this;
    }

    public Probation build() {
        return probation;
    }
}
