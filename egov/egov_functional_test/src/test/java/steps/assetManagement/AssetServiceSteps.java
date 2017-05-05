package steps.assetManagement;

import cucumber.api.java8.En;
import entities.assetManagement.assetService.HeaderDetails;
import entities.assetManagement.assetService.LocationDetails;
import excelDataFiles.AssetServiceDataReader;
import pages.assetManagement.AssetServicePage;
import steps.BaseSteps;

public class AssetServiceSteps extends BaseSteps implements En {

    public AssetServiceSteps() {

        And("^user will enter the details as (\\w+) and (\\w+)$", (String headerDetails
                , String locationDetails) -> {

            HeaderDetails headerDetails1 = new AssetServiceDataReader(assetTestDataFileName)
                    .getHeaderDetails(headerDetails);
            pageStore.get(AssetServicePage.class).enterHeaderDetails(headerDetails1);

            LocationDetails locationDetails1 = new AssetServiceDataReader(assetTestDataFileName)
                    .getLocationDetails(locationDetails);
            pageStore.get(AssetServicePage.class).enterLocationDetails(locationDetails1);

        });
        And("^user will enter the category details as (\\w+) and with asset summary status as (\\w+)$", (
                String categoryDetails, String assetStatus) -> {

            pageStore.get(AssetServicePage.class).enterCategoryDetails(categoryDetails);
            pageStore.get(AssetServicePage.class).enterAssetStatusDetails(assetStatus);
        });

    }
}

