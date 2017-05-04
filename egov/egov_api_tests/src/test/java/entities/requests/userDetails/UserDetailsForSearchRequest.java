package entities.requests.userDetails;

import org.codehaus.jackson.annotate.JsonProperty;

public class UserDetailsForSearchRequest {

    private int[] id;
    private String userName;

    @JsonProperty("RequestInfo1")
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
