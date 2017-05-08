package entities.responses.pgrCollections.UpdateComplaint;

public class UpdateComplaintResponse {
    private ServiceRequests[] serviceRequests;
    private ResponseInfo responseInfo;

    public ServiceRequests[] getServiceRequests() {
        return this.serviceRequests;
    }

    public void setServiceRequests(ServiceRequests[] serviceRequests) {
        this.serviceRequests = serviceRequests;
    }

    public ResponseInfo getResponseInfo() {
        return this.responseInfo;
    }

    public void setResponseInfo(ResponseInfo responseInfo) {
        this.responseInfo = responseInfo;
    }
}
