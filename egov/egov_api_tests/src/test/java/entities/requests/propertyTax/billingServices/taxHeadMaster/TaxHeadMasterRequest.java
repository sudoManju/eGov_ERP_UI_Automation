package entities.requests.propertyTax.billingServices.taxHeadMaster;

import entities.requests.propertyTax.RequestInfo;
import org.codehaus.jackson.annotate.JsonProperty;

public class TaxHeadMasterRequest {

    @JsonProperty("requestInfo")
    private RequestInfo requestInfo;

    private TaxHeadMasters[] taxHeadMasters;

    public RequestInfo getRequestInfo() {
        return this.requestInfo;
    }

    public void setRequestInfo(RequestInfo RequestInfo) {
        this.requestInfo = RequestInfo;
    }

    public TaxHeadMasters[] getTaxHeadMasters() {
        return this.taxHeadMasters;
    }

    public void setTaxHeadMasters(TaxHeadMasters[] taxHeadMasters) {
        this.taxHeadMasters = taxHeadMasters;
    }
}
