package entities.responses.propertyTax.billingServices.demandService;

import entities.responses.propertyTax.billingServices.ResponseInfo;
import org.codehaus.jackson.annotate.JsonProperty;

public class DemandServiceResponse {

    @JsonProperty("ResponseInfo")
    private ResponseInfo responseInfo;

    @JsonProperty("Demands")
    private Demands[] Demands;

    public ResponseInfo getResponseInfo() {
        return this.responseInfo;
    }

    public void setResponseInfo(ResponseInfo ResponseInfo) {
        this.responseInfo = ResponseInfo;
    }

    public Demands[] getDemands() {
        return this.Demands;
    }

    public void setDemands(Demands[] Demands) {
        this.Demands = Demands;
    }
}
