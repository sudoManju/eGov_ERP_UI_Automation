package entities.responses.propertyTax.billingServices;

import org.codehaus.jackson.annotate.JsonProperty;

public class TaxHeadMasterResponse {

    @JsonProperty("ResponseInfo")
    private ResponseInfo ResponseInfo;

    @JsonProperty("TaxHeadMasters")
    private TaxHeadMasters[] TaxHeadMasters;

    public ResponseInfo getResponseInfo() {
        return this.ResponseInfo;
    }

    public void setResponseInfo(ResponseInfo ResponseInfo) {
        this.ResponseInfo = ResponseInfo;
    }

    public TaxHeadMasters[] getTaxHeadMasters() {
        return this.TaxHeadMasters;
    }

    public void setTaxHeadMasters(TaxHeadMasters[] TaxHeadMasters) {
        this.TaxHeadMasters = TaxHeadMasters;
    }
}
