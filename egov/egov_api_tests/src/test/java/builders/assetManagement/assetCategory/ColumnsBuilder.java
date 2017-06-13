package builders.assetManagement.assetCategory;

import entities.requests.assetManagement.assetCategory.Columns;

public class ColumnsBuilder {

    Columns columns = new Columns();

    public ColumnsBuilder() {
        columns.setName("Floor 1");
        columns.setType("Text");
    }

    public ColumnsBuilder(String na) {
        columns.setName("No of shops");
        columns.setType("Text");
    }

    public Columns build() {
        return columns;
    }
}
