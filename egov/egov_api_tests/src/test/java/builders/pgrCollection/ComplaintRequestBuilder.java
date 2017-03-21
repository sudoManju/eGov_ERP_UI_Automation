package builders.pgrCollection;

import entities.requests.pgrCollections.ComplaintRequest;
import entities.requests.pgrCollections.RequestInfo;
import entities.requests.pgrCollections.ServiceRequest;

public final class ComplaintRequestBuilder {

    ComplaintRequest complaintRequest = new ComplaintRequest();
    ServiceRequest serviceRequest = new ServiceRequestBuilder().build();
    RequestInfo requestInfo = new RequestInfoBuilder().build();

    public ComplaintRequestBuilder() {
        complaintRequest.setServiceRequest(serviceRequest);
        complaintRequest.setRequestInfo(requestInfo);
    }

    public ComplaintRequestBuilder withRequestInfo(RequestInfo RequestInfo) {
        complaintRequest.RequestInfo = RequestInfo;
        return this;
    }

    public ComplaintRequestBuilder withServiceRequest(ServiceRequest ServiceRequest) {
        complaintRequest.ServiceRequest = ServiceRequest;
        return this;
    }

    public ComplaintRequest build() {
        return complaintRequest;
    }
}
