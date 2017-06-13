package builders.assetManagement.assetCategory;

import entities.requests.assetManagement.assetCategory.AssetFieldsDefination;
import entities.requests.assetManagement.assetCategory.Columns;

public class AssetFieldsDefinationBuilder {

    AssetFieldsDefination assetFieldsDefination = new AssetFieldsDefination();

    Columns[] columnses = new Columns[2];

    Columns columns1 = new ColumnsBuilder().build();

    Columns columns2 = new ColumnsBuilder("").build();

    public AssetFieldsDefinationBuilder(String name){

        switch (name){

            case "shoppingComplex" :
                assetFieldsDefination.setName("Shopping complex name1");
                assetFieldsDefination.setType("Text");
                assetFieldsDefination.setIsActive(true);

                break;

            case "shoppingName" :
                assetFieldsDefination.setName("shopping complex no1");
                assetFieldsDefination.setType("Text");
                assetFieldsDefination.setIsActive(true);

                break;

            case "Floor" :
                assetFieldsDefination.setName("No of floors1");
                assetFieldsDefination.setType("text");
                assetFieldsDefination.setIsActive(true);

                break;

            case "Shops" :
                assetFieldsDefination.setName("No of shops1");
                assetFieldsDefination.setType("text");
                assetFieldsDefination.setIsActive(true);

                break;

            case "FloorDetails" :
                assetFieldsDefination.setName("Floor Details 1");
                assetFieldsDefination.setType("Table");
                columnses[0] = columns1;
                columnses[1] = columns2;
                assetFieldsDefination.setColumns(columnses);

                break;
        }
    }

    public AssetFieldsDefination build(){
        return assetFieldsDefination;
    }
}
