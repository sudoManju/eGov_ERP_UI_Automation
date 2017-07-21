package builders.propertyTax.billingServices.taxPeriodMaster;

import entities.requests.propertyTax.billingServices.RequestInfo;
import entities.requests.propertyTax.billingServices.taxPeriodMaster.TaxPeriodsMasterRequest;
import entities.requests.propertyTax.billingServices.taxPeriodMaster.TaxPeriods;

public class TaxPeriodsMasterRequestBuilder {

    TaxPeriodsMasterRequest request = new TaxPeriodsMasterRequest();

    public TaxPeriodsMasterRequestBuilder withRequestInfo(RequestInfo requestInfo){
        request.setRequestInfo(requestInfo);
        return this;
    }

    public TaxPeriodsMasterRequestBuilder withTaxPeriods(TaxPeriods[] taxPeriods){
        request.setTaxPeriods(taxPeriods);
        return this;
    }

    public TaxPeriodsMasterRequest build(){
        return request;
    }
}
