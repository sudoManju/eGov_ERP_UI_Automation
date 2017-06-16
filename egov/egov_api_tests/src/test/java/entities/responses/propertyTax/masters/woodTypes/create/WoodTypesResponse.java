package entities.responses.propertyTax.masters.woodTypes.create;

public class WoodTypesResponse {
    private ResponseInfo responseInfo;
    private WoodTypes[] woodTypes;

    public ResponseInfo getResponseInfo() {
        return this.responseInfo;
    }

    public void setResponseInfo(ResponseInfo responseInfo) {
        this.responseInfo = responseInfo;
    }

    public WoodTypes[] getWoodTypes() {
        return this.woodTypes;
    }

    public void setWoodTypes(WoodTypes[] woodTypes) {
        this.woodTypes = woodTypes;
    }
}
