package builders.eGovEIS.emp;

import entities.requests.eGovEIS.emp.*;

public class EmployeeBuilder {

    Employee employee = new Employee();

    String[] documents = new String[0];
    int[] languagesKnown = {393,167,54};
    int[] juridictions = {2,1222};

    Education education1 = new EducationBuilder().build();
    Education[] educations = {education1};

    Probation probations1 = new ProbationBuilder().build();
    Probation[] probations = {probations1};

    Regularisation regularisation1 = new RegularisationBuilder().build();
    Regularisation[] regularisations = {regularisation1};

    ServiceHistory serviceHistory1 = new ServiceHistoryBuilder().build();
    ServiceHistory[] serviceHistory = {serviceHistory1};

    Technical technical1 = new TechnicalBuilder().build();
    Technical[] technical = {technical1};

    Test test1 = new TestBuilder().build();
    Test[] tests = {test1};

    public EmployeeBuilder(){
        employee.setTenantId("ap.kurnool");
        employee.setDocuments(documents);
        employee.setPlaceOfBirth("asdfgh");
        employee.setGroup(271);
        employee.setBankAccount("8708787989");
        employee.setBank(47);
        employee.setMaritalStatus("MARRIED");
        employee.setLanguagesKnown(languagesKnown);
        employee.setMedicalReportProduced(false);
        employee.setPhysicallyDisabled(false);
        employee.setCategory(384);
        employee.setCommunity(569);
        employee.setReligion(230);
        employee.setMotherTongue(54);
        employee.setJurisdictions(juridictions);
        employee.setEmployeeType(1);
        employee.setDateOfTermination("01/04/2016");
        employee.setDateOfAppointment("01/04/2016");
        employee.setDateOfJoining("01/04/2016");
        employee.setDateOfRetirement("01/04/2016");
        employee.setDateOfResignation("01/04/2016");
        employee.setEmployeeStatus(41);
        employee.setRecruitmentMode(158);
        employee.setRecruitmentType(45);
        employee.setRetirementAge(60);
        employee.setEducation(educations);
        employee.setProbation(probations);
        employee.setRegularisation(regularisations);
        employee.setServiceHistory(serviceHistory);
        employee.setTechnical(technical);
        employee.setTest(tests);
    }

    public EmployeeBuilder withCode(String code){
        employee.setCode(code);
        return this;
    }

    public EmployeeBuilder withAssignments(Assignments[] assignments){
        employee.setAssignments(assignments);
        return this;
    }

    public EmployeeBuilder withUser(User user){
        employee.setUser(user);
        return this;
    }

    public Employee build(){
        return employee;
    }
}
