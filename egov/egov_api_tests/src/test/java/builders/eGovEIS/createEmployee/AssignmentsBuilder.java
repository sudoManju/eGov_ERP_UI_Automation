package builders.eGovEIS.createEmployee;

import entities.requests.eGovEIS.createEmployee.Assignments;
import entities.requests.eGovEIS.createEmployee.Hod;

public final class AssignmentsBuilder {

    Assignments assignments = new Assignments();

    Hod hod1 = new HodBuilder().withDepartment("5").build();
    Hod hod2 = new HodBuilder().withDepartment("18").build();
    Hod[] hods = new Hod[2];


    public AssignmentsBuilder() {
        assignments.setPosition(1);
        assignments.setIsPrimary(true);
        assignments.setDepartment(5);
        assignments.setGovtOrderNumber("sadda");
        assignments.setDesignation(5);
        assignments.setToDate("31/12/2016");
        hods[0] = hod1;
        hods[1] = hod2;
        assignments.setHod(hods);
        assignments.setFunction(1);
        assignments.setFunctionary(1);
        assignments.setCreatedBy(1);
        assignments.setFromDate("01/01/2016");
        assignments.setLastModifiedBy(3);
        assignments.setLastModifiedDate("18/09/2016");
        assignments.setGrade(1);
        assignments.setFund(1);
        assignments.setEmployee(2);
        assignments.setCreatedDate("18/09/2016");
    }

    public AssignmentsBuilder withPosition(int position) {
        assignments.setPosition(position);
        return this;
    }

    public AssignmentsBuilder withIsPrimary(boolean isPrimary) {
        assignments.setIsPrimary(isPrimary);
        return this;
    }

    public AssignmentsBuilder withDepartment(int department) {
        assignments.setDepartment(department);
        return this;
    }

    public AssignmentsBuilder withGovtOrderNumber(String govtOrderNumber) {
        assignments.setGovtOrderNumber(govtOrderNumber);
        return this;
    }

    public AssignmentsBuilder withDesignation(int designation) {
        assignments.setDesignation(designation);
        return this;
    }

    public AssignmentsBuilder withToDate(String toDate) {
        assignments.setToDate(toDate);
        return this;
    }

    public AssignmentsBuilder withHod(Hod[] hod) {
        assignments.setHod(hod);
        return this;
    }

    public AssignmentsBuilder withFunction(int function) {
        assignments.setFunction(function);
        return this;
    }

    public AssignmentsBuilder withFunctionary(int functionary) {
        assignments.setFunctionary(functionary);
        return this;
    }

    public AssignmentsBuilder withCreatedBy(int createdBy) {
        assignments.setCreatedBy(createdBy);
        return this;
    }

    public AssignmentsBuilder withFromDate(String fromDate) {
        assignments.setFromDate(fromDate);
        return this;
    }

    public AssignmentsBuilder withLastModifiedBy(int lastModifiedBy) {
        assignments.setLastModifiedBy(lastModifiedBy);
        return this;
    }

    public AssignmentsBuilder withLastModifiedDate(String lastModifiedDate) {
        assignments.setLastModifiedDate(lastModifiedDate);
        return this;
    }

    public AssignmentsBuilder withGrade(int grade) {
        assignments.setGrade(grade);
        return this;
    }

    public AssignmentsBuilder withFund(int fund) {
        assignments.setFund(fund);
        return this;
    }

    public AssignmentsBuilder withEmployee(int employee) {
        assignments.setEmployee(employee);
        return this;
    }

    public AssignmentsBuilder withCreatedDate(String createdDate) {
        assignments.setCreatedDate(createdDate);
        return this;
    }

    public Assignments build() {
        return assignments;
    }
}
