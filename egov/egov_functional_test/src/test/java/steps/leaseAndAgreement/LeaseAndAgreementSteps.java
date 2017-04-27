package steps.leaseAndAgreement;

import cucumber.api.java8.En;
import entities.leaseAndAgreement.LandAgreementDetails;
import entities.leaseAndAgreement.LandAllotteeDetails;
import excelDataFiles.LeaseAndAgreementDataReader;
import pages.leaseAndAgreement.LeaseAndAgreementPage;
import steps.BaseSteps;

public class LeaseAndAgreementSteps extends BaseSteps implements En {

    public LeaseAndAgreementSteps() {

        And("^user will select the required asset service application to create the agreement$", () -> {
            pageStore.get(LeaseAndAgreementPage.class).searchAssetApplication();
        });

        And("^user will enter the allottee details as (\\w+) and agreement details as (\\w+)$", (String allotteeDataId,
                                                                                                 String agreementDataId) -> {

            LandAllotteeDetails landAllotteeDetails = new LeaseAndAgreementDataReader(lamsTestDataFileName).getAllotteeDetails(allotteeDataId);

            LandAgreementDetails landAgreementDetails = new LeaseAndAgreementDataReader(lamsTestDataFileName).getAgreementDetails(agreementDataId);

            pageStore.get(LeaseAndAgreementPage.class).enterAgreementDetails(landAllotteeDetails, landAgreementDetails);
        });

    }
}
