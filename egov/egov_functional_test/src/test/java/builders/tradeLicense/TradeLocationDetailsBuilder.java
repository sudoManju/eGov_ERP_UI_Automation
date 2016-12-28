package builders.tradeLicense;

import entities.tradeLicense.TradeLocationDetails;

/**
 * Created by bimal on 27/12/16.
 */
public class TradeLocationDetailsBuilder {

    TradeLocationDetails tradeLocationDetails = new TradeLocationDetails();

    public TradeLocationDetailsBuilder() {
    }
    public TradeLocationDetailsBuilder withpropertyAssessmentNumber(String aadhaarNumber){
        tradeLocationDetails.setPropertyAssessmentNumber(aadhaarNumber);
        return this;
    }

    public TradeLocationDetailsBuilder withownershipType(String ownershipType){
        tradeLocationDetails.setOwnershipType((ownershipType));
        return this;
    }

    public TradeLocationDetails build() {
        return tradeLocationDetails;
    }
}
