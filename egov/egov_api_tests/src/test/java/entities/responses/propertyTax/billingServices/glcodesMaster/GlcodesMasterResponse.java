package entities.responses.propertyTax.billingServices.glcodesMaster;

import org.codehaus.jackson.annotate.JsonProperty;

public class GlcodesMasterResponse {

    @JsonProperty("ResponseInfo")
    private ResponseInfo ResponseInfo;

    @JsonProperty("GlCodeMasters")
    private GlCodeMasters[] GlCodeMasters;

    public entities.responses.propertyTax.billingServices.glcodesMaster.ResponseInfo getResponseInfo() {
        return this.ResponseInfo;
    }

    public void setResponseInfo(entities.responses.propertyTax.billingServices.glcodesMaster.ResponseInfo ResponseInfo) {
        this.ResponseInfo = ResponseInfo;
    }

    public entities.responses.propertyTax.billingServices.glcodesMaster.GlCodeMasters[] getGlCodeMasters() {
        return this.GlCodeMasters;
    }

    public void setGlCodeMasters(entities.responses.propertyTax.billingServices.glcodesMaster.GlCodeMasters[] GlCodeMasters) {
        this.GlCodeMasters = GlCodeMasters;
    }
}
