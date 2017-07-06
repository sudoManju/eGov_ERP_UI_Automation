package entities.responses.propertyTax.masters.wallTypes.search;

import entities.responses.propertyTax.masters.ResponseInfo;

public class SearchWallTypesMasterResponse {
    private WallTypes[] wallTypes;
    private ResponseInfo responseInfo;

    public WallTypes[] getWallTypes() {
        return this.wallTypes;
    }

    public void setWallTypes(WallTypes[] wallTypes) {
        this.wallTypes = wallTypes;
    }

    public ResponseInfo getResponseInfo() {
        return this.responseInfo;
    }

    public void setResponseInfo(ResponseInfo responseInfo) {
        this.responseInfo = responseInfo;
    }
}
