package builders.propertyTax.billingServices.businessServiceMaster;

import entities.requests.propertyTax.billingServices.RequestInfo;
import entities.requests.propertyTax.billingServices.businessServiceMaster.BusinessServiceDetails;
import entities.requests.propertyTax.billingServices.businessServiceMaster.BusinessServiceMasterRequest;

public class BusinessServiceMasterRequestBuilder {

    BusinessServiceMasterRequest request = new BusinessServiceMasterRequest();

    public BusinessServiceMasterRequestBuilder withRequestInfo(RequestInfo requestInfo){
        request.setRequestInfo(requestInfo);
        return this;
    }

    public BusinessServiceMasterRequestBuilder withBusinessServiceDetails(BusinessServiceDetails[] businessServiceDetails){
        request.setBusinessServiceDetails(businessServiceDetails);
        return this;
    }

    public BusinessServiceMasterRequest build(){
        return request;
    }
}
