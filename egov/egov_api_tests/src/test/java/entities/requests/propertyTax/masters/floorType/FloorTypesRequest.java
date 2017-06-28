package entities.requests.propertyTax.masters.floorType;

import entities.requests.propertyTax.masters.RequestInfo;
import org.codehaus.jackson.annotate.JsonProperty;

public class FloorTypesRequest {

    private FloorTypes[] floorTypes;

    @JsonProperty("RequestInfo")
    private RequestInfo RequestInfo;

    public FloorTypes[] getFloorTypes ()
    {
        return floorTypes;
    }

    public void setFloorTypes (FloorTypes[] floorTypes)
    {
        this.floorTypes = floorTypes;
    }

    public RequestInfo getRequestInfo ()
    {
        return RequestInfo;
    }

    public void setRequestInfo (RequestInfo RequestInfo)
    {
        this.RequestInfo = RequestInfo;
    }

}
