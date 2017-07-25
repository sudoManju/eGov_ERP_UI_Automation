package builders.propertyTax.billingServices.taxHeadMaster;

import entities.requests.propertyTax.RequestInfo;
import entities.requests.propertyTax.billingServices.taxHeadMaster.TaxHeadMasterRequest;
import entities.requests.propertyTax.billingServices.taxHeadMaster.TaxHeadMasters;

public class TaxHeadMasterRequestBuilder {

    TaxHeadMasterRequest request = new TaxHeadMasterRequest();

    public TaxHeadMasterRequestBuilder withRequestInfo(RequestInfo requestInfo){
        request.setRequestInfo(requestInfo);
        return this;
    }

    public TaxHeadMasterRequestBuilder withTaxHeadMaster(TaxHeadMasters[] taxHeadMasters){
        request.setTaxHeadMasters(taxHeadMasters);
        return this;
    }

    public TaxHeadMasterRequest build(){
        return request;
    }
}
