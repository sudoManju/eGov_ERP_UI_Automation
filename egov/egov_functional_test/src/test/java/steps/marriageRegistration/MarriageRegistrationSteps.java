package steps.marriageRegistration;

import cucumber.api.PendingException;
import cucumber.api.java8.En;
import entities.marriageRegistration.MarriageRegistrationInformation;
import entities.works.ApproverDetails;
import pages.BasePage;
import pages.SewerageTax.newSewerageConnectionPage;
import pages.marriageRegistration.MarriageRegistrationPage;
import pages.works.SpillOverEstimatePage;
import steps.BaseSteps;
import utils.ExcelReader;

/**
 * Created by manjunatha-lap on 24/01/2017.
 */
public class MarriageRegistrationSteps extends BaseSteps implements En {
    public MarriageRegistrationSteps() {
        And("^he enters the applicants details as (\\w+)$", (String generalInformationDataId) -> {
            MarriageRegistrationInformation marriageRegistrationInformation = new ExcelReader(marriageRegistrationTestDataFileName).getApplicantsInformation(generalInformationDataId);
            pageStore.get(MarriageRegistrationPage.class).enterApplicantsInformation(marriageRegistrationInformation);
        });
        And("^he enters the bridegroom information as (\\w+) (\\w+)$", (String bridegroomInformationDataId, String brideInformationDataId) -> {
            MarriageRegistrationInformation marriageRegistrationInformation1 = new ExcelReader(marriageRegistrationTestDataFileName).getBrideGroomInformation(bridegroomInformationDataId);
            pageStore.get(MarriageRegistrationPage.class).enterBrideGroomInformation(marriageRegistrationInformation1, "husband");

            MarriageRegistrationInformation marriageRegistrationInformation2 = new ExcelReader(marriageRegistrationTestDataFileName).getBrideGroomInformation(brideInformationDataId);
            pageStore.get(MarriageRegistrationPage.class).enterBrideGroomInformation(marriageRegistrationInformation2, "wife");
        });
        And("^he enters the Witnesses Information$", () -> {
            pageStore.get(MarriageRegistrationPage.class).entersWitnessesInformation();
        });
        And("^he enters the checklist$", () -> {
            pageStore.get(MarriageRegistrationPage.class).enterChecklist();
        });
        And("^he forward to commissioner and closes the acknowledgement$", () -> {
            String approverDetailsDataId = "commissioner";

            ApproverDetails approverDetails = new ExcelReader(lineEstimateTestDataFileName).getApprovalDetailsForEstimate(approverDetailsDataId);

            pageStore.get(SpillOverEstimatePage.class).enterApproverDetails(approverDetails);

            pageStore.get(newSewerageConnectionPage.class).forward();

            scenarioContext.setApplicationNumber(pageStore.get(MarriageRegistrationPage.class).getApplicationNumber());

            scenarioContext.setActualMessage(pageStore.get(MarriageRegistrationPage.class).getSuccessMessage());

            pageStore.get(MarriageRegistrationPage.class).close();
        });
        And("^he choose to act upon the above new marriage application number$", () -> {
           pageStore.get(MarriageRegistrationPage.class).searchForMarriageRegistration();
           pageStore.get(MarriageRegistrationPage.class).selectAboveApplication(scenarioContext.getApplicationNumber());
        });
        And("^he approve the new marriage application  and close the acknowledgement$", () -> {
           pageStore.get(MarriageRegistrationPage.class).approve();

           scenarioContext.setRegistrationNumber(pageStore.get(MarriageRegistrationPage.class).getRegistrationNumber());

           scenarioContext.setActualMessage(pageStore.get(MarriageRegistrationPage.class).getSuccessMessage());

           pageStore.get(MarriageRegistrationPage.class).close();

        });
        And("^he enters the serial and page number$", () -> {
            pageStore.get(MarriageRegistrationPage.class).enterMarriageRegNum();
        });

        And("^he submit the data entry and he notified by \"([^\"]*)\"$", (String expectedMessage) -> {
            String actualMessage = scenarioContext.getActualMessage();
            pageStore.get(MarriageRegistrationPage.class).isSuccesful(expectedMessage,actualMessage);
        });

    }
}
