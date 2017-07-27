package builders.assetManagement.assetService;

import entities.requests.assetManagement.assetServices.create.AssetAttributes;

public class AssetAttributesBuilder {

AssetAttributes assetAttributes = new AssetAttributes();

public AssetAttributesBuilder(int i){

    switch (i){

        case 0:

            assetAttributes.setKey("North");
            assetAttributes.setType("Text");

            break;

        case 1:

            assetAttributes.setKey("East");
            assetAttributes.setType("Text");

            break;
        case 2:

            assetAttributes.setKey("West");
            assetAttributes.setType("Text");

            break;

        case 3:

            assetAttributes.setKey("South");
            assetAttributes.setType("Text");

            break;
    }
}

public AssetAttributes build(){
    return assetAttributes;
}
}
