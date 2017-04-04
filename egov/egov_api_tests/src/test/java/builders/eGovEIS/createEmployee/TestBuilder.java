package builders.eGovEIS.createEmployee;

import entities.requests.eGovEIS.createEmployee.Test;

public final class TestBuilder {

    Test testObject = new Test();

    public TestBuilder() {
        testObject.setYearOfPassing(2012);
        testObject.setCreatedBy("1");
        testObject.setTest("A01");
        testObject.setLastModifiedBy("1");
        testObject.setLastModifiedDate("18/09/2016");
        testObject.setRemarks("None");
        testObject.setCreatedDate("18/09/2016");
    }

    public TestBuilder withYearOfPassing(int yearOfPassing) {
        testObject.setYearOfPassing(yearOfPassing);
        return this;
    }

    public TestBuilder withCreatedBy(String createdBy) {
        testObject.setCreatedBy(createdBy);
        return this;
    }

    public TestBuilder withTest(String test) {
        testObject.setTest(test);
        return this;
    }

    public TestBuilder withLastModifiedBy(String lastModifiedBy) {
        testObject.setLastModifiedBy(lastModifiedBy);
        return this;
    }

    public TestBuilder withLastModifiedDate(String lastModifiedDate) {
        testObject.setLastModifiedDate(lastModifiedDate);
        return this;
    }

    public TestBuilder withRemarks(String remarks) {
        testObject.setRemarks(remarks);
        return this;
    }

    public TestBuilder withCreatedDate(String createdDate) {
        testObject.setCreatedDate(createdDate);
        return this;
    }

    public Test build() {
        return testObject;
    }
}
