package builders.assetManagement.assetService;

import entities.requests.assetManagement.assetServices.create.AssetFieldsDefination;

public class AssetFieldsDefinationBuilder {

    AssetFieldsDefination assetFieldsDefination = new AssetFieldsDefination();

    public AssetFieldsDefinationBuilder(int i){

        switch (i){

            case 0:

                assetFieldsDefination.setName("Land Register Number");
                assetFieldsDefination.setType("Text");
                assetFieldsDefination.setIsActive(true);
                assetFieldsDefination.setIsMandatory(true);

                break;


        }
    }

    public AssetFieldsDefination build(){
        return assetFieldsDefination;
    }
}
