package entities.tradeLicense;

/**
 * Created by bimal on 27/12/16.
 */
public class TradeLocationDetails {

    String propertyAssessmentNumber;
    String ownershipType;
    String locality;
    String ward;

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

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public String getLocality() {
        return locality;
    }

    public String getWard() {
        return ward;
    }
}
