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
            case 1:
                assetFieldsDefination.setName("Old Land Register Number");
                assetFieldsDefination.setType("Text");
                assetFieldsDefination.setIsActive(true);
                assetFieldsDefination.setIsMandatory(false);
                break;
            case 2:
                assetFieldsDefination.setName("Land Type");
                assetFieldsDefination.setType("Select");
                assetFieldsDefination.setIsActive(true);
                assetFieldsDefination.setIsMandatory(true);
                assetFieldsDefination.setValues("Hold,Free Hold");
                break;
            case 3:
                assetFieldsDefination.setName("Unit of Measurement");
                assetFieldsDefination.setType("Select");
                assetFieldsDefination.setValues("sq. ft.,sq. mt.");
                assetFieldsDefination.setIsActive(true);
                assetFieldsDefination.setIsMandatory(false);
                break;
            case 4:
                assetFieldsDefination.setName("Date of Deed Execution");
                assetFieldsDefination.setType("Date");
                assetFieldsDefination.setIsActive(true);
                assetFieldsDefination.setIsMandatory(false);
                break;
        }
    }

    public AssetFieldsDefination build(){
        return assetFieldsDefination;
    }
}
