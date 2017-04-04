package builders.eGovEIS.createEmployee;

import entities.requests.eGovEIS.createEmployee.*;
import org.apache.commons.lang3.RandomUtils;

public final class EmployeeBuilder {

    Employee employee = new Employee();

    Test test1 = new TestBuilder().build();
    Test[] tests = new Test[1];

    Technical technical1 = new TechnicalBuilder().build();
    Technical[] technicals = new Technical[1];

    Education education1 = new EducationBuilder().build();
    Education[] educations = new Education[1];

    Probation probation1 = new ProbationBuilder().build();
    Probation[] probations = new Probation[1];

    Assignments assignments1 = new AssignmentsBuilder().build();
    Assignments[] assignmentss = new Assignments[1];

    Regularisation regularisation1 = new RegularisationBuilder().build();
    Regularisation[] regularisations = new Regularisation[1];

    User user1 = new UserBuilder().build();
    User[] users = new User[1];

    ServiceHistory serviceHistory1 = new ServiceHistoryBuilder().build();
    ServiceHistory[] serviceHistories = new ServiceHistory[1];

    public EmployeeBuilder() {
        employee.setTenantId("1");
        employee.setMotherTongue("5");
        employee.setGpfNo("12" + get3DigitRandomInt());
        employee.setPassportNo("IND12" + get3DigitRandomInt());
        employee.setRecruitmentType("1");
        int a[] = {1};
        employee.setJurisdictions(a);
        tests[0] = test1;
        employee.setTest(tests);
        employee.setRetirementAge("45");
        employee.setEmployeeStatus("1");
        technicals[0] = technical1;
        employee.setTechnical(technicals);
        educations[0] = education1;
        employee.setEducation(educations);
        employee.setMaritalStatus("UNMARRIED");
        probations[0] = probation1;
        employee.setProbation(probations);
        employee.setPhysicallyDisabled("false");
        employee.setRecruitmentQuota("1");
        employee.setReligion("1");
        int b[] = {1, 3, 5};
        employee.setLanguagesKnown(b);
        employee.setDateOfJoining("17/10/2017");
        employee.setBank("21");
        employee.setDateOfResignation("17/09/2022");
        employee.setEmployeeType("3");
        employee.setPlaceOfBirth("Bengaluru");
        employee.setMedicalReportProduced("false");
        employee.setDateOfTermination("17/09/2022");
        employee.setCommunity("6");
        serviceHistories[0] = serviceHistory1;
        employee.setServiceHistory(serviceHistories);
        assignmentss[0] = assignments1;
        employee.setAssignments(assignmentss);
        regularisations[0] = regularisation1;
        employee.setRegularisation(regularisations);
        employee.setDateOfRetirement("17/09/2022");
        employee.setCategory("3");
        employee.setRecruitmentMode("1");
        employee.setGroup("33");
        employee.setDateOfAppointment("17/10/2017");
        users[0] = user1;
        employee.setUser(users);
        employee.setBankAccount("987456");
        employee.setBankBranch("30");
    }

    protected String get3DigitRandomInt() {
        return String.valueOf((RandomUtils.nextInt(100, 999)));
    }

    public EmployeeBuilder withTenantId(String tenantId) {
        employee.setTenantId(tenantId);
        return this;
    }

    public EmployeeBuilder withMotherTongue(String motherTongue) {
        employee.setMotherTongue(motherTongue);
        return this;
    }

    public EmployeeBuilder withGpfNo(String gpfNo) {
        employee.setGpfNo(gpfNo);
        return this;
    }

    public EmployeeBuilder withPassportNo(String passportNo) {
        employee.setPassportNo(passportNo);
        return this;
    }

    public EmployeeBuilder withRecruitmentType(String recruitmentType) {
        employee.setRecruitmentType(recruitmentType);
        return this;
    }

    public EmployeeBuilder withJurisdictions(int[] jurisdictions) {
        employee.setJurisdictions(jurisdictions);
        return this;
    }

    public EmployeeBuilder withTest(Test[] test) {
        employee.setTest(test);
        return this;
    }

