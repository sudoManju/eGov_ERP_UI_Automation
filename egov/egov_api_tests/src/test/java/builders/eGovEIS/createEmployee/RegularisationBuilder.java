package builders.eGovEIS.createEmployee;

import entities.requests.eGovEIS.createEmployee.Regularisation;

public final class RegularisationBuilder {

    Regularisation regularisation = new Regularisation();

    public RegularisationBuilder() {
        regularisation.setOrderNo("A1");
        regularisation.setCreatedBy(1);
        regularisation.setDeclaredOn("18/09/2016");
        regularisation.setOrderDate("18/09/2016");
        regularisation.setLastModifiedBy(1);
        regularisation.setDesignation(1);
        regularisation.setLastModifiedDate("18/09/2016");
        regularisation.setRemarks("None");
        regularisation.setCreatedDate("18/09/2016");
    }

    public static RegularisationBuilder aRegularisation() {
        return new RegularisationBuilder();
    }

    public RegularisationBuilder withOrderNo(String orderNo) {
        regularisation.setOrderNo(orderNo);
        return this;
    }

    public RegularisationBuilder withCreatedBy(int createdBy) {
        regularisation.setCreatedBy(createdBy);
        return this;
    }

    public RegularisationBuilder withDeclaredOn(String declaredOn) {
        regularisation.setDeclaredOn(declaredOn);
        return this;
    }

    public RegularisationBuilder withOrderDate(String orderDate) {
        regularisation.setOrderDate(orderDate);
        return this;
    }

    public RegularisationBuilder withLastModifiedBy(int lastModifiedBy) {
        regularisation.setLastModifiedBy(lastModifiedBy);
        return this;
    }

    public RegularisationBuilder withDesignation(int designation) {
        regularisation.setDesignation(designation);
        return this;
    }

    public RegularisationBuilder withLastModifiedDate(String lastModifiedDate) {
        regularisation.setLastModifiedDate(lastModifiedDate);
        return this;
    }

    public RegularisationBuilder withRemarks(String remarks) {
        regularisation.setRemarks(remarks);
        return this;
    }

    public RegularisationBuilder withCreatedDate(String createdDate) {
        regularisation.setCreatedDate(createdDate);
        return this;
    }

    public Regularisation build() {
        return regularisation;
    }
}
