package builders.AssetManagement;

import entities.requests.AssetManagement.AssetCategory;
import entities.requests.AssetManagement.CustomFields;

public final class AssetCategoryBuilder {
    AssetCategory assetCategory = new AssetCategory();
    CustomFields customFields1 = new CustomFieldsBuilder()
                                 .withName("Land")
                                 .withType("String")
                                 .withIsActive("true")
                                 .withIsMandatory("true")
                                 .withValues("abc")
                                 .withLocalText("localtext")
                                 .withRegExFormate("regex")
                                 .build();

    CustomFields customFields2 = new CustomFieldsBuilder()
                                .withName("PROPERTY")
                                .withType("String")
                                .withIsActive("true")
                                .withIsMandatory("true")
                                .withValues("abc")
                                .withLocalText("localtext")
                                .withRegExFormate("regex")
                                .build();


    public AssetCategoryBuilder() {
        assetCategory.setAssetCategoryType("MOVABLE");
        assetCategory.setTenantId("1");
//        assetCategory.setDepreciationRate();
        assetCategory.setDepreciationMethod("STRAIGHT_LINE_METHOD");
        assetCategory.setUnitOfMeasurement("10");
        assetCategory.setRevaluationReserveAccount("8");
        assetCategory.setAccumulatedDepreciationAccount("7");
        assetCategory.setCustomFields(customFields1);
        assetCategory.setCustomFields(customFields2);
        assetCategory.setDepreciationExpenseAccount("9");
        assetCategory.setName("Street lighting");
        assetCategory.setParent("32");
        assetCategory.setAssetAccount("6");
    }

    public AssetCategoryBuilder withAssetCategoryType(String assetCategoryType) {
        assetCategory.setAssetCategoryType(assetCategoryType);
        return this;
    }

    public AssetCategoryBuilder withTenantId(String tenantId) {
        assetCategory.setTenantId(tenantId);
        return this;
    }

    public AssetCategoryBuilder withDepreciationRate(String[] depreciationRate) {
        assetCategory.setDepreciationRate(depreciationRate);
        return this;
    }

    public AssetCategoryBuilder withDepreciationMethod(String depreciationMethod) {
        assetCategory.setDepreciationMethod(depreciationMethod);
        return this;
    }

    public AssetCategoryBuilder withUnitOfMeasurement(String unitOfMeasurement) {
        assetCategory.setUnitOfMeasurement(unitOfMeasurement);
        return this;
    }

    public AssetCategoryBuilder withRevaluationReserveAccount(String revaluationReserveAccount) {
        assetCategory.setRevaluationReserveAccount(revaluationReserveAccount);
        return this;
    }

    public AssetCategoryBuilder withAccumulatedDepreciationAccount(String accumulatedDepreciationAccount) {
        assetCategory.setAccumulatedDepreciationAccount(accumulatedDepreciationAccount);
        return this;
    }

    public AssetCategoryBuilder withCustomFields(CustomFields customFields) {
        assetCategory.setCustomFields(customFields);
        return this;
    }

    public AssetCategoryBuilder withDepreciationExpenseAccount(String depreciationExpenseAccount) {
        assetCategory.setDepreciationExpenseAccount(depreciationExpenseAccount);
        return this;
    }

    public AssetCategoryBuilder withName(String name) {
        assetCategory.setName(name);
        return this;
    }

    public AssetCategoryBuilder withParent(String parent) {
        assetCategory.setParent(parent);
        return this;
    }

    public AssetCategoryBuilder withAssetAccount(String assetAccount) {
        assetCategory.setAssetAccount(assetAccount);
        return this;
    }

    public AssetCategory build() {
        return assetCategory;
    }
}
