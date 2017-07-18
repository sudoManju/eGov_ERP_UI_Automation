package builders.propertyTax.billingServices.glcodesMaster;

import entities.requests.propertyTax.billingServices.glcodesMaster.GlcodeMasters;
import entities.requests.propertyTax.billingServices.glcodesMaster.GlcodesMasterRequest;
import entities.requests.propertyTax.billingServices.RequestInfo;

public class GlcodesMasterRequestBuilder {

    GlcodesMasterRequest request = new GlcodesMasterRequest();

    public GlcodesMasterRequestBuilder withRequestInfo(RequestInfo requestInfo){
        request.setRequestInfo(requestInfo);
        return this;
    }

    public GlcodesMasterRequestBuilder withGlcodes(GlcodeMasters[] glcodes){
        request.setGlcodeMasters(glcodes);
        return this;
    }

    public GlcodesMasterRequest build(){
        return request;
    }
}
