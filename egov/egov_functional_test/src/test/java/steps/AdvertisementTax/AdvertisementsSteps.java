package steps.AdvertisementTax;

import cucumber.api.PendingException;
import cucumber.api.java8.En;
import entities.works.ApproverDetails;
import pages.AdvertisementTax.AdvertisementsPage;
import pages.DashboardPage;
import pages.works.SpillOverEstimatePage;
import steps.BaseSteps;
import utils.ExcelReader;


/**
 * Created by karthik on 11/1/17.
 */
public class AdvertisementsSteps extends BaseSteps implements En {

    public AdvertisementsSteps(){

        And("^he chooses to create advertisement$", () -> {
            pageStore.get(DashboardPage.class).chooseToCreateAdvertisement();
        });

        And("^he enters details for advertisement creation$", () -> {
           pageStore.get(AdvertisementsPage.class).enterAdvertisementDetails();

           pageStore.get(AdvertisementsPage.class).enterPermissionDetails1();

           pageStore.get(AdvertisementsPage.class).enterLocalityDetails();

           pageStore.get(AdvertisementsPage.class).enterStructureDetails();

           String approverDetailsDataId = "commissioner";

           ApproverDetails approverDetails = new ExcelReader(lineEstimateTestDataFileName).getApprovalDetailsForEstimate(approverDetailsDataId);

           pageStore.get(SpillOverEstimatePage.class).enterApproverDetails(approverDetails);

        });
        And("^he forwards and closes the acknowledgement$", () -> {
            String number = pageStore.get(AdvertisementsPage.class).forward();
            scenarioContext.setApplicationNumber(number);

            String actualMsg = pageStore.get(AdvertisementsPage.class).successMessage();
            scenarioContext.setActualMessage(actualMsg);

            pageStore.get(AdvertisementsPage.class).close();

        });
        And("^he clicks on advertisement and opens the application$", () -> {
           pageStore.get(AdvertisementsPage.class).selectAdvertisementTag(scenarioContext.getApplicationNumber());
        });
        And("^he approves the advertisement application$", () -> {
            pageStore.get(AdvertisementsPage.class).approverComment();
            pageStore.get(AdvertisementsPage.class).approve();

            String advertisementNumber = pageStore.get(AdvertisementsPage.class).getAdvertisementNumber();
            scenarioContext.setApplicationNumber(advertisementNumber);

            String actualMsg = pageStore.get(AdvertisementsPage.class).successMessage();
            scenarioContext.setActualMessage(actualMsg);

            pageStore.get(AdvertisementsPage.class).close();
        });
        And("^he chooses to search advertisement$", () -> {
           pageStore.get(DashboardPage.class).chooseToSearchAdvertisement();
        });
        And("^he search and select the required advertisement$", () -> {
            pageStore.get(AdvertisementsPage.class).searchAndSelect(scenarioContext.getApplicationNumber());
        });
        And("^he view and close the acknowledgement$", () -> {
            String url = "http://kurnool-uat.egovernments.org/adtax/hoarding/adtax-search";
            pageStore.get(AdvertisementsPage.class).closeMultipleWindows(url);
//            pageStore.get(AdvertisementsPage.class).closeMultipleWindows();
        });
        And("^he choose to collect advertisement tax by advertisement wise$", () -> {
            pageStore.get(DashboardPage.class).chooseToCollectAdvTax();
        });
        And("^he search advertisement by advertisement number$", () -> {
            pageStore.get(AdvertisementsPage.class).searchByAdvertisementNumber(scenarioContext.getApplicationNumber());
        });
        And("^he choose advertisement for collecting advertisement tax$", () -> {
            pageStore.get(AdvertisementsPage.class).collectAdvertisementTax();
           // pageStore.get(AdvertisementsPage.class).closeMultiple("http://kurnool-uat.egovernments.org/adtax/hoarding/search");
        });
        And("^he chooses to create advertisement agency$", () -> {
            pageStore.get(DashboardPage.class).chooseToCreateAdvertisementAgency();
        });
        And("^he enter details for agency creation$", () -> {
           String name = pageStore.get(AdvertisementsPage.class).enterAgencyDetails();
           scenarioContext.setAssessmentNumber(name);
        });
        And("^he choose to collect advertisement tax by agency wise$", () -> {
            pageStore.get(AdvertisementsPage.class).searchByAgency(scenarioContext.getAssessmentNumber());

        });
        And("^he selects the agency for Tax/Fees collection$", () -> {
            pageStore.get(AdvertisementsPage.class).selectAdvertisementAgency();
        });
        And("^he choose to collect advertisement tax$", () -> {
            pageStore.get(AdvertisementsPage.class).collectAdvertisementTaxByAgency();
            pageStore.get(AdvertisementsPage.class).closeMultiple("http://kurnool-uat.egovernments.org/adtax/hoarding/search");

        });
        And("^he submit the details and closes acknowledgement$", () -> {
           pageStore.get(AdvertisementsPage.class).submit();

           String message = pageStore.get(AdvertisementsPage.class).agencyCreationMessage();
           scenarioContext.setActualMessage(message);

           pageStore.get(AdvertisementsPage.class).CloseAgency();
        });
        And("^he chooses to search advertisement agency$", () -> {
         pageStore.get(DashboardPage.class).chooseToSearchAdvertisementAgency();
        });
        And("^he enter details for search agency$", () -> {
           pageStore.get(AdvertisementsPage.class).searchAgency(scenarioContext.getAssessmentNumber());
        });
        And("^he view and closes the acknowledgement$", () -> {
           pageStore.get(AdvertisementsPage.class).CloseAgencySearch();
        });

        And("^he enters details for advertisement creation with agency$", () -> {
            pageStore.get(AdvertisementsPage.class).enterAdvertisementDetails();

            pageStore.get(AdvertisementsPage.class).enterPermissionDetails(scenarioContext.getAssessmentNumber());

            pageStore.get(AdvertisementsPage.class).enterLocalityDetails();

            pageStore.get(AdvertisementsPage.class).enterStructureDetails();

            String approverDetailsDataId = "commissioner";

            ApproverDetails approverDetails = new ExcelReader(lineEstimateTestDataFileName).getApprovalDetailsForEstimate(approverDetailsDataId);

            pageStore.get(SpillOverEstimatePage.class).enterApproverDetails(approverDetails);
        });

        And("^he search for advertisement for deactivate$", () -> {
            pageStore.get(AdvertisementsPage.class).searchAdvertisementForDeactivate(scenarioContext.getApplicationNumber());
        });
        And("^he deactivates the advertisement with remarks and date$", () -> {
            pageStore.get(AdvertisementsPage.class).deactivatesAdvertisement();
            String message =  pageStore.get(AdvertisementsPage.class).successMessageForDeactivation();
            scenarioContext.setActualMessage(message);
        });
        And("^user closes the acknowledgement pages$", () -> {
            String url ="http://kurnool-uat.egovernments.org/adtax/deactivate/search";
            pageStore.get(AdvertisementsPage.class).closeMultipleWindowsForDeactivateadvertisement(url);
        });

    }

}
