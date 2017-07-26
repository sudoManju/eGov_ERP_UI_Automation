package builders.propertyTax.billingServices.demandService;

import entities.requests.propertyTax.RequestInfo;
import entities.requests.propertyTax.billingServices.demandService.DemandServiceRequest;
import entities.requests.propertyTax.billingServices.demandService.Demands;

public class DemandServiceRequestBuilder {

    DemandServiceRequest request = new DemandServiceRequest();

    public DemandServiceRequestBuilder withRequestInfo(RequestInfo requestInfo){
        request.setRequestInfo(requestInfo);
        return this;
    }

    public DemandServiceRequestBuilder withDemands(Demands[] demands){
        request.setDemands(demands);
        return this;
    }

    public DemandServiceRequest build(){
        return request;
    }
}
