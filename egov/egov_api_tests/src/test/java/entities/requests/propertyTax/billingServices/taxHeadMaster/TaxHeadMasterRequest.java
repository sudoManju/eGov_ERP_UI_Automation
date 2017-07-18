package entities.requests.propertyTax.billingServices.taxHeadMaster;

import entities.requests.propertyTax.billingServices.RequestInfo;
import org.codehaus.jackson.annotate.JsonProperty;

public class TaxHeadMasterRequest {

    @JsonProperty("RequestInfo")
    private entities.requests.propertyTax.billingServices.RequestInfo RequestInfo;

    private TaxHeadMasters[] taxHeadMasters;

    public RequestInfo getRequestInfo() {
        return this.RequestInfo;
    }

    public void setRequestInfo(RequestInfo RequestInfo) {
        this.RequestInfo = RequestInfo;
    }

    public TaxHeadMasters[] getTaxHeadMasters() {
        return this.taxHeadMasters;
    }

    public void setTaxHeadMasters(TaxHeadMasters[] taxHeadMasters) {
        this.taxHeadMasters = taxHeadMasters;
    }
}
