package entities.responses.propertyTax.billingServices.businessService;

import entities.responses.propertyTax.billingServices.ResponseInfo;
import org.codehaus.jackson.annotate.JsonProperty;

public class BusinessServiceMasterResponse {

    @JsonProperty("ResponseInfo")
    private ResponseInfo responseInfo;

    @JsonProperty("BusinessServiceDetails")
    private BusinessServiceDetails[] BusinessServiceDetails;

    public ResponseInfo getResponseInfo() {
        return this.responseInfo;
    }

    public void setResponseInfo(ResponseInfo ResponseInfo) {
        this.responseInfo = ResponseInfo;
    }

    public entities.responses.propertyTax.billingServices.businessService.BusinessServiceDetails[] getBusinessServiceDetails() {
        return this.BusinessServiceDetails;
    }

    public void setBusinessServiceDetails(entities.responses.propertyTax.billingServices.businessService.BusinessServiceDetails[] BusinessServiceDetails) {
        this.BusinessServiceDetails = BusinessServiceDetails;
    }
}
