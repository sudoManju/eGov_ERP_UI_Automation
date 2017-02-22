package entities;


import entities.ServiceRequest;
import entities.RequestInfo;

public class ServiceRequestForBrokenBinRequest {
    private RequestInfo requestInfo;

    public ServiceRequest getServiceRequest() {
        return serviceRequest;
    }

    public void setServiceRequest(ServiceRequest serviceRequest) {
        this.serviceRequest = serviceRequest;
    }

    public RequestInfo getRequestInfo() {
        return requestInfo;
    }

    public void setRequestInfo(RequestInfo requestInfo) {
        this.requestInfo = requestInfo;
    }

    private ServiceRequest serviceRequest;
}
