package builders.assetManagement.assetService;

import entities.requests.assetManagement.assetServices.create.AssetAttributes;

public class AssetAttributesBuilder {

AssetAttributes assetAttributes = new AssetAttributes();

public AssetAttributesBuilder(int i){

    switch (i){

        case 0:

            assetAttributes.setKey("EAST");
            assetAttributes.setType("Text");

            break;

        case 1:

            assetAttributes.setKey("South");
            assetAttributes.setType("Text");

            break;
    }
}

public AssetAttributes build(){
    return assetAttributes;
}
}
