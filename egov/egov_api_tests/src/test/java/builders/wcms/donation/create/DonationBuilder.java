package builders.wcms.donation.create;

import entities.requests.wcms.donation.create.Donation;
import tests.BaseAPITest;

import static data.SearchParameterData.TENANT_DEFAULT;

public class DonationBuilder {

    Donation donation = new Donation();

    public DonationBuilder() {
        donation.setDonationAmount(String.valueOf(new BaseAPITest().getRandomIntFromRange(100, 999)));
        donation.setFromDate("2012-04-23T18:25:43.511Z");
        donation.setToDate("2012-04-23T18:25:43.511Z");
        donation.setActive(false);
        donation.setTenantId(TENANT_DEFAULT);
    }

    public DonationBuilder withFromDate(String fromDate) {
        donation.setFromDate(fromDate);
        return this;
    }

    public DonationBuilder withPropertyType(String propertyType) {
        donation.setPropertyType(propertyType);
        return this;
    }

    public DonationBuilder withToDate(String toDate) {
        donation.setToDate(toDate);
        return this;
    }

    public DonationBuilder withTenantId(String tenantId) {
        donation.setTenantId(tenantId);
        return this;
    }

    public DonationBuilder withMinPipeSize(String minPipeSize) {
        donation.setMinPipeSize(minPipeSize);
        return this;
    }

    public DonationBuilder withActive(boolean active) {
        donation.setActive(active);
        return this;
    }

    public DonationBuilder withMaxPipeSize(String maxPipeSize) {
        donation.setMaxPipeSize(maxPipeSize);
        return this;
    }

    public DonationBuilder withCategory(String category) {
        donation.setCategory(category);
        return this;
    }

    public DonationBuilder withDonationAmount(String donationAmount) {
        donation.setDonationAmount(donationAmount);
        return this;
    }

    public DonationBuilder withUsageType(String usageType) {
        donation.setUsageType(usageType);
        return this;
    }

    public Donation build() {
        return donation;
    }
}
