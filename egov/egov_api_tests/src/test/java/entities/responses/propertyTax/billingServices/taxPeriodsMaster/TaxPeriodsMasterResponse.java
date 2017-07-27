package entities.responses.propertyTax.billingServices.taxPeriodsMaster;

import entities.responses.propertyTax.billingServices.ResponseInfo;
import org.codehaus.jackson.annotate.JsonProperty;

public class TaxPeriodsMasterResponse {

    @JsonProperty("responseInfo")
    private ResponseInfo responseInfo;

    @JsonProperty("TaxPeriods")
    private TaxPeriods[] TaxPeriods;

    public ResponseInfo getResponseInfo() {
        return this.responseInfo;
    }

    public void setResponseInfo(ResponseInfo ResponseInfo) {
        this.responseInfo = ResponseInfo;
    }

    public TaxPeriods[] getTaxPeriods() {
        return this.TaxPeriods;
    }

    public void setTaxPeriods(TaxPeriods[] TaxPeriods) {
        this.TaxPeriods = TaxPeriods;
    }
}
