package entities.requests.propertyTax.masters.roofType;

import entities.requests.propertyTax.masters.RequestInfo;
import org.codehaus.jackson.annotate.JsonProperty;

public class RoofTypeMasterRequest {

    @JsonProperty("RequestInfo")
    private RequestInfo requestInfo;

    private RoofTypes[] roofTypes;

    public RequestInfo getRequestInfo() {
        return requestInfo;
    }

    public void setRequestInfo(RequestInfo requestInfo) {
        this.requestInfo = requestInfo;
    }

    public RoofTypes[] getRoofTypes() {
        return roofTypes;
    }

    public void setRoofTypes(RoofTypes[] roofTypes) {
        this.roofTypes = roofTypes;
    }
}
