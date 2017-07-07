package builders.eGovEIS.emp;

import entities.requests.eGovEIS.emp.Test;

public class TestBuilder {

    Test test = new Test();

    String[] documents = new String[0];

    public TestBuilder() {
        test.setId(1);
        test.setTest("asdfgh");
        test.setYearOfPassing(2001);
        test.setRemarks("asdfgh");
        test.setCreatedBy(61);
        test.setLastModifiedBy(61);
        test.setTenantId("ap.kurnool");
        test.setDocuments(documents);
    }

    public Test build() {
        return test;
    }
}
