package entities.tradeLicense;

/**
 * Created by bimal on 27/12/16.
 */
public class TradeLocationDetails {

    String propertyAssessmentNumber;
    String ownershipType;

    public String getpropertyAssessmentNumber() {
        return propertyAssessmentNumber;  }

    public void setPropertyAssessmentNumber(String propertyAssessmentNumber) {
        this.propertyAssessmentNumber = propertyAssessmentNumber;

    }

    public String getownershipType(){
        return ownershipType;
    }

    public void setOwnershipType(String ownershipType){
        this.ownershipType = ownershipType;
    }
}
