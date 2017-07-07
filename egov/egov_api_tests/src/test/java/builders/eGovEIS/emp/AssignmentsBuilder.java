package builders.eGovEIS.emp;

import entities.requests.eGovEIS.emp.Assignments;

public class AssignmentsBuilder {

    Assignments assignments = new Assignments();

    String[] hod = new String[0];
    String[] documents = new String[0];

    public AssignmentsBuilder() {
        assignments.setId(1);
        assignments.setPosition(360);
        assignments.setFunctionary(1);
        assignments.setFunction(59);
        assignments.setDepartment(3);
        assignments.setDesignation(74);
        assignments.setIsPrimary(true);
        assignments.setGrade(59);
        assignments.setGovtOrderNumber("asd123");
        assignments.setCreatedBy(61);
        assignments.setLastModifiedBy(61);
        assignments.setTenantId("ap.kurnool");
        assignments.setHod(hod);
        assignments.setDocuments(documents);
    }

    public AssignmentsBuilder withFromDate(String fromDate) {
        assignments.setFromDate(fromDate);
        return this;
    }

    public AssignmentsBuilder withToDate(String toDate) {
        assignments.setToDate(toDate);
        return this;
    }

    public Assignments build() {
        return assignments;
    }
}
