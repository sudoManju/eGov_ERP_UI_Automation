package entities.responses.propertyTax.billingServices.taHeadMaster;

import entities.responses.propertyTax.billingServices.ResponseInfo;
import org.codehaus.jackson.annotate.JsonProperty;

public class TaxHeadMasterResponse {

    @JsonProperty("ResponseInfo")
    private entities.responses.propertyTax.billingServices.ResponseInfo ResponseInfo;

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
