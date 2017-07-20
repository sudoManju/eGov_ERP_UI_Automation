package builders.propertyTax.billingServices.glcodesMaster;

import entities.requests.propertyTax.billingServices.RequestInfo;
import entities.requests.propertyTax.billingServices.glcodesMaster.GlcodeMasterSearchRequest;

public class GlcodeMasterSearchRequestBuilder {

    GlcodeMasterSearchRequest request = new GlcodeMasterSearchRequest();

    public GlcodeMasterSearchRequestBuilder withRequestInfo(RequestInfo requestInfo){
        request.setRequestInfo(requestInfo);
        return this;
    }

    public GlcodeMasterSearchRequest build(){
        return request;
    }
}
