package steps.tradeLicense;

import cucumber.api.PendingException;
import cucumber.api.java8.En;
import pages.tradeLicense.LicenseMastersPage;
import steps.BaseSteps;

/**
 * Created by tester1 on 6/13/2017.
 */
public class LicenseMastersSteps extends BaseSteps implements En {
    public LicenseMastersSteps(){

        And("^he creates the license category$", () -> {
            scenarioContext.setActualMessage(pageStore.get(LicenseMastersPage.class).createLicenseMaster());
        });
        And("^he closes the view page$", () -> {
            pageStore.get(LicenseMastersPage.class).closeViewPage();
        });

        And("^he creates the Unit Of Measurement$", () -> {
            scenarioContext.setActualMessage(pageStore.get(LicenseMastersPage.class).createLicenseMaster());
        });

        And("^he selects the above created category$", () -> {
            pageStore.get(LicenseMastersPage.class).selectCategory(scenarioContext.getLicenseCategory());
        });
        And("^he enters fee type as \"([^\"]*)\" Rate Type as \"([^\"]*)\" and UOM$", (String feeType, String rateType) -> {
            pageStore.get(LicenseMastersPage.class).enterDetails(feeType,rateType,scenarioContext.getUOM());
            scenarioContext.setFeeRateType(rateType);
        });
        And("^he creates the Sub Category$", () -> {
            scenarioContext.setActualMessage(pageStore.get(LicenseMastersPage.class).createLicenseMaster());
        });
        And("^he checks the Active checkbox$", () -> {
            pageStore.get(LicenseMastersPage.class).UOMActive();
        });

        And("^he close ack page$", () -> {
            pageStore.get(LicenseMastersPage.class).closeSubCategoryViewPage();
        });
        And("^he enters fee matrix details$", () -> {
            pageStore.get(LicenseMastersPage.class).enterFeeMatrixDetails(scenarioContext.getLicenseCategory(),scenarioContext.getLicenseSubCategory());
        });
        And("^he choose to enter category details$", () -> {
            scenarioContext.setLicenseCategory(pageStore.get(LicenseMastersPage.class).masterNameCode());
        });
        And("^he choose to enter sub category details$", () -> {
            scenarioContext.setLicenseSubCategory(pageStore.get(LicenseMastersPage.class).masterNameCode());
        });
        And("^he choose to enter create UOM details$", () -> {
            scenarioContext.setUOM(pageStore.get(LicenseMastersPage.class).masterNameCode());
        });
        And("^he creates the fee matrix$", () -> {
            scenarioContext.setActualMessage(pageStore.get(LicenseMastersPage.class).createLicenseMaster());

        });
        And("^he closes the View Fee Matrix page$", () -> {
            pageStore.get(LicenseMastersPage.class).closeViewFeeMatrix();
        });
        And("^he enters document type details$", () -> {
            scenarioContext.setActualMessage(pageStore.get(LicenseMastersPage.class).enterDocumentTypeDetails());
        });


    }
    }
