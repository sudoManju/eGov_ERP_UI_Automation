package builders.assetManagement.assetService;


import entities.requests.assetManagement.assetServices.create.*;

public class AssetBuilder {

    Asset asset = new Asset();
    AssetAttributes[] assetAttributes = new AssetAttributes[3];
    YearWiseDepreciation[] yearWiseDepreciations = new YearWiseDepreciation[3];
    LocationDetails locationDetails = new LocationDetailsBuilder().build();
    Department department = new DepartmentsBuilder().build();
    AssetCategory assetCategory = new AssetCategory();

    public AssetBuilder(String category) {
        asset.setTenantId("default");
        asset.setDepartment(department);
        switch (category) {

            case "Land":
                asset.setName("Land");
                asset.setAssetDetails("Land Asset Details");
                asset.setStatus("CAPITALIZED");
                asset.setDescription("Land Asset Created");
                asset.setDateOfCreation("5648954");
                asset.setGrossValue(10000);
                asset.setAccumulatedDepreciation(10.5);
                asset.setAssetReference(5);
                asset.setEnableYearWiseDepreciation(false);
                for (int i=0;i<3;i++){
                    assetAttributes[i] = new AssetAttributesBuilder(i).build();
                }
                assetCategory = new AssetCategoryBuilder("Land").build();
                asset.setAssetCategory(assetCategory);
                asset.setAssetAttributes(assetAttributes);
                asset.setLocationDetails(locationDetails);
                break;

            case "Market":
                asset.setName("Market");
                asset.setAssetDetails("Market Asset Details");
                asset.setStatus("CAPITALIZED");
                asset.setDescription("Land Asset Created");
                asset.setDateOfCreation("5648954");
                asset.setGrossValue(15000);
                asset.setAccumulatedDepreciation(1500);
                asset.setAssetReference(5);
                asset.setEnableYearWiseDepreciation(true);

                for (int i=0;i<3;i++){
                    assetAttributes[i] = new AssetAttributesBuilder(i).build();
                    yearWiseDepreciations[i] = new YearWiseDepreciationBuilder(i).build();
                }
                assetCategory = new AssetCategoryBuilder("Market").build();
                asset.setAssetCategory(assetCategory);
                asset.setAssetAttributes(assetAttributes);
                asset.setLocationDetails(locationDetails);
                break;
        }
    }

    public AssetBuilder withName(String name){
        asset.setName(name);
        return this;
    }
    public Asset build() {
        return asset;
    }
}
