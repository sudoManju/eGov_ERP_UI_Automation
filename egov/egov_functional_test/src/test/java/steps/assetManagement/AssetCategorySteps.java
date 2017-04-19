package steps.assetManagement;


import cucumber.api.java8.En;
import entities.assetManagement.AssetCategoryDetails;
import entities.assetManagement.CustomFieldsDetails;
import excelDataFiles.AssetCategoryDataReader;
import pages.assetManagement.AssetCategoryPage;
import steps.BaseSteps;

public class AssetCategorySteps extends BaseSteps implements En {

    public AssetCategorySteps(){

        And("^user will enter the details of asset category as (\\w+)$", (String assetCategory) -> {
            AssetCategoryDetails details = new AssetCategoryDataReader(assetTestDataFileName)
                                                                 .getAssetCategoryDetails(assetCategory);
            pageStore.get(AssetCategoryPage.class).enterAssetCategoryDetails(details);
        });
        And("^user will enter the details of custom fields as (\\w+)$", (String customFields) -> {

            pageStore.get(AssetCategoryPage.class).clickToCreateCustomFields();

            CustomFieldsDetails details = new AssetCategoryDataReader(assetTestDataFileName)
                                                                 .getCustomFieldsDetails(customFields);
            pageStore.get(AssetCategoryPage.class).enterCustomFieldsDetails(details);

            pageStore.get(AssetCategoryPage.class).addOrEditCustomFieldsButton();
        });
        And("^user create the asset category$", () -> {
           pageStore.get(AssetCategoryPage.class).clickOnCreateAssetCategoryButton();
        });
    }

}
