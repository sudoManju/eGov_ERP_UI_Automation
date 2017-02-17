package steps.lcms;

import cucumber.api.PendingException;
import cucumber.api.java8.En;
import entities.lcms.CreateLegalCase;
import pages.lcms.LegalCaseManagementPage;
import steps.BaseSteps;
import utils.ExcelReader;

/**
 * Created by vinaykumar on 2/2/17.
 */
public class LegalCaseManagementSteps extends BaseSteps implements En {

    public LegalCaseManagementSteps() {

        And("^user will enter the legal case details as (\\w+)$", (String legalCaseData) -> {

            CreateLegalCase createLegalCase = new ExcelReader(legalCaseTestDataFileName).getCreateLegalCaseDetails(legalCaseData);
            pageStore.get(LegalCaseManagementPage.class).enterLegalCaseDetails(createLegalCase);
        });

        And("^user closes the successful acknowledgement form$", () -> {
            String messageAndFileNumber = pageStore.get(LegalCaseManagementPage.class).closesAcknowledgementForm();
            scenarioContext.setActualMessage(messageAndFileNumber.split("\\>")[0]);
            scenarioContext.setApplicationNumber(messageAndFileNumber.split("\\>")[1]);
        });

        And("^user will enter the case file number to search the file$", () -> {
            pageStore.get(LegalCaseManagementPage.class).searchCaseFile(scenarioContext.getApplicationNumber());
        });

        And("^user will take the corresponding action on above as (\\w+)$", (String action) -> {
            pageStore.get(LegalCaseManagementPage.class).clickOnCorrespondingAction(action);
        });

        And("^user will closes the successful created or updated page$", () -> {
            String message = pageStore.get(LegalCaseManagementPage.class).closeCreatedOrUpdatedPage();
            scenarioContext.setActualMessage(message);
        });

        And("^user will enter the details of judgment implementation details based on (\\w+)$", (String mode) -> {
            pageStore.get(LegalCaseManagementPage.class).enterJudgmentImplementationDetails(mode);
        });

    }
}
