package steps.AdvertisementTax;

import cucumber.api.PendingException;
import cucumber.api.java8.En;
import pages.AdvertisementTax.AdvertisementsPage;
import pages.DashboardPage;
import steps.BaseSteps;


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

           pageStore.get(AdvertisementsPage.class).enterPermissionDetails();
        });
    }

}
