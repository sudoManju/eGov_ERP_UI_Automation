package entities.requests.userDetails;

import org.codehaus.jackson.annotate.JsonProperty;

public class UserDetailsForSearchRequest {

    private int[] id;

    @JsonProperty("RequestInfo")
    private RequestInfo requestInfo;

    public RequestInfo getRequestInfo() {
        return requestInfo;
    }

    public void setRequestInfo(RequestInfo requestInfo) {
        this.requestInfo = requestInfo;
    }

    public int[] getId() {
        return id;
    }

    public void setId(int[] id) {
        this.id = id;
    }
}
