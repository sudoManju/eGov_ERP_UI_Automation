package builders.leaseAndAgreement;

import entities.leaseAndAgreement.AllotteeDetails;

public final class LandAllotteeDetailsBuilder {
    AllotteeDetails allotteeDetails = new AllotteeDetails();

    public LandAllotteeDetailsBuilder withAadharNumber(String aadharNumber) {
        allotteeDetails.setAadharNumber(aadharNumber);
        return this;
    }

    public LandAllotteeDetailsBuilder withMobileNumber(String mobileNumber) {
        allotteeDetails.setMobileNumber(mobileNumber);
        return this;
    }

    public LandAllotteeDetailsBuilder withName(String name) {
        allotteeDetails.setName(name);
        return this;
    }

    public LandAllotteeDetailsBuilder withEmail(String emailIdx) {
        allotteeDetails.setEmail(emailIdx);
        return this;
    }

    public LandAllotteeDetailsBuilder withPan(String pan) {
        allotteeDetails.setPan(pan);
        return this;
    }

    public AllotteeDetails build() {
        return allotteeDetails;
    }
}
