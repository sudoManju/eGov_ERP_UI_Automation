package builders.eGovEIS.emp;

import entities.requests.eGovEIS.emp.Education;

public class EducationBuilder {

    Education education = new Education();

    String[] documents = new String[0];

    public EducationBuilder() {
        education.setId(1);
        education.setQualification("asdfgh");
        education.setMajorSubject("asdfgh");
        education.setYearOfPassing(2003);
        education.setUniversity("asdfgh");
        education.setCreatedBy(61);
        education.setLastModifiedBy(61);
        education.setTenantId("ap.kurnool");
        education.setDocuments(documents);
    }

    public Education build() {
        return education;
    }
}
