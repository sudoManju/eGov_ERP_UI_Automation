package entities.responses.propertyTax.billingServices.taxPeriodsMaster;

import org.codehaus.jackson.annotate.JsonProperty;

public class TaxPeriodsMasterResponse {

    @JsonProperty("ResponseInfo")
    private ResponseInfo ResponseInfo;

    @JsonProperty("TaxPeriods")
    private TaxPeriods[] TaxPeriods;

    public ResponseInfo getResponseInfo() {
        return this.ResponseInfo;
    }

    public void setResponseInfo(ResponseInfo ResponseInfo) {
        this.ResponseInfo = ResponseInfo;
    }

    public TaxPeriods[] getTaxPeriods() {
        return this.TaxPeriods;
    }

    public void setTaxPeriods(TaxPeriods[] TaxPeriods) {
        this.TaxPeriods = TaxPeriods;
    }
}
