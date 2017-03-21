package entities.requests.pgrCollections;

public class ComplaintRequest {
    public RequestInfo RequestInfo;

    public ServiceRequest ServiceRequest;

    public RequestInfo getRequestInfo() {
        return RequestInfo;
    }

    public void setRequestInfo(RequestInfo RequestInfo) {
        this.RequestInfo = RequestInfo;
    }

    public ServiceRequest getServiceRequest() {
        return ServiceRequest;
    }

    public void setServiceRequest(ServiceRequest ServiceRequest) {
        this.ServiceRequest = ServiceRequest;
    }

}

