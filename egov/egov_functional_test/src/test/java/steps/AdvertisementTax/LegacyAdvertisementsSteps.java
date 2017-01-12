package steps.AdvertisementTax;

import cucumber.api.PendingException;
import cucumber.api.java8.En;
import pages.AdvertisementTax.AdvertisementsPage;
import pages.AdvertisementTax.LegacyAdvertisementsPage;
import pages.DashboardPage;
import steps.BaseSteps;

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
            String url = "http://kurnool-uat.egovernments.org/adtax/hoarding/findhoarding-for-update";
            pageStore.get(AdvertisementsPage.class).closeMultipleWindows(url);
        });

    }
}
