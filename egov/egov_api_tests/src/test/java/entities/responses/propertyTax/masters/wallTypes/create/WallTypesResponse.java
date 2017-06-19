package entities.responses.propertyTax.masters.wallTypes.create;

public class WallTypesResponse {
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
