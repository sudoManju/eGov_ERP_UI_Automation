package entities.requests.propertyTax.masters.occupancy;

import entities.requests.propertyTax.masters.RequestInfo;
import org.codehaus.jackson.annotate.JsonProperty;

public class CreateOccupancyMasterRequest {

    private OccuapancyMasters[] occuapancyMasters;

    @JsonProperty("RequestInfo")
    private RequestInfo RequestInfo;

    public OccuapancyMasters[] getOccuapancyMasters() {
        return this.occuapancyMasters;
    }

    public void setOccuapancyMasters(OccuapancyMasters[] occuapancyMasters) {
        this.occuapancyMasters = occuapancyMasters;
    }

    public RequestInfo getRequestInfo() {
        return this.RequestInfo;
    }

    public void setRequestInfo(RequestInfo RequestInfo) {
        this.RequestInfo = RequestInfo;
    }
}
