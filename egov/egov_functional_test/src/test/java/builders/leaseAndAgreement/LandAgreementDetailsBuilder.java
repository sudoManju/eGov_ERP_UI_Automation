package builders.leaseAndAgreement;

import entities.leaseAndAgreement.AgreementDetails;

public final class LandAgreementDetailsBuilder {

    AgreementDetails agreementDetails = new AgreementDetails();

    public LandAgreementDetailsBuilder withTenderNumber(String tenderNumber) {
        agreementDetails.setTenderNumber(tenderNumber);
        return this;
    }

    public LandAgreementDetailsBuilder withTenderDate(String tenderDate) {
        agreementDetails.setTenderDate(tenderDate);

        return this;
    }

    public LandAgreementDetailsBuilder withNatureOfAllotment(String natureOfAllotment) {
        agreementDetails.setNatureOfAllotment(natureOfAllotment);
        return this;
    }

    public LandAgreementDetailsBuilder withCouncilNumber(String councilNumber) {
        agreementDetails.setCouncilNumber(councilNumber);
        return this;
    }

    public LandAgreementDetailsBuilder withCouncilDate(String councilDate) {
        agreementDetails.setCouncilDate(councilDate);
        return this;
    }

    public LandAgreementDetailsBuilder withLandRent(String landRent) {
        agreementDetails.setLandRent(landRent);
        return this;
    }

    public LandAgreementDetailsBuilder withPaymentCycle(String paymentCycle) {
        agreementDetails.setPaymentCycle(paymentCycle);
        return this;
    }

    public LandAgreementDetailsBuilder withBankGuaranteeAmount(String bankGuaranteeAmount) {
        agreementDetails.setBankGuaranteeAmount(bankGuaranteeAmount);
        return this;
    }

    public LandAgreementDetailsBuilder withBankGuaranteeDate(String bankGuaranteeDate) {
        agreementDetails.setBankGuaranteeDate(bankGuaranteeDate);
        return this;
    }

    public LandAgreementDetailsBuilder withSolvencyCertificateNumber(String solvencyCertificateNumber) {
        agreementDetails.setSolvencyCertificateNumber(solvencyCertificateNumber);
        return this;
    }

    public LandAgreementDetailsBuilder withSolvencyCertificateDate(String solvencyCertificateDate) {
        agreementDetails.setSolvencyCertificateDate(solvencyCertificateDate);
        return this;
    }

    public LandAgreementDetailsBuilder withSecurityDeposit(String securityDeposit) {
        agreementDetails.setSecurityDeposit(securityDeposit);
        return this;
    }

    public LandAgreementDetailsBuilder withSecurityDepositDate(String securityDepositDate) {
        agreementDetails.setSecurityDepositDate(securityDepositDate);
        return this;
    }

    public LandAgreementDetailsBuilder withCommencementDate(String commencementDate) {
        agreementDetails.setCommencementDate(commencementDate);
        return this;
    }

    public LandAgreementDetailsBuilder withRentIncrementMethod(String rentIncrementMethod) {
        agreementDetails.setRentIncrementMethod(rentIncrementMethod);
        return this;
    }

    public LandAgreementDetailsBuilder withTimePeriod(String timePeriod) {
        agreementDetails.setTimePeriod(timePeriod);
        return this;
    }

    public AgreementDetails build() {
        return agreementDetails;
    }
}
