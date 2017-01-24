package builders.tradeLicense;

import entities.tradeLicense.SearchTradeDetails;

/**
 * Created by tester1 on 1/24/2017.
 */
public class SearchTradeDetailsBuilder {

    SearchTradeDetails searchTradeDetails=new SearchTradeDetails();

    public SearchTradeDetailsBuilder(){}

    public SearchTradeDetailsBuilder withApplicationNumber(String applicationNumber) {
        searchTradeDetails.setApplicationNumber(applicationNumber);
        return this;
    }
    public SearchTradeDetailsBuilder withLicenseNumber(String licenseNumber) {
        searchTradeDetails.setLicenseNumber(licenseNumber);
        return this;
    }
    public SearchTradeDetails build() { return searchTradeDetails; }


}
