package entities.responses.propertyTax.billingServices.demandService;

import org.codehaus.jackson.annotate.JsonProperty;

public class DemandServiceResponse {

    @JsonProperty("ResponseInfo")
    private ResponseInfo ResponseInfo;

    @JsonProperty("Demands")
    private Demands[] Demands;

    public ResponseInfo getResponseInfo() {
        return this.ResponseInfo;
    }

    public void setResponseInfo(ResponseInfo ResponseInfo) {
        this.ResponseInfo = ResponseInfo;
    }

    public Demands[] getDemands() {
        return this.Demands;
    }

    public void setDemands(Demands[] Demands) {
        this.Demands = Demands;
    }
}
