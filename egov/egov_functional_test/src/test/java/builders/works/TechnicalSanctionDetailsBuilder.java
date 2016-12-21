package builders.works;

import entities.works.TechnicalSanctionDetails;

/**
 * Created by karthik on 20/12/16.
 */
public class TechnicalSanctionDetailsBuilder {

    TechnicalSanctionDetails technicalSanctionDetails = new TechnicalSanctionDetails();

    public TechnicalSanctionDetailsBuilder(){

    }

    public TechnicalSanctionDetailsBuilder withTechnicalSanctionNumber(String technicalSanctionNumber){
        technicalSanctionDetails.setTechnicalSanctionNumber(technicalSanctionNumber);
        return this;
    }

    public TechnicalSanctionDetailsBuilder withTechnicalSanctionDate(String technicalSanctionDate){
        technicalSanctionDetails.setTechnicalSanctionDate(technicalSanctionDate);
        return this;
    }

    public TechnicalSanctionDetailsBuilder withTechnicalSanctionAuthority(String technicalSanctionAuthority){
        technicalSanctionDetails.setTechnicalSanctionAuthority(technicalSanctionAuthority);
        return this;
    }

    public TechnicalSanctionDetails build(){
        return technicalSanctionDetails;
    }
}
