package entities.requests.wcms.getReservoirsTypes;

import entities.requests.wcms.RequestInfo;

public class SearchReservoirTypesRequest {
    private RequestInfo requestInfo;

    public RequestInfo getRequestInfo() {
        return this.requestInfo;
    }

    public void setRequestInfo(RequestInfo requestInfo) {
        this.requestInfo = requestInfo;
    }
}
