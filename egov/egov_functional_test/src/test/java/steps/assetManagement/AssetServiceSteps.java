package steps.assetManagement;

import cucumber.api.java8.En;
import entities.assetManagement.assetService.HeaderDetails;
import entities.assetManagement.assetService.LocationDetails;
import excelDataFiles.AssetServiceDataReader;
import pages.assetManagement.AssetServicePage;
import steps.BaseSteps;

public class AssetServiceSteps extends BaseSteps implements En {

    public AssetServiceSteps() {

        And("^user will enter the details (\\w+) and (\\w+) with asset summary status as (\\w+)$", (String headerDetails
                , String locationDetails, String assetStatus) -> {

            HeaderDetails headerDetails1 = new AssetServiceDataReader(assetTestDataFileName)
                    .getHeaderDetails(headerDetails);
            pageStore.get(AssetServicePage.class).enterHeaderDetails(headerDetails1);

            LocationDetails locationDetails1 = new AssetServiceDataReader(assetTestDataFileName)
                    .getLocationDetails(locationDetails);
            pageStore.get(AssetServicePage.class).enterLocationDetails(locationDetails1);

            pageStore.get(AssetServicePage.class).enterAssetStatusDetails(assetStatus);
        });

    }
}
