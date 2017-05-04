package entities.requests.userDetails;

import org.codehaus.jackson.annotate.JsonProperty;

public class CreateUserRequest {
    @JsonProperty("User")
    private User User;

    @JsonProperty("RequestInfo1")
    private RequestInfo RequestInfo;

    public User getUser() {
        return User;
    }

    public void setUser(User User) {
        this.User = User;
    }

    public RequestInfo getRequestInfo() {
        return RequestInfo;
    }

    public void setRequestInfo(RequestInfo RequestInfo) {
        this.RequestInfo = RequestInfo;
    }

}
