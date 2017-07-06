package entities.responses.propertyTax.masters.roofTypes.create;

import entities.responses.propertyTax.masters.ResponseInfo;

public class RoofTypeMasterResponse {
    private RoofTypes[] roofTypes;
    private ResponseInfo responseInfo;

    public RoofTypes[] getRoofTypes() {
        return this.roofTypes;
    }

    public void setRoofTypes(RoofTypes[] roofTypes) {
        this.roofTypes = roofTypes;
    }

    public ResponseInfo getResponseInfo() {
        return this.responseInfo;
    }

    public void setResponseInfo(ResponseInfo responseInfo) {
        this.responseInfo = responseInfo;
    }
}
