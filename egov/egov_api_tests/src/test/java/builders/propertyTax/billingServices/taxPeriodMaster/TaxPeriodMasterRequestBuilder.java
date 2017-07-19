package builders.propertyTax.billingServices.taxPeriodMaster;

import entities.requests.propertyTax.billingServices.RequestInfo;
import entities.requests.propertyTax.billingServices.taxPeriodMaster.TaxPeriodMasterRequest;
import entities.requests.propertyTax.billingServices.taxPeriodMaster.TaxPeriods;

public class TaxPeriodMasterRequestBuilder {

    TaxPeriodMasterRequest request = new TaxPeriodMasterRequest();

    public TaxPeriodMasterRequestBuilder withRequestInfo(RequestInfo requestInfo){
        request.setRequestInfo(requestInfo);
        return this;
    }

    public TaxPeriodMasterRequestBuilder withTaxPeriods(TaxPeriods[] taxPeriods){
        request.setTaxPeriods(taxPeriods);
        return this;
    }

    public TaxPeriodMasterRequest build(){
        return request;
    }
}
