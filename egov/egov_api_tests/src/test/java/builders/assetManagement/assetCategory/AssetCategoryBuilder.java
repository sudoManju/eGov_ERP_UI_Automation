package builders.assetManagement.assetCategory;

import entities.requests.assetManagement.assetCategory.AssetCategory;
import entities.requests.assetManagement.assetCategory.AssetFieldsDefination;

public class AssetCategoryBuilder {

    AssetCategory assetCategory = new AssetCategory();

    AssetFieldsDefination[] assetFieldsDefinations = new AssetFieldsDefination[5];

    AssetFieldsDefination assetFieldsDefination1 = new AssetFieldsDefinationBuilder("shoppingComplex").build();
    AssetFieldsDefination assetFieldsDefination2 = new AssetFieldsDefinationBuilder("shoppingName").build();
    AssetFieldsDefination assetFieldsDefination3 = new AssetFieldsDefinationBuilder("Floor").build();
    AssetFieldsDefination assetFieldsDefination4 = new AssetFieldsDefinationBuilder("Shops").build();
    AssetFieldsDefination assetFieldsDefination5 = new AssetFieldsDefinationBuilder("FloorDetails").build();

    public AssetCategoryBuilder() {
        assetCategory.setTenantId("ap.kurnool");
        assetCategory.setAssetCategoryType("MOVABLE");
        assetCategory.setDepreciationMethod("STRAIGHT_LINE_METHOD");
        assetCategory.setIsAssetAllow(true);
        assetCategory.setAssetAccount(3);
        assetCategory.setAccumulatedDepreciationAccount(3);
        assetCategory.setRevaluationReserveAccount(6);
        assetCategory.setDepreciationExpenseAccount(4);
        assetCategory.setUnitOfMeasurement(11);
        assetCategory.setVersion("v2");
        assetFieldsDefinations[0] = assetFieldsDefination1;
        assetFieldsDefinations[1] = assetFieldsDefination2;
        assetFieldsDefinations[2] = assetFieldsDefination3;
        assetFieldsDefinations[3] = assetFieldsDefination4;
        assetFieldsDefinations[4] = assetFieldsDefination5;
        assetCategory.setAssetFieldsDefination(assetFieldsDefinations);
    }

    public AssetCategoryBuilder withName(String name) {
        assetCategory.setName(name);
        return this;
    }

    public AssetCategory build() {
        return assetCategory;
    }
}