    public EmployeeBuilder withRetirementAge(String retirementAge) {
        employee.setRetirementAge(retirementAge);
        return this;
    }

    public EmployeeBuilder withEmployeeStatus(String employeeStatus) {
        employee.setEmployeeStatus(employeeStatus);
        return this;
    }

    public EmployeeBuilder withTechnical(Technical[] technical) {
        employee.setTechnical(technical);
        return this;
    }

    public EmployeeBuilder withEducation(Education[] education) {
        employee.setEducation(education);
        return this;
    }

    public EmployeeBuilder withMaritalStatus(String maritalStatus) {
        employee.setMaritalStatus(maritalStatus);
        return this;
    }

    public EmployeeBuilder withProbation(Probation[] probation) {
        employee.setProbation(probation);
        return this;
    }

    public EmployeeBuilder withPhysicallyDisabled(String physicallyDisabled) {
        employee.setPhysicallyDisabled(physicallyDisabled);
        return this;
    }

    public EmployeeBuilder withRecruitmentQuota(String recruitmentQuota) {
        employee.setRecruitmentQuota(recruitmentQuota);
        return this;
    }

    public EmployeeBuilder withReligion(String religion) {
        employee.setReligion(religion);
        return this;
    }

    public EmployeeBuilder withLanguagesKnown(int[] languagesKnown) {
        employee.setLanguagesKnown(languagesKnown);
        return this;
    }

    public EmployeeBuilder withDateOfJoining(String dateOfJoining) {
        employee.setDateOfJoining(dateOfJoining);
        return this;
    }

    public EmployeeBuilder withBank(String bank) {
        employee.setBank(bank);
        return this;
    }

    public EmployeeBuilder withDateOfResignation(String dateOfResignation) {
        employee.setDateOfResignation(dateOfResignation);
        return this;
    }

    public EmployeeBuilder withEmployeeType(String employeeType) {
        employee.setEmployeeType(employeeType);
        return this;
    }

    public EmployeeBuilder withPlaceOfBirth(String placeOfBirth) {
        employee.setPlaceOfBirth(placeOfBirth);
        return this;
    }

    public EmployeeBuilder withMedicalReportProduced(String medicalReportProduced) {
        employee.setMedicalReportProduced(medicalReportProduced);
        return this;
    }

    public EmployeeBuilder withDateOfTermination(String dateOfTermination) {
        employee.setDateOfTermination(dateOfTermination);
        return this;
    }

    public EmployeeBuilder withCommunity(String community) {
        employee.setCommunity(community);
        return this;
    }

    public EmployeeBuilder withServiceHistory(ServiceHistory[] serviceHistory) {
        employee.setServiceHistory(serviceHistory);
        return this;
    }

    public EmployeeBuilder withAssignments(Assignments[] assignments) {
        employee.setAssignments(assignments);
        return this;
    }

    public EmployeeBuilder withRegularisation(Regularisation[] regularisation) {
        employee.setRegularisation(regularisation);
        return this;
    }

    public EmployeeBuilder withDateOfRetirement(String dateOfRetirement) {
        employee.setDateOfRetirement(dateOfRetirement);
        return this;
    }

    public EmployeeBuilder withCategory(String category) {
        employee.setCategory(category);
        return this;
    }

    public EmployeeBuilder withRecruitmentMode(String recruitmentMode) {
        employee.setRecruitmentMode(recruitmentMode);
        return this;
    }

    public EmployeeBuilder withGroup(String group) {
        employee.setGroup(group);
        return this;
    }

    public EmployeeBuilder withDateOfAppointment(String dateOfAppointment) {
        employee.setDateOfAppointment(dateOfAppointment);
        return this;
    }

    public EmployeeBuilder withUser(User[] user) {
        employee.setUser(user);
        return this;
    }

    public EmployeeBuilder withBankAccount(String bankAccount) {
        employee.setBankAccount(bankAccount);
        return this;
    }

    public EmployeeBuilder withBankBranch(String bankBranch) {
        employee.setBankBranch(bankBranch);
        return this;
    }

    public Employee build() {
        return employee;
    }
}
