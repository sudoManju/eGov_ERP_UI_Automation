package entities.requests.propertyTax.billingServices.businessServiceMaster;

import entities.requests.propertyTax.billingServices.RequestInfo;
import org.codehaus.jackson.annotate.JsonProperty;

public class BusinessServiceMasterRequest {

    @JsonProperty("RequestInfo")
    private RequestInfo requestInfo;
    private BusinessServiceDetails[] businessServiceDetails;

    public RequestInfo getRequestInfo() {
        return requestInfo;
    }

    public void setRequestInfo(RequestInfo requestInfo) {
        this.requestInfo = requestInfo;
    }

    public BusinessServiceDetails[] getBusinessServiceDetails() {
        return businessServiceDetails;
    }

    public void setBusinessServiceDetails(BusinessServiceDetails[] businessServiceDetails) {
        this.businessServiceDetails = businessServiceDetails;
    }
}
