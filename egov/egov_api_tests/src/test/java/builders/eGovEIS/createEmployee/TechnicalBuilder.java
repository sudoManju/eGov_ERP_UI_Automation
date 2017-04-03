package builders.eGovEIS.createEmployee;

import entities.requests.eGovEIS.createEmployee.Technical;

public final class TechnicalBuilder {

    Technical technical = new Technical();

    public TechnicalBuilder() {
        technical.setYearOfPassing("2012");
        technical.setCreatedBy("1");
        technical.setSkill("E");
        technical.setLastModifiedBy("1");
        technical.setLastModifiedDate("18/09/2016");
        technical.setGrade("A");
        technical.setRemarks("None");
        technical.setCreatedDate("18/09/2016");
    }

    public TechnicalBuilder withYearOfPassing(String yearOfPassing) {
        technical.setYearOfPassing(yearOfPassing);
        return this;
    }

    public TechnicalBuilder withCreatedBy(String createdBy) {
        technical.setCreatedBy(createdBy);
        return this;
    }

    public TechnicalBuilder withSkill(String skill) {
        technical.setSkill(skill);
        return this;
    }

    public TechnicalBuilder withLastModifiedBy(String lastModifiedBy) {
        technical.setLastModifiedBy(lastModifiedBy);
        return this;
    }

    public TechnicalBuilder withLastModifiedDate(String lastModifiedDate) {
        technical.setLastModifiedDate(lastModifiedDate);
        return this;
    }

    public TechnicalBuilder withGrade(String grade) {
        technical.setGrade(grade);
        return this;
    }

    public TechnicalBuilder withRemarks(String remarks) {
        technical.setRemarks(remarks);
        return this;
    }

    public TechnicalBuilder withCreatedDate(String createdDate) {
        technical.setCreatedDate(createdDate);
        return this;
    }

    public Technical build() {
        return technical;
    }
}
