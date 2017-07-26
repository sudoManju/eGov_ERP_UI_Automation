package entities.requests.propertyTax.billingServices.demandService;

public class DemandDetails {
    private String taxHeadMasterCode;
    private int collectionAmount;
    private int id;
    private int taxAmount;

    public String getTaxHeadMasterCode() {
        return this.taxHeadMasterCode;
    }

    public void setTaxHeadMasterCode(String taxHeadMasterCode) {
        this.taxHeadMasterCode = taxHeadMasterCode;
    }

    public int getCollectionAmount() {
        return this.collectionAmount;
    }

    public void setCollectionAmount(int collectionAmount) {
        this.collectionAmount = collectionAmount;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTaxAmount() {
        return this.taxAmount;
    }

    public void setTaxAmount(int taxAmount) {
        this.taxAmount = taxAmount;
    }
}
