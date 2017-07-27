package builders.assetManagement.assetService;

import entities.requests.assetManagement.assetServices.create.AssetCategory;
import entities.requests.assetManagement.assetServices.create.AssetFieldsDefination;

public class AssetCategoryBuilder {

AssetCategory assetCategory = new AssetCategory();
AssetFieldsDefination[] assetFieldsDefinations = new AssetFieldsDefination[1];

public AssetCategoryBuilder(String category){
   assetCategory.setTenantId("default");

   switch (category){

       case "Land" :
           assetCategory.setName("Land");
           assetCategory.setId(1);
           assetCategory.setCode("006");
           assetCategory.setAssetCategoryType("Land");
           for (int i=0;i<1;i++) {
               assetFieldsDefinations[i] = new AssetFieldsDefinationBuilder(i).build();
           }
           assetCategory.setAssetFieldsDefination(assetFieldsDefinations);
               break;
   }

   assetCategory.setVersion("1.0");
   assetCategory.setDepreciationMethod("STRAIGHT_LINE_METHOD");
   assetCategory.setIsAssetAllow(false);
}

public AssetCategory build(){
    return assetCategory;
}
}
