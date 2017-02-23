package steps.ptis;

import cucumber.api.java8.En;
import entities.ptis.RegistrationDetails;
import excelDataFiles.PTISDataReader;
import pages.ptis.PropertyAcknowledgementPage;
import pages.ptis.PropertyDetailsPage;
import pages.ptis.TransferDetailsPage;
import steps.BaseSteps;

public class TransferSteps extends BaseSteps implements En {
    public TransferSteps() {
        And("^he pay tax using Cash$", () -> {
            pageStore.get(PropertyDetailsPage.class).payCash();
            pageStore.get(PropertyAcknowledgementPage.class).close1();
        });
        And("^he chooses Registration already done button$", () -> {
            pageStore.get(TransferDetailsPage.class).chooseRegistrationAlreadyDone();
        });
        And("^he enters registration details for the property (\\w+)$", (String registrationDetailsDataId) -> {
            RegistrationDetails registrationDetails = new PTISDataReader(ptisTestDataFileName).getRegistrationDetails(registrationDetailsDataId);
            pageStore.get(TransferDetailsPage.class).enterRegistrationDetails(registrationDetails);
        });
        And("^he enters enclosure details$", () -> {
            pageStore.get(TransferDetailsPage.class).enterEnclosureDetails();
        });
        And("^he searches for the assessment with mutation assessment number$", () -> {
            pageStore.get(TransferDetailsPage.class).searchAssessmentNumber(scenarioContext.getAssessmentNumber());
        });
        And("^he generate title transfer notice$", () -> {
            pageStore.get(TransferDetailsPage.class).generateTitleTransferNotice();
        });
    }
}
