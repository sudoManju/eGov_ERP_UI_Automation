package builders.propertyTax.billingServices.taxHeadMaster;

import entities.requests.propertyTax.billingServices.RequestInfo;
import entities.requests.propertyTax.billingServices.taxHeadMaster.TaxHeadMasterSearchRequest;

public class TaxHeadMasterSearchRequestBuilder {

    TaxHeadMasterSearchRequest request = new TaxHeadMasterSearchRequest();

    public TaxHeadMasterSearchRequestBuilder withRequestInfo(RequestInfo requestInfo){
        request.setRequestInfo(requestInfo);
        return this;
    }

    public TaxHeadMasterSearchRequest build(){
        return request;
    }
}
