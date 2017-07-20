package builders.propertyTax.billingServices.glcodesMaster;

import entities.requests.propertyTax.billingServices.glcodesMaster.GlcodeMasters;
import entities.requests.propertyTax.billingServices.glcodesMaster.GlcodeMasterRequest;
import entities.requests.propertyTax.billingServices.RequestInfo;

public class GlcodesMasterRequestBuilder {

    GlcodeMasterRequest request = new GlcodeMasterRequest();

    public GlcodesMasterRequestBuilder withRequestInfo(RequestInfo requestInfo){
        request.setRequestInfo(requestInfo);
        return this;
    }

    public GlcodesMasterRequestBuilder withGlcodes(GlcodeMasters[] glcodes){
        request.setGlcodeMasters(glcodes);
        return this;
    }

    public GlcodeMasterRequest build(){
        return request;
    }
}
