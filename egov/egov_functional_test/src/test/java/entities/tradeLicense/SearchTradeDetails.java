package entities.tradeLicense;

/**
 * Created by tester1 on 1/23/2017.
 */
public class SearchTradeDetails {

    private String applicationNumber;
    private String licenseNumber;

    public void setApplicationNumber(String applicationNumber) {
        this.applicationNumber = applicationNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getApplicationNumber() {
        return applicationNumber;
    }
}
