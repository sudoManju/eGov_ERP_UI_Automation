package entities.responses.propertyTax.billingServices.demandDetails;

import entities.responses.propertyTax.billingServices.ResponseInfo;
import org.codehaus.jackson.annotate.JsonProperty;

public class DemandDetailsResponse {

    @JsonProperty("ResponseInfo")
    private ResponseInfo responseInfo;

    @JsonProperty("DemandDetails")
    private DemandDetails[] DemandDetails;

    public ResponseInfo getResponseInfo() {
        return this.responseInfo;
    }

    public void setResponseInfo(ResponseInfo ResponseInfo) {
        this.responseInfo = ResponseInfo;
    }

    public DemandDetails[] getDemandDetails() {
        return this.DemandDetails;
    }

    public void setDemandDetails(DemandDetails[] DemandDetails) {
        this.DemandDetails = DemandDetails;
    }
}
