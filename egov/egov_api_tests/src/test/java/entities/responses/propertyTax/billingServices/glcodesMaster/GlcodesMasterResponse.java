package entities.responses.propertyTax.billingServices.glcodesMaster;

import entities.responses.propertyTax.billingServices.ResponseInfo;
import org.codehaus.jackson.annotate.JsonProperty;

public class GlcodesMasterResponse {

    @JsonProperty("ResponseInfo")
    private ResponseInfo responseInfo;

    @JsonProperty("GlCodeMasters")
    private GlCodeMasters[] GlCodeMasters;

    public ResponseInfo getResponseInfo() {
        return this.responseInfo;
    }

    public void setResponseInfo(ResponseInfo ResponseInfo) {
        this.responseInfo = ResponseInfo;
    }

    public entities.responses.propertyTax.billingServices.glcodesMaster.GlCodeMasters[] getGlCodeMasters() {
        return this.GlCodeMasters;
    }

    public void setGlCodeMasters(entities.responses.propertyTax.billingServices.glcodesMaster.GlCodeMasters[] GlCodeMasters) {
        this.GlCodeMasters = GlCodeMasters;
    }
}
