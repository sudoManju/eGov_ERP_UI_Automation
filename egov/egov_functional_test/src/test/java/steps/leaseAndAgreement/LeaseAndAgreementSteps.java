package steps.leaseAndAgreement;

import cucumber.api.PendingException;
import cucumber.api.java8.En;
import entities.leaseAndAgreement.LandAllotteeDetails;
import pages.leaseAndAgreement.LeaseAndAgreementPage;
import steps.BaseSteps;

public class LeaseAndAgreementSteps extends BaseSteps implements En {

    public LeaseAndAgreementSteps() {

        And("^user will select the required asset service application to create the agreement$", () -> {
            pageStore.get(LeaseAndAgreementPage.class).searchAssetApplication();
        });

        And("^user will enter the allottee details as (\\w+) and agreement details as (\\w+)$", (String allotteeDataId ,
                                                                                                        String agreementDataId) -> {
            LandAllotteeDetails landAllotteeDetails = new
            pageStore.get(LeaseAndAgreementPage.class).en;

        });

    }
}
