package builders.eGovEIS.createEmployee;

import entities.requests.eGovEIS.createEmployee.Education;

public final class EducationBuilder {
    Education education = new Education();
    public EducationBuilder() {
        education.setUniversity("VTU");
        education.setYearOfPassing(2012);
        education.setQualification("B.E");
        education.setCreatedBy(1);
        education.setMajorSubject("Electricals");
        education.setLastModifiedBy(1);
        education.setLastModifiedDate("18/09/2016");
        education.setCreatedDate("18/09/2016");
    }

    public EducationBuilder withUniversity(String university) {
        education.setUniversity(university);
        return this;
    }

    public EducationBuilder withYearOfPassing(int yearOfPassing) {
        education.setYearOfPassing(yearOfPassing);
        return this;
    }

    public EducationBuilder withQualification(String qualification) {
        education.setQualification(qualification);
        return this;
    }

    public EducationBuilder withCreatedBy(int createdBy) {
        education.setCreatedBy(createdBy);
        return this;
    }

    public EducationBuilder withMajorSubject(String majorSubject) {
        education.setMajorSubject(majorSubject);
        return this;
    }

    public EducationBuilder withLastModifiedBy(int lastModifiedBy) {
        education.setLastModifiedBy(lastModifiedBy);
        return this;
    }

    public EducationBuilder withLastModifiedDate(String lastModifiedDate) {
        education.setLastModifiedDate(lastModifiedDate);
        return this;
    }

    public EducationBuilder withCreatedDate(String createdDate) {
        education.setCreatedDate(createdDate);
        return this;
    }

    public Education build() { return education; }
}
