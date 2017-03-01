package entities.tradeLicense;

public class LicenseClosureDetails {

    private String statusDetails;
    private String tradeCategory;

    public void setStatusDetails(String statusDetails) {
        this.statusDetails = statusDetails;
    }

    public String getStatusDetails() {
        return statusDetails;
    }

    public void setTradeCategory(String tradeCategory) {
        this.tradeCategory = tradeCategory;
    }

    public String getTradeCategory() {
        return tradeCategory;
    }
}
