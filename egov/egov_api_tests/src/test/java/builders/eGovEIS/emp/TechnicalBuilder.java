package builders.eGovEIS.emp;

import entities.requests.eGovEIS.emp.Technical;

public class TechnicalBuilder {

    Technical technical = new Technical();

    String[] documents = new String[0];

    public TechnicalBuilder() {
        technical.setId(1);
        technical.setSkill("asdfgh");
        technical.setGrade("asdfgh");
        technical.setYearOfPassing(2003);
        technical.setRemarks("asdfgh");
        technical.setCreatedBy(61);
        technical.setLastModifiedBy(61);
        technical.setTenantId("ap.kurnool");
        technical.setDocuments(documents);
    }

    public Technical build() {
        return technical;
    }
}
