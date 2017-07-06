package entities.responses.propertyTax.masters.floorTypes.search;

import entities.responses.propertyTax.masters.ResponseInfo;

public class SearchFloorTypesResponse {
    private FloorTypes[] floorTypes;
    private ResponseInfo responseInfo;

    public FloorTypes[] getFloorTypes() {
        return this.floorTypes;
    }

    public void setFloorTypes(FloorTypes[] floorTypes) {
        this.floorTypes = floorTypes;
    }

    public ResponseInfo getResponseInfo() {
        return this.responseInfo;
    }

    public void setResponseInfo(ResponseInfo responseInfo) {
        this.responseInfo = responseInfo;
    }
}
