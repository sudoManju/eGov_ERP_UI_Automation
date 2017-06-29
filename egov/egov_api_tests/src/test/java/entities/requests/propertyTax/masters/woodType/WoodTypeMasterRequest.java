package entities.requests.propertyTax.masters.woodType;

import entities.requests.propertyTax.masters.RequestInfo;
import org.codehaus.jackson.annotate.JsonProperty;

public class WoodTypeMasterRequest {

    @JsonProperty("RequestInfo")
    private RequestInfo RequestInfo;

    private WoodTypes[] woodTypes;

    public RequestInfo getRequestInfo() {
        return this.RequestInfo;
    }

    public void setRequestInfo(RequestInfo RequestInfo) {
        this.RequestInfo = RequestInfo;
    }

    public WoodTypes[] getWoodTypes() {
        return this.woodTypes;
    }

    public void setWoodTypes(WoodTypes[] woodTypes) {
        this.woodTypes = woodTypes;
    }
}
