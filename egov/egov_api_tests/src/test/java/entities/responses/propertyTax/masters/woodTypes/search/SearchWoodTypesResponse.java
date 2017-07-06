package entities.responses.propertyTax.masters.woodTypes.search;

import entities.responses.propertyTax.masters.ResponseInfo;
import org.codehaus.jackson.annotate.JsonProperty;

public class SearchWoodTypesResponse {

    private ResponseInfo responseInfo;

    @JsonProperty("WoodTypes")
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
