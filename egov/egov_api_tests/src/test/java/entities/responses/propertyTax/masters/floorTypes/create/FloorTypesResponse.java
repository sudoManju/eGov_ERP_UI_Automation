package entities.responses.propertyTax.masters.floorTypes.create;

import entities.responses.propertyTax.masters.ResponseInfo;
import org.codehaus.jackson.annotate.JsonProperty;

public class FloorTypesResponse {

    @JsonProperty("FloorTypes")
    private FloorTypes[] floorTypes;

    @JsonProperty("ResponseInfo")
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
