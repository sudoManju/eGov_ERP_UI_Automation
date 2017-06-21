package builders.eGovEIS.emp;

import entities.requests.eGovEIS.emp.Regularisation;

public class RegularisationBuilder {

    Regularisation regularisation = new Regularisation();

    String[] documents = new String[0];

    public RegularisationBuilder(){
        regularisation.setId(1);
        regularisation.setDesignation(86);
        regularisation.setDeclaredOn("03/04/2017");
        regularisation.setOrderNo("asdfgh");
        regularisation.setOrderDate("05/04/2017");
        regularisation.setRemarks("asdfgh");
        regularisation.setCreatedBy(61);
        regularisation.setLastModifiedBy(61);
        regularisation.setTenantId("ap.kurnool");
        regularisation.setDocuments(documents);
    }

    public Regularisation build(){
        return regularisation;
    }
}
