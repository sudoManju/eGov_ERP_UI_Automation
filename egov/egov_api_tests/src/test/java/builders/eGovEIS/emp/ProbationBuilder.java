package builders.eGovEIS.emp;

import entities.requests.eGovEIS.emp.Probation;

public class ProbationBuilder {

    Probation probation = new Probation();

    String[] documents = new String[0];

    public ProbationBuilder(){
        probation.setId(1);
        probation.setDesignation(74);
        probation.setDeclaredOn("01/04/2017");
        probation.setOrderNo("asdfgh");
        probation.setOrderDate("04/04/2017");
        probation.setRemarks("asdfgh");
        probation.setCreatedBy(61);
        probation.setLastModifiedBy(61);
        probation.setTenantId("ap.kurnool");
        probation.setDocuments(documents);
    }

    public ProbationBuilder(String s){
        probation.setId(2);
        probation.setDesignation(70);
        probation.setDeclaredOn("01/04/2017");
        probation.setOrderNo("asdfgh");
        probation.setRemarks("asdfgh");
        probation.setCreatedBy(61);
        probation.setLastModifiedBy(61);
        probation.setTenantId("ap.kurnool");
        probation.setDocuments(documents);
    }

    public Probation build(){
        return probation;
    }
}
