package entities.requests.propertyTax.billingServices.demandService;

import entities.requests.propertyTax.RequestInfo;
import org.codehaus.jackson.annotate.JsonProperty;

public class DemandServiceRequest {

    private Demands[] Demands;

    @JsonProperty("RequestInfo")
    private RequestInfo requestInfo;

    public Demands[] getDemands() {
        return this.Demands;
    }

    public void setDemands(Demands[] Demands) {
        this.Demands = Demands;
    }

    public RequestInfo getRequestInfo() {
        return this.requestInfo;
    }

    public void setRequestInfo(RequestInfo RequestInfo) {
        this.requestInfo = RequestInfo;
    }
}
