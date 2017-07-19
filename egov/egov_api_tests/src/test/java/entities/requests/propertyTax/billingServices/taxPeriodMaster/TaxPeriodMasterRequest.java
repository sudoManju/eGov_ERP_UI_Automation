package entities.requests.propertyTax.billingServices.taxPeriodMaster;

import entities.requests.propertyTax.billingServices.RequestInfo;
import org.codehaus.jackson.annotate.JsonProperty;

public class TaxPeriodMasterRequest {

    @JsonProperty("RequestInfo")
    private RequestInfo requestInfo;

    private TaxPeriods[] taxPeriods;

    public RequestInfo getRequestInfo() {
        return requestInfo;
    }

    public void setRequestInfo(RequestInfo requestInfo) {
        this.requestInfo = requestInfo;
    }

    public TaxPeriods[] getTaxPeriods() {
        return taxPeriods;
    }

    public void setTaxPeriods(TaxPeriods[] taxPeriods) {
        this.taxPeriods = taxPeriods;
    }
}
