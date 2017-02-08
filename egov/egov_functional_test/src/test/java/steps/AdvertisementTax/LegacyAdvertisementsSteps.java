package steps.AdvertisementTax;

import cucumber.api.PendingException;
import cucumber.api.java8.En;
import entities.works.ApproverDetails;
import pages.AdvertisementTax.AdvertisementsPage;
import pages.AdvertisementTax.LegacyAdvertisementsPage;
import pages.DashboardPage;
import pages.works.SpillOverEstimatePage;
import steps.BaseSteps;
import utils.ExcelReader;

/**
 * Created by karthik on 12/1/17.
 */
public class LegacyAdvertisementsSteps extends BaseSteps implements En {

    public LegacyAdvertisementsSteps(){

        And("^he chooses to create legacy advertisements$", () -> {
            pageStore.get(DashboardPage.class).chooseForCreateLegacyAdvertisements();
        });
        And("^he enters details for legacy advertisement creation$", () -> {
            pageStore.get(AdvertisementsPage.class).enterAdvertisementDetails();

            pageStore.get(AdvertisementsPage.class).enterPermissionDetails();

            pageStore.get(AdvertisementsPage.class).enterLocalityDetails();

            pageStore.get(AdvertisementsPage.class).enterStructureDetails();

            pageStore.get(LegacyAdvertisementsPage.class).enterArrearsTaxDetails();
        });
        And("^he submit the application and closes the acknowledgement$", () -> {
            String applicationNumber = pageStore.get(LegacyAdvertisementsPage.class).submit();
            scenarioContext.setApplicationNumber(applicationNumber);

            String actualMessage = pageStore.get(LegacyAdvertisementsPage.class).successMessage();
            scenarioContext.setActualMessage(actualMessage);

            pageStore.get(LegacyAdvertisementsPage.class).close();
        });
        And("^he chooses to update legacy advertisements$", () -> {
          pageStore.get(DashboardPage.class).chooseToUpdateLegacyAdvertisements();
        });
        And("^he search for required file by application number$", () -> {
            pageStore.get(LegacyAdvertisementsPage.class).searchFile(scenarioContext.getApplicationNumber());

            pageStore.get(LegacyAdvertisementsPage.class).updateLegacyAd();
        });
        And("^he update the legacy advertisement and close the acknowledgement$", () -> {
            pageStore.get(LegacyAdvertisementsPage.class).update();
            pageStore.get(LegacyAdvertisementsPage.class).submit();

            String actualMsg = pageStore.get(LegacyAdvertisementsPage.class).successMessage();
            scenarioContext.setActualMessage(actualMsg);

            pageStore.get(AdvertisementsPage.class).closeMultipleWindows("/adtax/hoarding/findhoarding-for-update");
        });
        And("^he chooses to renewal advertisement$", () -> {
           pageStore.get(DashboardPage.class).choosesToRenewalAdvertisement();
        });
        And("^he search for required file by application number for renewal$", () -> {
            pageStore.get(LegacyAdvertisementsPage.class).searchFileForRenewal(scenarioContext.getApplicationNumber());
        });
        And("^he request for renewal and forward to commissioner$", () -> {
            pageStore.get(LegacyAdvertisementsPage.class).requestForRenewal();

            String approverDetailsDataId = "commissioner";

            ApproverDetails approverDetails = new ExcelReader(lineEstimateTestDataFileName).getApprovalDetailsForEstimate(approverDetailsDataId);

            pageStore.get(SpillOverEstimatePage.class).enterApproverDetails(approverDetails);

            String number = pageStore.get(AdvertisementsPage.class).forward();
            scenarioContext.setApplicationNumber(number);

            String actualMsg = pageStore.get(AdvertisementsPage.class).successMessage();
            String num = actualMsg.substring(actualMsg.length()-1);
            scenarioContext.setActualMessage(actualMsg);
            scenarioContext.setAssessmentNumber(num);

            pageStore.get(AdvertisementsPage.class).closeMultipleWindows("/adtax/hoarding/renewal-search");
        });

        And("^he opens the required application$", () -> {
            pageStore.get(AdvertisementsPage.class).selectAdvertisementTag(scenarioContext.getAssessmentNumber());
        });

    }
}
