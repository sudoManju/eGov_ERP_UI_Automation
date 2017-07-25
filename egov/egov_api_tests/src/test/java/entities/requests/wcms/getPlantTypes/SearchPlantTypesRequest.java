package entities.requests.wcms.getPlantTypes;

import entities.requests.wcms.RequestInfo;

public class SearchPlantTypesRequest {
    private RequestInfo requestInfo;

    public RequestInfo getRequestInfo() {
        return this.requestInfo;
    }

    public void setRequestInfo(RequestInfo requestInfo) {
        this.requestInfo = requestInfo;
    }
}
