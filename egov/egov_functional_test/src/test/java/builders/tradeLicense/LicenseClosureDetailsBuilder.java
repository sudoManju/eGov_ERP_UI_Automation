package builders.tradeLicense;

import entities.tradeLicense.LicenseClosureDetails;

/**
 * Created by tester1 on 1/20/2017.
 */
public class LicenseClosureDetailsBuilder {

    LicenseClosureDetails licenseClosureDetails= new LicenseClosureDetails();


    public LicenseClosureDetailsBuilder withStatusDetails(String status) {
        licenseClosureDetails.setStatusDetails(status);
        return this;
    }

    public LicenseClosureDetailsBuilder withTradeCategory(String tradeCategory) {
        licenseClosureDetails.setTradeCategory(tradeCategory);
        return this;
    }

    public LicenseClosureDetails build() { return licenseClosureDetails; }
}
